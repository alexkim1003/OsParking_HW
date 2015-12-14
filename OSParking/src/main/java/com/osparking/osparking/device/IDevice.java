/*
 * Copyright (C) 2015 Open Source Parking Inc.
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

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.net.Socket;

/**
 *
 * @author Open Source Parking Inc.
 */
public class IDevice {
    
    public static interface IManager {
        void finishConnection(Exception e, String description, byte deviceNo);
        void setPriority(int newPriority);
        void start();
        public void stopOperation(String reason);
        boolean isNeverConnected();
    }
    
    public static interface ISocket {
        public void setSocket(Socket eBoardSocket);
        Socket getSocket();
    }
    
    public static interface ISerial {
        public void setSerialPort(SerialPort serialPort);
        public SerialPort getSerialPort();    
        public CommPortIdentifier getPortIdentifier();
        public CommPort getCommPort();
        public void setCommPort(CommPort open);
        public int getBaudRate();
    }    
}
