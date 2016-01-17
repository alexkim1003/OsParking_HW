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
package com.osparking.osparking.device;

import com.osparking.global.names.IDevice;
import static com.osparking.global.Globals.gateDeviceTypes;
import java.awt.Color;
import java.io.IOException;
import java.util.TimerTask;
import static com.osparking.global.Globals.isConnected;
import static com.osparking.global.Globals.logParkingExceptionStatus;
import com.osparking.global.names.OSP_enums.DeviceType;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import com.osparking.osparking.ControlGUI;
import static com.osparking.global.names.DB_Access.gateCount;
import com.osparking.global.names.IDevice.IManager;
import com.osparking.global.names.OSP_enums;
import com.osparking.global.names.IDevice.ISocket;
import static com.osparking.global.names.OSP_enums.E_BoardType.LEDnotice;
import com.osparking.osparking.device.LEDnotice.LEDnoticeManager;
import com.osparking.osparking.device.LEDnotice.LEDnotice_enums.LED_MsgType;
import com.osparking.osparking.device.LEDnotice.LedProtocol;
import com.osparking.osparking.device.LEDnotice.LEDnoticeMessageQueue.MsgItem;
import com.osparking.osparking.device.NaraBar.NaraMessageQueue.NaraMsgItem;
import com.osparking.osparking.device.NaraBar.NaraBarMan;
import com.osparking.osparking.device.NaraBar.NaraEnums.Nara_MsgType;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import static com.osparking.global.names.OSP_enums.MsgCode.AreYouThere;
import static com.osparking.global.names.OSP_enums.MsgCode.EBD_GetID;
import static com.osparking.osparking.device.LEDnotice.LEDnoticeManager.ledNoticeProtocol;
import java.util.logging.Level;
import static javax.swing.text.html.HTML.Tag.HEAD;

//<editor-fold desc="-- Class LED_Task">
/**
 * Display the camera connection status with the Manager program
 * 
 * @author Open Source Parking Inc.
 */
public class LED_Task extends TimerTask {

    ControlGUI controlGUI = null;
    IDevice.IManager[][] deviceManagers = null;
    /**
     * used to give blinking effect to the status text label on the device socket connection
     */
    boolean setHalfTransparent = false;

    LedProtocol ledNoticeProtocol; 
    byte[] ledNoticeGetIDmsg;
    String getID_HexString;
    
    /**
     * Initializes this task with the main GUI and a managerLEDnotice array.
     * 
     * @param guiMain GUI form frame on which it's device connection status is to be displayed
     * @param gateManagerArr managerLEDnotice array through which their sockets are accessed 
     */
    public LED_Task(ControlGUI guiMain, IDevice.IManager[][] deviceManagers) {
        this.controlGUI = guiMain;
        this.deviceManagers = deviceManagers;
        ledNoticeProtocol = new LedProtocol();
        
        // Initialize some repeatedly used messages.
        getID_HexString = ledNoticeProtocol.getId();
        ledNoticeGetIDmsg = ledNoticeProtocol.hexToByteArray(getID_HexString);
//=======
//    /**
//     * Initializes this task with the main GUI and a manager array.
//     * 
//     * @param guiMain GUI form frame on which it's device connection status is to be displayed
//     * @param gateManagerArr manager array through which their sockets are accessed 
//     */
//    public LED_Task(ControlGUI guiMain, DeviceManager[][] deviceManagers) {
//        this.controlGUI = guiMain;
//        this.deviceManagers = deviceManagers;
//>>>>>>> osparking/master
    }
    
