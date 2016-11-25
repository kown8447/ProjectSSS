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
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.initspring.service.MemberService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
@Controller
public class IndexController {
	@Autowired
	private MemberService memberservice;
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
	}
}
