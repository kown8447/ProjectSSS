/*
 * @Class : CustomHandShakeInterceptor
 * @Date : 2016.12.09
 * @Author : 권기엽
 * @Desc
 * Security 를 통해 접속한 클라이언트의 Session 정보를 가져오기 위해 사용되는 클래스
*/
package kr.or.initspring.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class CustomHandShakeInterceptor extends HttpSessionHandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}

	/*
	 * @method Name : beforeHandshake
	 * @Author : 권기엽
	 * @description
	 * 사용자가 로그인 한 이후, Security를 타기 이전 request 객체를 사용하여 session 정보를 가져옴
	*/
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		ServletServerHttpRequest ssre = (ServletServerHttpRequest) request;
		
		HttpServletRequest req = ssre.getServletRequest();
		
		String member_id = (String)req.getSession().getAttribute("member_id");
		
		attributes.put("member_id", member_id);
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	
}
