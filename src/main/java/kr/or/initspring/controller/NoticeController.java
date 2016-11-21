/*
 * @Class : NoticeController
 * @Date : 2016.11.18
 * @Author : 송아름
 * @Desc
 * 게시판 공지사항과 관련된 로직을 처리하는 컨트롤러.
 * 글쓰기, 글수정, 답글쓰기, 글삭제, 글 상세내용  기능 포함
*/
package kr.or.initspring.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.initspring.dto.notice.CustomerNoticeDTO;
import kr.or.initspring.service.NoticeService;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeservice;

	// 글목록보기
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) throws ClassNotFoundException, SQLException {
		// List<Notice> list = customerservice.notices(pg, f, q);
		// model.addAttribute("list", list);
		System.out.println("글목록");
		return "notice.notice";
	}

	// 글등록 화면 처리
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.GET)
	public String noticeWrite() {
		System.out.println("글 등록 화면 컨트롤러");
		return "notice.noticeWrite";
	}

	// 글등록 처리(실제 글등록 처리)
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.POST)
	public String noticeWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {
		
		System.out.println("실제 글 등록 처리 컨트롤러");
		
		cn.setAdmin_code(principal.getName());
		
		String url = "redirect:notice.htm";
		System.out.println(cn.toString());
		try {
			url = noticeservice.NoticeWrite(cn, request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;
	}

}
