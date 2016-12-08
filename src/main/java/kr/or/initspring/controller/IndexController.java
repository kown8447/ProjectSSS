/*
 * @Class : IndexController
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 메인 페이지로 가기 위한 컨트롤러.
 * Return 기능밖에 하지 않음.
*/
package kr.or.initspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;
import kr.or.initspring.service.MemberService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Controller
public class IndexController {
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private View jsonview;
	
	/*
	 * @method Name : goHome
	 * @Author : 권기엽
	 * @description
	 * home.index 를 tiles resolver로 반환
	*/
	@RequestMapping("/login.htm")
	public String goHome(){
		return  "login.login";
	}
	/*
	    * @method Name : goLoginHome
	    * @Author : 김영빈
	    * @description
	    * 로그인한 멤버에 따라 principal 을 parameter 로 받아 아이디를 얻은뒤 
	    * 아이디로 권한을 찾아 화면을 다르게 보여준다.
	   */
	@RequestMapping("/index.htm")
	public String goLoginHome(Principal principal) {
		
		try{
			String member_id = principal.getName();
			if (memberservice.getRole(member_id).equals("ROLE_STUDENT")) {
				return "home.student_Main";
			} else if (memberservice.getRole(member_id).equals("ROLE_PROFESSOR")) {
				return "home.professor_Main";
			} else if (memberservice.getRole(member_id).equals("ROLE_ADMIN")) {
				return "home.admin_Main";
			} else{
				return  "login.login";
			}
		}catch(NullPointerException e){
			return "login.login";
		}
	}
	
	/*
	 * @method Name : goTimetableForm
	 * @Author : 권기엽
	 * @description 시간표 형태 출력을 위한 페이지 이동
	 */
	@RequestMapping(value={"goTimetableForm.htm","*/goTimetableForm.htm"})
	@Secured({"ROLE_STUDENT"})
	public String goTimetableForm(){
		return "member/viewTimetable";
	}
	
	/*
	 * @method Name : viewCurrentTimetable
	 * @Author : 권기엽
	 * @description 시간표 조회 클릭 시, 비동기화로 화면에 시간표 출력
	 */
	@RequestMapping(value={"viewCurrentTimetable.htm","*/viewCurrentTimetable.htm"})
	@Secured({"ROLE_STUDENT"})
	public View viewCurrentTimetable(Model model, Principal principal){
		
		List<OpenedLectureDTO> lists = null;
		List<PeriodDTO> periodList = null;
		lists = memberservice.viewCurrentTimetable(principal.getName());
		periodList = memberservice.getPeriodList();
		
		model.addAttribute("lists", lists);
		model.addAttribute("periodList", periodList);
		return jsonview;
	}

	/*
	 * @method Name : Error404
	 * @Author : 권기엽
	 * @description 404page 링크
	 */
	@RequestMapping("/Error404.htm")
	public String Error404(){
		return "error/404Error";
	}
	
	/*
	 * @method Name : Error500
	 * @Author : 권기엽
	 * @description 500page 링크
	 */
	@RequestMapping("/Error500.htm")
	public String Error500(){
		return "error/500Error";
	}
	
	/*
	 * @method Name : ErrorEtc
	 * @Author : 권기엽
	 * @description 기타 에러 페이지 링크
	 */
	@RequestMapping("/ErrorEtc.htm")
	public String ErrorEtc(){
		return "error/Error";
	}
}
