package kr.or.initspring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.initspring.service.MemberService;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberService memberservice;
	
	@RequestMapping(value="updatePwd.htm", method=RequestMethod.GET)
	public String updatePwd(){
		return "member.updatePwd";
	}
	
	@RequestMapping(value="updatePwd.htm", method=RequestMethod.POST)
	public String updatePwd(String password, Principal principal) throws Exception{
		String userid = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(password);
		boolean result = false;
		String viewpage = "";
		result = memberservice.updatePwd(userid, pwd);
		
		if(result==true){
			viewpage = "redirect:/index.htm";
		}else{
			viewpage = "redirect:updatePwd.htm";
		}
		return viewpage;
	}
}
