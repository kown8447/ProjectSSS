/*
 * @Class : RequestCouresController
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc
 * 수강신청 관련 정보를 제어하는 컨트롤러
 * 개설 강의 리스트 확인(전공/교양에 따른 검색 가능)
 * 예비수강 / 타학생 예비 시간표 조회 / 본수강 신청 / 수강 정정 / 경쟁률 확인
*/

package kr.or.initspring.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;
import kr.or.initspring.service.RequestCourseService;

@Controller
@Secured({ "ROLE_STUDENT" })
@RequestMapping("/requestcourse/")
public class RequestCourseController {

	@Autowired
	private RequestCourseService requestCourseService;
	
	@Autowired
	private View jsonview;

	@RequestMapping("courseMain.htm")
	public String courseMain() {
		return "requestCourse.courseMain";
	}

	/*
	 * @method Name : viewOpLecture
	 * @Author : 권기엽
	 * @description : 개설 강의 전체 목록을 보여주는 함수
	*/
	@RequestMapping("viewOpLecture.htm")
	public String viewOpLecture(Model model) {
		List<OpenedLectureDTO> lists = requestCourseService.viewOpLecture();
		List<CollegeDTO> college = requestCourseService.viewCollegeList();

		model.addAttribute("lists", lists);
		model.addAttribute("colleges", college);
		return "requestCourse.viewOpLecture";
	}
	
	/*
	 * @method Name : viewDepartmentList
	 * @Author : 권기엽
	 * @description : 단과대학 코드에 따른 소속 학과/학부를 출력하는 함수
	*/
	@RequestMapping("viewDepartmentList.htm")
	public View viewDepartmentList(
			@RequestParam(value="college_code", required=false) String college_code, 
			Model model){
		List<DepartmentDTO> department = requestCourseService.viewDepartmentList(college_code);
		model.addAttribute("result", department);
		return jsonview;
	}
	
	/*
	 * @method Name : searchSubject
	 * @Author : 권기엽
	 * @description : 학과/학부 코드에 따른 개설강의 리스트를 비동기로 뿌려주는 함수
	*/
	@RequestMapping("searchSubject.htm")
	public View searchSubject(
			@RequestParam(value="department_code", required=false) String department_code,
			Model model
			){		
		System.out.println("department_code : " + department_code);
		HashMap<String, String> keyword = new HashMap<String, String>();
		keyword.put("department_code", department_code);
		List<OpenedLectureDTO> lists = requestCourseService.searchSubject(keyword);
		model.addAttribute("lists", lists);
		return jsonview;
	}
	
	
	/*
	 * @method Name : searchOpLectureOrderbySubjectName
	 * @Author : 권기엽
	 * @description : 과목 이름 정렬을 통한 개설과목 리스트 출력
	*/
	@RequestMapping("searchOpLectureOrderbySubjectName.htm")
	public View searchOpLectureOrderbySubjectName(
			@RequestParam(value="department_code", required=false) String department_code,
			@RequestParam(value="order_by", required=false, defaultValue="asc") String order_by,
			Model model
			){		
		HashMap<String, String> keyword = new HashMap<String, String>();
		keyword.put("department_code", department_code.trim());
		keyword.put("order_by", order_by.trim());
		
		List<OpenedLectureDTO> lists = requestCourseService.searchOpLectureOrderbySubjectName(keyword);
		model.addAttribute("searchList", lists);
		return jsonview;
	}
	
	
	/*
	 * @method Name : searchOpLectureOrderbySubjectName
	 * @Author : 권기엽
	 * @description : 교수명  정렬을 통한 개설과목 리스트 출력
	*/
	@RequestMapping("searchOpLectureOrderbyProfessorName.htm")
	public View searchOpLectureOrderbyProfessorName(
			@RequestParam(value="department_code", required=false) String department_code,
			@RequestParam(value="order_by", required=false, defaultValue="asc") String order_by,
			Model model
			){
		HashMap<String, String> keyword = new HashMap<String, String>();
		keyword.put("department_code", department_code.trim());
		keyword.put("order_by", order_by.trim());
		
		
		List<OpenedLectureDTO> lists = requestCourseService.searchOpLectureOrderbyProfessorName(keyword);
		model.addAttribute("searchList", lists);
		return jsonview;
	}
	
