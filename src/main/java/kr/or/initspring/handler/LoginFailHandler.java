package kr.or.initspring.handler;

import java.io.IOException;
import java.sql.SQLException;

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
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String encpwd = "";
		System.out.println("핸들러에서 userid : " + userid);
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		String url = "login/loginFail.htm";
		
		int isValidId = 0;
		isValidId = logindao.isValidID(userid);
		try{
			if(isValidId == 0){
				request.setAttribute("failmessage", "아이디가 없습니다. 회원가입 후 이용해 주세요.");
			}else{
				encpwd = logindao.getPwdByUserid(userid);
				if(bCryptPasswordEncoder.matches(pwd, encpwd) == false){
					request.setAttribute("failmessage", "비밀번호가 일치하지 않습니다. 다시 로그인 해 주세요.");
				}
			}
		}catch(Exception e){
			System.out.println("LoginFailHandler / onAuthenticationFailure : " + e.getMessage());
		}
		forward(request,response,url);
	}

	public void forward(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
}
