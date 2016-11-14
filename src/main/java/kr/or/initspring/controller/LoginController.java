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

import kr.or.initspring.service.LoginService;

@Controller
@RequestMapping("/login/")
public class LoginController {

	@Autowired
	private LoginService loginservice;
	
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login(){
		return "login.login";
	}
	
	@RequestMapping("loginFail.htm")
	public String loginFail(){
		return "login.loginFail";
	}
}
