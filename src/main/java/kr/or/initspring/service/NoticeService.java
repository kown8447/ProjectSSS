package kr.or.initspring.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
	 * @method Name : NoticeWrite
	 * @Author : 송아름
	 * @description : 글 등록 service
	 */
	public String NoticeWrite(Principal principal, CustomerNoticeDTO cn, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {
		System.out.println("서비스탔다");
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
}
