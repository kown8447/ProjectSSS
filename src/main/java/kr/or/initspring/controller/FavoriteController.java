package kr.or.initspring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.initspring.service.FavoriteService;

@Controller
@Secured({ "ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR" })
@RequestMapping("/favorite/")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteservice;
	
	@Autowired
	private View jsonview;

	@RequestMapping("config.htm")
	public String favoriteConfigView(Principal principal,Model model) {
		System.out.println("즐겨찾기 설정 페이지로");
		System.out.println("수정 테스트");
		favoriteservice.getFavariteConfigData(principal.getName(), model);
		return "favorite.config";
	}
	
}
