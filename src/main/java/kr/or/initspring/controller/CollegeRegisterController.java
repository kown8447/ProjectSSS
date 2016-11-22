/*
 * @Class : CollegeRegisterController
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 학적 조회 컨트롤러.
 * 학생 / 관리자 / 교수 권한을 가진 사용자들이 사용할 수 있도록 security 검증.
*/
package kr.or.initspring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.collegeRegister.RecordRequestDTO;
import kr.or.initspring.service.CollegeStudentService;



@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/collegeregister/")
public class CollegeRegisterController {
	@Autowired
	private CollegeStudentService  collegestudentservice;
	
	@Autowired
	private View jsonview;
	/*
	 * @method Name : viewMemberInfo
	 * @Author : 권기엽, 최준호
	 * @description : 학생용 Mypage로 연결하는 함수
	*/
	@RequestMapping(value="viewmember.htm")
	public String viewMemberInfo(Principal principal){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		return "collegeregister.memberInfo";
	}
	
	/*
	 * @method Name : viewMemberInfo
	 * @Author : 권기엽,최준호
	 * @description : 학생의 기본 학적 정보를 보여주는 함수
	*/
	@RequestMapping(value="viewstudent.htm")
	public String viewStudentInfo(Principal principal, Model model){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		collegestudentservice.viewStudentInfo(principal.getName(),model);
		
		return "collegeregister.studentInfo";
	}
	
	/*
	 * @method Name :  viewStudentRecordInfo
	 * @Author : 최준호
	 * @description : 학생 본인의 성적을 비동기 통신으로 받아오는함수
	*/
	@RequestMapping(value="record.htm")
	public String viewStudentRecordInfo(Principal principal, Model model){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		collegestudentservice.viewStudentRecordInfo(principal.getName(),model);
		return "collegeregister.studentRecord";
	}
	
	/*
	 * @method Name : viewStudentRecordAjax
	 * @Author : 최준호
	 * @description : 학생 본인의 성적을 비동기 통신으로 받아오는함수
	*/
	@RequestMapping(value="StudentRecordAjax.htm")
	public  View viewStudentRecordAjax(RecordRequestDTO recordrequest,Principal principal, Model model){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		collegestudentservice.viewStudentRecordAjax(recordrequest,principal.getName(),model);
		return jsonview;
	}
	/*
	 * @method Name : viewRegisterInfo
	 * @Author : 최준호
	 * @description : 등록/장학기록을 확인할수 있는 함수
	*/
	@RequestMapping(value="register.htm")
	public String viewRegisterInfo(Principal principal, Model model){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		collegestudentservice.viewRegisterInfo(principal.getName(),model);
		return "collegeregister.register";
	}
}
