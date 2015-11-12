/* 
 * Copyright (C) 2015 Open Source Parking Inc.(www.osparking.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.osparking.osparking.device.LEDnotice;

import com.osparking.osparking.device.*;
import com.osparking.osparking.ControlGUI;
import com.osparking.global.names.DeviceManager;
import java.net.Socket;
import java.util.logging.Level;
import static com.osparking.global.Globals.*;
import static com.osparking.global.names.OSP_enums.DeviceType.*;
import com.osparking.global.names.ParkingTimer;
import static com.osparking.global.names.DB_Access.gateCount;
import com.osparking.global.names.OSP_enums.EBD_Row;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.DisplayArea;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.DisplayArea.BOTTOM_ROW;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_TEXT_ONE;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.GET_ID;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SAVE_TEXT;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SET_MONITOR;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.RoomType.GENERAL_TEXT;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.LED_COLUMNS;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.SUCCESS;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

/**
 * Manages a gate bar via a socket communication while current socket connection is valid.
 * To do that, gateManager uses a socket reader(SockReader) Thread instance and a Runnable class
 * (AreYouThereSender) instance which is created by the SockReader object.
 * 
 * @author Open Source Parking Inc.
 */
public class LEDnoticeManager extends Thread implements DeviceManager {
    //<editor-fold desc="--class variables">
    private byte deviceNo = 0; // ID of the gate bar being served by this manager. A valid ID starts from 1.
    private ControlGUI mainForm; // main form of the gate bar simulator.
    
    /**
     * socket for the communication with the gate bar.
     */
    private Socket socket = null; // socket that connects with a e-board
    
    /**
     * a timer employed to send Open commands to the designated gate bar for sure.
     */
    ParkingTimer timerSendOpenCmd = null;
    
    byte [] cmdIDarr = new byte[4]; // open command ID
    byte [] fiveByteArr =new byte[5]; // storage for (code + ID)
    
    boolean justBooted = true;
    private boolean neverConnected = true;
    public LedProtocol ledNoticeProtocol = new LedProtocol(); 
    
    static final int TOPTEXT_ROOM = 0; // 1
    static final int BOTTEXT_ROOM = 1; // 2
    static final int TOP_BOT_TEXT_ROOM = 2;    
    
    //</editor-fold>    
    
