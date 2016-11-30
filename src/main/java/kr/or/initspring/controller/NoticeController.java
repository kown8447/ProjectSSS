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
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.initspring.dto.notice.CustomerNoticeDTO;
import kr.or.initspring.service.NoticeService;

@Controller
@Secured({"ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
@RequestMapping("/notice/")
public class NoticeController {

	@Autowired
	private NoticeService noticeservice;

	/*
	 * @method Name : notices
	 * @Author : 송아름
	 * @description : 공지사항 글 목록보기
	 */
	@RequestMapping("notice.htm")
	public String notices(@RequestParam(value="pg",defaultValue="1") int pg, String f, String keyword, Model model) throws ClassNotFoundException, SQLException {	
		
		HashMap<String, Object> map = noticeservice.notices(pg, f, keyword);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pg", map.get("pg"));
		model.addAttribute("allPage", map.get("allPage"));
		model.addAttribute("block", map.get("block"));
		model.addAttribute("fromPage", map.get("fromPage"));
		model.addAttribute("toPage", map.get("toPage"));
		model.addAttribute("start", map.get("start"));
		model.addAttribute("end", map.get("end"));
		model.addAttribute("query", map.get("query"));
		
		return "notice.notice";
	}

	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 공지사항 글 등록 화면 처리
	 */
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String noticeWrite() {
		return "notice.noticeWrite";
	}

	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 공지사항 실제 글 등록 처리
	 */
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN"})
	public String noticeWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {
		
		String url = "redirect:notice.htm";
		
		try {
			url = noticeservice.noticeWrite(principal, cn, request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;
	}
	
	 
	/*
	 * @method Name : noticeDetail
	 * @Author : 송아름
	 * @description : 공지사항 글 상세보기
	 */
	 @RequestMapping("noticeDetail.htm")
	 public String noticeDetail(int notice_index , Model model ) throws ClassNotFoundException, SQLException{
		 CustomerNoticeDTO noticedto = noticeservice.noticeDetail(notice_index);
		 model.addAttribute("notice", noticedto);
  	     return "notice.noticeDetail";
	 }
	 
	/*
	 * @method Name : noticeDel
	 * @Author : 송아름
	 * @description : 공지사항 글 삭제하기
	 */
	@RequestMapping("noticeDel.htm")
	@Secured({"ROLE_ADMIN"})
	public String noticeDel(int notice_index) throws ClassNotFoundException, SQLException {
		String url = noticeservice.noticeDel(notice_index);
		return url;
	}
	
	
	
	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 공지사항 글수정하기 (두가지 처리 : 화면(select) , 처리(update))
	 */
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String noticeEdit(int notice_index, Model model) throws ClassNotFoundException, SQLException {

		CustomerNoticeDTO notice = noticeservice.noticeEdit(notice_index);
		model.addAttribute("notice", notice);
		return "notice.noticeEdit";
	}
	

	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 공지사항 글 실제 수정 처리
	 */
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN"})
	public String noticeEdit(CustomerNoticeDTO cn, HttpServletRequest request)
			throws ClassNotFoundException, SQLException, IOException {

		String url = noticeservice.noticeEdit(cn, request);
		return url;
	}
	
	

	/*
	 * @method Name : noticeDownload
	 * @Author : 송아름
	 * @description : 공지사항 파일 다운로드
	 */
	@RequestMapping("download.htm")
	public void noticeDownload(String p, String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		noticeservice.noticeDownload(p, f, request, response);

	}
	
	/*
	 * @method Name : replyWrite
	 * @Author : 송아름
	 * @description : 공지사항 게시물 상세보기에서 답변클릭시 처리화면 
	 */
	@RequestMapping(value = "replyWrite.htm", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String replyWrite(int notice_index, Model model)throws ClassNotFoundException, SQLException {

		CustomerNoticeDTO notice = noticeservice.replyWrite(notice_index);
		model.addAttribute("notice", notice);
		return "notice.replyWrite";
	}
	
	/*
	 * @method Name : replyWrite
	 * @Author : 송아름
	 * @description : 공지사항 실제 답글 등록 처리
	 */
	@RequestMapping(value = "replyWrite.htm", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN"})
	public String replyWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {
		
		
		String url = "redirect:notice.htm";
		
		try {
			url = noticeservice.replyWrite(principal, cn, request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;
	}
	
}
