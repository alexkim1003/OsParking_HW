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

import static com.osparking.global.Globals.language;
import static com.osparking.global.names.ControlEnums.Languages.*;

/**
 *
 * @author Open Source Parking Inc.
 */
public class ControlEnums {
  
    public enum Languages{
        KOREAN, ENGLISH
    }
    
    public enum TitleTypes {
        LOGIN_FRAME_TITLE, ATTLIST_FRAME_TITLE, AFFILI_BUILD_FRAME_TITLE, 
        VEHICLESFORM_FRAME_TITLE, DRIVER_SELECTION_FRAME_TITLE, DRIVER_LIST_FRAME_TITLE,
        CAR_ARRIVALS_FRAME_TITLE, SEARCH_CRITERIA_PANEL_TITLE, ARRIVAL_PROPERTIES_PANEL_TITLE,
        ARRIVAL_TIME_PANEL_TITLE, VEHICLE_ARIIVAL_DETAILS_PANEL_TITLE, VEHICLE_ARRIVAL_LIST_PANEL_TITLE,
        FULL_SIZE_IMAGE_FRAME_TITLE, DISALLOWED_CAR_FRAME_TITLE, VISITING_CAR_FRAME_TITLE,
        LOGIN_RECORD_FRAME_TITLE, RUN_RECORD_FRAME_TITLE, GATE_PANEL_TITLE, SYSTEM_SETTINGS_FRAME_TITLE,
        E_BOARD_SETTINGS_FRAME_TITLE,
        DEFAULT_PANEL_TITLE, VEHICLE_PANEL_TITLE, TOP_PANEL_TITLE, BOTTOM_PANEL_TITLE,
        
        ODS_HELP_DIALOG_FRAME_TITLE,
    }
    
//    public enum LabelTypesOld {
//        LOGIN_ID_LABEL, PW_LABEL, REQUIRED_LABEL, GUIDELINE_LABEL, REQUIRED1_LABEL, REQUIRED2_LABEL,
//        USER_ID_LABEL, 
//        
//        ADMIN_LABEL, 
        
//        NAME_LABEL, CELL_PHONE_LABEL, PHONE_LABEL, EMAIL_LABEL, 
//        CHANGE_PW_LABEL, NEW_PW_LABLE, REPEAT_PW_LABEL, MY_PW_LABEL, CREATION_LABEL,
//        AFFILIATION_LIST_LABEL, LOWER_LIST_LABEL, BUILDING_LIST_LABEL, ROOM_LIST_LABEL, 
//        HELP_AFFIL_LABEL, HELP_BUILDING_LABEL, FORM_MODE_LABEL, SEARCH_MODE_LABEL, CREATE_MODE_LABEL, 
//        MODIFY_MODE_LABEL, SEARCH_LABEL, ORDER_LABEL, CAR_TAG_LABEL, MODI_DATE_LABEL, 
//        NOTIFICATION_LABEL, EXACT_COMP_LABEL, PARK_ALLOWED_LABEL, REASON_LABEL, OTHER_INFO_LABEL,  
//        REGI_DATE_LABEL, COUNT_LABEL, REQUIRED3_LABEL, GATE_NAME_LABEL, ATTENDANT_LABEL, BAR_OP_LABEL, 
//        ARRIVAL_TIME_LABEL, RECOGNIZED_LABEL, REGISTERED_LABEL,
//        AFFILIATION_LABEL, BUILDING_LABEL, ROOM_LABEL,
//        VISIT_PURPOSE_LABEL, VISIT_WHERE_LABEL, FILE_SIZE_LABEL, RECORD_COUNT_LABEL,
//        LAST_1HOUR_LABEL, LAST_24HOURS_LABEL, DURATION_SET_LABEL,
//        NON_REGI_TAG1_LABEL, NON_REGI_TAG2_LABEL, OPEN_LABEL,
//        STATUS_LABEL, GATE_LABEL, CAMERA_LABEL, EBOARD_LABEL, GATE_BAR_LABEL, 
//        DISALLOWED_LABEL, STATISTICS_SIZE_LABEL, RECORD_PASSING_LABEL,
//        PASSWORD_LEVEL_LABEL, LOGGING_LEVEL_LABEL, LANGUAGE_LABEL, MAX_LINE_LABEL,
//        GATE_NUM_LABEL, IMG_KEEP_LABEL, VEHICLE_IMG_SIZE_LABEL, VEHICLE_IMG_WIDTH_LABEL,
//        VEHICLE_IMG_HEIGHT_LABEL, CAMERA_IP_ADDRESS_LABEL, GATE_BAR_IP_ADDRESS_LABEL,
//        E_BOARD_IP_ADDRESS_LABEL, PORT_LABEL, ELECTRONIC_DISPLAY_LABEL, CYCLE_LABEL,
//        BLINGKING_LABEL, FLOWING_LABEL, 
//        CONTENT_TYPE_LABEL, MSG_LABEL, COLOR_LABEL, 
//        FONT_LABEL, EFFECT_LABEL, SEARCH_PERIOD_LABEL, 
//        SECONDS_LABEL, VEHICLES_LABEL, LOT_NAME_LABEL,
//        LOWER_LABEL,  
//    }
    
