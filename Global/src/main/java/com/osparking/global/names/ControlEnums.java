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
        
        public String getContent() {
            return contents[language.ordinal()];
        }
    }
    
    public enum ButtonTypes {
        LOGIN_BTN("로그인(L)", "Login"),
        CLOSE_BTN("닫기(C)", "Close"),
        SAVE_BTN("저장(S)", "Save"),
        CREATE_BTN("생성(R)", "Create"),
        DELETE_BTN("삭제(D)", "Delete"),
        MODIFY_BTN("수정(M)", "Modify"),
        CANCEL_BTN("취소(C)", "Cancel"),
        SEARCH_BTN("검색(S)", "Search"),
        SAVE_AS_BTN("저장", "Save As"),
        CHECK_BTN("중복검색", "Check"),
        CLEAR_BTN("초기화(L)", "Clear"),
        DELETE_ALL_BTN("전체삭제(E)", "Delete All"),
        READ_ODS_BTN("<HTML>ods  읽기(<U>O</U>)</HTML>", "<HTML>Read  <U>O</U>ds</HTML>"),
        SAVE_ODS_BTN("<HTML>ods  저장(<U>A</U>)</HTML>", "<HTML>S<U>a</U>ve Ods"),
        CREATE_NO_SHORT_BTN("생성", "Create"),
        MODIFY_NO_SHORT_BTN("수정", "Modify"),
        DELETE_NO_SHORT_BTN("삭제", "Delete"),
        DELETE_ALL_NO_SHORT_BTN("전체삭제", "Delete All"),
        READ_ODS_NO_SHORT_BTN("시트읽기", "Read ods"),
        SAVE_NO_SHORT_BTN("저장", "Save"),
        CANCEL_NO_SHORT_BTN("취소", "Cancel"),
        OWNER_BTN("운전자", "Owner"),
        SELECT_BTN("선택(T)", "Select"),
        MANAGE_BTN("관리(M)", "Manage"),
        FIX_IT_BTN("설정(F)", "Fix It"),
        ARRIVALS_BTN("<HTML>입차기록(<U>A</U>)</HTML>", "<HTML><U>A</U>rrivals</HTML>"),
        VEHICLES_BTN("<HTML>등록차목록(<U>V</U>)</HTML>", "<HTML><U>V</U>ehicles</HTML>"),
        USERS_BTN("<HTML>사용자목록(<U>U</U>)</HTML>", "<HTML><U>U</U>sers</HTML>"),
        CAR_ARRIVAL_BTN("모의 입차", "Car Arrival"),
        STATISTICS_BTN("통계", "statistics"),
        GATE_BAR_OPEN_BTN("차단기 열림(O)", "Open Bar"),
        GATE_BAR_CLOSE_BTN("차단기 닫힘(C)", "Close Bar"),
        E_BOARD_SETTINGS_BTN("세부 설정", "Content Settings");
        
        ButtonTypes(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent() {
                return contents[language.ordinal()];
        }        
    }
    
    public enum ButtonContent {  
        CHECK_BTN("중복검색", "Check");
        
        ButtonContent(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent() {
                return contents[language.ordinal()];
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
        
        public String getContent() {
                return contents[language.ordinal()];
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
        ID_CHECK_DIALOG ("아이디 중복검사가 필요합니다.", "Need to check if 'id' is usable(unoccupied)."),
        EMAIL_CHECK_DIALOG("이메일 중복검사가 필요합니다.", "Need to check if 'E-Mail' is usable(unoccupied)."),
        ATT_NAME_CHECK_DIALOG("  - 관리원 이름 두 글자 이상", "  - Name should be longer than 1 character"),
        ATT_CELL_CHECK_DIALOG("  - 휴대폰 번호 입력 오류", "  - Wrong cell phone number"),
        PHONE_CHECK_DIALOG("  - 전화 번호 입력 오류", "  - Phone number error"),
        CELL_PHONE_CHECK_DIALOG(
                "  - 전화번호 나 휴대폰 중 하나 입력", "  - Either phone, cell-phone or both is needed"),
        PASSWORD_CHECK_DIALOG(
                "  - 비밀번호 형식이 요구사항과 맞지 않음.", "  - Password doesn't meet syntax requirements"),
        REPEAT_PW_CHECK_ERROR("  - 새 비밀번호 반복입력 불일치", "  - New passwords don't match"),
        ADMIN_PW_CHECK_DIALOG("  - 관리원 비밀번호 오류", "  - Your Password is Wrong!"),
        DELETE_FAIL_DAILOG("비밀번호가 올바르지 않습니다.", "Password is wrong!"),
        ID_LENGTH_CHECK_DIALOG("아이디는 두 자 이상이어야 합니다.", "ID should consists of at least 2 characters."),
        ID_FIRST_CHAR_CHECK_DIALOG(
                "첫 글자가 영문자가 아닙니다", "nested character isn't an alpha-numeric, space, or dot(.)" ), 
        ID_CHAR_CHECK_DIALOG(
                "내포된 글자가 영숫자, 공백 혹은 점(.)이 아닙니다", "first character is not an alphabet" ),
        ID_END_CHAR_CHECK_DIALOG("끝 글자가 영숫자가 아닙니다", "last character isn't an alpha-numeric" ),
        AFFILIATION_DELETE_ALL_DAILOG(
                "모든 상위 및 하위 소속들을 삭제합니까?", "Want to delete all of higher and lower affiliations?"),
        AFFILIATION_DELETE_ALL_RESULT_DAILOG(
                "모든 소속들이 성공적으로 삭제되었습니다", "Every affiliations are deleted successfully." ),
        BUILDING_DELETE_ALL_DAILOG(
                "모든 건물 및 소속 호실들을 삭제합니까?", "Want to delete all buildings and rooms?" ),
        BUILDING_DELETE_ALL_RESULT_DAILOG(
                "모든 건물 및 소속 호실들이 성공적으로 삭제되었습니다", "Every Buildings and Rooms are deleted."),
        CAR_TAG_DIALOG("차량번호가 누락되었습니다.", "Car Tag Number is missing."),
        DRIVER_DIALOG("운전자 정보가 누락되었습니다.", "Car Owner/Driver is missing."),
        VEHICLE_CREATION_FAIL_DIALOG("차량 등록을 실패하였습니다.", "failed vehicle creation "),
        VEHICLE_CREATE_CANCEL_DIALOG(
                "작성 중인 차량정보를 포기하겠습니까?", "Want to desert car information created so far?"),
        VEHICLE_MODIFY_CANCEL_DAILOG(
                "변경 중인 차량정보를 포기하겠습니까?", "Want to desert car information updated so far?"),
        VEHICLE_DELETE_ALL_DAILOG(
                "모든 등록 차량을 삭제합니까?", "Want do delete all vehicle information?"),
        VEHICLE_DELETE_ALL_RESULT_DAILOG(
                "모든 차량 정보가 삭제되었습니다.", "All Vehicles are Deleted"),
        VEHICLE_SAVE_ODS_FAIL_DIALOG(
                "등록된 차량이없습니다.", "No vehicle to save!"),
        DRIVER_CREATRION_FAIL_DIALOG("운전자 정보 생성 실패.", "Failed to create a owner/driver record."), 
        DRIVER_DELETE_ALL_RESULT_DAILOG("모든 운전자가 삭제되었습니다.", "All drivers were deleted"),
        DRIVER_MODIFY_FAIL_DAILOG(
                "하나의 운전자만 선택하여 변경하십시오.", "Please narrow down to a single driver to update!"),
        DRIVER_CREATE_CANCEL_DIALOG(
                "새로 생성중인 운전자 정보를 취소하시겟습니까?", "Do you want to quit creating a new driver?"),
        DRIVER_MODIFY_CANCEL_DAILOG(
                "변경 중인 운전자 정보를 취소하시겟습니까?", "Do you want to cancel modifying driver information?"),
        
        DRIVER_NAME_CHECK_DIALOG("\"필수사항\" 인 이름을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 이름 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)", 
                "Want to input a \"required\" driver name?" 
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes] name field will have focus." 
                            + System.getProperty("line.separator") 
                            + "Otherwise, modification will be discarded.)"),
        
        DRIVER_CELL_CHECK_DIALOG("\"필수사항\" 인 휴대전화을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 휴대전화 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)",
                "Want to input a \"required\" cell phone number?"  
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes], cell phone will have focus," 
                            + System.getProperty("line.separator") 
                            + "Otherwise, modification will be discarded.)"),
        
        DRIVER_CREATE_CHECK_CELL_DIALOG("\"필수사항\" 인 휴대전화을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 휴대전화 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)", 
              "Want to input a \"required\" cell phone number?" 
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes], cell phone will have focus," 
                            + System.getProperty("line.separator") 
                            + "Otherwise, creation will be discarded.)"),
        
        DRIVER_CREATE_FAIL_DIALOG("운전자 이름을입력하지 않으셧습니다." 
                            + System.getProperty("line.separator")
                            + "운전자 생성이 자동적으로 종료됩니다." ,
                "As the driver's name is missing," 
                            + System.getProperty("line.separator") 
                            + "driver creation is automatically cancelled."),
        
        DRIVER_CLOSE_FORM_DIALOG(
                "생성/수정 중인정보를 포기하시겟습니까?", "Want to desert car information being created/modified?"),
        DATE_INPUT_CHECK_DIALOG("시작일과 종료일을 모두 입력하십시오", "Enter starting and ending date both"),
        
        DATE_INPUT_ERROR_DIALOG( "종료일이 시작일보다 앞설 수 없습니다\n" 
                + "날짜를 변경하여 입력하십시오.", 
                "Ending date can't precede starting date\n" 
                        + "Please, correct search range."),
         
        STOP_RUNNING_DIALOG("실행중인 프로그램을 종료합니다.", "Program stops running."),
        SHUT_DOWN_CONFIRM_DIALOG("프로그램을 종료하시겠습니까?", "Do you want to stop the system?"),
        
        LOGALWAYS_DAILOG("현제 단계 (로깅하지 않음.)," + System.lineSeparator() 
                + " 다음과 같은 '필수적인' 항목들만 로깅 :" + System.lineSeparator() 
                + System.lineSeparator() 
                + " - 시스템 시작시간과 종료시간" + System.lineSeparator() 
                + " - 삭제 된 레코드 수"+ System.lineSeparator() 
                + " - 삭제 된 이미지의 수"+ System.lineSeparator() 
                + " - 삭제 된 로그 폴더 및 텍스트 파일의 개수"+ System.lineSeparator() 
                + " - 삭제 된 로그 폴더 및 텍스트 파일의 경로", 
                "At this level (No Logging)," + System.lineSeparator() 
                + " Following 'Mandatory' items are logged :" + System.lineSeparator() 
                + System.lineSeparator() 
                + " - System start and stop time" + System.lineSeparator() 
                + " - Number of deleted old records"+ System.lineSeparator() 
                + " - Number of deleted old images"+ System.lineSeparator() 
                + " - Number of deleted log folders and text files"+ System.lineSeparator() 
                + " - File path of deleted log folders and text files"),
        
        SETTINGSCHANGE_DAILOG(" '시스템 설정 변경' 단계 로깅 항목 :" + System.lineSeparator() 
                + System.lineSeparator() 
                + " - 필수 항목" + System.lineSeparator() 
                + "   추가" + System.lineSeparator()
                + " - 시스템 설정 변경"+ System.lineSeparator() 
                + " - 관리원/사용자 정보 변경"+ System.lineSeparator() 
                + " - 운전자 정보 변경"+ System.lineSeparator() 
                + " - 차량정보 변경", 
                "'System Settings' Level Logged Items:" + System.lineSeparator() 
                + System.lineSeparator() 
                + " - Mandatory Items" + System.lineSeparator() 
                + "   plus" + System.lineSeparator()
                + " - System Settings Change" + System.lineSeparator() 
                + " - Attendant/User Info Change" + System.lineSeparator() 
                + " - Drivers Info Change" + System.lineSeparator() 
                + " - Vehicles Info Change" + System.lineSeparator()),
        
        EBDSETTINGSCHANGE_DIALOG(" '시스템, 사용자, 차량' 단계 로깅 항목 :" + System.lineSeparator() 
                + System.lineSeparator() 
                + " - 시스템 설정 변경 항목" + System.lineSeparator() 
                + "   추가"+ System.lineSeparator() 
                + " - 전광판 설정 변경"+ System.lineSeparator() ,
                "'E-Board Settings' Level Logged Items: " + System.lineSeparator() 
                + System.lineSeparator() 
                + " - System Settings Logged Items" + System.lineSeparator() 
                + "   plus" + System.lineSeparator()
                + " - E-Board Settings Change" + System.lineSeparator()),

        LANGUAGE_USE_DIALOG("GUI 언어 선택", "GUI language selection."),
        PHOTO_SIZE_INPUT_ERROR_DIALOG(
                "사진크기를 100이상으로 입력해주시기 바랍니다.", "Please enter a picture size value of 100 or more."),
        
        CHANGE_GATE_DAILOG("입구 갯수를 변경할 경우," + System.lineSeparator() 
                    + "'OsParking' 이 자동으로 종료됩니다." + System.lineSeparator() 
                    + "'OsParking' 을 다시 실행시켜주시기 바랍니다.",
            "After Gate count change," + System.lineSeparator() 
                    + "'OSParking' shuts down by itself." + System.lineSeparator() 
                    + "So, you need to start OSParking again."),
        
        FAIL_SAVE_SETTINGS_DIALOG("시스템 설정 저장에 실패하였습니다.", "The storage system settings failed."),
        SAVE_SETTINGS_DIALOG(
                "시스템 설정 저장에 성공하였습니다.", "The system settings have been saved successfully."),
        INPUT_ERROR_DAILOG("1 이상의 값을 입력하세요.", "Enter a value of 1 or more .."),
        IP_FORMAT_ERROR_DAILOG("의 IP 주소 형식이 올바르지 않습니다.", "in IP address format error"),
        
        SAVE_OR_CANEL_DIALOG("설정이 변경되었습니다..\n \n"
                    + "[저장] 또는 [취소]버튼을 선택해 주세요.", 
            "Settings Changed.\n \n"
                    + "Either [Save] or [Cancel], please."),
        
        E_BOARD_SAVE_FAIL_DIALOG(
                "전광판 설정을 실패하였습니다.", "This e-board settings update saving DB operation failed."),
        E_BOARD_NO_INPUT_MSG_DIALOG("  - 문구를 입력해주십시오.", "  - Please enter a message"),
        CHANGE_DIALOG("변경사항", "Changes"),
        CURRENT_DIALOG("변경 전", "Current"),
        MODIFY_DIALOG("변경 후", "Modified"),
        SAVE_DIALOG("저장되었습니다.", "Saved"),
        LOGIN_FAIL_DIALOG("아이디 혹은 비밀번호가 일치 하지 않습니다..", "ID or Password is wrong!"),
        INPUT_ID_DIALOG("\"아이디\"를 입력하세요.", "Enter \'Login ID\'!"),
        INPUT_PW_DIALOG("\"비밀번호\"를 입력하세요.", "Enter \'Password\'"),
        
        L2_INPUT_DIALOG("\"하위소속\"을 선택하지 않으셨습니다.\n"
                + "하위소속을 선택 하시겠습니까 ?\n"
                + "아니요 버튼을 누르게 되면 상위 소속이 초기화됩니다.\n", 
                "You did not select \'Lower Affiliation\'\n"
                + "Do you want to Select Lower Affiliation ?\n"
                + "If you choose \'no\',  Higher will be reset"),
        
        UNIT_INPUTDIALOG("\"호실번호\"를 선택하지 않으셨습니다.\n"
                + "\"호실번호\" 선택 하시겠습니까 ?\n"
                + "아니요 버튼을 누르게 되면 건물 번호가 초기화됩니다.\n", 
                "You did not select \'Room\'\n"
                + "Do you want to Select Room ?\n"
                + "If you choose \'no\' button will delete the changes"),
        
        PW_FOURDIGIT_DIALOG("  - 숫자(0~9) 네 자리로 구성", "  - four digit number (0~9)"),
        
        PW_SIXDIGIT_DIALOG("  - 6 ~ 40자로 구성\n"
                + "  - 영문 문자(a-z,A~Z)를 한 글자 이상 포함\n"
                + "  - 숫자(0-9)를 한 글자 이상 포함",
                "  - consists of 6 to 40 characters\n"
                + "  - contains at least one English alphabet (a-z,A~Z)\n"
                + "  - includes more than one number key(0-9)"),
        
        PW_COMPLEX_DIALOG(
                "  - 8 ~ 40자로 구성\n"
                + "  - 영문 소문자(a~z) 한 글자 이상 포함\n"
                + "  - 영문 대문자(A~Z) 한 글자 이상 포함\n"
                + "  - 숫자(0-9) 한 자 이상 포함\n"
                + "  - 다음 특수 문자 중 한 글자 이상 포함",
                "  - consists of 8 to 40 characters\n"
                + "  - contains at least one lower case alphabet (a-z)\n"
                + "  - contains at least one upper case alphabet (A-Z)\n"
                + "  - includes more than one number key(0-9)\n"
                + "  - includes at least one special character shown below"
                ),
        
        SAME_DATA_INPUT_DIALOG("'에 같은 값이 존재합니다.", "Same data exists in '"),
        
        BUILDING_IN_DIALOG("건물 번호", "Building number"),
        ROOM_IN_DIALOG("호실 번호", "Room number"),
        LEVEL1_NAME_DIALOG("상위 소속", "Higher Affiliation"),
        LEVEL2_NAME_DIALOG("하위 소속", "Lower Affiliation");

//        SAVE_AS_FAIL2_DIALOG, 
//        SAVE_ODS_FAIL_DIALOG,        
        
//        AFFILIATION_ODS_READ_DIALOG, 
//        AFFILIATION_MODIFY_DIALOG,
//        
//        BUILDING_DELETE_RESULT_DIALOG,
//        BUILDING_MODIFY_DIALOG,
//        
//        CREATION_FAIL_DIALOG, 
//        CHECK_AFFILIATION_ODS_DIALOG, 
//        CHECK_BUILDING_ODS_DIALOG, 
//        CREATION_SUCCESS_DIALOG,
//        
//        DELETE_DIALOG,
//        DELETE_SUCCESS_DIALOG, 
//        DELETE_FAIL1_DAILOG, 
//        DELETE_FAIL2_DAILOG, 
//        DELETE_FAIL3_DAILOG, 
//        DELETE_CARS_DIALOG, 
//        DELETE_A_CAR_DIALOG, 
//        DELETE_ALL_DAILOG, 
//        DELETE_ONE_DIALOG, 
//        DELETE_MORE_DIALOG,
//        
//        EMAIL_DUP_TURE_DIALOG, 
//        EMAIL_DUP_FALSE_DIALOG, 
//        EMAIL_CHECK_FAIL_DIALOG, 
//        
//        LOWER_DELETE_RESULT_DIALOG, 
//        LOWER_MODIFY_DIALOG, 
//        LOGOUT_DIAILG,
//        LOGOUT_CONFIRM_DIALOG,
//        
//        READ_VEHICLE_ODS_DIALOG, 
//        READ_DRIVER_ODS_DIALOG, 
//        READ_ODS_DIALOG, 
//        READ_ODS_FAIL_DIALOG,
//        READ_BUILDING_ODS_DIALOG, 
//        READ_FAIL_BUILDING_ODS_DIALOG,
//        READ_AFFILIATION_ODS_DIALOG,
//        READ_FAIL_AFFILIATION_ODS_DIALOG,
//        REJECT_USER_DIALOG,
//        UNIT_DELETE_DIALOG,
//        UNIT_DELETE_RESULT_DIALOG,
//        UNIT_DELETE_FAIL_RESULT_DIALOG,
//        
//        VEHICLE_CLOSE_FORM_DIALOG, 
//        VEHICLE_MODIFY_FAIL_DAILOG,
//        WORK_MODE_DIALOG, 
        
        DialogMSGTypes(String korean, String english) {
            contents[KOREAN.ordinal()] = korean;
            contents[ENGLISH.ordinal()] = english;
        }
        
        private String[] contents = new String[Languages.values().length];
        
        public String getContent() {
                return contents[language.ordinal()];
        }        
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
