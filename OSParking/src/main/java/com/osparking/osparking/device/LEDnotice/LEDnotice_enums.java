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

/**
 *
 * @author Open Source Parking Inc.
 */
public class LEDnotice_enums {
    public enum MsgType {
        DEL_TEXT_ONE (0x31), // delete individual text
        DEL_TEXT_ALL (0x33),  // delete whole text
        INTR_TXT_ON (0x3c),  // set interrupt text on
        INTR_TXT_OFF (0x3d),  // set interrupt text off
        
        SET_COMM_SPD (0x40), // set communication speed
        SET_ID (0x42), // set device ID
        GET_ID (0x43),  // get device ID
        GET_VERSION (0x44), // get version string
        
        SAVE_FLASH (0x53), // store into flash memory
        SAVE_TEXT (0x71), // store display text
        SAVE_INTR (0x75), // store interrupt text
        SAVE_RAM (0x76), // store RAM text 
        
        SET_MONITOR(0X82); // set monitor size
        
        private int value;

        MsgType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }       
    }
}
