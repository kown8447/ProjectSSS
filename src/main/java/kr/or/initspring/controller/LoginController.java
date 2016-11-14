package kr.or.initspring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.service.LoginService;

@Controller
@RequestMapping("/login/")
public class LoginController {

	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private View jsonview;
	
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login(){
		return "login.login";
	}
	
	@RequestMapping("loginFail.htm")
	public String loginFail(){
		return "login.loginFail";
	}
	
	@RequestMapping("searchID.htm")
	public String searchID(){
		return "login.searchID";
	}
	
	@RequestMapping("searchIDajax.htm")
	public View searchID(String name, String email, Model model){
		
		String userid = null;
		
		System.out.println("비동기 요청 : " + email + "/" + name);
		
		userid = loginservice.searchID(name, email);
		
		model.addAttribute("userid", userid);
		
		return jsonview;
	}
}
