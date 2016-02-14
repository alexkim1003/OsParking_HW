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

/**
 *
 * @author Open Source Parking Inc.
 */
public class GUI_Language {
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
}
