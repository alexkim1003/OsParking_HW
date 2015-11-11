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
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import static com.osparking.global.Globals.*;
import static com.osparking.global.names.OSP_enums.DeviceType.*;
import com.osparking.global.names.ParkingTimer;
import static com.osparking.global.names.DB_Access.gateCount;
import com.osparking.global.names.OSP_enums;
import com.osparking.global.names.OSP_enums.EBD_Row;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.GET_ID;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SAVE_TEXT;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.SET_MONITOR;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.STX;
import static com.osparking.osparking.device.LEDnotice.LedProtocol.SUCCESS;
import java.util.logging.Logger;

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
    
    //</editor-fold>    

    byte [] cmdIDarr = new byte[4]; // open command ID
    byte [] fiveByteArr =new byte[5]; // storage for (code + ID)
    
    boolean justBooted = true;
    private boolean neverConnected = true;

    public LedProtocol ledNoticeProtocol = new LedProtocol(); 
    
    /**
     * 
     * @param mainForm main GUI form of the whole manager program
     * @param deviceNo ID of the E-Board to manage
     */
    public LEDnoticeManager(ControlGUI mainForm, byte deviceNo)
    {
        super("osp_EBD_" + deviceNo + "_Manager");
        this.mainForm = mainForm; 
        this.deviceNo = deviceNo;
    }    
    
    public void run()
    {   
        byte[] preMsg = new byte[3];
        byte typeInt;
        byte posiETX; // jbpark03
        
        byte[] MsgPost = new byte[100];
                    
        while (true) // infinite communication with an e-board
        {
            if (mainForm.isSHUT_DOWN()) {
                return;
            }
            
            int msgLength = -2;
            // read device message as long as connection is good
            
            try {
                synchronized(mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo])  
                {
                    //<editor-fold desc="-- Wait connection, send default settings, read message code">
                    if (! isConnected(getSocket())) 
                    {
                        mainForm.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                        neverConnected = false;
                    
                        if (justBooted) {
                            justBooted = false;
                            byte[] message = mainForm.getDefaultMessage(
                                    deviceNo, OSP_enums.EBD_Row.TOP, --mainForm.msgSNs[deviceNo]);
                            sendDefaultText_ScreenSize(mainForm, deviceNo, OSP_enums.EBD_Row.TOP, message);
                            
                            message = mainForm.getDefaultMessage(
                                    deviceNo, OSP_enums.EBD_Row.BOTTOM, --mainForm.msgSNs[deviceNo]);
//                            sendDefaultText_ScreenSize(mainForm, deviceNo, OSP_enums.EBD_Row.BOTTOM, message);
                        } 
                    }
                    //</editor-fold>
                } 
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
                        
                        if (typeUint == GET_ID.getValue()) {
                            // process LEDnotice device heartbeat
                            mainForm.tolerance[E_Board.ordinal()][deviceNo].assignMAX();
                        } else if (typeUint == SET_MONITOR.getValue()) {
                            ParkingTimer msgSendingTimer 
                                    = mainForm.getSendEBDmsgTimer()[deviceNo][EBD_Row.TOP.ordinal()];
                            if (msgSendingTimer.hasTask()) {
                                msgSendingTimer.cancelTask();
                                System.out.println("monitor size setting timer task cancelled");
                            }
                        } else {
                            if (typeUint == SAVE_TEXT.getValue()) {
                                ParkingTimer msgSendingTimer 
                                        = mainForm.getSendEBDmsgTimer()[deviceNo][EBD_Row.TOP.ordinal()];
                                if (msgSendingTimer.hasTask()) {
                                    msgSendingTimer.cancelTask();
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

    public static void sendDefaultText_ScreenSize(ControlGUI mainForm, 
            byte deviceNo, EBD_Row row, byte[] message) 
    {
        while (mainForm.getSendEBDmsgTimer()[deviceNo][row.ordinal()].hasTask()) 
        {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                logParkingException(Level.SEVERE, ex, "default text sender sleeping");
            }
        }
        
        mainForm.getSendEBDmsgTimer()[deviceNo][row.ordinal()].reschedule(
                new SendEBDMessageTask(
                        mainForm, deviceNo, row, message, mainForm.msgSNs[deviceNo]));
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
}
