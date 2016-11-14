package kr.or.initspring.controller;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/collegeregister/")
public class CollegeRegisterController {

	@RequestMapping(value="viewmember.htm")
	public String viewMemberInfo(Principal principal){
		System.out.println("principal을 통한 userid 추출 : " + principal.getName());
		return "collegeregister.memberInfo";
	}
}
