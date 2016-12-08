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
