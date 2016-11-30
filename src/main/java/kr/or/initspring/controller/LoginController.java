/*
 * @Class : LoginController
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 로그인과 관련된 로직을 처리하는 컨트롤러.
 * 아이디 찾기, 비밀번호 찾기 기능 포함
*/

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
	
	/*
	 * @method Name : login
	 * @Author : 권기엽
	 * @description
	 * 기본 로그인 Form으로 Spring security도 이 Form 을 로그인 Form 으로 지정함 
	*/		
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String login() {
		return "login.login";
	}

	/*
	 * @method Name : loginFail
	 * @Author : 권기엽
	 * @description
	 * 로그인 실패시 보여줄 url 지정 
	*/	
	@RequestMapping("loginFail.htm")
	public String loginFail() {
		return "login.loginFail";
	}

	@RequestMapping(value="searchID.htm", method = RequestMethod.GET)
	public String searchID() {
		return "login/searchID";
	}

	/*
	 * @method Name : searchID
	 * @Author : 권기엽
	 * @description
	 * 비동기를 통한 유저 아이디 찾기.
	*/	
	@RequestMapping("searchIDajax.htm")
	public View searchID(String member_name, String member_email, Model model) {
		
		String member_id = null;

		System.out.println("비동기 요청 : " + member_email + "/" + member_name);

		member_id = loginservice.searchID(member_name, member_email);

		model.addAttribute("member_id", member_id);

		return jsonview;
	}

	@RequestMapping(value = "searchPwd.htm", method = RequestMethod.GET)
	public String searchPwd() {
		return "login/searchPwd";
	}

	/*
	 * @method Name : searchPwd
	 * @Author : 권기엽
	 * @description
	 * 비동기를 통한 유저 비밀번호 찾기.
	 * 사용자가 입력한 아이디와 이메일을 대조하여 유효한 메일인지 확인함.
	 * 이메일이 유효할 경우, 15자리의 랜덤 숫자+문자+특수문자 조합 문자열을 생성하여 메일로 전송.
	 * velocityEngine을 사용하여 html template를 이메일로 전송.
	*/	
	@RequestMapping(value = "searchPwd.htm", method = RequestMethod.POST)
	public View searchPwd(String member_id, String member_email, Model model) {
		final String address = member_email;
		final String temp_pwd = getRandomPassword(15);
		boolean result = false;
		
		String confirmEmail = loginservice.getEmailByUserid(member_id);
		
		System.out.println("member_email : " + member_email);
		System.out.println("confirmEmail : " + confirmEmail);
		
		if(member_email.equals(confirmEmail)){
			try {
				result = loginservice.updatePwd(member_id, bCryptPasswordEncoder.encode(temp_pwd));
				
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

	/*
	 * @method Name : getRandomPassword
	 * @Author : 권기엽
	 * @description
	 * 이메일로 전송할 임시 비밀번호 생성
	*/	
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
