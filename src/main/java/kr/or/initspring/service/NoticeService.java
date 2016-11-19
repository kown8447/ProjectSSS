/*
 * @Class : NoticeService
 * @Date : 2016.11.18
 * @Author : 송아름
 * @Desc
 * 게시판 공지사항 관련 서비스
*/

package kr.or.initspring.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dto.NoticeDTO;

@Service
public class NoticeService {
	
	/*
	* @method Name : noticeWrite
	* @Author: 송아름
	* @description : 게시판 공지사항 글쓰기 파일업로드
	*/
	public String noticeWrite(NoticeDTO noticedto, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		CommonsMultipartFile file = noticedto.getFile();

		if (file != null && file.getSize() > 0) {

			String fname = file.getOriginalFilename(); // 파일명 얻기
			String path = request.getServletContext().getRealPath("/upload/notice");
			String fullpath = path + "\\" + fname;
			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
		}

		noticedto.setNotice_File(file.getName());

		return "redirect:intdex.htm";
	}
}
