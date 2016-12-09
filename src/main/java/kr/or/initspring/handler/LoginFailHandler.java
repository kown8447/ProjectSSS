/*
 * @Class : LoginFailHandler
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * Security 에서 Login 실패 커스텀을 관장하는 핸들러.
*/

package kr.or.initspring.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.or.initspring.dao.LoginDAO;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * @method Name : onAuthenticationFailure
	 * @Author : 권기엽, 김영빈
	 * @description
	 * 로그인 실패의 경우의 수에 따라, 뿌려질 메시지를 지정하는 함수
	*/
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		String encpwd = "";
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		String url = "login/loginFail.htm";
		
		int isValidId = 0;
		isValidId = logindao.isValidID(member_id);
		try{
			if(isValidId == 0){
				request.setAttribute("failmessage", "아이디가 없습니다." + "<br>"+ "회원가입 후 이용해 주세요.");
			}else{
				encpwd = logindao.getPwdByUserid(member_id);
				if(bCryptPasswordEncoder.matches(member_pwd, encpwd) == false){
					request.setAttribute("failmessage", "비밀번호가 일치하지 않습니다." + "<br>"+ "다시 로그인 해 주세요.");
				}
			}
		}catch(Exception e){
			System.out.println("LoginFailHandler / onAuthenticationFailure : " + e.getMessage());
		}
		forward(request,response,url);
	}

	/*
	 * @method Name : forward
	 * @Author : 권기엽, 김영빈
	 * @description
	 * 지정한 Url 로 forward 하는 함수
	*/
	public void forward(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
}
