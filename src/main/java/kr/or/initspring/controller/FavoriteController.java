package kr.or.initspring.controller;
/*
 * @Class : FavoriteController
 * @Date : 2016.11.30
 * @Author : 최준호
 * @Desc
 * 즐겨찾기 컨트롤러
 * 회원의 권할별로 서로 다른 즐겨찾기 메뉴 제공
*/
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

	/*
	 * @method Name : favoriteConfigView
	 * @Author : 최준호
	 * @description
	 *  회원을 즐겨찾기 설정 페이지로 이동
	 *  이동시키면서 즐겨찾기 대상이 될수 있는 링크 목록과 회원이 이전에 등록했던 즐겨찾기 링크를 가져온다
	 */
	@RequestMapping("config.htm")
	public String favoriteConfigView(Principal principal,Model model) {
		favoriteservice.getFavariteConfigData(principal.getName(), model);
		return "favorite.config";
	}
	
	/*
	 * @method Name : appendFavorite
	 * @Author : 최준호
	 * @description
	 * 비동기 통신으로 회원의 즐겨찾기 목록을 추가해주는 메소드
	 */
	@RequestMapping("favoriteAppend.htm")
	public View appendFavorite(Principal principal,String link_code,Model model) {
		boolean result=favoriteservice.appendFavorite(principal.getName(), link_code);
		model.addAttribute("result", result);
		return jsonview;
	}
	
	/*
	 * @method Name :  deleteFavorite
	 * @Author : 최준호
	 * @description
	 * 비동기통신으로 회원의 즐겨찾기를 지워주는 메소드
	 */
	@RequestMapping("favoriteDelete.htm")
	public View deleteFavorite(Principal principal,String link_code,Model model) {
		boolean result=favoriteservice.deleteFavarite(principal.getName(), link_code);
		model.addAttribute("result", result);
		return jsonview;
	}
	/*
	 * @method Name :  getFavoriteList
	 * @Author : 최준호
	 * @description
	 * 비동기 통신으로 회원의 즐겨찾기 목록을 가져오는 메소드
	 */
	@RequestMapping("favoriteCall.htm")
	public View getFavoriteList(Principal principal,Model model) {
		favoriteservice.getFavariteList(principal.getName(), model);
		return jsonview;
	}
	
	
}