    public enum LabelContent {
        LOGIN_ID_LABEL("아이디", "Login ID"), 
        PW_LABEL("비밀번호",  "Password"), 
        REQUIRED_LABEL("정보수정 조건",  "Data Condition"), 
        GUIDELINE_LABEL("정보 입력 지침",  "Guideline"), 
        REQUIRED1_LABEL(": 필수",  ": Required"), 
        REQUIRED2_LABEL(": 최소 1",  ": Choose 1"),
        USER_ID_LABEL("사용자 ID: ",  "My ID: "),
        ADMIN_LABEL("매니저",  "Manager"),
        NAME_LABEL("이름",  "Name"),
        CELL_PHONE_LABEL("휴대전화",  "Cell Phone"),
        PHONE_LABEL("유선전화",  "Phone"),
        EMAIL_LABEL("이메일",  "E-Mail"),
        CHANGE_PW_LABEL("비밀번호 변경",  "Change Password"),
        NEW_PW_LABLE("새 비밀번호",  "New Password"),
        REPEAT_PW_LABEL("비밀번호 확인",  "Repeat Password"),
        MY_PW_LABEL("현재 비밀번호",  "My Password"),
        CREATION_LABEL("자료 생성일",  "Creation Date"),
        AFFILIATION_LIST_LABEL("상위 소속 목록",  "Higher Affiliations"),
        LOWER_LIST_LABEL("소속 부서 목록",  "Lower Affiliations"),
        BUILDING_LIST_LABEL("건물(동) 목록",  "Building Numbers"), 
        ROOM_LIST_LABEL("호실 목록",  "Rooms of Building"), 
        HELP_AFFIL_LABEL("소속 명칭 ods 파일 형식",  
                "Affiliation name list ods file content"), 
        HELP_BUILDING_LABEL("건물 호실 ods 파일 형식",  
                "Building room number list ods file content"), 
        FORM_MODE_LABEL("작업모드 : ",  "Form Mode :"), 
        SEARCH_MODE_LABEL("차량 검색",  "Searching"), 
        CREATE_MODE_LABEL("차량 등록",  "Car Creation"), 
        MODIFY_MODE_LABEL("차량 변경",  "Modification"), 
        SEARCH_LABEL("검색 값",  "Search Key"), 
        ORDER_LABEL("순번",  "List#"), 
        CAR_TAG_LABEL("차량번호",  "Tag No."), 
        MODI_DATE_LABEL("최근변경",  "Modi' Date"),
        NOTIFICATION_LABEL("세대통보",  "Notification"),
        EXACT_COMP_LABEL ("전체비교",  "Exact Comp'"),
        PARK_ALLOWED_LABEL("주차허용",  "Park Allowed"),
        REASON_LABEL ("불허사유",  "Reason"),
        OTHER_INFO_LABEL  ("기타정보",  "Other Info'"),
        REGI_DATE_LABEL ("최초등록",  "Regi' Date"),
        COUNT_LABEL ("차량 대수",  "Vehicle Count"),
        REQUIRED3_LABEL ("*: 필수 항목",  "*: required field"),
        GATE_NAME_LABEL ("입구 이름",  "Gate Name"),
        ATTENDANT_LABEL ("관리원",  "Attendant"),
        BAR_OP_LABEL ("차단기",  "Bar Operation"),
        ARRIVAL_TIME_LABEL("도착시간",  "Arrival Time"),       
        RECOGNIZED_LABEL ("인식된 차량번호",  "Tag Recognized"),
        REGISTERED_LABEL("등록된 차량번호",  "Tag Registered"),
        AFFILIATION_LABEL ("소속부서",  "Affiliations"),
        BUILDING_LABEL ("소속건물",  "Building"),
        ROOM_LABEL("호수",  "Room No."),
        VISIT_PURPOSE_LABEL("방문목적",  "Purpose of Visit"),
        VISIT_WHERE_LABEL ("방문 하는 곳",  "Where to go"),
        FILE_SIZE_LABEL ("파일 크기",  "File Size"),
        RECORD_COUNT_LABEL("차량 합계",  "Record Count"),
        LAST_1HOUR_LABEL ("최근 1시간",  "Last 1 hour"),
        LAST_24HOURS_LABEL ("최근 24시간",  "Last 24 hour"),
        DURATION_SET_LABEL("기간지정",  "Duration"),
        NON_REGI_TAG1_LABEL("방문세대",  "Visit Households"),
        NON_REGI_TAG2_LABEL("방문부서",  "Visit Departments"),
        OPEN_LABEL("차단기 개방",  "Open Gate"),
        STATUS_LABEL ("상황 게시",  "Recent Events"),
        GATE_LABEL ("입구",  "Gate"),
        CAMERA_LABEL ("카메라",  "Camera"),
        EBOARD_LABEL ("전광판",  "E-Board"),
        GATE_BAR_LABEL ("차단기",  "G-Bar"),
        DISALLOWED_LABEL("일시적으로 허용되지 않은 차량입니다.",  
                "Car Temporarily Not Permitted"),
        STATISTICS_SIZE_LABEL("통계 모집단 크기",  "Statistics Population Size"),
        RECORD_PASSING_LABEL("통과 시간 기록",  "Record Passing Delay"),
        PASSWORD_LEVEL_LABEL("비밀번호 난이도",  "Password Complexity Level"),
        LOGGING_LEVEL_LABEL("일반연산 로깅 레벨",  "General Operation Logging Level"),
        LANGUAGE_LABEL("표시 언어",  "Language Chooser"),
        MAX_LINE_LABEL("메인 상황게시 최대 행수",  "Recent Event Line Max"),
        GATE_NUM_LABEL("입구 수",  "Number of Gates"),
        IMG_KEEP_LABEL("사진 저장 기간",  "Image Keeping Duration"),
        VEHICLE_IMG_SIZE_LABEL("차량 사진 크기",  "Vehicle Image Size"),
        VEHICLE_IMG_WIDTH_LABEL("너비",  "Width"),
        VEHICLE_IMG_HEIGHT_LABEL("높이",  "Height"),
        CAMERA_IP_ADDRESS_LABEL("카메라 IP 주소",  "Camera IP Address"),
        GATE_BAR_IP_ADDRESS_LABEL("차단기 IP 주소",  "GateBar IP Address"),
        E_BOARD_IP_ADDRESS_LABEL("전광판 IP 주소",  "E-Board IP Address"),
        PORT_LABEL("포트",  "Port No"),
        ELECTRONIC_DISPLAY_LABEL("전광판 설정",  "Electronic Display Board"),
        CYCLE_LABEL("주기",  "Cycle"),
        BLINGKING_LABEL("깜빡임",  "Blinking"),
        FLOWING_LABEL("흐름",  "Flowing"),
        CONTENT_TYPE_LABEL ("표시 유형",  "Content Type"),
        MSG_LABEL ("문자열",  "Message"),
        COLOR_LABEL ("색상",  "Color"),
        FONT_LABEL ("폰트",  "Font"),
        EFFECT_LABEL ("효과",  "Effect"),
        SEARCH_PERIOD_LABEL("검색 기간",  "Search Period"),
        SECONDS_LABEL ("초",  "seconds"),
        VEHICLES_LABEL (" 대",  " vehicles"),
        LOT_NAME_LABEL("주차장 이름",  "Parking Lot Name"),
        LOWER_LABEL(" 부서 목록", "Lower affiliations of");
        
