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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.or.initspring.dao.LoginDAO;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		String userid = request.getParameter("userid");
		System.out.println("핸들러에서 userid : " + userid);
		int joinstate;
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		String url = "login/loginFail.htm";
		
		try {
			joinstate = logindao.getJoinstateByUserid(userid);
			if(joinstate == 0){
				System.out.println("승인 대기 상태입니다.");
				request.setAttribute("failmessage", "가입 승인 대기 상태입니다. 인증 메일을 확인해 주세요.");
				forward(request,response,url);
			}else if(joinstate == 1){
				System.out.println("비밀번호 또는 아이디가 일치하지 않거나 권한이 없음");
				request.setAttribute("failmessage", "아이디 또는 비밀번호가 틀립니다. 다시 확인해 주세요.");
				forward(request,response,url);
			}else{
				System.out.println("예기치 못한 에러");
				request.setAttribute("failmessage", "인증 오류입니다.지속될 경우 관리자에게 문의 부탁드립니다.");
				forward(request,response,url);
			}
		} catch (Exception e) {
			System.out.println("LoginFailHandler / onAuthenticationFailure : " + e.getMessage());
			if(e instanceof NullPointerException || e instanceof SQLException){
				System.out.println("아이디가 없음");
				request.setAttribute("failmessage", "아이디가 없습니다. 회원가입 후에 이용 부탁드립니다.");
				forward(request,response,url);
			}
		} 
	}

	public void forward(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
}
