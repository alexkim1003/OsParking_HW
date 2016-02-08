/*
 * Copyright (C) 2015 YongSeok
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

import static com.osparking.global.Globals.loginID;
import static com.osparking.global.names.DB_Access.parkingLotLocale;

/**
 *
 * @author Open Source Parking Inc.
 */
public class GUI_Language {
    public static String[][] title = {
        // <editor-fold defaultstate="collapsed" desc="-- FRAMETITLE"> 
        {"주차관리 로그인", "Attendant Login"},
        {"사용자 정보관리 및 목록", "User List Management"},
        {"소속 및 건물", "Affiliation and Building"},
        {"등록차량 관리", "Registered Vehicles"},
        {"운전자 검색", "Find Driver"},
        {"운전자 목록", "Driver List"},
        {"도착 목록", "Arrival Records"},
        {"검색 기준", "Search Criteria"},
        {"도착 속성", "Arrival Properties"},
        {"도착 기간", "Arrival Time"},
        {"도착차량 상세정보", "Vehicle Arrival Details"},
        {"도착차량 목록", "Vehicle Arrival List"},
        {"입차 사진", "car arrival image"},
        {"허가되지 않은 차량", "DisAllowed Car"},
        {"방문객 정보 입력", "Visitor Information Entry"},
        {"사용자 로그인 기록", "User Login Record"},
        {"OsParking 실행 기록", "OsParking Program Run Record"},
        {"최근 도착 차량", "Recent car Arrivals"},
        {"시스템 설정", "System Settings"},
        {"전광판 설정", "Electronic Display Settings"},
        {"평시", "Default"},
        {"차량", "Vehicle"},
        {"상단", "TOP"},
        {"하단", "BOTTOM"},
        //</editor-fold>  
    };
    public static String[][] button = 
    {
        // <editor-fold defaultstate="collapsed" desc="-- Button"> 
        {"로그인(L)", "Login"},
        {"닫기(C)", "Close"},
        {"저장(S)", "Save"},
        {"생성(R)", "Create"},
        {"삭제(D)", "Delete"},
        {"수정(M)", "Modify"},
        {"취소(C)", "Cancel"},
        {"검색(S)", "Search"},
        {"저장", "Save As"},
        {"중복검색", "Check"},
        {"초기화(L)", "Clear"},
        {"전체삭제(E)", "Delete All"},
        {"<HTML>ods  읽기(<U>O</U>)</HTML>", "<HTML>Read  <U>O</U>ds</HTML>"},
        {"<HTML>ods  저장(<U>A</U>)</HTML>", "<HTML>S<U>a</U>ve Ods"},
        {"생성", "Create"},
        {"수정", "Modify"},
        {"삭제", "Delete"},
        {"전체삭제", "Delete All"},
        {"시트읽기", "Read ods"},
        {"저장", "Save"},
        {"취소", "Cancel"},
        {"운전자", "Owner"},
        {"선택(T)", "Select"},
        {"관리(M)", "Manage"},
        {"설정(F)", "Fix It"},
        {"<HTML>입차기록(<U>A</U>)</HTML>", "<HTML><U>A</U>rrivals</HTML>"},
        {"<HTML>등록차목록(<U>V</U>)</HTML>", "<HTML><U>V</U>ehicles</HTML>"},
        {"<HTML>사용자목록(<U>U</U>)</HTML>", "<HTML><U>U</U>sers</HTML>"},
        {"모의 입차", "Car Arrival"},
        {"통계", "statistics"},
        {"차단기 열림(O)", "Open Bar"},
        {"차단기 닫힘(C)", "Close Bar"},
        {"세부 설정", "Content Settings"},
        //</editor-fold>  
    };
    