    /**
     * 
     * @param mainForm main GUI form of the whole manager program
     * @param deviceNo ID of the E-Board to manage
     */
    public LEDnoticeManager(final ControlGUI mainForm, final byte deviceNo)
    {
        super("osp_EBD_" + deviceNo + "_Manager");
        this.mainForm = mainForm; 
        this.deviceNo = deviceNo;
        
        (new Thread("LEDnoticeWorkerThread")
        {
            public void run() {
                while (true) {
                    try {
                        synchronized (msgQdoor) {
                            msgQdoor.wait();
                        }
                    } catch (InterruptedException ex) {
                        logParkingException(Level.SEVERE, ex, "waiting message queue not empty", deviceNo);
                    }
                    while (!ledNoticeMessages.isEmpty()) {
                        MsgItem currItem = ledNoticeMessages.peek();
                        
                        if (isConnected(socket)) {
                            try {
                                socket.getOutputStream().write(currItem.getMessage());
                                ledNoticeMessages.peek().incSendCount();
                                System.out.println("written: " + currItem.getHexStr());
                            } catch (IOException ex) {
                                logParkingException(Level.SEVERE, ex, "writing message to LEDnotice", deviceNo);
                            }
                        } else {
                            try {
                                synchronized (mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo]) {
                                    mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                                }
                            } catch (InterruptedException ex) {
                                logParkingException(Level.SEVERE, ex, "waiting LEDnotice socket connection", deviceNo);
                            }
                        }
                        //<editor-fold desc="-- Delay some milliseconds by message type">
                        try {
                            switch (currItem.getType()) {
                                case SET_MONITOR: 
                                    Thread.sleep(RESEND_PERIOD * 5);
                                    break;
                                    
                                case DEL_TEXT_ONE:
                                case DEL_GROUP:
                                case DEL_TEXT_ALL:
                                    Thread.sleep(RESEND_PERIOD * 30);
                                    break;
                                
                                case SAVE_TEXT:
                                    Thread.sleep(RESEND_PERIOD * 15);
                                    break;
                                    
                                default:
                                    Thread.sleep(RESEND_PERIOD);
                                    break;
                            }
                            //</editor-fold>
                        } catch (InterruptedException ex) {
                            logParkingException(Level.SEVERE, ex, "taking a rest between two sending", deviceNo);
                        }
                    }
                }
            }
        }).start();
    }
    
    public void run()
    {   
        byte[] preMsg = new byte[3];
        byte typeInt;
        byte posiETX;
        
        byte[] MsgPost = new byte[100];
                    
        while (true) // infinite communication with an e-board
        {
            if (mainForm.isSHUT_DOWN()) {
                return;
            }
            
            int msgLength = -2;
            // read device message as long as connection is good
            
            //<editor-fold desc="-- Repeat processing one message from LEDnotice">
            try {
                synchronized(mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo])  
                {
                    //<editor-fold desc="-- Wait connection, send default settings, read message code">
                    if (! isConnected(getSocket())) 
                    {
                        mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                        neverConnected = false;
                    }
                    //</editor-fold>
                } 
                if (justBooted) {
                    //<editor-fold desc="-- Handle first connection processing">
                    justBooted = false;

                    // 전광판 모니터 크기 설정을 지시한다.
                    ledNoticeMessages.add(new MsgItem(SET_MONITOR, 
                            ledNoticeProtocol.getScreenSetString(1, LED_COLUMNS, 2)));
                    
                    // 다음, 전광판의 기본 표시 문구를 전송한다.
                    // 상단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
                    ledNoticeMessages.add(new MsgItem(DEL_TEXT_ONE, 
                            ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
                    // 상단 행, 실제 표시할 문구를 전송한다.
                    ledNoticeMessages.add(new MsgItem(SAVE_TEXT, getLEDnoticeDefaultMsg(EBD_Row.TOP)));
                    
                    // 하단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
                    ledNoticeMessages.add(new MsgItem(DEL_TEXT_ONE, 
                            ledNoticeProtocol.delData(GENERAL_TEXT, BOTTEXT_ROOM)));
                    // 하단 행, 실제 표시할 문구를 전송한다.
                    ledNoticeMessages.add(new MsgItem(SAVE_TEXT, getLEDnoticeDefaultMsg(EBD_Row.BOTTOM)));
                    //</editor-fold>
                } 
                //<editor-fold desc="-- Read arriving message from LEDnotice">
                // SocketTimeoutException will arise when no data on the socket during 1 second
                msgLength = socket.getInputStream().read(preMsg); // waits for PULSE_PERIOD miliseconds 
                typeInt = preMsg[2];  
                int typeUint = LedProtocol.byteToUint(typeInt);
                
                int mIdx = 0;
                while ( true )  
                {
                    posiETX = (byte)(socket.getInputStream().read());
                    if (posiETX == 3) {
                        MsgPost[mIdx++] = posiETX;
                        break;
                    } else {
                        if (posiETX == 0x10) {
                            byte aByte = (byte)socket.getInputStream().read();
                            posiETX = (byte)(aByte - 0x20);
                        }
                        MsgPost[mIdx++] = posiETX;
                    }
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="-- Handle message arrived">
                if (msgLength == -1) {
                    // 'End of stream' means other party closed socket. So, I need to close it from my side.
                    finishConnection(null,  "End of stream reached, gate #" + deviceNo, deviceNo);
                } else {
                    if (validMessage(preMsg, MsgPost[mIdx - 1])) 
                    {
                        String data_Test = "";
                        int i = 0;
                        
                        for ( ; i < preMsg.length; i++) { data_Test += String.format("%02X", preMsg[i]); }
                        for (i = 0; i < mIdx; i++) { data_Test += String.format("%02X", MsgPost[i]); }
                        System.out.println("msg came: " + data_Test);
                        
                        if (data_Test.equals("02313103")) {
                            System.out.println("");
                        }
                        
                        if (typeUint == GET_ID.getValue()) {
                            // process LEDnotice device heartbeat
                            mainForm.tolerance[E_Board.ordinal()][deviceNo].assignMAX();
                        } else {
                            if (typeUint == SET_MONITOR.getValue()) {
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (ledNoticeMessages.peek().getType() == SET_MONITOR) {
                                    MsgItem item = ledNoticeMessages.remove();
                                    System.out.println("Monitor size set after " + item.getSendCount() + " trials.");
                                }
                            } else if (typeUint == DEL_TEXT_ONE.getValue()) { 
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (ledNoticeMessages.peek().getType() == DEL_TEXT_ONE) {
                                    MsgItem item = ledNoticeMessages.remove();
                                    System.out.println("LED row cleared after " + item.getSendCount() + " trials.");
                                }
                            } else if (typeUint == SAVE_TEXT.getValue()) {
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (ledNoticeMessages.peek().getType() == SAVE_TEXT) {
                                    MsgItem item = ledNoticeMessages.remove();
                                    System.out.println("LED text written after " + item.getSendCount() + " trials.");
                                }
                            }
                        }
                    }
                }
                //</editor-fold>                    
            } catch (SocketTimeoutException e) {
                // exit this try statement and go to the following statement
            } catch (InterruptedException ex) {
                if (!mainForm.isSHUT_DOWN()) {
                    logParkingException(Level.INFO, ex, "E-Board manager #" + deviceNo + " waits socket conn'");
                    finishConnection(ex,  "E-Board manager #" + deviceNo + " waits socket conn'", deviceNo);
                }
            } catch (IOException e) {
                if (!mainForm.isSHUT_DOWN()) {
                    logParkingExceptionStatus(Level.SEVERE, e, "IOEx- closed socket, E-board #" + deviceNo,
                            mainForm.getStatusTextField(), deviceNo);
                    finishConnection(e, "server closed socket for ", deviceNo);
                }
            } catch (Exception e2) {
                logParkingExceptionStatus(Level.SEVERE, e2, 
                        e2.getMessage() + "server- closed socket forE-Board #" + deviceNo,
                        mainForm.getStatusTextField(), deviceNo);
                finishConnection(e2, "E-Board manager Excp", deviceNo);
            }
            //</editor-fold>
            if (mainForm.tolerance[E_Board.ordinal()][deviceNo].getLevel() <= 0) {
                finishConnection(null, "LED: tolerance depleted for", deviceNo);
            }
        }
    }

    /**
     * stops serving a gate bar.
     */
    @Override
    public void stopOperation(String reason) {
        finishConnection(null, reason, deviceNo);
        interrupt();
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * closes socket connection to a gate bar.
     * 
     * before closing the socket, it cancels any existing relevant tasks.
     */
    @Override
    public void finishConnection(Exception e, String description, byte gateNo) 
    {
        synchronized(mainForm.getSocketMutex()[E_Board.ordinal()][gateNo]) 
        {
            if (0 < gateNo && gateNo <= gateCount) 
            {
                if (isConnected(socket)) 
                {   
                    String msg =  "E-Board #" + gateNo;

                    addMessageLine(mainForm.getMessageTextArea(), "  ------" +  msg + " disconnected");
                    logParkingException(Level.INFO, e, description + " " + msg);

                    mainForm.getSockConnStat()[E_Board.ordinal()][gateNo].
                            recordSocketDisconnection(System.currentTimeMillis());
                    closeSocket(getSocket(), "while gate bar socket closing");
                    socket = null;
                }
            } else {
                System.out.println("this never ever gateNo");
            }
        }
            
        if (mainForm.getConnectDeviceTimer()[E_Board.ordinal()][gateNo] == null) {
            System.out.println("this never ever happens");
        } else {
            mainForm.getConnectDeviceTimer()[E_Board.ordinal()][gateNo].reRunOnce();
            addMessageLine(mainForm.getMessageTextArea(), "Trying to connect to E-Board #" + gateNo);
        }
    }

    @Override
    public boolean isNeverConnected() {
        return neverConnected;
    }

    private boolean validMessage(byte[] preMsg, byte possibleETX) {
//        if (preMsg[1] == SUCCESS) {
//            System.out.println("success");
//        } else {
//            System.out.println("fail");
//        }
        if (preMsg[0] == LedProtocol.intSTX 
                && possibleETX == LedProtocol.intETX
                && preMsg[1] == SUCCESS) 
        {
            return true;
        } else {
            return false;
        }
    }

    public String getLEDnoticeDefaultMsg(EBD_Row row) {
        int setFont = 1; // getLEDrowFont();

        int startSpeed = 15; // 1 ~ 31
        int stopTime = 1; // 1 ~ 10
        int endSpeed = 15;
        int repeatCnt = 1;
        String displayText = null;
        
        if (row == EBD_Row.TOP) {
            displayText =                     
                    ledNoticeProtocol.textType(TOPTEXT_ROOM, // memory room number (range: 0~31)
                    (row == EBD_Row.TOP ? DisplayArea.TOP_ROW : DisplayArea.BOTTOM_ROW), 
                    EffectType.VERTICAL_ADD, startSpeed, stopTime, 
                    EffectType.NONE, endSpeed, repeatCnt, setFont, 
                    "오픈소스파킹");
        } else {
            startSpeed = 15; // 1 ~ 31
            stopTime = 5; // 1 ~ 10
            endSpeed = 15;
            repeatCnt = 2;

            displayText = ledNoticeProtocol.textType(1, // memory room number (range: 0~31)
                    BOTTOM_ROW, 
                    LEDnotice_enums.EffectType.FLOW_RtoL, startSpeed, stopTime, 
                    LEDnotice_enums.EffectType.FLOW_DOWN, endSpeed, repeatCnt, setFont,
                    "OzParking");
        }
        return displayText;
    }

    class MsgItem {
        private MsgType type;
        private String hexStr;
        private byte[] message;
        private int sendCount = 0;
        
        MsgItem(MsgType type, String hexStr) {
            this.type = type;
            this.hexStr = hexStr;
            message = ledNoticeProtocol.hexToByteArray(hexStr);
        }

        /**
         * @return the message
         */
        public byte[] getMessage() {
            return message;
        }

        /**
         * @return the type
         */
        public MsgType getType() {
            return type;
        }

        /**
         * @return the hexStr
         */
        public String getHexStr() {
            return hexStr;
        }
        
        public void incSendCount() {
            sendCount++;
        }

        /**
         * @return the sendCount
         */
        public int getSendCount() {
            return sendCount;
        }
    }
    
    class MessageQueue extends LinkedList<MsgItem> {
        @Override
        public boolean add(MsgItem item) {
            super.add(item);
            System.out.println("Added: " + item.getType() + ", Hex: " + item.getHexStr());
            
            synchronized(msgQdoor) {
                msgQdoor.notify(); // wake up someone sleeping on this queue
            }
            return true;
        }
    }
    
    Object msgQdoor = new Object();
    MessageQueue ledNoticeMessages = new MessageQueue();
}
