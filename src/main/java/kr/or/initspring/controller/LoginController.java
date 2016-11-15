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
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.initspring.service.LoginService;

@Controller
@RequestMapping("/login/")
public class LoginController {

	@Autowired
	private LoginService loginservice;

	@Autowired
	private View jsonview;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "login.login";
	}

	@RequestMapping("loginFail.htm")
	public String loginFail() {
		return "login.loginFail";
	}

	@RequestMapping("searchID.htm")
	public String searchID() {
		return "login.searchID";
	}

	@RequestMapping("searchIDajax.htm")
	public View searchID(String name, String email, Model model) {

		String userid = null;

		System.out.println("비동기 요청 : " + email + "/" + name);

		userid = loginservice.searchID(name, email);

		model.addAttribute("userid", userid);

		return jsonview;
	}

	@RequestMapping(value = "searchPwd.htm", method = RequestMethod.GET)
	public String searchPwd() {
		return "login.searchPwd";
	}

	@RequestMapping(value = "searchPwd.htm", method = RequestMethod.POST)
	public View searchPwd(String userid, String email, Model model) {

		final String address = email;
		final String temp_pwd = getRandomPassword(15);
		boolean result = false;
		
		String confirmEmail = loginservice.getEmailByUserid(userid);
		
		if(email.equals(confirmEmail)){
			try {
				result = loginservice.updatePwd(userid, bCryptPasswordEncoder.encode(temp_pwd));
				
				if(result){
					final MimeMessagePreparator preparator = new MimeMessagePreparator() {
						@Override
						public void prepare(MimeMessage mimeMessage) throws Exception {
							final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
							message.setTo(address);
							message.setFrom("kown8447@gmail.com");
							message.setSubject("SSS 임시 비밀번호 발급 메일");
							final Map<String, Object> model = new HashMap<String, Object>();
							model.put("temp_pwd", temp_pwd);
							final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "tempPwd.html",
									"UTF-8", model);
							message.setText(text, true);
						}
					};
					this.mailSender.send(preparator);
					model.addAttribute("pwdresult", "success");
				}else{
					model.addAttribute("pwdresult", "fail");
				}
			} catch (Exception e) {
				System.out.println("LoginController / searchPwd " + e.getMessage());
				model.addAttribute("pwdresult", "error");
			}
		}else{
			model.addAttribute("pwdresult", "incorrect");
		}
		return jsonview;
	}

	public String getRandomPassword(int length) {
		char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '#',
				'^' };
		StringBuffer temp_pwd = new StringBuffer();
		Random rn = new Random();
		for (int i = 0; i < length; i++) {
			temp_pwd.append(charaters[rn.nextInt(charaters.length)]);
		}
		return temp_pwd.toString();
	}
}