    public static String[][] label = 
    {
        // <editor-fold defaultstate="collapsed" desc="-- Label"> 
        { "아이디", "Login ID"} ,
        { "비밀번호",  "Password"},
        { "정보수정 조건",  "Data Condition"},
        { "정보 입력 지침",  "Guideline"},
        { ": 필수",  ": Required"},
        { ": 최소 1",  ": Choose 1"},
        { "사용자 ID: ",  "My ID: "},
        
        { "매니저",  "Manager"},
        
        { "이름",  "Name"},
        { "휴대전화",  "Cell Phone"},
        { "유선전화",  "Phone"},
        { "이메일",  "E-Mail"},
        { "비밀번호 변경",  "Change Password"},
        { "새 비밀번호",  "New Password"},
        { "비밀번호 확인",  "Repeat Password"},
        { "현재 비밀번호",  "My Password"},
        { "자료 생성일",  "Creation Date"},
        { "상위 소속 목록",  "Higher Affiliations"},
        { "소속 부서 목록",  "Lower Affiliations"},
        { "건물(동) 목록",  "Building Numbers"},
        { "호실 목록",  "Rooms of Building"},
        { "소속 명칭 ods 파일 형식",  "Affiliation name list ods file content"},
        { "건물 호실 ods 파일 형식",  "Building room number list ods file content"},
        { "작업모드 : ",  "Form Mode :"},
        { "차량 검색",  "Searching"},
        { "차량 등록",  "Car Creation"},
        { "차량 변경",  "Modification"},
        { "검색 값",  "Search Key"},
        { "순번",  "List#"},
        { "차량번호",  "Tag No."},
        { "최근변경",  "Modi' Date"},
        
        { "세대통보",  "Notification"},
        { "전체비교",  "Exact Comp'"},
        { "주차허용",  "Park Allowed"},
        { "불허사유",  "Reason"},
        { "기타정보",  "Other Info'"},
        { "최초등록",  "Regi' Date"},
        { "차량 대수",  "Vehicle Count"},
        { "*: 필수 항목",  "*: required field"},
        { "입구 이름",  "Gate Name"},
        { "관리원",  "Attendant"},
        { "차단기",  "Bar Operation"},
        { "도착시간",  "Arrival Time"},
        
        { "인식된 차량번호",  "Tag Recognized"},
        { "등록된 차량번호",  "Tag Registered"},
        { "소속부서",  "Affiliations"},
        { "소속건물",  "Building"},
        { "호수",  "Room No."},
        { "방문목적",  "Purpose of Visit"},
        { "방문 하는 곳",  "Where to go"},
        { "파일 크기",  "File Size"},
        { "차량 합계",  "Record Count"},
        { "최근 1시간",  "Last 1 hour"},
        { "최근 24시간",  "Last 24 hour"},
        { "기간지정",  "Duration"},
        { "방문세대",  "Visit Households"},
        { "방문부서",  "Visit Departments"},
        { "차단기 개방",  "Open Gate"},
        { "상황 게시",  "Recent Events"},
        { "입구",  "Gate"},
        { "카메라",  "Camera"},
        { "전광판",  "E-Board"},
        { "차단기",  "G-Bar"},
        { "일시적으로 허용되지 않은 차량입니다.",  "Car Temporarily Not Permitted"},
        { "통계 모집단 크기",  "Statistics Population Size"},
        { "통과 시간 기록",  "Record Passing Delay"},
        { "비밀번호 난이도",  "Password Complexity Level"},
        { "일반연산 로깅 레벨",  "General Operation Logging Level"},
        { "표시 언어",  "Language Chooser"},
        { "메인 상황게시 최대 행수",  "Recent Event Line Max"},
        { "입구 수",  "Number of Gates"},
        { "사진 저장 기간",  "Image Keeping Duration"},
        { "차량 사진 크기",  "Vehicle Image Size"},
        { "너비",  "Width"},
        { "높이",  "Height"},
        { "카메라 IP 주소",  "Camera IP Address"},
        { "차단기 IP 주소",  "GateBar IP Address"},
        { "전광판 IP 주소",  "E-Board IP Address"},
        { "포트",  "Port No"},
        { "전광판 설정",  "Electronic Display Board"},
        { "주기",  "Cycle"},
        { "깜빡임",  "Blinking"},
        { "흐름",  "Flowing"},
        { "표시 유형",  "Content Type"},
        { "문자열",  "Message"},
        { "색상",  "Color"},
        { "폰트",  "Font"},
        { "효과",  "Effect"},
        { "검색 기간",  "Search Period"},
        { "초",  "seconds"},
        { " 대",  " vehicles"},
        { "주차장 이름",  "Parking Lot Name"},
        //</editor-fold>  
    };
    
