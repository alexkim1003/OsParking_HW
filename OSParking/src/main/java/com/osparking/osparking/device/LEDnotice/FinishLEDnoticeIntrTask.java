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

import static com.osparking.global.Globals.isConnected;
import static com.osparking.global.Globals.logParkingException;
import com.osparking.global.names.OSP_enums;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import com.osparking.osparking.ControlGUI;
import com.osparking.osparking.device.LEDnotice.LEDnoticeManager.MsgItem;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.GROUP_TYPE.INTR_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.DEL_GROUP;
import static com.osparking.osparking.device.LEDnotice.LEDnotice_enums.MsgType.INTR_TXT_OFF;
import java.util.TimerTask;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Open Source Parking Inc.
 */
public class FinishLEDnoticeIntrTask extends TimerTask {
    ControlGUI mainGUI;
    byte deviceNo;
    OSP_enums.EBD_Row rowNo;
    private int sendCount = 0;

    public FinishLEDnoticeIntrTask(ControlGUI mainGUI, int deviceNo, OSP_enums.EBD_Row row)
    {
        this.mainGUI = mainGUI;
        this.deviceNo = (byte) deviceNo;
        this.rowNo = row;
    }

    @Override
    public synchronized void run() {
        try 
        {
            synchronized(mainGUI.getSocketMutex()[E_Board.ordinal()][deviceNo]) 
            {
                if (! isConnected(mainGUI.getDeviceManagers()[E_Board.ordinal()][deviceNo].getSocket())) 
                {
        System.out.println("point 1");
                    mainGUI.getSocketMutex()[E_Board.ordinal()][deviceNo].wait();
                }
            }
            ++sendCount;
        System.out.println("point 2");
            
            LEDnoticeManager manager 
                    = (LEDnoticeManager) mainGUI.getDeviceManagers()[E_Board.ordinal()][deviceNo];
        System.out.println("point 3");
            
            manager.getLedNoticeMessages().add(new MsgItem(INTR_TXT_OFF, 
                    manager.ledNoticeProtocol.intOff()));
        System.out.println("point 4");

            manager.getLedNoticeMessages().add(new MsgItem(DEL_GROUP, 
                    manager.ledNoticeProtocol.delGroup(INTR_GROUP)));
        System.out.println("point 5");
            
//        } catch (IOException e) {
//            mainGUI.getDeviceManagers()[E_Board.ordinal()][deviceNo].finishConnection(e, 
//                    "LED notice interrupt finish message sent", deviceNo);
        } catch (InterruptedException ex) {
            logParkingException(Level.SEVERE, ex, "LEDnotice Board #" + deviceNo + " sender wait socket conn'");
        }          
    }
    
    /**
     * supplies this open command resent count.
     * used to check the system performance in case of network error/delay.
     * 
     * @return the sendCount
     */
    public int getResendCount() {
        if (sendCount - 1 < 0) {
            JOptionPane.showMessageDialog(null, "negative resend count");
        }        
        return sendCount - 1;  // first send shouldn't be counted
    }        
}
