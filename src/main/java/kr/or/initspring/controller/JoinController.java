/*
 * @Class : JoinController
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원 가입을 위한 컨트롤러.
 * 회원 가입은 3단계로 진행하며, 2단계에서는 velocityEngine을 사용하여 이메일 인증 진행.
*/
package kr.or.initspring.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.dto.join.MemberDTO;
import kr.or.initspring.service.JoinService;

import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Controller
@RequestMapping("/join/")
public class JoinController {

	@Autowired
	private JoinService joinservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;	//암호화를 위한 인코더
	
	@Autowired
	private JavaMailSender mailSender;	//Email 전송을 위한 클래스
	
	@Autowired
	private VelocityEngine velocityEngine;	//HTML 파일을 통째로 전송하기 위한 velocity
	
	@Autowired
	private View jsonview;	//비동기 처리를 위한 jsonview 객체
	
	
	@RequestMapping(value="join.htm", method=RequestMethod.GET)
	public String joinStep(){
		return "join.joinStep";
	}
	   /*
	    * @method Name : joinStep
	    * @Author : 김영빈
	    * @description
	    * 처음 회원가입시 학생, 교수 ,관리자 중 택 1 
	   */
	@RequestMapping(value="join.htm", method=RequestMethod.POST)
	public View joinStep(int code_type,  
			Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		CodeMgDTO codemg = new CodeMgDTO();
		codemg.setCode_type(code_type);
		MemberDTO member = new MemberDTO();
		member.setCode_type(codemg.getCode_type());
		session.setAttribute("member", member);
		if(code_type==0 ||code_type==1||code_type==2){
			model.addAttribute("result", "success");
		}else{
			model.addAttribute("result", "fail");
		}
		
		return jsonview;
	}
	/*
	    * @method Name : joinStepRole
	    * @Author : 김영빈
	    * @description
	    * 권한에 따라 페이지를 다르게 함 
	   */
	@RequestMapping(value="join1.htm", method=RequestMethod.GET)
	public String joinStepRole(HttpServletRequest request){
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		if(member.getCode_type()==0){
			return "join.joinStep1_Student";
		}else if(member.getCode_type()==1){
			return "join.joinStep1_Professor";
		}else{
			return "join.joinStep1_Admin";
		}
	
		
	}
	   /*
	    * @method Name : joinStep1
	    * @Author : 김영빈
	    * @description
	    * 회원가입 1단계
	    * Code_MG 테이블 과의 데이터를 비교.
	    * code_type -> 학생/교수/관리자 비교
	    * code_name -> code_mg 테이블에 있는 이름
	    * code_birth -> code_mg 테이블에 있는 생년월일. yyyy-MM-dd 형식을 가지며, Java에서는 DATE Type으로 받음, format 함수 이용해서 date 형식 바꺼줌
	    * joincheck1 -> code_mg에 등록된 회원인지 확인 등록-> return true
	    * joincheck2 -> code_mg로 등록된 멤버가 있는지 확인 있으면  return false 
	   */   
	@RequestMapping(value = "join1.htm", method = RequestMethod.POST)
	public View joinStep1(String code_name, String code_year, String code_month, String code_day, String code,
			Model model, HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		boolean result = false;
		boolean result2 = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		java.util.Date parsed = format.parse(code_year + code_month + code_day);
		java.sql.Date code_birth = new java.sql.Date(parsed.getTime());

		CodeMgDTO codemg = new CodeMgDTO();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		codemg.setCode(code);
		codemg.setCode_type(member.getCode_type());
		codemg.setCode_birth(code_birth);
		codemg.setCode_name(code_name);

		result = joinservice.joinCheck1(codemg);
		result2 = joinservice.joinCheck2(codemg);
		if (result == true && result2 == true) {
			member.setCode(codemg.getCode());
			member.setMember_name(codemg.getCode_name());
			member.setMember_birth(codemg.getCode_birth());
			session.setAttribute("member", member);
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}

		return jsonview;
	}
	
	@RequestMapping(value="join2.htm", method=RequestMethod.GET)
	public String joinStep2(){
		return "join.joinStep2";
	}

	/*
	 * @method Name : authEmail
	 * @Author : 권기엽
	 * @description
	 * 이메일 인증을 위한 함수.
	 * 사용자로부터 입력받은 메일을 테이블과 대조하여, 유효한 메일인지 확인.
	 * 확인이 된 메일은 인증번호(sessionID())를 html에 담아서 발송 -> 발송시에는 velocityEngine을 사용하여 통째로 전송
	*/		
	@RequestMapping("authEmail.htm")
	public View authEmail(String member_email, HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		
		final String sessionID = session.getId();
		final String address = member_email;
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(address);
				message.setFrom("kown8447@gmail.com");
				message.setSubject("SSS 회원가입 인증 메일");
				final Map<String, Object> model = new HashMap<String, Object>();
				model.put("authkey", sessionID);
				final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, 
								"authMail.html","UTF-8",model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		member.setMember_email(member_email);
		
		model.addAttribute("mailresult", "success");
		model.addAttribute("sessionID", sessionID);
		return jsonview;
	}
	
	@RequestMapping(value="join3.htm", method=RequestMethod.GET)
	public String joinStep3(){
		return "join.joinStep3";
	}
	
	/*
	 * @method Name : joinStep3
	 * @Author : 권기엽
	 * @description
	 * 회원가입 3단계.
	 * MEMBER 테이블에 담길 나머지 정보를 받는 곳.
	 * 암호는 bCryptPasswordEncoder 를 통해서 암호화 하여 DB에 저장.
	*/	
	@RequestMapping(value="join3.htm", method=RequestMethod.POST)
	public String joinStep3(MemberDTO member_temp, HttpServletRequest request, Model model) throws IOException{
		boolean result = false;
		String viewpage = "";
		HttpSession session = request.getSession();
		String pwd = bCryptPasswordEncoder.encode(member_temp.getMember_pwd());
		String addr =member_temp.getAddr_num()+"?"+ member_temp.getMember_addr()+"?" + member_temp.getMember_addr_detail();
		CommonsMultipartFile file = member_temp.getFile();
		String filename = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/profiles");
		String fullpath = path + "\\" + filename;
		
		if (!filename.equals("")) {
			// 서버에 파일 쓰기 작업
			FileOutputStream fs = new FileOutputStream(fullpath);
			fs.write(file.getBytes());
			fs.close();
		}
		
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		
		member.setMember_id(member_temp.getMember_id());
		member.setMember_pwd(pwd);
		member.setMember_phone(member_temp.getMember_phone());
		member.setMember_sex(member_temp.getMember_sex());
		member.setMember_picture(filename);
		member.setMember_addr(addr);
		
		try {
			result = joinservice.insertMember(member);
		} catch (Exception e) {
			System.out.println("joinController / joinStep3 : " + e.getMessage());
		}
		if(result){
			viewpage = "redirect:/login.htm";
		}else{
			System.out.println("멤버가입 실패");
			viewpage = "redirect:join1.htm";
		}
		return viewpage;
	}
	
	
	

	/*
	 * @method Name : checkID
	 * @Author : 권기엽
	 * @description
	 * 비동기화를 통한 ID 중복 체크
	*/
	@RequestMapping("checkID.htm")
	public View checkID(String member_id, Model model){
		boolean result = false;
		
		result = joinservice.checkID(member_id);
		
		if(result){
			model.addAttribute("checkresult", "fail");
		}else{
			model.addAttribute("checkresult", "success");
		}
		return jsonview;
	}
	
	@RequestMapping("welcome.htm")
	public String welcome(){
		return "login.login";
	}
	

	
}