        LabelContent(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent(Languages langIDX) {
//                return contents[langIDX.ordinal()];
                return contents[language.ordinal()];
        }
        
    }
    
    public enum ButtonTypes {
        LOGIN_BTN, CLOSE_BTN, SAVE_BTN, CREATE_BTN, 
        DELETE_BTN, MODIFY_BTN, CANCEL_BTN, SEARCH_BTN, 
        SAVE_AS_BTN, 
        
//        CHECK_BTN,
        CLEAR_BTN, DELETE_ALL_BTN, READ_ODS_BTN, SAVE_ODS_BTN,
        CREATE_NO_SHORT_BTN, MODIFY_NO_SHORT_BTN, DELETE_NO_SHORT_BTN, 
        DELETE_ALL_NO_SHORT_BTN, READ_ODS_NO_SHORT_BTN, 
        SAVE_NO_SHORT_BTN, CANCEL_NO_SHORT_BTN, OWNER_BTN, SELECT_BTN, 
        MANAGE_BTN, FIX_IT_BTN, 
        ARRIVALS_BTN, VEHICLES_BTN, USERS_BTN, CAR_ARRIVAL_BTN, 
        STATISTICS_BTN,
        GATE_BAR_OPEN_BTN, GATE_BAR_CLOSE_BTN, E_BOARD_SETTINGS_BTN,
    }
    
