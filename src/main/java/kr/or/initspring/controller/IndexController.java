package kr.or.initspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index.htm")
	public String goHome(){
		return "home.index";
	}
}
