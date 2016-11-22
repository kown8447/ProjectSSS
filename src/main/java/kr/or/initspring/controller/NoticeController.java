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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/*
	 * @method Name : notices
	 * @Author : 송아름
	 * @description : 공지사항 글 목록보기
	 */
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) throws ClassNotFoundException, SQLException {
		List<CustomerNoticeDTO> list = noticeservice.notices(pg, f, q);
		model.addAttribute("list", list);
		return "notice.notice";
	}

	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 공지사항 글 등록 화면 처리
	 */
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.GET)
	public String noticeWrite() {
		return "notice.noticeWrite";
	}

	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 공지사항 실제 글 등록 처리
	 */
	@RequestMapping(value = "noticeWrite.htm", method = RequestMethod.POST)
	public String noticeWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {
		
		String url = "redirect:notice.htm";
		System.out.println(cn.toString());
		
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
	 public String noticeDetail(String notice_index , Model model ) throws ClassNotFoundException, SQLException{
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
	public String noticeDel(String notice_index) throws ClassNotFoundException, SQLException {
		String url = noticeservice.noticeDel(notice_index);
		return url;
	}
	
	
	
	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 공지사항 글수정하기 (두가지 처리 : 화면(select) , 처리(update))
	 */
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit(String notice_index, Model model) throws ClassNotFoundException, SQLException {

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
	
	

}