    public enum ButtonContent {  
        CHECK_BTN("중복검색", "Check");
        
        ButtonContent(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent(Languages langIDX) {
                return contents[langIDX.ordinal()];
        }
    }
    
    public enum ToolTipContent {
        LOGIN_BTN_TOOLTIP("관리자 로그인", "Click to Login"),
        CLOSE_BTN_TOOLTIP("현재 창을 닫기", "Close Window"),
        SAVE_AS_TOOLTIP("파일로 저장", "Save as file"),
        ID_INPUT_TOOLTIP("영문숫자 최대 20자!", "Up to 20 alphanumeric characters"),
        NAME_INPUT_TOOLTIP("두 자 이상 입력하세요", "Please enter at least two characters"),
        PW_INPUT_TOOTLTIP("관리자 비밀번호 입력!", "Enter User Password!"),
        CELL_INPUT_TOOLTIP("숫자 11자리 입력하세요", "Enter the 11-digit number"),
        PHONE_INPUT_TOOLTIP("숫자 4자리 이상 입력하세요", "Please enter at least a four-digit number"),
        REPEAT_PW_INPUT_TOOLTIP("한번 더 입력하세요", "Please enter it again."),
        SEARCH_INPUT_TOOLTIP("속성 값 입력", "Enter Search Key."),
        PW_FOURDIGIT_TOOLTIP("네 자리 숫자 입력 (자세한 내용: ? 클릭)", 
                "Enter a 4 digit number(for details click '?')"),
        PW_SIXDIGIT_TOOLTIP("6 자리 이상 영자 및 숫자 입력 (자세한 내용: ? 클릭)", 
                "Enter 6 or more digits of alpha-numeric(for details click '?'"),
        PW_COMPLEX_TOOLTIP("8 자리 이상 영자, 숫자 및 특수문자 입력 (자세한 내용: ? 클릭)", 
                "Enter 8 or more digits of alphabet, number, and special character(for details click '?')"),
        CAR_TAG_INPUT_TOOLTIP("챠량번호로 검색", "Search CarTag"),
        DRIVER_INPUT_TOOLTIP("운전자로 검색", "Search Driver"),
        AFFILIATION_TOOLTIP("소속으로 검색", "Search Affiliation"),
        BUILDING_TOOLTIP("건물로 검색", "Search Building"),
        OTHER_TOOLTIP("기타정보로 검색", "Search Other Info"),
        CELL_PHONE_INPUT_TOOLTIP("휴대전화로 검색", "Search Cell Phone"),
        LANDLINE_INPUT_TOOLTIP("유선전화로 검색", "Search LandLine"),
        HIGHER_TOOLTIP("상위 소속으로 검색", "Search Higher Affiliation"),
        LOWER_TOOLTIP("하위 소속로 검색", "Search Lower Affiliation"),
        UNIT_TOOLTIP("호수로 검색", "Search Unit"),
        CLEAR_BTN_TOOLTIP("영역 초기화", "Clears Criteria"),
        FIX_IT_BTN_TOOLTIP("선택된 라디오 버튼을 적용", "Remember Radio Button Selection"),
        CAR_TAG_TF_TOOLTIP("입력 후 [엔터]", "Type and [Enter]"),
        INC_BTN_TOOLTIP("0,1 씩 증가", "Inc by 0.1"),
        DEC_BTN_TOOLTIP("0,1 씩 감소", "Dec by 0.1"),
        SETTINGS_SAVE_TOOLTIP("변경된 내용 저장하기", "Save your changes"),
        SETTINGS_CANCEL_TOOLTIP("변경된 내용을 취소하기", "Desert changes made");
        
