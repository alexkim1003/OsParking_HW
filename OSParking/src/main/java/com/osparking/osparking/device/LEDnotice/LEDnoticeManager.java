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

import com.osparking.global.Globals;
import com.osparking.osparking.ControlGUI;
import com.osparking.global.names.DeviceManager;
import java.net.Socket;
import java.util.logging.Level;
import static com.osparking.global.Globals.*;
import com.osparking.global.names.DB_Access;
import static com.osparking.global.names.DB_Access.connectionType;
import static com.osparking.global.names.OSP_enums.DeviceType.*;
import com.osparking.global.names.ParkingTimer;
import static com.osparking.global.names.DB_Access.gateCount;
import static com.osparking.global.names.DB_Access.gateNames;
import com.osparking.global.names.JDBCMySQL;
import static com.osparking.global.names.OSP_enums.ConnectionType.RS_232;
import com.osparking.global.names.OSP_enums.DisplayArea;
import static com.osparking.global.names.OSP_enums.DisplayArea.BOTTOM_ROW;
import static com.osparking.global.names.OSP_enums.DisplayArea.TOP_ROW;
import com.osparking.global.names.OSP_enums.EBD_DisplayUsage;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.DEFAULT_BOTTOM_ROW;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.DEFAULT_TOP_ROW;
import com.osparking.global.names.OSP_enums.EBD_Row;
import com.osparking.global.names.OSP_enums.PermissionType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorBox.Green;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorBox.Red;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.ColorFont;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.EffectType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.FontBox.Gothic;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.GROUP_TYPE.TEXT_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.IntOnType.Unlimited;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.LEDnoticeDefaultContentType;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.LEDnoticeVehicleContentType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.LEDnoticeVehicleContentType.VehicleTag;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_GROUP;
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
import static com.osparking.osparking.device.LEDnotice.LedProtocol.MIN_SPEED;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.SUCCESS;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.getViewWidth;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.isLtoRFlowType;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.isRtoLFlowType;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.TooManyListenersException;
import java.util.logging.Logger;

