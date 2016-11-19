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
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.initspring.dto.NoticeDTO;
import kr.or.initspring.service.NoticeService;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
	
	@Autowired
	 private NoticeService noticeservice;
	
	//공시사항 글목록
	@RequestMapping("notice.htm")
	 public String notices(String pg , String f , String q , Model model) throws ClassNotFoundException , SQLException {
		/*List<NoticeDTO> list = noticeservice.notices(pg, f, q);
		model.addAttribute("list", list); */
		return "notice.notice";
	}
	
		// 글등록 화면 처리
		 @RequestMapping(value = "noticeWrite.htm", method = RequestMethod.GET)
		 public String noticeWrite() {
			 return "notice.noticeWrite";
		 }

		 // 글등록 처리(실제 글등록 처리)
		 @RequestMapping(value = "noticeWrite.htm", method = RequestMethod.POST)
		 public String noticeWrite(NoticeDTO noticedto, HttpServletRequest request)
		   throws IOException, ClassNotFoundException, SQLException {
			  
			  String url = "redirect:notice.htm";
			  try{
				  url = noticeservice.noticeWrite(noticedto, request);
			  }catch(Exception e){
				  System.out.println(e.getMessage());
			  }
			  return url;
		 }
}