        ToolTipContent(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent(Languages langIDX) {
                return contents[langIDX.ordinal()];
        }
    }
    
    public enum TableTypes {
        USER_ID_HEADER, NAME_HEADER, MANAGER_HEADER, CELL_PHONE_HEADER, PHONE_HEADER, EMAIL_HEADER, MODIFIED_HEADER,
        ORDER_HEADER, HIGHER_HEADER, LOWER_HEADER, BUILDING_HEADER, ROOM_HEADER,  
        CAR_TAG_HEADER, DRIVER_HEADER, LOW_HIGH_HEADER, ROOM_BUILD_HEADER, OTHER_INFO_HEDER, REASON_HEADER,
        ARRIVAL_TIME_HEADER, LOGIN_TIME_HEADER, LOGOUT_TIME_HEADER, DURATION_HEADER, SHUTDOWN_HEADER, START_HEADER,
    }
    
    public enum ATTLIST_ComboBoxTypes {
        NAME, ID
    }
    
    public enum CheckBoxTypes {
        OPEN_CHECKBOX
    }
    
    public enum DialogMSGTypes {
        ID_CHECK_DIALOG, EMAIL_CHECK_DIALOG, ATT_NAME_CHECK_DIALOG, ATT_CELL_CHECK_DIALOG, PHONE_CHECK_DIALOG, CELL_PHONE_CHECK_DIALOG, 
        PASSWORD_CHECK_DIALOG, REPEAT_PW_CHECK_ERROR, ADMIN_PW_CHECK_DIALOG, DELETE_FAIL_DAILOG, ID_LENGTH_CHECK_DIALOG, 
        ID_FIRST_CHAR_CHECK_DIALOG, ID_CHAR_CHECK_DIALOG, ID_END_CHAR_CHECK_DIALOG, 
        AFFILIATION_DELETE_ALL_DAILOG, AFFILIATION_DELETE_ALL_RESULT_DAILOG, 
        BUILDING_DELETE_ALL_DAILOG, BUILDING_DELETE_ALL_RESULT_DAILOG,
        CAR_TAG_DIALOG, DRIVER_DIALOG, VEHICLE_CREATION_FAIL_DIALOG, VEHICLE_CREATE_CANCEL_DIALOG,
        VEHICLE_MODIFY_CANCEL_DAILOG, VEHICLE_DELETE_ALL_DAILOG, VEHICLE_DELETE_ALL_RESULT_DAILOG,
        VEHICLE_SAVE_ODS_FAIL_DIALOG,
        DRIVER_CREATRION_FAIL_DIALOG,  DRIVER_DELETE_ALL_RESULT_DAILOG,
        DRIVER_MODIFY_FAIL_DAILOG, DRIVER_CREATE_CANCEL_DIALOG, DRIVER_MODIFY_CANCEL_DAILOG,
        
