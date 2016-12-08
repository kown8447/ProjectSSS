package kr.or.initspring.handler;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.initspring.service.RequestCourseService;


public class WebSocketHandler extends TextWebSocketHandler {

	//private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

	private ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
	
	private static Map<String, Object> allSessions;

	@Autowired
	private RequestCourseService requestCourseService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("클라이언트 접속 완료");

		allSessions = session.getAttributes();
		String member_id = (String) allSessions.get("member_id");

		try {
			
			System.out.println("session : " + session.getId());
			timer.scheduleAtFixedRate(() -> sendData(session, member_id), 0, 1, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			System.out.println("showTime : " + e.getMessage());
		}

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		try {
			System.out.println("afterConnectionClosed 실행.. +" + status);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("closeSession : " + e.getMessage());
		}
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
	}

	private void sendData(WebSocketSession session, String member_id) {
		List<String> data = null;

		try {
			data = requestCourseService.getWaiting();

			int count = data.indexOf(member_id);

			if(session.isOpen()){
				session.sendMessage(new TextMessage(Integer.toString(count)));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
