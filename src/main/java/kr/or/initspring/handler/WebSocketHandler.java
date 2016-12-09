/*
 * @Class : WebSocketHandler
 * @Date : 2016.12.09
 * @Author : 권기엽
 * @Desc
 * 수강 신청 대기열 표시를 위한 WebSocketHandler. 스케쥴러를 사용하여 클라이언트에게 지속적으로 메시지를 송신함
*/
package kr.or.initspring.handler;


import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.initspring.service.RequestCourseService;


public class WebSocketHandler extends TextWebSocketHandler {

	private ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
	
	private static Map<String, Object> allSessions;

	@Autowired
	private RequestCourseService requestCourseService;

	/*
	 * @method Name : afterConnectionEstablished
	 * @Author : 권기엽
	 * @description
	 * 클라이언트가 서버에 접속된 이후, SendData 함수를 스케줄러를 사용하여 주기적으로 송신하는 함수
	*/
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

	/*
	 * @method Name : sendData
	 * @Author : 권기엽
	 * @description
	 * 자신의 대기 순번을 가져와서 클라이언트에게 송신하는 함수
	*/
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
