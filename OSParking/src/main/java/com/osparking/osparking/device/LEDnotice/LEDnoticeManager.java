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

import com.osparking.osparking.ControlGUI;
import com.osparking.global.names.DeviceManager;
import java.net.Socket;
import java.util.logging.Level;
import static com.osparking.global.Globals.*;
import static com.osparking.global.names.OSP_enums.DeviceType.*;
import com.osparking.global.names.ParkingTimer;
import static com.osparking.global.names.DB_Access.gateCount;
import com.osparking.global.names.OSP_enums;
import com.osparking.global.names.OSP_enums.DisplayArea;
import static com.osparking.global.names.OSP_enums.DisplayArea.BOTTOM_ROW;
import static com.osparking.global.names.OSP_enums.DisplayArea.TOP_ROW;
import com.osparking.global.names.OSP_enums.EBD_DisplayUsage;
import com.osparking.global.names.OSP_enums.EBD_Row;
import com.osparking.osparking.Settings_System;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorBox.Green;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorBox.Red;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorFont;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType.NONE;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType.RASER;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType.STOP_MOVING;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.FontBox.Gothic;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.GROUP_TYPE.INTR_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.GROUP_TYPE.TEXT_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.IntOnType.Unlimited;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_TEXT_ALL;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_TEXT_ONE;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.INTR_TXT_OFF;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.INTR_TXT_ON;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SAVE_INTR;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SAVE_TEXT;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SET_MONITOR;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.RoomType.GENERAL_TEXT;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.delGroup;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.delTextOne;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.getID;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.intrTxtOff;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.intrTxtOn;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.saveIntr;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.saveText;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.setMonitor;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.LED_COLUMN_CNT;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.MAX_SPEED;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.MIN_PAUSE;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.STOP_TIME_MIN;
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
    static public LedProtocol ledNoticeProtocol = new LedProtocol(); 
    
    static final int TOPTEXT_ROOM = 0; // 1
    static final int BOTTEXT_ROOM = 1; // 2
    static final int TOP_BOT_TEXT_ROOM = 2;    
    
    static StringBuffer ledNoticeBlankString = new StringBuffer(); 
    static {
        for (int i = 0; i < LED_COLUMN_CNT; i++ )
            ledNoticeBlankString.append("  ");        
    }
    
    public static LEDnoticeSettings[] ledNoticeSettings; // = new LEDnoticeSettings[gateCount];
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
        
        (new Thread("osp_LEDnoticeWorkerThread")
        {
            public void run() {
                while (true) {
                    try {
                        synchronized (msgQdoor) {
                            System.out.println("before worker wait");
                            msgQdoor.wait();
                            System.out.println("after worker wait");
                        }
                    } catch (InterruptedException ex) {
                        logParkingException(Level.SEVERE, ex, "waiting message queue not empty", deviceNo);
                    }
                    while (!ledNoticeMessages.isEmpty()) {
                        MsgItem currItem = getLedNoticeMessages().peek();
                        
                        if (isConnected(socket)) {
                            try {
                                System.out.println(currItem.getType().toString() + "~> " + currItem.getHexStr());
                                socket.getOutputStream().write(currItem.getMessage());
                                getLedNoticeMessages().peek().incSendCount();
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
                                case SAVE_INTR:
                                    Thread.sleep(RESEND_PERIOD * 15);
                                    break;
                                    
                                default:
                                    Thread.sleep(RESEND_PERIOD * 10);
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
        
        readLEDnoticeSettings();
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
                    getLedNoticeMessages().add(new MsgItem(SET_MONITOR, 
                            ledNoticeProtocol.getScreenSetString(1, LED_COLUMN_CNT, 2)));
                    
                    showLEDnoticeDefaultMessage(EBD_Row.TOP);
                    showLEDnoticeDefaultMessage(EBD_Row.BOTTOM);

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
                        String msgCame = "";
                        int i = 0;
                        
                        for ( ; i < preMsg.length; i++) { msgCame += String.format("%02X", preMsg[i]); }
                        for (i = 0; i < mIdx; i++) { msgCame += String.format("%02X", MsgPost[i]); }
                        
                        if (typeUint != getID)
                            System.out.println("<~~: " + msgCame);
                        
                        switch (typeUint) {
                            case getID:
                                // process LEDnotice device heartbeat
                                mainForm.tolerance[E_Board.ordinal()][deviceNo].assignMAX();
                                break;
                                
                            case saveIntr:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == SAVE_INTR) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("LED interrupt written after " + item.getSendCount() + " trials.");
                                }
                                break;
                                
                            case intrTxtOn:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == INTR_TXT_ON) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("LED interrupt ON after " + item.getSendCount() + " trials.");
                                }
                                break;
                                
                            case intrTxtOff:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == INTR_TXT_OFF) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("LED interrupt OFF after " + item.getSendCount() + " trials.");
                                }
                                break;
                                
                            case delGroup:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == DEL_GROUP) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("Group memory deleted after " + item.getSendCount() + " trials.");
                                }
                                break;
                                
                            case setMonitor:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == SET_MONITOR) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("Monitor size set after " + item.getSendCount() + " trials.");
                                }
                                break;

                            case delTextOne:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == DEL_TEXT_ONE) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("LED row cleared after " + item.getSendCount() + " trials.");
                                }
                                break;

                            case saveText:
                                // 큐의 첫 항목이 같은 타입이면 그 항목을 제거한다
                                if (getLedNoticeMessages().peek().getType() == SAVE_TEXT) {
                                    MsgItem item = getLedNoticeMessages().remove();
                                    System.out.println("LED text written after " + item.getSendCount() + " trials.");
                                }
                                break;
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

    public String getLEDnoticeDefaultMsg(EBD_Row row, int pauseTime, String displayText,
            EffectType startEffect, EffectType finishEffect, ColorFont colorFont, boolean isFastVersion) 
    {
        int roomNo; // memory room number (range: 0~31)
        DisplayArea displayRow;
        
        if (row == EBD_Row.TOP) {
            roomNo = TOPTEXT_ROOM;
            displayRow = TOP_ROW;
        } else {
            roomNo = BOTTEXT_ROOM;
            displayRow = BOTTOM_ROW;
        }
        
        int startSpeed = 15; // 1 ~ 31
        int endSpeed = 15;
        final int repeatCnt = 1;
        
        if (isFastVersion) {
            startSpeed = 31;
//            endSpeed = 31;
        }
        
        return ledNoticeProtocol.textType(roomNo, displayRow, startEffect, startSpeed, pauseTime, 
                finishEffect, endSpeed, repeatCnt, colorFont.getHexStr(), displayText);
    }

    /**
     * @return the ledNoticeMessages
     */
    public MessageQueue getLedNoticeMessages() {
        return ledNoticeMessages;
    }

    public void sendCarArrival_interruptMessage(byte gateNo, String tagNumber, int delay) {
        MessageQueue mQueue = getLedNoticeMessages();
        int room = 3;
        
        mQueue.add(new MsgItem(SAVE_INTR, getMsgHexString(EBD_Row.TOP, room, tagNumber)));
        mQueue.add(new MsgItem(INTR_TXT_ON, ledNoticeProtocol.intOn(Unlimited, room, 1)));
        mainForm.getSendEBDmsgTimer()[gateNo][EBD_Row.TOP.ordinal()].reRunOnce(
                new FinishLEDnoticeIntrTask(mainForm, gateNo, EBD_Row.BOTTOM), delay);    
        System.out.println("scheduled after ms: " + delay);
    }

    private String getMsgHexString(EBD_Row row, int roomNumber, String message) {
        final int repeatCnt = 1;
            
        DisplayArea area;
        
        if (row == EBD_Row.TOP)
            area = DisplayArea.TOP_ROW;
        else 
            area = DisplayArea.BOTTOM_ROW;
        
        String text = ledNoticeProtocol.interruptText(roomNumber, area, RASER, MAX_SPEED, 
                STOP_TIME_MIN, NONE, MAX_SPEED, repeatCnt, ColorFont.RedGothic, message);
        return text;
    }    
    
    Thread demoThread = null;
    
    private ColorFont getColorFont( int colorIdx, int fontIdx) {
        if (fontIdx == Gothic.ordinal()) { 
            if (colorIdx == Red.ordinal()) { // 빨강
                return ColorFont.RedGothic;
            }else if (colorIdx == Green.ordinal()) { // 초록
                return ColorFont.GreenGothic;
            }else{
                return ColorFont.OrangeGothic;
            }
        } else { // 명조 (Ming font)
            if (colorIdx == Red.ordinal()) { 
                return ColorFont.RedMing;
            } else if (colorIdx == Green.ordinal()) {
                return ColorFont.GreenMing;
            } else {
                return ColorFont.OrangeMing;
            }
        }    
    }
    
    public void showAllEffects(int tabIdx, int colorIdx, int fontIdx) {
        final String setFont = getColorFont(colorIdx, fontIdx).getHexStr();
        
        try{
            demoThread = new Thread("ospEBD_effectDemo") {
            
                public void run() {
                    int startSpeed = 15; // 1 ~ 31
                    int stopTime = 5; // 1 ~ 10
                    int endSpeed = 15;
                    int repeatCnt = 1;

                    String displayText; // First delete all rooms for the general text.

                    // 다음, 전광판의 기본 표시 문구를 전송한다.
                    // 상단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
                    getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
                    try {
                        int totalCount = EffectType.values().length;
                        int seqNo = 1;
                        for (EffectType effect : EffectType.values()) {
                            
                            // first display demo effect sequence number 
                            displayText = 
                                    ledNoticeProtocol.textType(TOPTEXT_ROOM, TOP_ROW, STOP_MOVING, 
                                            MAX_SPEED, stopTime, EffectType.NONE, MAX_SPEED, 1, setFont, 
                                            (seqNo++) + "/" + totalCount + "-th") ;
                            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, displayText));
                            
                            
                            int startIdx = effect.getLabel().length() - LED_COLUMN_CNT * 2;
                            String displayCore = 
                                    effect.getLabel().substring(startIdx < 0 ? 0 : startIdx, effect.getLabel().length());

                            displayText = 
                                    ledNoticeProtocol.textType(TOPTEXT_ROOM + 1, TOP_ROW, effect, startSpeed, stopTime, 
                                    EffectType.NONE, endSpeed, repeatCnt, setFont, displayCore);
                            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, displayText));

                            Thread.sleep(15000);
                            
                            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                                    ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
                            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                                    ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM + 1)));
                        }
                    } catch (InterruptedException ex) {
                        getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
//                        getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
//                                ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
                        demoThread = null;
                        return;
                    }
                };
            };
            demoThread.start();
        } catch (NullPointerException e){
            logParkingException(Level.SEVERE, e, "Null, demo every effects");
        }          
    }

    public void finishShowingDemoEffect(Settings_System aThis, int index) {
        if (demoThread != null) {
            demoThread.interrupt();
        }
        showLEDnoticeDefaultMessage(EBD_Row.TOP);
        showLEDnoticeDefaultMessage(EBD_Row.BOTTOM);
    }

    public void showLEDnoticeDefaultMessage(EBD_Row row) {
        // 다음, 전광판의 기본 표시 문구를 전송한다.
        if (row == EBD_Row.TOP) {
            // 상단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                    ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
            // 상단 행, 실제 표시할 문구를 전송한다.
            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                    getLEDnoticeDefaultMsg(EBD_Row.TOP, 1, "주차장의 안드로이드", EffectType.FLOW_RtoL,
                            EffectType.NONE, ColorFont.OrangeMing, false)));
        } else {
            // 하단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                    ledNoticeProtocol.delData(GENERAL_TEXT, BOTTEXT_ROOM)));
            // 하단 행, 실제 표시할 문구를 전송한다.
            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                    getLEDnoticeDefaultMsg(EBD_Row.BOTTOM, 5, "오픈소스파킹", EffectType.FLOW_UP,
                            EffectType.FLOW_DOWN, ColorFont.GreenGothic, false)));
        }
    }

    public void showCurrentEffect(Settings_System aThis, int tabIndex, int typeIndex, String displayText, 
            int startIndex, int pauseIndex, int finishIndex, int colorIndex, int fontIndex ) {
        
        String pureContent = null;
         switch (LEDnotice_enums.LEDnoticeDefaultContentType.values()[typeIndex]) {
             case Verbatim: 
                 pureContent = displayText;
                 break;
             default:
                 pureContent = "Other content";
                 break;
         }
    
        // 다음, 전광판의 기본 표시 문구를 전송한다.
        // 상단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
        getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
         
//        getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
//                ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
        
        EBD_Row row = (tabIndex % 2 == 0 ? EBD_Row.TOP : EBD_Row.BOTTOM);
        
        // 일단 빈 문자열을 전광판에 기록한다.
//        int room = 3;
//        
//        getLedNoticeMessages().add(new MsgItem(SAVE_INTR, 
//                getMsgHexString(EBD_Row.TOP, room, ledNoticeBlankString.toString())));
//        getLedNoticeMessages().add(new MsgItem(INTR_TXT_ON, ledNoticeProtocol.intOn(Unlimited, room, 1)));        
//        
//        getLedNoticeMessages().add(new MsgItem(INTR_TXT_OFF, ledNoticeProtocol.intOff()));
//        getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(INTR_GROUP)));        
        
    //        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
    //                getLEDnoticeDefaultMsg(row, pauseIndex + 1, ledNoticeBlankString.toString(), 
    //                EffectType.values()[startIndex], EffectType.values()[finishIndex], 
    //                getColorFont(colorIndex, fontIndex) )));
        
//        getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
//                ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
                
        // 상단 행, 실제 표시할 문구를 전송한다.

        int pausePeriod = pauseIndex + 1;
        
        if (row == EBD_Row.TOP && tabIndex % 2 == 1)
            pausePeriod = MIN_PAUSE;
      
        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                getLEDnoticeDefaultMsg(row, pausePeriod, pureContent, 
                EffectType.values()[startIndex + 1], EffectType.values()[finishIndex], 
                getColorFont(colorIndex, fontIndex), true )));
    }

    private void readLEDnoticeSettings() {
        int count = EBD_DisplayUsage.values().length;
        ledNoticeSettings = new LEDnoticeSettings[count];
        for (int i = 0; i < count; i++) {
            ledNoticeSettings[i] = new LEDnoticeSettings();
        }
    }

    public static class MsgItem {
        private MsgType type;
        private String hexStr;
        private byte[] message;
        private int sendCount = 0;
        
        public MsgItem(MsgType type, String hexStr) {
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
    
    public class MessageQueue extends LinkedList<MsgItem> {
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
    private MessageQueue ledNoticeMessages = new MessageQueue();
}