        DRIVER_NAME_CHECK_DIALOG,
        DRIVER_CELL_CHECK_DIALOG,
        DRIVER_CREATE_CHECK_CELL_DIALOG,
        DRIVER_CREATE_FAIL_DIALOG,  
        
        DRIVER_CLOSE_FORM_DIALOG, DATE_INPUT_CHECK_DIALOG, DATE_INPUT_ERROR_DIALOG,
         
        STOP_RUNNING_DIALOG, SHUT_DOWN_CONFIRM_DIALOG,
        LOGALWAYS_DAILOG, SETTINGSCHANGE_DAILOG, EBDSETTINGSCHANGE_DIALOG,
        LANGUAGE_USE_DIALOG, PHOTO_SIZE_INPUT_ERROR_DIALOG,
        CHANGE_GATE_DAILOG, FAIL_SAVE_SETTINGS_DIALOG, SAVE_SETTINGS_DIALOG,
        INPUT_ERROR_DAILOG, IP_FORMAT_ERROR_DAILOG,
        SAVE_OR_CANEL_DIALOG,
        E_BOARD_SAVE_FAIL_DIALOG, 
        E_BOARD_NO_INPUT_MSG_DIALOG, CHANGE_DIALOG, CURRENT_DIALOG, MODIFY_DIALOG,      
        SAVE_DIALOG, LOGIN_FAIL_DIALOG, 
        INPUT_ID_DIALOG, INPUT_PW_DIALOG, L2_INPUT_DIALOG, UNIT_INPUTDIALOG,
        
        
        
        
       
        
        
        AFFILIATION_DELETE_DIALOG, AFFILIATION_DELETE_RESULT_DIALOG, 
        AFFILIATION_ODS_READ_DIALOG, AFFILIATION_MODIFY_DIALOG,
        
        BUILDING_ODS_READ_DIALOG, BUILDING_ODS_READ_FAIL_DIALOG,
        BUILDING_DELETE_RESULT_DIALOG, BUILDING_DELETE_DIALOG, BUILDING_MODIFY_DIALOG,
        
        CREATION_FAIL_DIALOG, CHECK_AFFILIATION_ODS_DIALOG, CHECK_BUILDING_ODS_DIALOG, CREATION_SUCCESS_DIALOG,
        
        DELETE_DIALOG, DELETE_SUCCESS_DIALOG, 
        DELETE_FAIL1_DAILOG, DELETE_FAIL2_DAILOG, DELETE_FAIL3_DAILOG, DELETE_CARS_DIALOG, DELETE_A_CAR_DIALOG, 
        DELETE_ALL_DAILOG, DELETE_ONE_DIALOG, DELETE_MORE_DIALOG,
        
        EMAIL_DUP_TURE_DIALOG, EMAIL_DUP_FALSE_DIALOG, EMAIL_CHECK_FAIL_DIALOG, 
        
        ID_DUP_CHECK_DIALOG, ID_CHECK_GOOD_DIALOG, 
        
