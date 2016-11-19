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
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.initspring.dto.CodeMgDTO;
import kr.or.initspring.service.CodeService;
import kr.or.initspring.service.MemberService;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR"})
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private CodeService codeservice;
	
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
	
	@RequestMapping("code.htm")
	public String codeMg(){
		return "codemg.code";
		
	}
	
	@RequestMapping(value="codeRegister.htm", method=RequestMethod.POST)
	public String registerCode(CodeMgDTO code){
		System.out.println("코드 등록 컨트롤");
		int result = 0;
		String viewpage = "";
		
		result = codeservice.insertCode(code);
		
		if(result == 1){
			viewpage = "codemg.code";
		}
		
		return viewpage;
	}
	
	@RequestMapping("codelist.htm")
	public String viewCodeList(Model model){
		System.out.println("코드 리스트 보여주기 컨트롤");
		
		List<CodeMgDTO> codelist = codeservice.codelist();
		System.out.println(codelist);
		model.addAttribute("codelist", codelist);
		return "codemg.codelist";
	}
	
	@RequestMapping("codedetail.htm")
	public String detailCode(Model model, String code){
		System.out.println("코드 상세보기 컨트롤");
		
		CodeMgDTO codeDTO = codeservice.detailcode(code);
		System.out.println(codeDTO);
		model.addAttribute("code", codeDTO);
		return "codemg.codedetail";
	}
	
	@RequestMapping(value = "updateCode.htm", method=RequestMethod.POST)
	public String updateCode(Model model,  String code, int code_type, String code_name, Date code_birth){
		System.out.println("코드 수정하기 컨트롤");
		System.out.println(code + "/"+ code_type +"/"+ code_name +"/"+ code_birth);
		
		int result = 0;
		String viewpage = "";
		
		result = codeservice.updateCode(code, code_type, code_name, code_birth);
		CodeMgDTO codeDTO = new CodeMgDTO();
		
		codeDTO.setCode(code);
		codeDTO.setCode_type(code_type);
		codeDTO.setCode_name(code_name);
		codeDTO.setCode_birth(code_birth);
		
		model.addAttribute("code", codeDTO);
		
		if(result ==1){
			viewpage = "redirect:codelist.htm";
		}
		
		return viewpage;
	}
}
