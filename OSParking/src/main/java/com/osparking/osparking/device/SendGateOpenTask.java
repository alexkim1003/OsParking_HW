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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import static com.osparking.global.Globals.logParkingException;
import com.osparking.global.names.IDevice;
import static com.osparking.global.names.OSP_enums.DeviceType.GateBar;
import com.osparking.global.names.OSP_enums.MsgCode;
import static com.osparking.global.names.OSP_enums.MsgCode.Open;
import com.osparking.osparking.ControlGUI;
import javax.swing.JOptionPane;
//import parking.device.CameraReader.WriteMUTEX;

/**
 * send one specific open command to a gate bar.
 * 
 * @author Open Source Parking Inc.
 */
public class SendGateOpenTask implements Runnable {
    ControlGUI mainForm;
//    Socket socket;
    byte gateID;
    
    int openCmd_ID;
    byte[] msgCode = new byte[]{(byte)MsgCode.Open.ordinal()}; //length:1
    
    byte[] integerArr = null;
    private int resendCount = 0;
    byte[] messageArr = new byte[9]; // code(1 byte), cmd ID(4 bytes), delay(4 bytes)
    
    /**
     * Open Command Sender Task constructor.
     * 
     * @param managerForm manager program GUI
     * @param gateID ID of the gate being simulated
     * @param openCmd_ID ID of the open command to send*
     * @param passingDelay car passing delay in milliseconds
     */
    public SendGateOpenTask(ControlGUI managerForm, byte gateID, int openCmd_ID, int passingDelay) {
        
        this.mainForm = managerForm;
        this.gateID = gateID;
        this.openCmd_ID = openCmd_ID;

        messageArr[0] = (byte)Open.ordinal();
        
        integerArr = ByteBuffer.allocate(4).putInt(openCmd_ID).array();
        System.arraycopy(integerArr, 0, messageArr, 1, 4);
        
        integerArr = ByteBuffer.allocate(4).putInt(passingDelay).array();
        System.arraycopy(integerArr, 0, messageArr, 5, 4);
    }

    /**
     * send an open command using socket's output stream.
     */
    @Override
    public synchronized void run() {
        
        try 
        {
            synchronized(mainForm.getSocketMutex()[GateBar.ordinal()][gateID]) 
            {
//                if (! isConnected(gateMan.getSocket())) 
                if (! IDevice.isConnected(mainForm.getDeviceManagers()[GateBar.ordinal()][gateID], GateBar, gateID))
                {
                    System.out.println("before opentask");
                    mainForm.getSocketMutex()[GateBar.ordinal()][gateID].wait();
                    System.out.println("after opentask");
                }
            }
            ++resendCount;
//            if (connectionType[GateBar.ordinal()][gateID] == OSP_enums.ConnectionType.RS_232.ordinal()) {
////                IDevice.ISerial gateMan = (IDevice.ISerial) mainForm.getDeviceManagers()[GateBar.ordinal()][gateID];
//                if (deviceType[GateBar.ordinal()][gateID] == NaraBar.ordinal()) {
//                    NaraBarMan gateMan = (NaraBarMan) mainForm.getDeviceManagers()[GateBar.ordinal()][gateID];
//                    gateMan.getNaraBarMessages().add(new NaraMsgItem(Nara_MsgType.GateUp));
////                    gateMan.getCommPort().getOutputStream().write(messageArr);
//                }
//            } else
            {
                IDevice.ISocket gateMan = (IDevice.ISocket) mainForm.getDeviceManagers()[GateBar.ordinal()][gateID];
                gateMan.getSocket().getOutputStream().write(messageArr);
            }
        } catch (IOException e) {
            mainForm.getDeviceManagers()[GateBar.ordinal()][gateID].finishConnection(null, 
                    "writing open cmd #" + openCmd_ID + " to bar#" + gateID, gateID);
        } catch (InterruptedException ex) {
            logParkingException(Level.SEVERE, ex, "gate #" + gateID + " open sender wait socket conn'");
        }    
    }

    /**
     * supplies this open command resent count.
     * used to check the system performance in case of network error/delay.
     * 
     * @return the resendCount
     */
    public int getResendCount() {
        if (resendCount - 1 < 0) {
            JOptionPane.showMessageDialog(null, "negative resend count");
        }
        return resendCount - 1;  // first send shouldn't be counted
    }
}
