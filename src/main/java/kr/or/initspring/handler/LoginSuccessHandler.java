/*
 * @Class : LoginSuccessHandler
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * Security 에서 Login 성공 커스텀을 관장하는 핸들러.
*/

package kr.or.initspring.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import kr.or.initspring.dao.LoginDAO;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : onAuthenticationSuccess
	 * @Author : 권기엽
	 * @description
	 * 로그인을 성공하였으나, 회원상태가 임시 비밀번호 발급상태라면 비밀번호 변경창으로 유도.
	*/
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		String url = getReturnUrl(request,response);
		String member_id = request.getParameter("member_id");
		if(logindao.getMemberTempByUserid(member_id) == 1){
			response.sendRedirect(request.getSession().getServletContext().getContextPath()+"/member/edit.htm");
		}else{
			response.sendRedirect(url);
		}
	}

	public void forward(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
	/*
	 * @method Name : getReturnUrl
	 * @Author : 권기엽
	 * @description
	 * Security 를 타기 이전의 URL 을 기억하는 함수
	*/
	private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			return request.getSession().getServletContext().getContextPath();
		}
		return savedRequest.getRedirectUrl();
	}
	
}
