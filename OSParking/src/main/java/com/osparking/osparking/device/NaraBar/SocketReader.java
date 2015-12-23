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
package com.osparking.osparking.device.NaraBar;

import static com.osparking.global.Globals.DEBUG;
import static com.osparking.global.Globals.addMessageLine;
import static com.osparking.global.Globals.closeSocket;
import static com.osparking.global.Globals.isConnected;
import static com.osparking.global.Globals.logParkingException;
import com.osparking.global.names.OSP_enums.DeviceType;
import static com.osparking.global.names.OSP_enums.DeviceType.GateBar;
import com.osparking.osparking.ControlGUI;
import static com.osparking.osparking.device.NaraBar.NaraEnums.Nara_MsgType.Broken;
import static com.osparking.osparking.device.NaraBar.RS_232_Manager.readDeliveredMessage;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/**
 * Manages a gate bar via a socket communication while current socket connection is valid.
 * To do that, gateManager uses a socket reader(SockReader) Thread instance and a Runnable class
 * (AreYouThereSender) instance which is created by the SockReader object.
 * 
 * @author Open Source Parking Inc.
 */
public class SocketReader extends Thread {
    //<editor-fold desc="--class variables">
    ControlGUI mainGUI;
    private byte gateID = 0; // ID of the gate bar being served by this manager. A valid ID starts from 1.
    private NaraBarMan barManager; // main form of the gate bar simulator.
    
    /**
     * socket for the communication with the gate bar.
     */
    
    /**
     * a timer employed to send Open commands to the designated gate bar for sure.
     */
//    ParkingTimer openGateTimer = null;
    
    /**
     * a timer used to send a heartbeat to the gate bar regularily.
     */
//    public ParkingTimer timerAreYouThere = null;
    //</editor-fold>    

    byte [] cmdIDarr = new byte[4]; // open command ID
    byte [] fiveByteArr =new byte[5]; // storage for (code + ID)
    
    private boolean neverConnected = true;
    
    /**
     * A unique constructor of the GateManager class.
     * 
     * @param barManager gate bar manager(sends commands and handles responses)
     * @param gateID ID of the gate bar to manage
     */
    public SocketReader(ControlGUI mainGUI, NaraBarMan barManager, byte gateID)
    {
        super("osp_GateBar_" + gateID + "_Manager");        
        this.mainGUI = mainGUI; 
        this.barManager = barManager; 
        this.gateID = gateID; 
//        openGateTimer = mainForm.getOpenGateCmdTimer()[gateID];
    }    

    public void run()
    {   
        while (true) // infinite reading of a gate bar socket 
        {
            synchronized (mainGUI.getSocketMutex()[DeviceType.GateBar.ordinal()][gateID]) 
            {
                if (!isConnected(barManager.getSocket())) {
                    try {
                        mainGUI.getSocketMutex()[DeviceType.GateBar.ordinal()][gateID].wait();
                    } catch (InterruptedException ex) {
                        System.out.println("intred excp");
                    }
                }
            }
            
            try {
                // 자료를 포트에서 읽어서 바른 차단기 메시지이면 차단기 명령 수신 대기자를 깨움
                barManager.setMsg(readDeliveredMessage(barManager.getSocket().getInputStream()));
            } catch (SocketTimeoutException ex) {
                System.out.println("time out");
            } catch (IOException ex) {
                System.out.println("IO excep");
            }
            if (barManager.getMsg() != Broken) {
                System.out.println("message type: " + barManager.getMsg());
                synchronized(barManager.getMsgArrived()) {
                    barManager.getMsgArrived().notify();
                }
            }            
        }
    }

    /**
     * stops serving a gate bar.
     */
    public void stopOperation(String reason) {
        finishConnection(null, reason, gateID);
        interrupt();
    }

    /**
     * closes socket connection to a gate bar.
     * 
     * before closing the socket, it cancels any existing relevant tasks.
     */
    public void finishConnection(Exception e, String description, byte gateNo) {

        synchronized(mainGUI.getSocketMutex()[GateBar.ordinal()][gateNo]) 
        {
            if (isConnected(barManager.getSocket())) 
            {
                String msg =  "Gate bar #" + gateNo;

                addMessageLine(mainGUI.getMessageTextArea(), "  ------" + msg + " disconnected");
                logParkingException(Level.INFO, e, description + " " + msg);

                long closeTm = System.currentTimeMillis();

                mainGUI.getSockConnStat()[GateBar.ordinal()][gateNo].recordSocketDisconnection(closeTm);
                
                if (DEBUG) {
                    System.out.println("M9. Gate bar #" + gateNo + " disconnected at: " + closeTm);                        
                }
                closeSocket(barManager.getSocket(), "while gate bar socket closing");
                barManager.setSocket(null);
            }                
        } 
    }

    /**
     * @return the everConnected
     */
    public boolean isNeverConnected() {
        return neverConnected;
    }
}
