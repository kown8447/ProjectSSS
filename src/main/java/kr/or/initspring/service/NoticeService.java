package kr.or.initspring.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dao.NoticeDAO;
import kr.or.initspring.dto.notice.CustomerNoticeDTO;

@Service
public class NoticeService {

	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 글 등록 파일 업로드 service
	 */
	public String noticeWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		CommonsMultipartFile file = cn.getFile();
		String filenames = null;
		if (file != null && file.getSize() > 0) {

			String fname = file.getOriginalFilename(); // 파일명 얻기
			String path = request.getServletContext().getRealPath("/files/notice");
			String fullpath = path + "\\" + fname;

			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
			filenames = fname;

		}
		cn.setNotice_file(filenames);

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		cn.setAdmin_code(noticedao.selectAdmin(principal.getName()));
		noticedao.insert(cn);	

		return "redirect:notice.htm";
	}
	
	/*
	 * @method Name : noticeWrite
	 * @Author : 송아름
	 * @description : 답글 등록 파일 업로드 service
	 */
	public String replyWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		CommonsMultipartFile file = cn.getFile();
		String filenames = null;
		if (file != null && file.getSize() > 0) {

			String fname = file.getOriginalFilename(); // 파일명 얻기
			String path = request.getServletContext().getRealPath("/files/notice");
			String fullpath = path + "\\" + fname;

			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
			filenames = fname;

		}
		cn.setNotice_file(filenames);

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
          
        noticedao.addStep(cn);
		cn.setAdmin_code(noticedao.selectAdmin(principal.getName()));
		cn.setNotice_step(cn.getNotice_step()+1);
		cn.setNotice_depth(cn.getNotice_depth()+1);
		
		noticedao.replyWrite(cn);
		

		return "redirect:notice.htm";
	}
	
	/*
	 * @method Name : replyWrite
	 * @Author : 송아름
	 * @description : 답글 화면처리(원본글 제목그대로 가져오기)
	 */
	public CustomerNoticeDTO replyWrite(int notice_index) throws ClassNotFoundException, SQLException {
		
		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		CustomerNoticeDTO noticedto = noticedao.getNotice(notice_index);
		return noticedto;
	}



	/*
	 * @method Name : notices
	 * @Author : 송아름
	 * @description : 글 목록 service
	 */
	public HashMap<String, Object> notices(int pg, String f, String keyword) throws ClassNotFoundException, SQLException {
		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int pagesize = 10;
		int pagenum = pg;
		String field = "notice_title";
	    String query = "";
		

		if (f != null && !f.equals("")) {
			field = f;
		}
		if (keyword != null && !keyword.equals("")) {
			query = keyword;
		}

		int start = (pagenum * pagesize) - (pagesize - 1); 
	    int end = pagenum * pagesize; 
		List<CustomerNoticeDTO> list = noticedao.getNotices(field, query, start, end);
		
		int total = noticedao.getCount(field, query); 
		
		int allPage = (int) Math.ceil(total / (double) pagesize); // 페이지수

		int block = 10; 
		int fromPage = ((pagenum - 1) / block * block) + 1; // 보여줄 페이지의 시작
		int toPage = ((pagenum - 1) / block * block) + block; // 보여줄 페이지의 끝
		if (toPage > allPage) { 
			toPage = allPage;
		}
	
		map.put("query", query);
		map.put("list", list);
		map.put("pg", pagenum);
		map.put("allPage", allPage);
		map.put("block", block);
		map.put("fromPage", fromPage);
		map.put("toPage", toPage);
		map.put("start", start);
		map.put("end", end);
		map.put("total", total);
		
		return map;
	}
	
	
	/*
	 * @method Name : noticeDetail
	 * @Author : 송아름
	 * @description : 글 상세보기 service
	 */
	public CustomerNoticeDTO noticeDetail(int notice_index) throws ClassNotFoundException, SQLException {

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		CustomerNoticeDTO noticedto = noticedao.getNotice(notice_index);
		return noticedto;
	}
	
	/*
	 * @method Name : noticeCount
	 * @Author : 송아름
	 * @description : 글 조회수 service
	 */
	public void noticeCount(String readerId, int notice_index) throws ClassNotFoundException, SQLException {
		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		
		String readerCode= noticedao.selectAdmin(readerId);
		String writerCode= noticedao.getWriterCode(notice_index);
		
		if (readerCode==null||!readerCode.equals(writerCode)) {
			noticedao.increase(notice_index);
		}

	}

	/*
	 * @method Name : noticeDel
	 * @Author : 송아름
	 * @description : 글 삭제하기 service
	 */
	public String noticeDel(int notice_index) throws ClassNotFoundException, SQLException {

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		CustomerNoticeDTO cn = noticedao.getNotice(notice_index);
		noticedao.delete(notice_index);
		
		noticedao.noticeDeleteAndUpdate(notice_index);

	      if (cn.getNotice_depth() == 0) {
	    	  noticedao.noticeDeleteAndUpdate(cn.getNotice_refer());
	      }
	      noticedao.delete(notice_index);

		return "redirect:notice.htm";
	}
	
	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 글 수정하기 service
	 */
	public CustomerNoticeDTO noticeEdit(int notice_index) throws ClassNotFoundException, SQLException {
	
		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		CustomerNoticeDTO noticedto = noticedao.getNotice(notice_index);
		return noticedto;
	}

	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 글 수정하기 (파일재업로드)service
	 */
	public String noticeEdit(CustomerNoticeDTO cn, HttpServletRequest request)
			throws ClassNotFoundException, SQLException, IOException {

		CommonsMultipartFile file = cn.getFile();
		String filenames = null;

		if (file != null && file.getSize() > 0) {

			String fname = file.getOriginalFilename(); // 파일명 얻기
			String path = request.getServletContext().getRealPath("/files/notice");
			String fullpath = path + "\\" + fname;

			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
			filenames = fname;
		}

		cn.setNotice_file(filenames);

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		noticedao.update(cn);
		
		return "redirect:noticeDetail.htm?notice_index=" + cn.getNotice_index();
	}
	
	/*
	 * @method Name : download
	 * @Author : 송아름
	 * @description : 공지사항 파일다운로드
	 */
	public void noticeDownload(String p, String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String fname = new String(f.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");
		String fullpath = request.getServletContext().getRealPath("/files/" + p + "/" + f);

		FileInputStream fin = new FileInputStream(fullpath);

		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024]; // 전체를 다읽지 않고 1204byte씩 읽어서
		int size = 0;
		while ((size = fin.read(buf, 0, buf.length)) != -1) // buffer 에 1024byte
		{
			sout.write(buf, 0, size); // 1kbyte씩 출력
		}
		fin.close();
		sout.close();
	}



}
