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
package com.osparking.global.names;

import com.osparking.global.Globals;
import static com.osparking.global.Globals.language;
import static com.osparking.global.Globals.ourLang;
import static com.osparking.global.names.ControlEnums.ToolTipContent.*;
import static com.osparking.global.names.DB_Access.parkingLotLocale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.osparking.global.names.DB_Access.pwStrengthLevel;
import com.osparking.global.names.OSP_enums.PWStrengthLevel;

public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PW_PATTERN_4DIGITS = "((?=\\d{4}).{4,4})";
    private static final String PW_PATTERN_6ALNUM = "((?=.*[a-zA-Z])(?=.*\\d).{6,40})";
        // one Upper, one Lower, one digit, one special char, 
    private static final String PW_PATTERN_COMPLEX = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%&*()]).{8,40})";

    public PasswordValidator() {
        if (pwStrengthLevel == PWStrengthLevel.FourDigit.ordinal()) 
        {
            pattern = Pattern.compile(PW_PATTERN_4DIGITS);
        } else if (pwStrengthLevel == PWStrengthLevel.SixDigit.ordinal()) 
        {
            pattern = Pattern.compile(PW_PATTERN_6ALNUM);
        } else 
        {
            pattern = Pattern.compile(PW_PATTERN_COMPLEX);
        }
    }

    public boolean isInValidForm(final String password) {
            matcher = pattern.matcher(password);
            return matcher.matches();
    }
    
    public String getPasswordTooltip() 
    {
        if (pwStrengthLevel == PWStrengthLevel.FourDigit.ordinal()) 
        {
            return PW_FOURDIGIT_TOOLTIP.getContent(language);
        } 
        else if (pwStrengthLevel == PWStrengthLevel.SixDigit.ordinal()) 
        {
            return PW_SIXDIGIT_TOOLTIP.getContent(language);
        } 
        else 
        {
            return PW_COMPLEX_TOOLTIP.getContent(language);
        }   
    }
    
    
    public String getWrongPWFormatMsg(short level) 
    {
        if (level == PWStrengthLevel.FourDigit.ordinal()) 
        {
            return getTextFor(ControlEnums.DialogMSGTypes.PW_FOURDIGIT_DIALOG);
        } 
        else if (level == PWStrengthLevel.SixDigit.ordinal()) 
        {
            return getTextFor(ControlEnums.DialogMSGTypes.PW_SIXDIGIT_DIALOG);
        } 
        else 
        {
            return getTextFor(ControlEnums.DialogMSGTypes.PW_COMPLEX_DIALOG);
        }   
    }
    
    
    private String getTextFor(ControlEnums.DialogMSGTypes msgType){
        StringBuilder sBuilder = new StringBuilder();
        
        switch (parkingLotLocale.getLanguage()) {
            case "ko":
                sBuilder.append("* 다음 조건을 만족하는 비밀번호를 입력하세요.\n");
                sBuilder.append("\n");
                break;
            default :
                sBuilder.append("* Enter password satisfying below conditions:\n");
                sBuilder.append("\n");
                break;
        }
        switch (msgType) {
            case PW_FOURDIGIT_DIALOG:
            switch (parkingLotLocale.getLanguage()) {
                case "ko":
                    sBuilder.append("  - 숫자(0~9) 네 자리로 구성\n");
                    sBuilder.append("\n");
                    sBuilder.append("(예) 0123");
                    break;
                default:
                    sBuilder.append("  - four digit number (0~9)\n");
                    sBuilder.append("\n");
                    sBuilder.append("(e.g., 0123)");
                    break;
            }
            break;
            
            case PW_SIXDIGIT_DIALOG:
            switch (parkingLotLocale.getLanguage()) {
                case "ko":
                    sBuilder.append("  - 6 ~ 40자로 구성\n");
                    sBuilder.append("  - 영문 문자(a-z,A~Z)를 한 글자 이상 포함\n");
                    sBuilder.append("  - 숫자(0-9)를 한 글자 이상 포함\n");
                    sBuilder.append("\n");
                    sBuilder.append(" (예) pti34z");
                    break;
                default:
                    sBuilder.append("  - consists of 6 to 40 characters\n");
                    sBuilder.append("  - contains at least one English alphabet (a-z,A~Z)\n");
                    sBuilder.append("  - includes more than one number key(0-9)\n");
                    sBuilder.append("\n");
                    sBuilder.append("(e.g., pti34z)");
                    break;
            }
            break;
            
            case PW_COMPLEX_DIALOG:
            switch (parkingLotLocale.getLanguage()) {
                case "ko":
                    sBuilder.append("  - 8 ~ 40자로 구성\n");
                    sBuilder.append("  - 영문 소문자(a~z) 한 글자 이상 포함\n");
                    sBuilder.append("  - 영문 대문자(A~Z) 한 글자 이상 포함\n");
                    sBuilder.append("  - 숫자(0-9) 한 자 이상 포함\n");
                    sBuilder.append("  - 다음 특수 문자 중 한 글자 이상 포함\n");
                    sBuilder.append("     !  @  #  $  %  &  *  (  )\n");
                    sBuilder.append("\n");
                    sBuilder.append(" (예) abM56!xy");
                    break;
                default:
                    sBuilder.append("  - consists of 8 to 40 characters\n");
                    sBuilder.append("  - contains at least one lower case alphabet (a-z)\n");
                    sBuilder.append("  - contains at least one upper case alphabet (A-Z)\n");
                    sBuilder.append("  - includes more than one number key(0-9)\n");
                    sBuilder.append("  - includes at least one special character shown below\n");
                    sBuilder.append("     !  @  #  $  %  &  *  (  )\n");
                    sBuilder.append("\n");
                    sBuilder.append("(e.g., abM56!xy)");
                    break;
            }
            break;
        default:
            break;
        }
        return sBuilder.toString();
    }
}