    /**
     * Displays connection status to each hardware device of each gate periodically. 
     * 
     * One row of LED labels represents device components at an enterance gate.
     * To give blinking Las Vegas sign effect, assigns different transparency degrees for each neighboring rows.
     */
    public void run() {

//<<<<<<< HEAD
        for (DeviceType devType : DeviceType.values()) {
            byte typeNo= (byte)devType.ordinal();
//=======
//        for (DeviceType type : DeviceType.values()) {
//            byte typeNo= (byte)type.ordinal(); // tn: type number
//>>>>>>> osparking/master
            
            for (byte gateNo = 1; gateNo <= gateCount; gateNo++) // gn : gate number
            {
                try {
                    if (setHalfTransparent) 
                    {
                        //<editor-fold desc="--decrease alpha value of odd row LED label">
//<<<<<<< HEAD
                        if (deviceManagers[typeNo][gateNo] != null && 
                                IDevice.isConnected(deviceManagers[typeNo][gateNo], devType, gateNo))
                        {

                            sendHeartBeat(devType, gateNo);
//                            System.out.println("type: " + devType + ", no: " + gateNo);
//                            System.out.println("level: " + controlGUI.tolerance[devType.ordinal()][gateNo].getLevel());
                            controlGUI.tolerance[devType.ordinal()][gateNo].decrease();
//=======
//                        if (deviceManagers[typeNo][gateNo] != null 
//                                && isConnected(deviceManagers[typeNo][gateNo].getSocket())) {
//
//                            sendHeartBeat(typeNo, gateNo);                        
//                            controlGUI.tolerance[type.ordinal()][gateNo].decrease();
//>>>>>>> osparking/master

                            if (gateNo % 2 == 0 )
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 0.0f, 1.0f, 0.0f, 1.0f));
                            else
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 0.0f, 1.0f, 0.0f, 0.5f));
                        } else {
                            if (gateNo % 2 == 0 ) // for blinking effect between adjacent rows
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 1.0f, 0.0f, 0.0f, 1.0f));
                            else
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 1.0f, 0.0f, 0.0f, 0.5f));
                        }
                        //</editor-fold>
                    } else {
                        //<editor-fold desc="--decrease alpha value of even row LED label">
//<<<<<<< HEAD
                        if (deviceManagers[typeNo][gateNo] != null && 
                                IDevice.isConnected(deviceManagers[typeNo][gateNo], devType, gateNo))
                        {
                            sendHeartBeat(devType, gateNo);     
//                            System.out.println("type: " + devType + ", no: " + gateNo);
//                            System.out.println("level: " + controlGUI.tolerance[devType.ordinal()][gateNo].getLevel());
                            controlGUI.tolerance[devType.ordinal()][gateNo].decrease();
//=======
//                        if (deviceManagers[typeNo][gateNo] != null 
//                                && isConnected(deviceManagers[typeNo][gateNo].getSocket())) {
//
//                            sendHeartBeat(typeNo, gateNo);                        
//                            controlGUI.tolerance[type.ordinal()][gateNo].decrease();
//>>>>>>> osparking/master

                            if (gateNo % 2 == 0 ) 
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 0.0f, 1.0f, 0.0f, 0.5f));
                            else    
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 0.0f, 1.0f, 0.0f, 1.0f));
                        } else {
                            if (gateNo % 2 == 0 ) // for blinking effect between adjacent rows
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 1.0f, 0.0f, 0.0f, 0.5f));
                            else
                                controlGUI.getDeviceConnectionLEDs()[typeNo][gateNo]
                                        .setForeground(new Color( 1.0f, 0.0f, 0.0f, 1.0f));
                        }
                        //</editor-fold>
                    }
                } catch (Exception e) {
                    logParkingExceptionStatus(Level.SEVERE, e, "LED task finishing: ", 
                            controlGUI.getStatusTextField(), gateNo);            
                }
            }
        }
        setHalfTransparent  =  ! setHalfTransparent;
    }
//<<<<<<< HEAD
    
    private void sendHeartBeat(DeviceType type, byte gateNo) {
        OutputStream outStream;
        byte[] msgBytes = null;
        
        if (type == E_Board) {
            switch (gateDeviceTypes[gateNo].eBoardType) {
                case LEDnotice:
                    msgBytes = ledNoticeGetIDmsg;
                    break;

                default:
                    msgBytes = ByteBuffer.allocate(1).put((byte)EBD_GetID.ordinal()).array();
                    break;
            }
        } else {
            try {
                msgBytes = ByteBuffer.allocate(1).put((byte)AreYouThere.ordinal()).array();
            } catch (Exception exc) {
                System.out.println("exc");
            }
        }
        
        try {
            if (type == DeviceType.E_Board && 
                    gateDeviceTypes[gateNo].eBoardType == OSP_enums.E_BoardType.LEDnotice) 
            {
                LEDnoticeManager manager = (LEDnoticeManager)deviceManagers[type.ordinal()][gateNo];
                if (manager.getLedNoticeMessages().size() == 0) 
                {
                    manager.getLedNoticeMessages().add(new MsgItem(LED_MsgType.GET_ID, getID_HexString));
                }
            } else if (type == DeviceType.GateBar &&
                    gateDeviceTypes[gateNo].gateBarType == OSP_enums.GateBarType.NaraBar) 
            {
                // send status message to the gate bar firmware
                NaraBarMan manager = (NaraBarMan)deviceManagers[type.ordinal()][gateNo];
                if (manager.getNaraBarMessages().size() == 0) 
                {
                    manager.getNaraBarMessages().add(new NaraMsgItem(Nara_MsgType.Status));
                }
            } else {
                outStream = ((ISocket)deviceManagers[type.ordinal()][gateNo]).getSocket().getOutputStream();
                outStream.write(msgBytes);
            }
        } catch (IOException e) {
            deviceManagers[type.ordinal()][gateNo].finishConnection(e, "while sending heartbeat", gateNo);
        }
//=======
//
//    private void sendHeartBeat(byte typeNo, byte gateNo) {
//        try {
////            System.out.println("3. try sending AreYouThere");
//            int beat = (typeNo == E_Board.ordinal() ? EBD_GetID.ordinal() : AreYouThere.ordinal());
//            deviceManagers[typeNo][gateNo].getSocket().getOutputStream().write(beat);
////            System.out.println("3.1. AreYouThere sent");
//        } catch (IOException e) {
//            deviceManagers[typeNo][gateNo].finishConnection(e, "while sending heartbeat", gateNo);
//        }           
//>>>>>>> osparking/master
    }
}
