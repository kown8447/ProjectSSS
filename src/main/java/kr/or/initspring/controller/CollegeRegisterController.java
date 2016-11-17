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

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/collegeregister/")
public class CollegeRegisterController {

	/*
	 * @method Name : viewMemberInfo
	 * @Author : 권기엽
	 * @description : 사용자 정보를 확인할 수 있는 함수
	*/
	@RequestMapping(value="viewmember.htm")
	public String viewMemberInfo(Principal principal){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		return "collegeregister.memberInfo";
	}
}
