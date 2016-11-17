/*
 * @Class : IndexController
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 메인 페이지로 가기 위한 컨트롤러.
 * Return 기능밖에 하지 않음.
*/
package kr.or.initspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/*
	 * @method Name : goHome
	 * @Author : 권기엽
	 * @description
	 * home.index 를 tiles resolver로 반환
	*/
	@RequestMapping("/index.htm")
	public String goHome(){
		return "home.index";
	}
}
