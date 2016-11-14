package kr.or.initspring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.initspring.dto.MemberTestDTO;
import kr.or.initspring.service.JoinService;


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
	
	@RequestMapping(value="join.htm", method=RequestMethod.GET)
	public String joinMember(){
		return "join.join";
	}
	
	@RequestMapping(value="join.htm", method=RequestMethod.POST)
	public String joinMember(MemberTestDTO member) throws Exception{
		String viewpage="";
		boolean result = false;
		
		Random rnd =new Random();
		StringBuffer buf =new StringBuffer();
		
		for(int i=0;i<20;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10))); 
		    }
		}

		final String address = member.getEmail();
		member.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		member.setRandkey(buf.toString());
		
		result = joinservice.insertMember(member);
		
		if(result){
			System.out.println("대기 가입 성공");
			final String randkey = buf.toString();
			final String userid = member.getUserid();
			final String code_type = Integer.toString(member.getCode_type());
			final MimeMessagePreparator preparator = new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(address);
					message.setFrom("kown8447@gmail.com");
					message.setSubject("SSS 회원가입 인증 메일");
					final Map<String, Object> model = new HashMap<String, Object>();
					model.put("randkey", randkey);
					model.put("userid", userid);
					model.put("code_type",code_type);
					final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, 
									"authMail.html","UTF-8",model);
					message.setText(text, true);
				}
			};
			this.mailSender.send(preparator);
			viewpage = "redirect:/index.htm";
		}else{
			System.out.println("멤버 가입 실패");
			viewpage = "redirect:join.htm";
		}
		return viewpage;
	}
	
	@RequestMapping("confirm.htm")
	public String joinConfirm(
			@RequestParam("randkey") String randkey,
			@RequestParam("userid") String userid,
			@RequestParam("code_type") int code_type
			) throws Exception
	{
		boolean result = joinservice.confirmMember(userid, randkey, code_type);
		String viewpage = "";
		if(result){
			System.out.println("회원 인증 확인. 가입 성공");
			viewpage = "join.welcome";
		}else{
			System.out.println("회원 인증 실패. 재가입 유도");
			viewpage = "redirect:join/join.htm";
		}
		return viewpage;
	}
}