    public static String[][] toolTip = {
        // <editor-fold defaultstate="collapsed" desc="-- Tooltip"> 
//        {"관리자 로그인", "Click to Login"},
        {"화면 닫기", "Close Login Window"},
        {"파일로 저장", "Save as file"},
        {"영문숫자 최대 20자!", "Up to 20 alphanumeric characters"},
        {"두 자 이상 입력하세요", "Please enter at least two characters"},
        {"관리자 비밀번호 입력!", "Enter User Password!"},
        {"숫자 11자리 입력하세요", "Enter the 11-digit number"},
        {"숫자 4자리 이상 입력하세요", "Please enter at least a four-digit number"},
        {"한번 더 입력하세요", "Please enter it again."},
        {"속성 값 입력", "Enter Search Key."},
        {"네 자리 숫자 입력 (자세한 내용: ? 클릭)", "Enter a 4 digit number(for details click '?')"},
        {"6 자리 이상 영자 및 숫자 입력 (자세한 내용: ? 클릭)", "Enter 6 or more digits of alpha-numeric(for details click '?'"},
        {"8 자리 이상 영자, 숫자 및 특수문자 입력 (자세한 내용: ? 클릭)", "Enter 8 or more digits of alphabet, number, and special character(for details click '?')"},
        {"챠량번호로 검색", "Search CarTag"},
        {"운전자로 검색", "Search Driver"},
        {"소속으로 검색", "Search Affiliation"},
        {"건물로 검색", "Search Building"},
        {"기타정보로 검색", "Search Other Info"},
        {"휴대전화로 검색", "Search Cell Phone"},
        {"유선전화로 검색", "Search LandLine"},
        {"상위 소속으로 검색", "Search Higher Affiliation"},
        {"하위 소속로 검색", "Search Lower Affiliation"},
        {"호수로 검색", "Search Unit"},
        {"영역 초기화", "Clears Criteria"},
        {"선택된 라디오 버튼을 적용", "Remember Radio Button Selection"},
        {"입력 후 [엔터]", "Type and [Enter]"},
        {"0,1 씩 증가", "Inc by 0.1"},
        {"0,1 씩 감소", "Dec by 0.1"},
        {"변경 내용을 저장", "Save your changes"},
        {"변경 내용을 취소", "It rejected the changes made"},
        //</editor-fold>  
    };
    
    public static String[][] tableHeader = {
        // <editor-fold defaultstate="collapsed" desc="-- TableHeader"> 
        {"아이디", "User ID"},
        {"이름", "Name"},
        {"매니저", "Manager"},
        {"휴대전화", "Cell Phone"},
        {"유선전화", "Phone"},
        {"이메일", "E-mail"},
        {"수정날짜", "Modified"},
        {"순번", "Order"},
        {"상위 소속", "Higher Affiliation"},
        {"하위 소속", "Lower Affiliation"},
        {"건물 번호", "Building"},
        {"호실 번호", "Room"},
        {"차량번호", "Tag No."},
        {"운전자", "Driver"},
        {"하위-상위소속", "Lower-Higher"},
        {"호실-건물", "Room-Building"},
        {"기타정보", "Other Info'"},
        {"불허 사유", "Reason"},
        {"도착일시", "Arrival Date"},
        { "로그인 시간",  "Login Time"},
        { "로그아웃 시간",  "Logout Time"},
        { "경과시간(시:분:초)",  "Duration(hh:mm:ss)"},
        { "시스템 종료",  "Shutdown"},
        { "시스템 시작",  "Start Up"},
        //</editor-fold>  
    };
    
