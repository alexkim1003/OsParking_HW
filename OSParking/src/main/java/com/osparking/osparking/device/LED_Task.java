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

import static com.osparking.global.Globals.gateDeviceTypes;
import com.osparking.global.names.DeviceManager;
import java.awt.Color;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import static com.osparking.global.Globals.isConnected;
import static com.osparking.global.Globals.logParkingExceptionStatus;
import com.osparking.global.names.OSP_enums.DeviceType;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import com.osparking.osparking.ControlGUI;
import static com.osparking.global.names.DB_Access.gateCount;
import static com.osparking.global.names.OSP_enums.MsgCode.AreYouThere;
import static com.osparking.global.names.OSP_enums.MsgCode.EBD_GetID;
import static com.osparking.osparking.Settings_System.mainForm;
import com.osparking.osparking.device.LEDnotice.LedProtocol;

//<editor-fold desc="-- Class LED_Task">
/**
 * Display the camera connection status with the Manager program
 * 
 * @author Open Source Parking Inc.
 */
public class LED_Task extends TimerTask {

    ControlGUI controlGUI = null;
    DeviceManager[][] deviceManagers = null;
    /**
     * used to give blinking effect to the status text label on the device socket connection
     */
    boolean setHalfTransparent = false;

    LedProtocol ledNoticeProtocol; 
    byte[] ledNoticeGetIDmsg;
    
    /**
     * Initializes this task with the main GUI and a manager array.
     * 
     * @param guiMain GUI form frame on which it's device connection status is to be displayed
     * @param gateManagerArr manager array through which their sockets are accessed 
     */
    public LED_Task(ControlGUI guiMain, DeviceManager[][] deviceManagers) {
        this.controlGUI = guiMain;
        this.deviceManagers = deviceManagers;
        ledNoticeProtocol = new LedProtocol();
        
        // Initialize some repeatedly used messages.
        ledNoticeGetIDmsg = ledNoticeProtocol.hexToByteArray(ledNoticeProtocol.getId());
    }
    
    /**
     * Displays connection status to each hardware device of each gate periodically. 
     * 
     * One row of LED labels represents device components at an enterance gate.
     * To give blinking Las Vegas sign effect, assigns different transparency degrees for each neighboring rows.
     */
    public void run() {

        for (DeviceType type : DeviceType.values()) {
            byte typeNo= (byte)type.ordinal();
            
            for (byte gateNo = 1; gateNo <= gateCount; gateNo++) // gn : gate number
            {
                try {
                    if (setHalfTransparent) 
                    {
                        //<editor-fold desc="--decrease alpha value of odd row LED label">
                        if (deviceManagers[typeNo][gateNo] != null 
                                && isConnected(deviceManagers[typeNo][gateNo].getSocket())) {

                            sendHeartBeat(type, gateNo);
                            controlGUI.tolerance[type.ordinal()][gateNo].decrease();

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
                        if (deviceManagers[typeNo][gateNo] != null 
                                && isConnected(deviceManagers[typeNo][gateNo].getSocket())) {

                            sendHeartBeat(type, gateNo);                        
                            controlGUI.tolerance[type.ordinal()][gateNo].decrease();

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
    
    private void sendHeartBeat(DeviceType type, byte gateNo) {
        byte typeNo= (byte)type.ordinal();
        
        try {
            if (type == E_Board) {
                switch (gateDeviceTypes[gateNo].eBoardType) {
                    case LEDnotice:
                        deviceManagers[typeNo][gateNo].getSocket().getOutputStream().write(ledNoticeGetIDmsg);
                        int priority = Thread.currentThread().getPriority();
//                        System.out.print("B" + controlGUI.tolerance[E_Board.ordinal()][gateNo].getLevel());
                        break;

                    default:
                        deviceManagers[typeNo][gateNo].getSocket().getOutputStream().write(EBD_GetID.ordinal());
                        break;
                }
                
            } else {
                deviceManagers[typeNo][gateNo].getSocket().getOutputStream().write(AreYouThere.ordinal());
            }
            
            
        } catch (IOException e) {
            deviceManagers[typeNo][gateNo].finishConnection(e, "while sending heartbeat", gateNo);
        }           
    }
}
