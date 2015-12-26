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
package com.osparking.osparking.device.LEDnotice;

import gnu.io.SerialPortEvent;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Open Source Parking Inc.
 */
public class RS_232_ManagerTest {
    
    public RS_232_ManagerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testSerialEvent() {
        System.out.println("serialEvent");
        SerialPortEvent e = null;
        RS_232_Manager instance = null;
        instance.serialEvent(e);
        fail("The test case is a prototype.");
    }

    @Test
    public void testReadDeliveredMessage() throws Exception {
        System.out.println("readDeliveredMessage");
        InputStream inStream = null;
        LEDnotice_enums.LED_MsgType expResult = null;
        LEDnotice_enums.LED_MsgType result = RS_232_Manager.readDeliveredMessage(inStream);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