        LOWER_DELETE_DIALOG, LOWER_DELETE_RESULT_DIALOG, LOWER_MODIFY_DIALOG, 
        LOGOUT_DIAILG, LOGOUT_CONFIRM_DIALOG,
        
        MODIFY_DAILOG,
        
        SAVE_AS_FAIL1_DIALOG, SAVE_AS_FAIL2_DIALOG, SAVE_AS_SUCCESS_DIALOG, SAVE_ODS_FAIL_DIALOG,
        
        READ_VEHICLE_ODS_DIALOG, READ_DRIVER_ODS_DIALOG, READ_ODS_DIALOG, READ_ODS_FAIL_DIALOG,
        READ_BUILDING_ODS_DIALOG, READ_FAIL_BUILDING_ODS_DIALOG, READ_AFFILIATION_ODS_DIALOG, READ_FAIL_AFFILIATION_ODS_DIALOG,
        REJECT_USER_DIALOG,
        
        UNIT_DELETE_DIALOG, UNIT_DELETE_RESULT_DIALOG, UNIT_DELETE_FAIL_RESULT_DIALOG,
        UNIT_MODIFY_DIALOG,
        USER_UPDATE_SUCCESS_DIALOG, USER_UPDATE_FAIL_DIALOG,
        
        PW_FOURDIGIT_DIALOG, PW_SIXDIGIT_DIALOG, PW_COMPLEX_DIALOG,
        
        VEHICLE_CLOSE_FORM_DIALOG, VEHICLE_MODIFY_FAIL_DAILOG, WORK_MODE_DIALOG, 
        SAME_DATA_INPUT_DIALOG
    }
    
    public static enum DialogTitleTypes {
        ATT_SAVE_AS_FAIL_DIALOGTITLE, ATT_EMAIL_DUP_DIALOGTITLE, ATT_EMAIL_SYNTAX_CHECK_DIALOG,
        ATT_ID_DUP_CHCEK_DIALOGTITLE, ATT_USER_UPDATE_DIALOGTITLE, ATT_SFAVE_AS_SUCCESS_DIALOGTITLE, ATT_HELP_DIALOGTITLE,
        READ_ODS_DIALOGTITLE, READ_ODS_FAIL_DIALOGTITLE,  
        AFFILIATION_MODIFY_DIALOGTITLE,
        BUILDING_MODIFY_DIALOGTITLE, LOWER_MODIFY_DIALOGTITLE, UNIT_MODIFY_DIALOGTITLE,
        REJECT_USER_DIALOGTITLE, VEHICLE_CHECK_DIALOGTITLE, 
        VEHICLE_MODIFY_FAIL_DIALOGTITLE, WARING_DIALOGTITLE,  ERROR_DIALOGTITLE,
        WORK_MODE_DIALOGTITLE,
        
        CREATION_RESULT_DIALOGTITLE, CREATTION_FAIL_DIALOGTITLE,   
        
        DELETE_ALL_DAILOGTITLE, DELETE_ALL_RESULT_DIALOGTITLE,
        
        DELETE_DIALOGTITLE, DELETE_RESULT_DIALOGTITLE, DELETE_FAIL_DAILOGTITLE,
        
        MODIFY_DAILOGTITLE, MODIFY_FAIL_DIALOGTITLE, 
        
        CANCEL_DIALOGTITLE,
        
        SAVE_DIALOGTITLE,
        
        CONFIRM_DIALOGTITLE,
        LOGGING_DIALOGTITLE, PASSWORD_REQUIR_DIALOGTITLE, LANGUAGE_SELECT_DIALOGTITLE,
        
        
        STATISTICS_INPUT_ERROR_DIALOGTITLE, PHOTO_SIZE_INPUT_ERROR_DIALOGTITLE,
        IP_FORMAT_ERROR_DAILOGTITLE
    }
    
