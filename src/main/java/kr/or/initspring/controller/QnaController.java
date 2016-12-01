/*
 * @Class : QnaNoticeController
 * @Date : 2016.11.18
 * @Author : 우명제
 * @Desc
 * Qna게시판 사용을 위한 컨트롤러.
 * 회원은 글등록, 상세보기, 수정, 삭제, 답글, 댓글 기능이 있다.
 * 게시물 리스트페이지는 검색기능, 페이징처리로 구현이 되어있다.
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.initspring.dto.qna.CustomerQnaDTO;
import kr.or.initspring.dto.qna.CustomerQnaReplyDTO;
import kr.or.initspring.service.QnaService;

@Controller
@Secured({ "ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR" })
@RequestMapping("/qnanotice/")
public class QnaController {

	@Autowired
	private QnaService qnaservice;

	@Autowired
	private View jsonview;

	/*
	 * @method Name : qnanotice
	 * @Author: 우명제
	 * @description : QnA게시판 글 목록보기 + 검색기능
	 */
	@RequestMapping(value = "/qnanotice.htm")
	public String qnanotice(@RequestParam(value = "pg", defaultValue = "1") int pg, String searchType, String keyword,
			Model model) throws ClassNotFoundException, SQLException {

		HashMap<String, Object> map = qnaservice.qnaNotices(pg, searchType, keyword);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("pg", map.get("pg"));
		model.addAttribute("allPage", map.get("allPage"));
		model.addAttribute("block", map.get("block"));
		model.addAttribute("fromPage", map.get("fromPage"));
		model.addAttribute("toPage", map.get("toPage"));
		model.addAttribute("start", map.get("start"));
		model.addAttribute("end", map.get("end"));
		model.addAttribute("keyword", map.get("keyword"));
		model.addAttribute("searchType", map.get("searchType"));
		return "qnanotice.qnaNotice";
	}

	/*
	 * @method Name : qnaWrite
	 * @Author: 우명제
	 * @description : QnA게시판 글 등록 화면
	 */
	@RequestMapping(value = "/qnaWrite.htm", method = RequestMethod.GET)
	public String qnaWrite() {

		return "qnanotice.qnaWrite";
	}

	/*
	 * @method Name : qnaWrite
	 * @Author: 우명제
	 * @description : QnA게시판 실제 글 등록 처리
	 */
	@RequestMapping(value = "/qnaWrite.htm", method = RequestMethod.POST)
	public String qnaWrite(Principal principal, CustomerQnaDTO qna, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		qna.setMember_id(principal.getName());
		String url = "redirect:qnanotice.htm";
		try {
			url = qnaservice.qnaWrite(qna, request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;
	}

	/*
	 * @method Name : qnaDetail
	 * @Author: 우명제
	 * @description : QnA게시판 글 상세 화면보기 및 조회수처리
	 */
	@RequestMapping("/qnaDetail.htm")
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class, RuntimeException.class})
	public String qnaDetail(Principal principal,int qna_index, Model model) throws Exception {
		String id=principal.getName();
			
		if(id==null){
			id="";
		}
		
		try{
		CustomerQnaDTO qna = qnaservice.qnaDetail(qna_index);
		model.addAttribute("qna", qna);
		
		qnaservice.qnaCount(id, qna.getMember_id(), qna_index);
		model.addAttribute("readerId",id);
		
		List<CustomerQnaReplyDTO> rqna = qnaservice.qnaCmtList(qna_index);
		model.addAttribute("rqna", rqna);
		} catch (Exception e) {
		      System.out.println("트랜잭션 오류 : " + e.getMessage());
		      
		      throw e;
		   }
		      	  
		return "qnanotice.qnaDetail";
		
	}

	/*
	 * @method Name : qnaDel.htm
	 * @Author: 우명제
	 * @description : QnA게시판 글 삭제
	 */
	@RequestMapping("/qnaDel.htm")
	public String qnaDel(int qna_index) throws ClassNotFoundException, SQLException {

		String url = qnaservice.qnaDel(qna_index);
		return url;
	}

	/*
	 * @method Name : qnaEdit 
	 * @Author: 우명제
	 * @description : QnA게시판 글 수정(두가지 처리 : 화면(select) , 처리(update))
	 */
	@RequestMapping(value = "/qnaEdit.htm", method = RequestMethod.GET)
	public String qnaEdit(int qna_index, Model model) throws ClassNotFoundException, SQLException {

		CustomerQnaDTO qna = qnaservice.qnaEdit(qna_index);
		model.addAttribute("qna", qna);

		return "qnanotice.qnaEdit";
	}

	/*
	 * @method Name : qnaEdit
	 * @Author: 우명제
	 * @description : QnA게시판 실제 수정처리
	 */
	@RequestMapping(value = "/qnaEdit.htm", method = RequestMethod.POST)
	public String qbaEdit(CustomerQnaDTO qna, HttpServletRequest request)
			throws ClassNotFoundException, SQLException, IOException {

		String url = qnaservice.qnaEdit(qna, request);
		return url;
	}

	/*
	 * @method Name : download
	 * @Author: 우명제
	 * @description : QnA게시판 파일 다운로드
	 */
	@RequestMapping("/download.htm")
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		qnaservice.download(p, f, request, response);

	}

	/*
	 * @method Name : qnaReply
	 * @Author: 우명제
	 * @description : QnA게시판 답글 작성 페이지 이동
	 */
	@RequestMapping(value = "/qnaReply.htm", method = RequestMethod.GET)
	public String qnaReply(Principal principal,int qna_index, Model model) throws ClassNotFoundException, SQLException {
		String id = principal.getName();
		model.addAttribute("id", id);
		
		CustomerQnaDTO qna = qnaservice.qnaDetail(qna_index);
		model.addAttribute("qna", qna);
		return "qnanotice.qnaReply";
	}

	/*
	 * @method Name : qnaReply
	 * @Author: 우명제
	 * @description : QnA게시판 답글 작성 페이지 이동
	 */
	@RequestMapping(value = "/qnaReply.htm", method = RequestMethod.POST)
	public String qnaReply(Principal principal, CustomerQnaDTO qna, HttpServletRequest request)
			throws ClassNotFoundException, IOException, SQLException {

		qna.setMember_id(principal.getName());
		String url = "redirect:qnanotice.htm";
		try {
			url = qnaservice.qnaReply(qna, request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;
	}

	/*
	 * @method Name : qnaCmt.htm
	 * @Author: 최준호, 우명제
	 * @description : QnA게시판 댓글
	 */
	@RequestMapping(value = "/qnaCmt.htm")
	public View qnaCmt(Principal principal, CustomerQnaReplyDTO rqna, Model model)
			throws ClassNotFoundException, SQLException {
		rqna.setMember_id(principal.getName());

		int result = qnaservice.qnaCmt(principal.getName(), rqna, model);
		if (result > 0) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		return jsonview;
	}
	
	/*
	 * @method Name : qnaCmtDel.htm
	 * @Author: 최준호, 우명제
	 * @description : QnA게시판 댓글 삭제
	 */
	@RequestMapping(value= "/qnaCmtDel.htm")
	public View qnaCmtDel(int reply_index, Model model,int qna_index){
		
		int result = qnaservice.qnaCmtDel(reply_index,qna_index,model);

		if (result > 0) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		
		return jsonview;
	}
	
	/*
	 * @method Name : qnaCmtDel.htm
	 * @Author: 최준호, 우명제
	 * @description : QnA게시판 댓글 수정
	 */
	@RequestMapping(value= "qnaCmtUpdate.htm")
	public View qnaCmtUpdate(int reply_index,String reply_content,int qna_index,Model model){

		int result = qnaservice.qnaCmtUpdate(reply_index,reply_content,qna_index,model);

		if (result > 0) {
			model.addAttribute("result", "success");
		} else {
			model.addAttribute("result", "fail");
		}
		
		return jsonview;
	}
	


}