	/*
	 * @method Name : download
	 * @Author : 권기엽
	 * @description : 파일 다운로드 함수
	*/
	@RequestMapping("download.htm")
	public void download(String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String fname = new String(f.getBytes("euc-kr"), "8859_1");
		System.out.println(fname);
		response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");
		String fullpath = request.getServletContext().getRealPath("/files/lecturePlan/" + f);
		System.out.println(fullpath);
		FileInputStream fin = new FileInputStream(fullpath);
		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fin.read(buf, 0, buf.length)) != -1){
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}
	
	/*
	 * @method Name : preRegisterForm
	 * @Author : 권기엽
	 * @description : 관리자가 설정한 시간 및 대상 학년에 따라 사용자에게 보여지는 페이지를 다르게 return 하는 함수(예비 수강 신청)
	*/
	@RequestMapping("preRegister.htm")
	public String preRegisterForm(Principal principal, Model model){
		String viewpage = "";
		String member_id = principal.getName();
		viewpage = requestCourseService.possiblePreRegister(member_id);
		return viewpage;
	}
	
	/*
	 * @method Name : realRegiserForm
	 * @Author : 권기엽
	 * @description : 관리자가 설정한 시간 및 대상 학년에 따라 사용자에게 보여지는 페이지를 다르게 return 하는 함수(본 수강 신청)
	*/
	@RequestMapping("realRegiser.htm")
	public String realRegiserForm(Principal principal, Model model){
		String viewpage = "";
		String member_id = principal.getName();
		viewpage = requestCourseService.possibleRealRegister(member_id);
		return viewpage;
	}
	
	/*
	 * @method Name : correctRegiserForm
	 * @Author : 권기엽
	 * @description : 수강 정정 페이지 
	*/
	@RequestMapping("correctRegiser.htm")
	public String correctRegiserForm(Principal principal, Model model){
		String viewpage = "";
		String member_id = principal.getName();
		viewpage = requestCourseService.possibleCorrectRegister(member_id);
		return viewpage;
	}
	
	/*
	 * @method Name : searchBykeword
	 * @Author : 권기엽
	 * @description : 검색 키워드에 따라 개설강의 과목을 보여주는 함수. keyword가 없을 경우 기본적으로 과목명으로 검색하며, 전체 목록이 보여짐
	*/
	@RequestMapping("searchBykeword.htm")
	public View searchBykeword(
			@RequestParam(value="searchType", defaultValue="subject_name") String searchType,
			@RequestParam(value="keyword", defaultValue="") String keyword,
			Model model
			){
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("keyword", keyword.toUpperCase());
		
		List<OpenedLectureDTO> lists = requestCourseService.searchByKeyword(map);
		model.addAttribute("lists",lists);
		
		return jsonview;
	}
	
	/*
	 * @method Name : getOpSubjectInfo
	 * @Author : 권기엽
	 * @description : 과목 목록에서 과목정보를 클릭했을 때 modal 창에 뿌려질 정보를 비동기로 처리하는 함수
	*/
	@RequestMapping("getOpSubjectInfo.htm")
	public View getOpSubjectInfo(
			@RequestParam("subject_code") String subject_code, Model model, Principal principal
			){
		System.out.println("subject_code : " + subject_code);
		OpenedLectureDTO subject_info = requestCourseService.getOpSubjectInfoBySubjectCode(subject_code, principal.getName());
		model.addAttribute("subject_info", subject_info);
		return jsonview;
	}
	
	/*
	 * @method Name : getSubjectCredit
	 * @Author : 권기엽
	 * @description : 과목의 배정학점을 가져오는 함수. 배정학점을 더해서 21학점을 넘지 않게 계산하기 위함
	*/
	@RequestMapping("getSubjectCredit.htm")
	public View getSubjectCredit(Model model, @RequestParam("subject_code") String subject_code){
		
		int subject_credit = requestCourseService.getSubjectCredit(subject_code);
		model.addAttribute("subject_credit", subject_credit);
		return jsonview;
	}
	
	/*
	 * @method Name : checkGrade
	 * @Author : 권기엽
	 * @description : 과목의 제한 학년과 사용자의 학년을 비교하기 위한 함수. 사용자의 학년이 제한 학년보다 낮을 경우 수강할 수 없음
	*/
	@RequestMapping("checkGrade.htm")
	public View checkGrade(Principal principal, Model model,
			@RequestParam("subject_code") String subject_code){
		boolean result = false;		
		result = requestCourseService.checkGrade(principal.getName(), subject_code);
		model.addAttribute("result", result);
		return jsonview;
	}
	
	/*
	 * @method Name : checkBeforeSubject
	 * @Author : 권기엽
	 * @description : 선수과목을 체크하기 위한 함수. 학생이 선수과목을 이수하지 않을 경우 수강할 수 없음
	*/
	@RequestMapping("checkBeforeSubject.htm")
	public View checkBeforeSubject(Principal principal, Model model,
			@RequestParam("subject_code") String subject_code
			){
		int result = 0;
		result = requestCourseService.checkBeforeSubject(principal.getName(), subject_code);
		model.addAttribute("result", result);
		model.addAttribute("subject_code", subject_code);
		return jsonview;
	}
	
	/*
	 * @method Name : requestReserve
	 * @Author : 권기엽
	 * @description : 수강신청 버튼을 클릭했을 때 발생하는 이벤트. 수강신청 테이블에 정보를 삽입한다.
	*/
	@RequestMapping("requestReserve.htm")
	public View requestReserve(Principal principal, Model model, 
			@RequestParam("subject_codes[]") ArrayList<String> subject_codes,
			@RequestParam("timetable_share") int timetable_share) throws Exception{

		boolean result = false;
		result = requestCourseService.requestReserve(principal.getName(), subject_codes, timetable_share);
		model.addAttribute("result", result);
		return jsonview;
	}
	
	/*
	 * @method Name : getPreTimetable
	 * @Author : 권기엽
	 * @description : 예비수강 신청 페이지 로딩시 비동기 처리로 예비수강신청 시간표 보여주는 함수, 시간표 공유 여부도 함께 출력
	*/
	@RequestMapping("getPreTimetable.htm")
	public View getPreTimetable(Principal principal, Model model){
		List<OpenedLectureDTO> lists = requestCourseService.getPreTimetable(principal.getName());
		int timetable_share = requestCourseService.getTimetableShare(principal.getName());
		List<PeriodDTO> periodList = requestCourseService.getPeriodList();
		model.addAttribute("lists", lists);
		model.addAttribute("timetable_share", timetable_share);
		model.addAttribute("periodList", periodList);
		return jsonview;
	}
	
	/*
	 * @method Name : searchOtherTimetable
	 * @Author : 권기엽
	 * @description : 타학생 시간표 조회 화면 이동
	*/
	@RequestMapping("searchOtherTimetable.htm")
	public String searchOtherTimetable(){
		return "requestCourse.searchOtherTimetable";
	}
	
	/*
	 * @method Name : checkTimetableShare
	 * @Author : 권기엽
	 * @description : 사용자의 시간표 공유 여부 확인
	*/
	@RequestMapping("checkTimetableShare.htm")
	public View checkTimetableShare(Principal principal, Model model){
		int result = 0;
		String value="";
		result = requestCourseService.getTimetableShare(principal.getName());
		if(result==0){value="no";}
		else{value="yes";}
		model.addAttribute("share", value);
		return jsonview;
	}
	
	/*
	 * @method Name : checkStudentCode
	 * @Author : 권기엽
	 * @description : 조회할 학생의 학번이 유효한지 체크
	*/
	@RequestMapping("checkStudentCode.htm")
	public View checkStudentCode(@RequestParam("student_code") String student_code, Model model){
		boolean result = false;
		result = requestCourseService.checkStudentCode(student_code);
		String value="";
		if(result==true){value="yes";}
		else{value="no";}
		model.addAttribute("check_code", value);
		return jsonview;
	}
	
	/*
	 * @method Name : checkOthersShare
	 * @Author : 권기엽
	 * @description : 조회 대상의 학생이 시간표 공유를 허용했는지 확인
	*/
	@RequestMapping("checkOthersShare.htm")
	public View checkOthersShare(@RequestParam("student_code") String student_code, Model model){
		boolean result = false;
		result = requestCourseService.checkOthersShare(student_code);
		String value="";
		if(result==true){value="approve";}
		else{value="deny";}
		model.addAttribute("check_share", value);
		return jsonview;
	}
	
	/*
	 * @method Name : loadOtherTimetable
	 * @Author : 권기엽
	 * @description : 조회한 학생의 시간표 정보 가져오기
	*/
	@RequestMapping("loadOtherTimetable.htm")
	public View loadOtherTimetable(@RequestParam("student_code") String student_code, Model model){
		List<OpenedLectureDTO> lists = requestCourseService.loadOtherTimetable(student_code);
		List<PeriodDTO> periodList = requestCourseService.getPeriodList();
		model.addAttribute("otherTimetables", lists);
		model.addAttribute("periodList", periodList);
		return jsonview;
	}
	
	
	/*
	 * @method Name : getRealTimetable
	 * @Author : 권기엽
	 * @description : 본 수강 신청 페이지 로딩시 비동기 처리로 예비수강신청 시간표 보여주는 함수, 수강 신청 실패 과목은 시간표에서 제외시키고, 검색 폼 최상단에 출력
	*/
	@RequestMapping("getRealTimetable.htm")
	public View getRealTimetable(Principal principal, Model model){
		List<OpenedLectureDTO> lists = requestCourseService.getRealTimetable(principal.getName());
		List<PeriodDTO> periodList = requestCourseService.getPeriodList();
		List<OpenedLectureDTO> failedLists = requestCourseService.getFailedList(principal.getName());
		model.addAttribute("lists", lists);
		model.addAttribute("periodList", periodList);
		model.addAttribute("failedLists", failedLists);
		return jsonview;
	}
	
	/*
	 * @method Name : insertRealDbSubject
	 * @Author : 권기엽
	 * @description : 본 수강 신청에서 과목 등록 요청했을 때 처리. 정원 이상일 경우 DB에 Insert 할 수 없다.
	*/
	@RequestMapping("insertRealDbSubject.htm")
	public View insertRealDbSubject(Principal principal, Model model,
			@RequestParam("subject_code")String subject_code) throws Exception{
		HashMap<String, String> map = requestCourseService.insertRealDbSubject(principal.getName(), subject_code);
		model.addAttribute("map", map);
		return jsonview;
	}
	
	
	/*
	 * @method Name : deleteSubject
	 * @Author : 권기엽
	 * @description : 시간표를 클릭했을 때 과목을 삭제하고 해당 과목의 학점을 리턴하는 함수
	*/
	@RequestMapping("deleteSubject.htm")
	public View deleteSubject(Model model, @RequestParam("subject_code") String subject_code, Principal principal) throws Exception{
		
		int subject_credit = requestCourseService.deleteSubject(principal.getName(), subject_code);
		model.addAttribute("subject_credit", subject_credit);
		return jsonview;
	}
	
	
	/*
	 * @method Name : getRealTimetable
	 * @Author : 권기엽
	 * @description : 본 수강 신청 페이지 로딩시 비동기 처리로 예비수강신청 시간표 보여주는 함수, 수강 신청 실패 과목은 시간표에서 제외시키고, 검색 폼 최상단에 출력
	*/
	@RequestMapping("getCorrectTimetable.htm")
	public View getCorrectTimetable(Principal principal, Model model){
		List<OpenedLectureDTO> lists = requestCourseService.getRealTimetable(principal.getName());
		List<PeriodDTO> periodList = requestCourseService.getPeriodList();
		model.addAttribute("lists", lists);
		model.addAttribute("periodList", periodList);
		return jsonview;
	}
}