/**
 * Manages an E-board via a socket communication while current socket connection is valid.
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
    
    private SerialPort serialPort = null;
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
    
    public static LEDnoticeSettings[] ledNoticeSettings; 
    static {
        int count = EBD_DisplayUsage.values().length;
        ledNoticeSettings = new LEDnoticeSettings[count];
        readLEDnoticeSettings(ledNoticeSettings);
    }
    
    //</editor-fold>    
    private Object threadWaitingForACK = new Object();    
    Thread msgSender = null;
    
    private CommPortIdentifier portIdentifier;
    private CommPort commPort;
    
    InputStream inStream;
    
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
        
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier("COM6");
//            = CommPortIdentifier.getPortIdentifier(devicePort[E_Board.ordinal()][deviceID]);        
        } catch (NoSuchPortException ex) {
            logParkingException(Level.SEVERE, ex, "getting port identifier", deviceNo);
        }
        
        msgSender = new Thread("osp_LEDnoticeWriterThread")
        {
            public void run() {
                while (true) {
                    try {
                        //<editor-fold desc="-- Wait until it is connected">
                        synchronized (mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo]) {
                            if (!isConnected(socket, serialPort, E_Board, deviceNo, mainForm.tolerance))                            
                            {
                                try {
                                    System.out.println("before wait");
                                    mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                                    System.out.println("after woke up");
                                } catch (InterruptedException ex) {
                                    logParkingException(Level.SEVERE, ex, "waiting LEDnotice socket", deviceNo);
                                }
                            }
                        }
                        //</editor-fold>
                        synchronized (msgQdoor) {
                            msgQdoor.wait();
                        }                            
                        
                        while (!ledNoticeMessages.isEmpty()) {
                            //<editor-fold desc="-- Write message to LEDnotice if connected">
                            MsgItem currItem = getLedNoticeMessages().peek();
                            
                            try {
                                System.out.println(currItem.getType().toString() + "~> " + currItem.getHexStr());
                                if (connectionType[E_Board.ordinal()][deviceNo] == RS_232.ordinal()) {
                                    if (serialPort != null)
                                        serialPort.getOutputStream().write(currItem.getMessage());
                                } else {
                                    socket.getOutputStream().write(currItem.getMessage());
                                }
                                getLedNoticeMessages().peek().incSendCount();
                                
                                if (currItem.getType() == MsgType.GET_ID) {
                                    currItem = getLedNoticeMessages().remove();
                                }
                            } catch (IOException ex) {
                                logParkingException(Level.SEVERE, ex, "writing LEDnotice serial port", deviceNo);
                            }
                            //</editor-fold>
                            
                            try {
                                //<editor-fold desc="-- Delay some milliseconds by message type">
                                switch (currItem.getType()) {
//                                    case SET_MONITOR: 
//                                        Thread.sleep(RESEND_PERIOD * 5);
//                                        break;
//
//                                    case DEL_TEXT_ONE:
//                                    case DEL_GROUP:
//                                    case DEL_TEXT_ALL:
//                                        Thread.sleep(RESEND_PERIOD * 30);
//                                        break;
//
//                                    case SAVE_TEXT:
//                                    case SAVE_INTR:
//                                        Thread.sleep(RESEND_PERIOD * 15);
//                                        break;

                                    default:
//                                        if (connectionType[E_Board.ordinal()][deviceNo] == RS_232.ordinal())
//                                        {
//                                            synchronized (ackArrival) {
//                                                ackArrival.wait();
//                                            }
//                                        } else 
                                        {
                                            Thread.sleep(100);
                                        }
                                        break;
                                }
                                //</editor-fold>
                            } catch (InterruptedException ex) {
                                logParkingException(Level.SEVERE, ex, "taking a rest between two sending", deviceNo);
                                try {
                                    throw ex;
                                } catch (InterruptedException ex1) {
                                    logParkingException(Level.SEVERE, ex1, "rethrowing exception to outer", deviceNo);
                                }
                            }
                        }
                    } catch (InterruptedException ex) {
                        logParkingException(Level.SEVERE, ex, "Interrupted while waiting queue item added", deviceNo);
                        return;
                    }
                }
            }
        };
        msgSender.start();
        
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
    }
    
    Object ackArrival = new Object();
    
    public static void readLEDnoticeSettings(LEDnoticeSettings[] ledNoticeSettings) {
        Connection conn = null;
        Statement selectStmt = null;
        ResultSet rs = null;
        
        try{
            conn = JDBCMySQL.getConnection();
            selectStmt = conn.createStatement();
            rs = selectStmt.executeQuery("Select * From eboard_lednotice");
            
            while(rs.next()){
                int row = rs.getByte("usage_row");
                int used = rs.getByte("row_used");
                String content = rs.getString("verbatim_content");
                int type = rs.getByte("display_type");
                int startEffect = rs.getByte("start_effect");
                int pauseTime = rs.getByte("pause_time");
                int finishEffect = rs.getByte("finish_effect");
                int color = rs.getByte("text_color");
                int font = rs.getByte("text_font");
                
                ledNoticeSettings[row] = new LEDnoticeSettings(
                        used, type, content, startEffect, pauseTime, finishEffect, color, font);
            }
        } catch (SQLException ex) {
            Globals.logParkingException(Level.SEVERE, ex, "while reading e-board settings");  
        } finally {
            closeDBstuff(conn, selectStmt, rs, "Resource return used in eboard setting loading for ROW: ");
        }        
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
            
            // read device message as long as connection is good
            //<editor-fold desc="-- Repeat processing one message from LEDnotice">
            try {
                synchronized(mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo])  
                {
                    //<editor-fold desc="-- Wait connection, send default settings, read message code">
                    if (!isConnected(socket, serialPort, E_Board, deviceNo, mainForm.tolerance))
                    {
                        mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                        neverConnected = false;
                    }
                    //</editor-fold>
                } 

                inStream = socket.getInputStream();
            
                //<editor-fold desc="-- Read arriving message from LEDnotice">
                // SocketTimeoutException will arise when no data on the socket during 1 second
                int byteIndex = 0;
                int msgLength = inStream.read(preMsg); // waits for PULSE_PERIOD miliseconds 

                typeInt = preMsg[2];  

                int typeUint = LedProtocol.byteToUint(typeInt);

                while ( true )
                {
                    posiETX = (byte)(inStream.read());
                    if (posiETX == -1) {
                        finishConnection(null, "LEDnotice closed socket", deviceNo);
                        break;
                    } else 
                    if (posiETX == 3) {
                        MsgPost[byteIndex++] = posiETX;
                        break;
                    } else {
                        if (posiETX == 0x10) {
                            byte aByte = (byte)inStream.read();
                            posiETX = (byte)(aByte - 0x20);
                        }
                        MsgPost[byteIndex++] = posiETX;
                    }
                }
                
                if (msgLength == -1) {
                    // 'End of stream' means other party closed socket. So, I need to close it from my side.
                    finishConnection(null,  "End of stream reached, gate #" + deviceNo, deviceNo);
                } else {
                    if (validMessage(preMsg, MsgPost[byteIndex - 1])) 
                    {
                        processValidMessage(preMsg, byteIndex, MsgPost, typeUint);
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
        if (msgSender != null)
            msgSender.interrupt();
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
                if (isConnected(socket, serialPort, E_Board, gateNo, mainForm.tolerance))
                {   
                    String msg =  "E-Board #" + gateNo;

                    addMessageLine(mainForm.getMessageTextArea(), "  ------" +  msg + " disconnected");
                    logParkingException(Level.INFO, e, description + " " + msg);

                    mainForm.getSockConnStat()[E_Board.ordinal()][gateNo].
                            recordSocketDisconnection(System.currentTimeMillis());
                    if (connectionType[E_Board.ordinal()][gateNo] == RS_232.ordinal()) {
                        if (getSerialPort() != null) {
                            getSerialPort().close();
                            setSerialPort(null);
                        }
                    } else {
                        closeSocket(getSocket(), "while gate bar socket closing");
                        socket = null;
                    }
                }
            } else {
                System.out.println("this never ever gateNo");
            }
        }
            
        if (mainForm.getConnectDeviceTimer()[E_Board.ordinal()][gateNo] != null) {
            if (!mainForm.isSHUT_DOWN()) {
//                try {
                    //                if (getPortIdentifier().isCurrentlyOwned()) {
//                    getPortIdentifier().removePortOwnershipListener(null);
//                }
//                    commPort.close();
//                    inStream.close();
                    mainForm.getConnectDeviceTimer()[E_Board.ordinal()][gateNo].reRunOnce();
                    addMessageLine(mainForm.getMessageTextArea(), "Trying to connect to E-Board #" + gateNo);
//                } catch (IOException ex) {
//                    Logger.getLogger(LEDnoticeManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        }
    }

    @Override
    public boolean isNeverConnected() {
        return neverConnected;
    }

    public boolean validMessage(byte[] preMsg, byte possibleETX) {
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
        }
        
        if (row == EBD_Row.BOTTOM) {
            endSpeed = MIN_SPEED;
        }
        
        return ledNoticeProtocol.textType(roomNo, displayRow, startEffect, startSpeed, pauseTime, 
                finishEffect, endSpeed, repeatCnt, colorFont.getHexStr(), displayText, false);
    }

    /**
     * @return the ledNoticeMessages
     */
    public MessageQueue getLedNoticeMessages() {
        return ledNoticeMessages;
    }

    static int interruptRoom = 0;
    
    public void sendCarArrival_interruptMessage(LEDnoticeSettings topSetting, LEDnoticeSettings bottomSetting,
            byte gateNo, String tagNumber, 
            PermissionType permission,
            String remark, int delay) 
    {
        MessageQueue mQueue = getLedNoticeMessages();

        interruptRoom = interruptRoom++ % 5;
        
        int startEffIdx = (int)topSetting.startEffectIdx;
        EffectType startEffect = EffectType.values()[startEffIdx + 1];
        final int startSpeed = 15; // 1 ~ 31
        int stopTime = 1; // 1 ~ 10
        
        int endEffIdx = (int)topSetting.finishEffectIdx;
        EffectType endEffect = EffectType.values()[endEffIdx];
        final int endSpeed = 15;
        final int repeatCnt = 1;  
        
        int colorIdx = topSetting.colorIdx;
        int fontIdx = topSetting.fontIdx;
        ColorFont topFont = getColorFont(colorIdx, fontIdx);
        
        String topMsg = getInterruptMessage(topSetting, bottomSetting, 
                EBD_Row.TOP, tagNumber, permission, remark);
        
        colorIdx = bottomSetting.colorIdx;
        fontIdx = bottomSetting.fontIdx;
        ColorFont bottomFont = getColorFont(colorIdx, fontIdx);
        String bottomMsg = getInterruptMessage(topSetting, bottomSetting, 
                EBD_Row.BOTTOM, tagNumber, permission, remark);

        // 긴 문장이 있으면 시작 효과를 좌로 흐름으로 변경
        if ((getViewWidth(topMsg) > LedProtocol.LED_COLUMN_CNT * 2) ||
                (getViewWidth(bottomMsg) > LedProtocol.LED_COLUMN_CNT * 2)) 
        {
            if (!isRtoLFlowType(startEffect) && !isLtoRFlowType(startEffect)) {
                startEffect = EffectType.FLOW_RtoL;
            }
        }
            
        mQueue.add(new MsgItem(SAVE_INTR, ledNoticeProtocol.interruptBothRows(interruptRoom, 
                DisplayArea.WHOLE_AREA, 
                startEffect, startSpeed, stopTime,  
                endEffect, endSpeed, repeatCnt,
                topFont, topMsg, bottomFont, bottomMsg)));
        
        mQueue.add(new MsgItem(INTR_TXT_ON, ledNoticeProtocol.intOn(Unlimited, interruptRoom, 1)));
        mainForm.getSendEBDmsgTimer()[gateNo][EBD_Row.TOP.ordinal()].reRunOnce(
                new FinishLEDnoticeIntrTask(mainForm, gateNo, EBD_Row.BOTTOM), delay);    
        System.out.println("scheduled after ms: " + delay);
    }

    Thread demoThread = null;
    
    private ColorFont getColorFont( int colorIdx, int fontIdx) {
        if (fontIdx == Gothic.ordinal()) { 
            if (colorIdx == Red.ordinal()) {
                return ColorFont.RedGothic;
            }else if (colorIdx == Green.ordinal()) {
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
    
    public void showAllEffects(int tabIdx, final int stopIndex, int colorIdx, int fontIdx) {
        final String setFont = getColorFont(colorIdx, fontIdx).getHexStr();
        
        try{
            demoThread = new Thread("ospEBD_effectDemo") {
                public void run() {
                    int startSpeed = 15; // 1 ~ 31
                    int stopTime = stopIndex + 1; // 1 ~ 10
                    int endSpeed = 15;
                    int repeatCnt = 1;

                    String displayText; // First delete all rooms for the general text.

                    // 다음, 전광판의 기본 표시 문구를 전송한다.
                    // 상단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
                    getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
                    try {
                        int totalCount = EffectType.values().length;
                        int seqNo = 1;
                        EffectType finishEffect; 
                        
                        for (EffectType startEffect : EffectType.values()) {
                            // first display demo effect sequence number 
                            displayText = ledNoticeProtocol.textType(TOPTEXT_ROOM, TOP_ROW, 
                                    EffectType.NONE, MAX_SPEED, 3, EffectType.NONE, 
                                    MAX_SPEED, 1, setFont, (seqNo++) + "/" + totalCount + "-th", false) ;
                            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, displayText));
//                            Thread.sleep(3000);
                            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                                    ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));                           
                            
                            int startIdx = startEffect.getLabel().length() - LED_COLUMN_CNT * 2;
                            String displayCore = startEffect.getLabel().substring(
                                    startIdx < 0 ? 0 : startIdx, startEffect.getLabel().length());
                            
                            if (startEffect == EffectType.FLOW_DOWN) {
                                finishEffect = startEffect;
                                startEffect = EffectType.NONE;
                            } else if (startEffect == EffectType.FLOW_UP) {
                                finishEffect = startEffect;
                                startEffect = EffectType.NONE;
                            } else {
                                finishEffect = EffectType.NONE;
                            }
                            displayText = ledNoticeProtocol.textType(TOPTEXT_ROOM + 1, TOP_ROW, 
                                    startEffect, startSpeed, stopTime, finishEffect, 
                                    endSpeed, repeatCnt, setFont, displayCore, true);
                            getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, displayText));
                            Thread.sleep(15000);

                            // first, clear the LED row with a blank line
                            clearDefaultDisplay(TOPTEXT_ROOM + 1, TOP_ROW);
                            
                            getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
                        }
                    } catch (InterruptedException ex) {
                        // first, clear the LED row with a blank line
                        displayText = ledNoticeProtocol.textType(TOPTEXT_ROOM, TOP_ROW, 
                                EffectType.STOP_MOVING, MAX_SPEED, 1, EffectType.STOP_MOVING, 
                                MAX_SPEED, 1, setFont, ledNoticeBlankString.toString(), false);
                        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, displayText));
                            
                        // delete text memory 
                        getLedNoticeMessages().add(
                                new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
                        
                        // display default message
                        showLEDnoticeDefaultMessage(EBD_Row.TOP);
                        showLEDnoticeDefaultMessage(EBD_Row.BOTTOM);                        
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

    private void clearDefaultDisplay(int room, DisplayArea displayArea) {
        String emptyText = ledNoticeProtocol.textType(room, displayArea, 
                EffectType.STOP_MOVING, MAX_SPEED, 1, EffectType.STOP_MOVING, 
                MAX_SPEED, 1, ColorFont.GreenGothic.getHexStr(), ledNoticeBlankString.toString(), false) ;
        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, emptyText));
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(LEDnoticeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       

    public void finishShowingDemoEffect(int index) {
        if (demoThread != null) {
            demoThread.interrupt();
        } else {
            getLedNoticeMessages().add(new MsgItem(DEL_GROUP, ledNoticeProtocol.delGroup(TEXT_GROUP)));
            showLEDnoticeDefaultMessage(EBD_Row.TOP);
            showLEDnoticeDefaultMessage(EBD_Row.BOTTOM);
        }
    }

    public void showLEDnoticeDefaultMessage(EBD_Row row) {
        // 다음, 전광판의 기본 표시 문구를 전송한다.
        if (row == EBD_Row.TOP) {
            // 상단 행 문구 표시 전에 일단 현재 내용을 제거한다.
            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                    ledNoticeProtocol.delData(GENERAL_TEXT, TOPTEXT_ROOM)));
            
            LEDnoticeSettings setting = ledNoticeSettings[DEFAULT_TOP_ROW.ordinal()];
            
            // 상단 행 실제 표시할 문구를 전송한다.
            if (setting.isUsed) {
                ColorFont colorFont = getColorFont(setting.colorIdx, setting.fontIdx);
                getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                        getLEDnoticeDefaultMsg(EBD_Row.TOP, 1, 
                                getDefaultContent(setting), 
                                EffectType.values()[setting.startEffectIdx + 1],
                                EffectType.values()[setting.finishEffectIdx],
                                colorFont,
                                false)));
            }
        } else {
            // 하단 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
            getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, 
                    ledNoticeProtocol.delData(GENERAL_TEXT, BOTTEXT_ROOM)));
            
            LEDnoticeSettings setting = ledNoticeSettings[DEFAULT_BOTTOM_ROW.ordinal()];
            
            // 하단 행, 실제 표시할 문구를 전송한다.
            if (setting.isUsed) {
                ColorFont colorFont = getColorFont(setting.colorIdx, setting.fontIdx);
                getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                        getLEDnoticeDefaultMsg(EBD_Row.BOTTOM, 5, // pause time
                                getDefaultContent(setting), 
                                EffectType.values()[setting.startEffectIdx + 1],
                                EffectType.values()[setting.finishEffectIdx],
                                colorFont,
                                false)));
            }
        }
    }

    public String getAckStatistics(byte deviceNo) {
//        return " AckAvg:" + (delayTotal[deviceNo] / delayCount[deviceNo]) + "ms/cnt:" + delayCount[deviceNo]
        if (recentCount[deviceNo] == 0)
            return "avg not accumed yet";
        else 
            return " AckAvg:" + (recentTotal[deviceNo] / recentCount[deviceNo]) + "ms/cnt:" 
                    + recentCount[deviceNo] + System.lineSeparator();
    }    
    
    public void showCurrentEffect(EBD_DisplayUsage usage, LEDnoticeSettings ledNoticeSetting ) {
        String pureContent = null;
        int room;         EBD_Row row;         DisplayArea area;
        
        if (usage.ordinal() % 2 == 0) {
            room = TOPTEXT_ROOM;             area = TOP_ROW;             row = EBD_Row.TOP;
        } else {
            room = BOTTEXT_ROOM;             area = BOTTOM_ROW;         row = EBD_Row.BOTTOM;
        }

        pureContent = getDefaultContent(ledNoticeSetting);
        
        // first, clear the LED row with a blank line
        clearDefaultDisplay(room, area);
        String emptyText = ledNoticeProtocol.textType(room, area, 
                EffectType.STOP_MOVING, MAX_SPEED, 1, EffectType.STOP_MOVING, 
                MAX_SPEED, 1, ColorFont.GreenGothic.getHexStr(), ledNoticeBlankString.toString(), false) ;
        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, emptyText));

        // 표시할 행, 문구를 표시하기 전에 일단 현재 내용을 제거한다.
        getLedNoticeMessages().add(new MsgItem(DEL_TEXT_ONE, ledNoticeProtocol.delData(GENERAL_TEXT, room)));                
        
        // 표시할 행, 실제 표시할 문구를 전송한다.
        int pausePeriod = ledNoticeSetting.pauseTimeIdx + 1;
        
        if (row == EBD_Row.TOP && usage.ordinal() % 2 == 1)
            pausePeriod = MIN_PAUSE;
      
        getLedNoticeMessages().add(new MsgItem(SAVE_TEXT, 
                getLEDnoticeDefaultMsg(row, pausePeriod, pureContent, 
                EffectType.values()[ledNoticeSetting.startEffectIdx + 1], 
                EffectType.values()[ledNoticeSetting.finishEffectIdx], 
                getColorFont(ledNoticeSetting.colorIdx, ledNoticeSetting.fontIdx), true )));
    }

    private String getInterruptMessage(LEDnoticeSettings topSetting, LEDnoticeSettings bottomSetting,
            EBD_Row ebd_Row, String tagNumber, PermissionType permission, String remark) 
    {
        LEDnoticeSettings setting;
        String result;
        
        if (ebd_Row == EBD_Row.TOP)
            setting = topSetting;
        else
            setting = bottomSetting;
        
        int firstHalfLen = LEDnoticeVehicleContentType.values().length;
        if (setting.contentTypeIdx < firstHalfLen) {
            switch (LEDnoticeVehicleContentType.values()[setting.contentTypeIdx]) {
                case VehicleTag:
                    result = tagNumber;
                    break;
                    
                case RegistrationStat:
                    if (permission == PermissionType.ALLOWED) 
                        result = "등록차량";
                    else if (permission == PermissionType.UNREGISTERED)
                        result = "방문차량";
                    else
                        result = "주차제한차량";
                    break;
                    
                case VehicleRemark:
                    if (permission == PermissionType.ALLOWED) 
                        result = "등록차량";
                    else if (permission == PermissionType.UNREGISTERED)
                        result = "방문차량";
                    else {
                        if (remark == null || remark.length() == 0) 
                            result = "주차제한차량";
                        else
                            result = remark;
                    }
                    break;
                default:
                    result = "";
                    break;
            }
            return result;
        } else {
            setting.contentTypeIdx -= firstHalfLen;
            return getDefaultContent(setting);
        }
    }
    
    private String getDefaultContent(LEDnoticeSettings ledNoticeSetting) {
        int index = ledNoticeSetting.contentTypeIdx;
        String result;
        switch (LEDnoticeDefaultContentType.values()[index]) {
            case GateName:
                result = gateNames[deviceNo];
                break;
            
            case ParkingLotName:
                result = DB_Access.parkingLotName;
                break;
                
            case ParkingLot_GateName:
                result = DB_Access.parkingLotName + "-" + gateNames[deviceNo];
                break;

            default:
                result = ledNoticeSetting.verbatimContent;
                break;
        }
        return result;
    }

    @Override
    public void setSerialPort(SerialPort serialPort) {
        try {
            if (serialPort != null) {
                serialPort.notifyOnDataAvailable(true);
                serialPort.addEventListener(new RS_232_Manager(this, serialPort));
            }
        } catch (TooManyListenersException ex) {
            Logger.getLogger(LEDnoticeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.serialPort = serialPort;
    }

    /**
     * @return the serialPort
     */
    public SerialPort getSerialPort() {
        return serialPort;
    }

    private void signalAckArrival() {
//        if (connectionType[E_Board.ordinal()][deviceNo] == RS_232.ordinal()) {
//            synchronized (ackArrival) {
//                ackArrival.notify();
//            }
//        }
    }

    /**
     * @return the portIdentifier
     */
    public CommPortIdentifier getPortIdentifier() {
        return portIdentifier;
    }

    /**
     * @param portIdentifier the portIdentifier to set
     */
    public void setPortIdentifier(CommPortIdentifier portIdentifier) {
        this.portIdentifier = portIdentifier;
    }

    /**
     * @return the commPort
     */
    public CommPort getCommPort() {
        return commPort;
    }

    /**
     * @param commPort the commPort to set
     */
    public void setCommPort(CommPort commPort) {
        this.commPort = commPort;
    }

    public void processValidMessage(byte[] preMsg, int byteIndex, byte[] MsgPost, int typeUint) {
        String msgCame = "";
        int i = 0;

        for ( ; i < preMsg.length; i++) { msgCame += String.format("%02X", preMsg[i]); }
        for (i = 0; i < byteIndex; i++) { msgCame += String.format("%02X", MsgPost[i]); }

        if (typeUint != getID)
            System.out.println("<~~: " + msgCame);

        switch (typeUint) {
            case getID:
                // process LEDnotice device heartbeat
                mainForm.tolerance[E_Board.ordinal()][deviceNo].assignMAX();
                // wake up sleeping writer.
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

    public static class MsgItem {
        private MsgType type;
        private String hexStr;
        private byte[] message;
        private int sendCount = 0;
        private long timeAddedMs;
        
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
            item.timeAddedMs = System.currentTimeMillis();
            super.add(item);
            System.out.println("Added: " + item.getType() + ", Hex: " + item.getHexStr());
            synchronized(msgQdoor) {
                msgQdoor.notify(); // wake up someone sleeping on this queue
            }
            return true;
        }
        
        public MsgItem remove() {
            long currDelay = System.currentTimeMillis() - peek().timeAddedMs;
            delayTotal[deviceNo] += currDelay;
            delayCount[deviceNo]++;
            if (delayCount[deviceNo] == DB_Access.statCount) {
                recentTotal[deviceNo] = delayTotal[deviceNo];
                recentCount[deviceNo] = DB_Access.statCount;
            }
            return super.remove();
        }
    }
    
    static long[] delayTotal = new long[gateCount + 1];
    static int[] delayCount = new int[gateCount + 1];
    
    static long[] recentTotal = new long[gateCount + 1];
    static int[] recentCount = new int[gateCount + 1];
    
    Object msgQdoor = new Object();
    Object ackArrived = new Object();
    private MessageQueue ledNoticeMessages = new MessageQueue();
}