package kr.or.initspring.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.CodeMgDTO;
import kr.or.initspring.dto.MemberTestDTO;
import kr.or.initspring.service.JoinService;

import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Controller
@RequestMapping("/join/")
public class JoinController {

	@Autowired
	private JoinService joinservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private View jsonview;
	
	
	@RequestMapping(value="join1.htm", method=RequestMethod.GET)
	public String joinStep1(){
		return "join.joinStep1";
	}
	
	@RequestMapping(value="join1.htm", method=RequestMethod.POST)
	public View joinStep1(int code_type, String code_name, @DateTimeFormat(pattern="yyyy-MM-dd") Date code_birth, String code, 
			Model model, HttpServletRequest request){

		HttpSession session = request.getSession();
		boolean result = false;
		
		CodeMgDTO codemg = new CodeMgDTO();
		codemg.setCode(code);
		codemg.setCode_type(code_type);
		codemg.setCode_birth(code_birth);
		codemg.setCode_name(code_name);
		
		MemberTestDTO member = new MemberTestDTO();
		result = joinservice.joinCheck1(codemg);
		
		if(result){
			member.setCode(codemg.getCode());
			member.setCode_type(codemg.getCode_type());
			member.setName(codemg.getCode_name());
			member.setBirth(codemg.getCode_birth());
			session.setAttribute("member", member);
			model.addAttribute("result", "success");
		}else{
			model.addAttribute("result", "fail");
		}
		
		return jsonview;
	}
	
	@RequestMapping(value="join2.htm", method=RequestMethod.GET)
	public String joinStep2(){
		return "join.joinStep2";
	}
	
	@RequestMapping("authEmail.htm")
	public View authEmail(String email, HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		
		final String sessionID = session.getId();
		final String address = email;
		
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
		
		MemberTestDTO member = (MemberTestDTO)session.getAttribute("member");
		member.setEmail(email);
		
		System.out.println(member.toString());
		
		model.addAttribute("mailresult", "success");
		model.addAttribute("sessionID", sessionID);
		return jsonview;
	}
	
	@RequestMapping(value="join3.htm", method=RequestMethod.GET)
	public String joinStep3(){
		return "join.joinStep3";
	}
	
	@RequestMapping(value="join3.htm", method=RequestMethod.POST)
	public String joinStep3(MemberTestDTO member_temp, HttpServletRequest request, Model model){
		
		boolean result = false;
		String viewpage = "";
		HttpSession session = request.getSession();
		String pwd = bCryptPasswordEncoder.encode(member_temp.getPwd());
		MemberTestDTO member = (MemberTestDTO)session.getAttribute("member");
		member.setUserid(member_temp.getUserid());
		member.setPwd(pwd);
		
		try {
			result = joinservice.insertMember(member);
		} catch (Exception e) {
			System.out.println("joinController / joinStep3 : " + e.getMessage());
		}
		if(result){
			viewpage = "redirect:welcome.htm";
		}else{
			System.out.println("멤버가입 실패");
			viewpage = "redirect:join1.htm";
		}
		return viewpage;
	}
	
	@RequestMapping("checkID.htm")
	public View checkID(String userid, Model model){
		boolean result = false;
		
		result = joinservice.checkID(userid);
		
		if(result){
			model.addAttribute("checkresult", "fail");
		}else{
			model.addAttribute("checkresult", "success");
		}
		return jsonview;
	}
	
	@RequestMapping("welcome.htm")
	public String welcome(){
		return "join.welcome";
	}
	
}