    public enum TextType{
        CAR_TAG_TF, DRIVER_TF, OTHER_INFO_TF, CELL_PHONE_TF, LANDLINE_TF,
        LOG_OUT_TF, UNKNOWN_TF, UNREGISTERED_TF, NOT_APPLICABLE_TF,
        STATUS_TF, START_MSG, STOP_MSG, FIRST_RUN_MSG, 
        NO_MSG, ERROR_RATE_MSG , NO_APP_MSG, NO_SOCKET_DISCON_MSG, NO_COMMAND_MSG,
        OPEN_MSG, INTERRUPT_MSG, DISCONN_MSG, CONN_MSG, ON_ARTIFI_ERROR_MSG, ERROR_RATE_MSG2,
        ERROR_MSG, ERROR_CHECK_BOX_MSG, LETEST_MSG, PASSING_MSG,
        DELETE_LOG_MSG, DELETE_FILE_MSG,
        
        REFUSED_CONN_TF, HELP_TA, PASSING_DELAY_AVG_MSG, 
        DIS_CONN_MSG, COMMAND_ACK_MSG, TRY_CONN_MSG,
        LOGIN_MSG, LOGOUT_MSG, PASSING_DELAY_MSG, 
        ERR_INC_MSG, ERR_DEC_MSG,
    }
    
    public enum ComboBoxItemTypes{
        LOWER_HIGHER_CB_ITEM, ROOM_BUILDING_CB_ITEM,
        HIGHER_CB_ITEM, LOWER_CB_ITEM, BUILDING_CB_ITEM, ROOM_CB_ITEM, 
        GATE_CB_ITEM, ATTENDANT_CB_ITEM, BAR_CB_ITEM, ATTENDANT_LOGOUT_ITEM, 
        BAR_ALLOWED_CB_ITEM, BAR_LAZY_ATT_CB_ITEM, BAR_MANUAL_CB_ITEM, BAR_REMAIN_CLOSED_ATT_CB_ITEM, 
        USER_CB_ITEM, FOUR_DIGIT_CB_ITEM, SIX_DIGIT_CB_ITEM, COMPLEX_CB_ITEM,
        NO_LOGGING_CB_ITEM, SETTING_CHANGE_CB_ITEM, LOG_E_BOARD_CHANGE_CB_ITEM,
        DAY_CB_ITEM, DAYS_CB_ITEM,
        VERBATIM_CB_ITEM, VEHICLE_TAG_CB_ITEM, REGISTRATION_STAT_CB_ITEM, GATE_NAME_CB_ITEM, 
        CURRENT_DATE_CB_ITEM, CURRENT_TIME_CB_ITEM, CURRENT_DATE_TIME_CB_ITEM,
        RED_COLOR_CB_ITEM, ORANGE_COLOR_CB_ITEM, GREEN_COLOR_CB_ITEM, 
        BLACK_COLOR_CB_ITEM, BLUE_COLOR_CB_ITEM,
        
        BLINKING_CB_ITEM, LTOR_CB_ITEM, RTOL_CB_ITEM, STILL_FRAME_CB_ITEM,
    }
    
    public enum MenuITemTypes{
        RECORD_MENU, ARRIVAL_MENU_ITEM, BOOTING_MENU_ITEM, LOGIN_RECORD_MENU_ITEM,
        VEHICLE_MENU, VEHICLE_MANAGE_MENU_ITEM, DRIVERS_MENU_ITEM,
        AFFILIATION_MENU, MANAGE_MENU_ITEM, 
        USERS_MENU,  
        SYSTEM_MENU, SETTING_MENU_ITEM, QUIT_MENU_ITEM,
        LOGIN_MENU, LOGOUT_MENU, LOGIN_MENU_ITEM, LOGOUT_MENU_ITEM,
        MANAGER_MANU, 
    }
    
    public enum MessageTypes{
        LOGIN_MSG, LOGOUT_MSG, PASSING_DELAY_MSG, 
    }
    
}
