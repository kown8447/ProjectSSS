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

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
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
	 * @description : 관리자가 설정한 시간 및 대상 학년에 따라 사용자에게 보여지는 페이지를 다르게 return 하는 함수
	*/
	@RequestMapping("preRegister.htm")
	public String preRegisterForm(Principal principal, Model model){
		String viewpage = "";
		String member_id = principal.getName();
		viewpage = requestCourseService.possiblePreRegister(member_id);
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
		map.put("keyword", keyword);
		
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
			@RequestParam("subject_code") String subject_code, Model model
			){
		System.out.println("subject_code : " + subject_code);
		OpenedLectureDTO subject_info = requestCourseService.getOpSubjectInfoBySubjectCode(subject_code);
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
		model.addAttribute("lists", lists);
		model.addAttribute("timetable_share", timetable_share);
		return jsonview;
	}
	
}