    public static String[][] textField = {
        // <editor-fold defaultstate="collapsed" desc="-- Button"> 
        {"(차량번호)", "(Tag No.)"},
        {"(운전자)", "(Driver)"},
        {"(기타정보)", "(Other Info)"},
        {"(휴대전화)", "(Cell Phone)"},
        {"(유선전화)", "(LandLine)"},
        {"(로그아웃)", "(Log Out)"},
        {"(자료없음)", "(unknown)"},
        {"(미등록)", "(Non-Registered)"},
        {"(해당사항없음)", "(Not Applicable)"},
        {"<중요 상태 정보>", "<Critical Status Information>"},
        {"시스템 시작", "System started"},
        {"시스템 종료", "System stopped"},
        {"OsParking 의 첫 번째 실행", "Very First Run of OsParking!"},
        {"없음", "N/A"},
        {"인공 에러율: ", "Artificial error rate: "},
        {"인공 에러 없음", "No artificial error"},
        {": 소켓 단절 없음.", ": no socket disconn'"},
        {"개방 명령 없음.", "no Open command statistics"},
        {"개방", "Open"},
        {"입차", "Interrupt"},
        {"단절됨", "disconnected"},
        {"연결됨", "connected"},
        {"인공 에러 삽입", "Artificial error is on"},
        {"에러율 : ", "prob of error: "},
        {"에러 ", "error"},
        {"에러 체크 박스를 먼저 선택해주세요. ", "First, select error check box, OK?"},
        {"최근 ", "latest"},
        {" 대 도착, 평균 통과 시간", " car arrivals, average passing delay is"},
        {"삭제된 기록--폴더: ", "Deleted logs--directory: "},
        {", 파일: ", "files: "},
        //</editor-fold>  
    };
    
    public static String[][] comboBoxItem = {
        // <editor-fold defaultstate="collapsed" desc="-- ComboBoxItem"> 
        {"(하위-상위소속)", "(Lower-Higher)"},
        {"(호수-건물)", "(Bldg-Rm#)"},
        {"(상위 소속)", "(Higher Group)"},
        {"(하위 소속)", "(Lower Group)"},
        {"(건물 번호)", "(Building No,)"},
        {"(호수)", "(Room No.)"},
        {"(입구)", "(Gate)"},
        {"(관리원)", "(Attendant)"},
        {"(차단기)", "(Unselected)"},
        {"(로그아웃)", "(logged out)"},
        {"자격개방", "Legal Open"},
        {"불문개방", "Auto' Open"},
        {"수동개방", "Manual Open"},
        {"폐쇄", "Remain Closed"},
        {"(모든 사용자)", "(everybody)"},
        {"네 자리 숫자", "Four digits"},
        {"6자리 이상 영숫자", "Six-digit or more alpha-numeric"},
        {"8자리 이상 복합구성", "8 digit or more complex configuration"},
        {"로깅하지 않음", "no logging"},
        {"시스템 설정 변경", "System settings change"},
        {"시스템, 사용자, 차량", "Log E-Board change too"},
        {"일", "day"},
        {"일", "days"},
        {"문구 자체", "Exact Words"},
        {"차량번호", "Plate Number"},
        {"등록 상태", "Registration Status"},
        {"입구 명칭", "Gate Name"},
        {"현재 날짜", "Current Date"},
        {"현재 시간", "Current Time"},
        {"현재 날짜-시간", "Current Date and Time"},
        {"빨강", "RED"},
        {"주황", "ORANGE"},
        {"초록", "GREEN"},
        {"검정", "BLACK"},
        {"파랑", "BLUE"},
        {"깜빡임", "Blinking"},
        {"우로 흐름", "L to R Flow"},
        {"좌로 흐름", "R to L Flow"},
        {"정지", "Still Frame"},
        //</editor-fold>  
    };
    
