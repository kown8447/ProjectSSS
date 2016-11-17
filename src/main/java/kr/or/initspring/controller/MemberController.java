/*
 * @Class : MemberContorller
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원정보과 관련된 로직을 처리하는 컨트롤러.
 * 회원 정보 열람, 수정, 삭제 등
*/

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

	/*
	 * @method Name : updatePwd
	 * @Author : 권기엽
	 * @description
	 * 회원의 비밀번호 찾기 이후, 임시비밀번호에서 정식 비밀번호로 바꾸는 함수
	 * 해당 함수가 정상적으로 진행될 경우, 비밀번호 변경 및 member_temp 컬럼의 값이 0 으로 변경됨
	*/
	@RequestMapping(value="updatePwd.htm", method=RequestMethod.POST)
	public String updatePwd(String member_pwd, Principal principal) throws Exception{
		String member_id = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(member_pwd);
		boolean result = false;
		String viewpage = "";
		result = memberservice.updatePwd(member_id, pwd);
		
		if(result==true){
			viewpage = "redirect:/index.htm";
		}else{
			viewpage = "redirect:updatePwd.htm";
		}
		return viewpage;
	}
}
