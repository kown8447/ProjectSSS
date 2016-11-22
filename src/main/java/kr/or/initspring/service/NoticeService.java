package kr.or.initspring.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		System.out.println(cn.toString());

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		cn.setAdmin_code(noticedao.selectAdmin(principal.getName()));
		noticedao.insert(cn);

		return "redirect:notice.htm";
	}

	/*
	 * @method Name : notices
	 * @Author : 송아름
	 * @description : 글 목록 service
	 */
	public List<CustomerNoticeDTO> notices(String pg, String f, String q) throws ClassNotFoundException, SQLException {

		int page = 1;
		String field = "notice_title";
		String query = "%%";

		if (pg != null && pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		if (f != null && f.equals("")) {
			field = f;
		}
		if (q != null && q.equals("")) {
			query = q;
		}

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		List<CustomerNoticeDTO> list = noticedao.getNotices(page, field, query);

		return list;
	}

	/*
	 * @method Name : noticeDetail
	 * @Author : 송아름
	 * @description : 글 상세보기 service
	 */
	public CustomerNoticeDTO noticeDetail(String notice_index) throws ClassNotFoundException, SQLException {

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		CustomerNoticeDTO noticedto = noticedao.getNotice(notice_index);
		return noticedto;
	}

	/*
	 * @method Name : noticeDel
	 * @Author : 송아름
	 * @description : 글 삭제하기 service
	 */
	public String noticeDel(String notice_index) throws ClassNotFoundException, SQLException {

		NoticeDAO noticedao = sqlsession.getMapper(NoticeDAO.class);
		noticedao.delete(notice_index);

		return "redirect:notice.htm";
	}
	
	/*
	 * @method Name : noticeEdit
	 * @Author : 송아름
	 * @description : 글 수정하기 service
	 */
	public CustomerNoticeDTO noticeEdit(String notice_index) throws ClassNotFoundException, SQLException {
		System.out.println("글 수정하기 service");
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
