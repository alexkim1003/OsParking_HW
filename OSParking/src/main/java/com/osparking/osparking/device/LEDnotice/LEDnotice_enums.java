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
    
    public enum DisplayArea {
        TOP_ROW(1), 
        BOTTOM_ROW(2), 
        WHOLE_AREA(3);
        
        int value;
        DisplayArea(int value) {
            this.value = value;
        }
        
        int getValue() {
            return value;
        }
    }    

    public enum EffectType {
        NONE (0x3f), // 효과 없음
        
        FLOW_RtoL (0x40), // 왼쪽 흐름
        FLOW_LtoR (0x41), // left to right flow
        FLOW_UP (0x42), // upward flow
        FLOW_DOWN (0x43), // downward flow
        
        STOP_MOVING (0x44), // stand still
        BLINKING (0x45), // blinking effect
        REVERSE (0x46), // reverse mode
        
        FLASHING (0x47), // flashing effect
        BLIND (0x48), // 블라인드 커튼 방식
        RASER (0x49), // 레이저 빔 방식
        CENTERING (0x4a), // 중앙으로 이동
        EXTEND (0x4b), // 펼침
        BLINK_RtoL_RED (0x4c), // 좌흐름 적색 깜박임
        BLINK_LtoR_RED (0x4d), // 우흐름 적색 깜박임
        BLINK_RtoL_GREEN (0x4e), // 좌흐름 녹색 깜박임
        BLINK_LtoR_GREEN (0x4f), // 우흐름 녹색 깜박임
        
        CIRCLING (0x50), // 회전
        OPENING_L_R (0x51), // 좌우열기
        CLOSING_L_R (0x52), // 좌우닫기
        OPENING_UP_DN (0x53), // 상하열기
        CLOSING_UP_DN (0x54), // 상하닫기
        
        MODULE_MOVE (0x55), // 모듈별 이동
        MODULE_CIRCLE (0x56), // 모듈별 회전
        COLOR_UP_DN (0x57), // 상하색 분리
        COLOR_L_R (0x58), // 좌우색 분리
        MOVE_EDEG (0x59), // 테두리 이동
        
        EXPAND (0x5a), // 확대
        EXPAND_VERTICAL (0x5b), // 세로 확대
        EXPAND_HORIZONTAL (0x5c), // 가로 확대
        
        BLINK_ROW (0x5d), // 줄 깜박임
        VERTICAL_ADD (0x5e), // 가로 쌓기
        SPRINKLE (0x5f); // 흩뿌리기
        
        private int value;

        EffectType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }       
    }    
    
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