    public static String[][] dialogMSG = {
        // <editor-fold defaultstate="collapsed" desc="-- DialogMSG"> 
        {"아이디 중복검사가 필요합니다.", "Need to check if 'id' is usable(unoccupied)."},
        {"이메일 중복검사가 필요합니다.", "Need to check if 'E-Mail' is usable(unoccupied)."},
        {"  - 관리원 이름 두 글자 이상", "  - Name should be longer than 1 character"},
        {"  - 휴대폰 번호 입력 오류", "  - Wrong cell phone number"},
        {"  - 전화 번호 입력 오류", "  - Phone number error"},
        {"  - 전화번호 나 휴대폰 중 하나 입력", "  - Either phone, cell-phone or both is needed"},
        {"  - 비밀번호 형식이 요구사항과 맞지 않음.", "  - Password doesn't meet syntax requirements"},
        {"  - 새 비밀번호 반복입력 불일치", "  - New passwords don't match"},
        {"  - 관리원 비밀번호 오류", "  - Your Password is Wrong!"},
        {"비밀번호가 올바르지 않습니다.", "Password is wrong!"},
        {"아이디는 두 자 이상이어야 합니다.", "ID should consists of at least 2 characters."},
        {"첫 글자가 영문자가 아닙니다", "nested character isn't an alpha-numeric, space, or dot(.)" },
        {"내포된 글자가 영숫자, 공백 혹은 점(.)이 아닙니다", "first character is not an alphabet" },
        {"끝 글자가 영숫자가 아닙니다", "last character isn't an alpha-numeric" },
        {"모든 상위 및 하위 소속들을 삭제합니까?", "Want to delete all of higher and lower affiliations?"},
        {"모든 소속들이 성공적으로 삭제되었습니다", "Every affiliations are deleted successfully." },
        {"모든 건물 및 소속 호실들을 삭제합니까?", "Want to delete all buildings and rooms?" },
        {"모든 건물 및 소속 호실들이 성공적으로 삭제되었습니다", "Every Buildings and Rooms are deleted."},
        {"차량번호가 누락되었습니다.", "Car Tag Number is missing."},
        {"운전자 정보가 누락되었습니다.", "Car Owner/Driver is missing."},
        {"차량 등록을 실패하였습니다.", "failed vehicle creation "},
        {"작성 중인 차량정보를 포기하겠습니까?", "Want to desert car information created so far?"},
        {"변경 중인 차량정보를 포기하겠습니까?", "Want to desert car information updated so far?"},
        {"모든 등록 차량을 삭제합니까?", "Want do delete all vehicle information?"},
        {"모든 차량 정보가 삭제되었습니다.", "All Vehicles are Deleted"},
        {"등록된 차량이없습니다.", "No vehicle to save!"},
        {"운전자 정보 생성 실패.", "Failed to create a owner/driver record."},
        {"모든 운전자가 삭제되었습니다.", "All drivers were deleted"},
        {"하나의 운전자만 선택하여 변경하십시오.", "Please narrow down to a single driver to update!"},
        {"새로 생성중인 운전자 정보를 취소하시겟습니까?", "Do you want to quit creating a new driver?"},
        {"변경 중인 운전자 정보를 취소하시겟습니까?", "Do you want to cancel modifying driver information?"},
        
        {
         "\"필수사항\" 인 이름을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 이름 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)", 
            
        "Want to input a \"required\" driver name?" 
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes] name field will have focus." 
                            + System.getProperty("line.separator") 
                            + "Otherwise, modification will be discarded.)"
        },
        
