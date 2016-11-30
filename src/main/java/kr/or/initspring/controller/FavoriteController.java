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
		favoriteservice.getFavariteConfigData(principal.getName(), model);
		return "favorite.config";
	}
	
	@RequestMapping("favoriteAppend.htm")
	public View appendFavorite(Principal principal,String link_code,Model model) {
		boolean result=favoriteservice.appendFavorite(principal.getName(), link_code);
		model.addAttribute("result", result);
		return jsonview;
	}
	
	@RequestMapping("favoriteDelete.htm")
	public View deleteFavorite(Principal principal,String link_code,Model model) {
		boolean result=favoriteservice.deleteFavarite(principal.getName(), link_code);
		model.addAttribute("result", result);
		return jsonview;
	}
	
	@RequestMapping("favoriteCall.htm")
	public View getFavoriteList(Principal principal,Model model) {
		favoriteservice.getFavariteList(principal.getName(), model);
		return jsonview;
	}
	
	
}