        { 
         "\"필수사항\" 인 휴대전화을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 휴대전화 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)",
            
          "Want to input a \"required\" cell phone number?"  
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes], cell phone will have focus," 
                            + System.getProperty("line.separator") 
                            + "Otherwise, modification will be discarded.)" 
        },
        
        {
          "\"필수사항\" 인 휴대전화을 입력하시겟습니까?" 
                            + System.getProperty("line.separator") 
                            + "([예] 버튼을 누르면 휴대전화 입력란으로 이동합니다." 
                            + System.getProperty("line.separator") 
                            + "그렇지 않으면, 수정정보가 포기됩니다.)", 
            
          "Want to input a \"required\" cell phone number?" 
                            + System.getProperty("line.separator") 
                            + "(By pressing [Yes], cell phone will have focus," 
                            + System.getProperty("line.separator") 
                            + "Otherwise, creation will be discarded.)"
        },
        
        {
          "운전자 이름을입력하지 않으셧습니다." 
                            + System.getProperty("line.separator")
                            + "운전자 생성이 자동적으로 종료됩니다." ,
            
         "As the driver's name is missing," 
                            + System.getProperty("line.separator") 
                            + "driver creation is automatically cancelled."
        },
        
        {"생성/수정 중인정보를 포기하시겟습니까?", "Want to desert car information being created/modified?"},
        {"시작일과 종료일을 모두 입력하십시오", "Enter starting and ending date both"},
        {
            "종료일이 시작일보다 앞설 수 없습니다\n" +
                                    "날짜를 변경하여 입력하십시오.", 
            "Ending date can't precede starting date\n" +
                                    "Please, correct search range."
        },
        
        {"실행중인 프로그램을 종료합니다.", "Program stops running."},
        {"프로그램을 종료하시겠습니까?", "Do you want to stop the system?"},
        {
            "현제 단계 (로깅하지 않음.)," + System.lineSeparator() 
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
                    + " - File path of deleted log folders and text files"
        },
        {
            " '시스템 설정 변경' 단계 로깅 항목 :" + System.lineSeparator() 
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
                    + " - Vehicles Info Change" + System.lineSeparator()
        },
        {
                " '시스템, 사용자, 차량' 단계 로깅 항목 :" + System.lineSeparator() 
                    + System.lineSeparator() 
                    + " - 시스템 설정 변경 항목" + System.lineSeparator() 
                    + "   추가"+ System.lineSeparator() 
                    + " - 전광판 설정 변경"+ System.lineSeparator() ,
            "'E-Board Settings' Level Logged Items: " + System.lineSeparator() 
                    + System.lineSeparator() 
                    + " - System Settings Logged Items" + System.lineSeparator() 
                    + "   plus" + System.lineSeparator()
                    + " - E-Board Settings Change" + System.lineSeparator()
        },
        {"GUI 언어 선택", "GUI language selection."},
        {"사진크기를 100이상으로 입력해주시기 바랍니다.", "Please enter a picture size value of 100 or more."},
        {
            "입구 갯수를 변경할 경우," + System.lineSeparator() 
                    + "'OsParking' 이 자동으로 종료됩니다." + System.lineSeparator() 
                    + "'OsParking' 을 다시 실행시켜주시기 바랍니다.",
            "After Gate count change," + System.lineSeparator() 
                    + "'OSParking' shuts down by itself." + System.lineSeparator() 
                    + "So, you need to start OSParking again."
            
        },
        {"시스템 설정 저장에 실패하였습니다.", "The storage system settings failed."},
        {"시스템 설정 저장에 성공하였습니다.", "The system settings have been saved successfully."},
        {"1 이상의 값을 입력하세요.", "Enter a value of 1 or more .."},
        {"의 IP 주소 형식이 올바르지 않습니다.", "in IP address format error"},
        {"설정이 변경되었습니다..\n \n"
                    + "[저장] 또는 [취소]버튼을 선택해 주세요.", 
            "Settings Changed.\n \n"
                    + "Either [Save] or [Cancel], please."
        },
        {"전광판 설정을 실패하였습니다.", "This e-board settings update saving DB operation failed."},
        {"  - 문구를 입력해주십시오.", "  - Please enter a message"},
        {"변경사항", "Changes"},
        {"변경 전", "Current"},
        {"변경 후", "Modified"},
        {"저장되었습니다.", "Saved"},
        {"아이디 혹은 비밀번호가 일치 하지 않습니다..", "ID or Password is wrong!"},
        {"\"아이디\"를 입력하세요.", "Enter \'Login ID\'!"},
        {"\"비밀번호\"를 입력하세요.", "Enter \'Password\'"},
        
        {"\"하위소속\"을 선택하지 않으셨습니다.\n"
            + "하위소속을 선택 하시겠습니까 ?\n"
            + "아니요 버튼을 누르게 되면 상위 소속이 초기화됩니다.\n", 
        "You did not select \'Lower Affiliation\'\n"
            + "Do you want to Select Lower Affiliation ?\n"
            + "If you choose \'no\',  Higher will be reset"},
        {"\"호실번호\"를 선택하지 않으셨습니다.\n"
            + "\"호실번호\" 선택 하시겠습니까 ?\n"
            + "아니요 버튼을 누르게 되면 건물 번호가 초기화됩니다.\n", 
        "You did not select \'Room\'\n"
            + "Do you want to Select Room ?\n"
            + "If you choose \'no\' button will delete the changes"},
        //</editor-fold>  
    };
    
    public static String[][] dialogTitle = {
        // <editor-fold defaultstate="collapsed" desc="-- DialogTitle"> 
        {"파일명 변경 필요성 알림", "Choose Different File Name"}, 
        {"이메일 중복 검사 결과", "Duplicate Check Result"}, 
        {"이메일 주소 검사 결과", "Syntax Check Result"},                 
        
        {"아이디 중복검사 결과", "ID Check Result"},               
        {"관리원 정보 수정 결과", "User Info Change Result"},         
        {"텍스트(.txt) 파일 생성", "Text File(*.txt) Creation"}, 
        {"비밀번호 요구조건", "Password Requirements"},
        
        
        {"차트 분석 결과", "Sheet Analysis Result"},                
        {"차트 형식 오류", "Sheet Cell Data Format Error"},       
        
        {"상위 소속 변경", "Higher Affiliation Change"},  
        
        {"건물 번호 변경 확인", "Building No. Change Confirm'"},
        {"소속 명칭 변경", "Change Low Affil' Confirm'"},
        {"호실 변경", "Room Number Change Confirm'"},
        
        {"중복 값 입력 오류", "Duplicate Data Error'"},
        {"차량 필수 자료 오류", "Required Field Missing"},
        
        {"차량 변경 실패", "Vehicle Modification Failure"},
        {"경고", "WARNING"},
        {"오류", "Error"},
        
        {"작업 모드 환기", "Current Work Mode"},
        
        
        {"생성 결과", "Creation Result"},
        {"생성 실패", "Creation Error" },               
        
        {"전체 삭제 확인", "All Record Deletion Confirmation"},
        {"전체 삭제 결과", "All Record Deletion Result"},
        
        {"삭제 확인", "Deletion Confirmation"},
        {"삭제 결과", "Deletion Result"},
        {"삭제 실패", "Deletion Failure"},
        
        {"수정 확인", "Modification Confirm"},
        {"수정 실패", "Modification Failure"},
        
        {"취소 확인", "Cancel Confirmation"},
        
        {"저장 확인", "Save Confirmation"},
        
        {"확인", "Confirm"},
        {"어떤것들이 로깅되는가 ?", "What is being LOGGED?"},
        {"비밀번호 요구사항", "Password Requirements"},
        {"언어 사용", "Language Usage"},
        
        {"통계 주기 입력 오류", "Statistics Cycle Input Error"},
        {"사진 크기 입력 오류", "Picture Size Input Error"},
        {"IP주소 형식 오류", "IP address format error"},
        //</editor-fold>  
    };
    
    public static String[][] menuItem = {
         // <editor-fold defaultstate="collapsed" desc="-- MenuItem"> 
        {"<HTML>주차기록(<U>A</U>)</HTML>", "<HTML><U>A</U> Records</HTML>"},
        {"입차기록", "Arrival"},
        {"실행기록", "Booting"},
        {"로그인기록", "Login"},
        {"<HTML>등록차량(<U>V</U>)</HTML>", "<HTML><U>V</U>ehicles</HTML>"},
        {"차량관리", "Vehicle"},
        {"운전자", "Driver"},
        {"<HTML>건물소속(<U>F</U>)</HTML>", "<HTML>A<U>f</U>filiation</HTML>"},
        {"목록관리", "Manage"},
        {"<HTML>사용자(<U>U</U>)</HTML>", "<HTML><U>U</U>sers</HTML>"},
        {"<HTML>실행명령(<U>S</U>)</HTML>", "<HTML><U>S</U>ystem</HTML>"},
        {"설정", "Settins"},
        {"종료", "Quit"},
        {"<HTML>로그인(<U>I</U>)</HTML>", "<HTML>Log <U>I</U>n</HTML>"},
        {"<HTML>로그아웃(<U>O</U>)</HTML>", "<HTML>Log <U>O</U>ut</HTML>"},
        {"로그인", "Log In"},
        {"로그아웃", "Log Out"},
        {"매니저 :", "Manager :"},
        //</editor-fold>
    };
}

