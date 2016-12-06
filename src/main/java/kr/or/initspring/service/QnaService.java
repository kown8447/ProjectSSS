/*
 * @Class : QnaNoticeService
 * @Date : 2016.11.18
 * @Author : 우명제
 * @Desc
 * Qna 게시물 정보 관련 서비스
*/
package kr.or.initspring.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dao.QnaDAO;
import kr.or.initspring.dto.qna.CustomerQnaDTO;
import kr.or.initspring.dto.qna.CustomerQnaReplyDTO;

@Service
public class QnaService {

	@Autowired
	private SqlSession sqlsession;
	/*
	 * @method Name : qnaNotices
	 * @Author : 우명제
	 * @description : qna게시판 list + 검색 service + 댓글 수 출력
	 */
	public HashMap<String, Object> qnaNotices(int pg, String searchType, String keyword)
			throws ClassNotFoundException, SQLException {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		HashMap<String, Object> map = new HashMap<String, Object>();

		int pagesize = 10;
		int pagenum = pg;
		String field = "qna_title";
		String field1 = "qna_content";
		String query = "";
		List<CustomerQnaDTO> list = null;
		int start = (pagenum * pagesize) - (pagesize - 1);
		int end = pagenum * pagesize;

		if (keyword != null && !keyword.equals("")) {
			query = keyword;
			if (searchType.equals("0")) {
				list = qnaDao.getQna(field, query, start, end);
			} else if (searchType.equals("1")) {
				list = qnaDao.getQnaMul(field, field1, query, start, end);
			} else if (searchType.equals("2")) {
				field = "member_id";
				list = qnaDao.getQna(field, query, start, end);
			}
		} else {
			list = qnaDao.getQna(field, query, start, end);
		}

		int total = qnaDao.getCount(field, query);
		
     	for(int i=0;i<list.size();i++){
          int cmtCount = qnaDao.qnaCmtCount(list.get(i).getQna_index());
          list.get(i).setQna_cmtCount(cmtCount);
     	}
		int allPage = (int) Math.ceil(total / (double) pagesize);

		int block = 10;
		int fromPage = ((pagenum - 1) / block * block) + 1;
		int toPage = ((pagenum - 1) / block * block) + block;
		if (toPage > allPage) {
			toPage = allPage;
		}
		map.put("searchType", searchType);
		map.put("keyword", query);
		map.put("list", list);
		map.put("pg", pagenum);
		map.put("allPage", allPage);
		map.put("block", block);
		map.put("fromPage", fromPage);
		map.put("toPage", toPage);
		map.put("start", start);
		map.put("end", end);
		map.put("total",total);
		return map;
	}

	/*
	 * @method Name : qnaWrite
	 * @Author : 우명제 
	 * @description : 글 등록 service
	 */
	public String qnaWrite(CustomerQnaDTO qna, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		CommonsMultipartFile file = qna.getFile();
		String filenames = null;

		if (file != null && file.getSize() > 0) {
			String fname = file.getOriginalFilename();
			String path = request.getServletContext().getRealPath("/files/qnanotice");
			String fullpath = path + "\\" + fname;

			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
			filenames = fname;
		}

		qna.setQna_file(filenames);

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		qnaDao.qnaInsert(qna);
		return "redirect:qnanotice.htm";
	}

	/*
	 * @method Name : qnaDetail
	 * @Author : 우명제
	 * @description : 글 상세보기 service
	 */
	public CustomerQnaDTO qnaDetail(int qna_index) throws ClassNotFoundException, SQLException {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		CustomerQnaDTO qna = qnaDao.qnaDetail(qna_index);
		return qna;
	}
	
	/*
	 * @method Name : qnaCount
	 * @Author : 우명제
	 * @description : 글 조회수 service
	 */
	public void qnaCount(String readerId, String writerId, int qna_index) throws ClassNotFoundException, SQLException {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		if (!readerId.equals(writerId)) {
			qnaDao.qnaCount(qna_index);
		}

	}

	/*
	 * @method Name : qnaDel 
	 * @Author : 우명제 
	 * @description : 글 삭제 service
	 */
	public String qnaDel(int qna_index) throws ClassNotFoundException, SQLException {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		CustomerQnaDTO qna = qnaDao.qnaDetail(qna_index);

		qnaDao.qnaDeleteBeforeWork(qna_index);

		if (qna.getQna_depth() == 0) {
			qnaDao.qnaDeleteAndUpdate(qna.getQna_refer());
		}
		qnaDao.qnaDelete(qna_index);

		return "redirect:qnanotice.htm";
	}

	/*
	 * @method Name : qnaEdit 
	 * @Author : 우명제 
	 * @description : 글 수정페이지 service
	 */
	public CustomerQnaDTO qnaEdit(int qna_index) throws ClassNotFoundException, SQLException {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		CustomerQnaDTO qna = qnaDao.qnaDetail(qna_index);

		return qna;
	}

	 /*
	    * @method Name : qnaEdit 
	    * @Author : 우명제 
	    * @description : 글 수정 처리 service
	    */
	   public String qnaEdit(CustomerQnaDTO qna, HttpServletRequest request)
	         throws IOException, ClassNotFoundException, SQLException {
	      
	      QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
	      if(qna.getFile() != null){
	         CommonsMultipartFile file = qna.getFile();
	         String filenames = null;
	         if (file != null && file.getSize() > 0) {
	            String fname = file.getOriginalFilename();
	            String path = request.getServletContext().getRealPath("/files/qnanotice");
	            String fullpath = path + "\\" + fname;
	            
	            FileOutputStream fs = new FileOutputStream(fullpath);    
	            fs.write(file.getBytes());
	            fs.close();
	            filenames = fname;        
	            
	            qna.setQna_file(filenames);      
	            qnaDao.qnaUpdate(qna);
	            }else{
	               qnaDao.qnaNotFileUpdate(qna);   
	            }
	         }
	      return "redirect:qnaDetail.htm?qna_index=" + qna.getQna_index();
	   }


	/*
	 * @method Name : download 
	 * @Author : 우명제
	 * @description : 파일 다운로드 service
	 */
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String fname = new String(f.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");
		String fullpath = request.getServletContext().getRealPath("/files/" + p + "/" + f);

		FileInputStream fin = new FileInputStream(fullpath);

		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}

	/*
	 * @method Name : qnaReply
	 * @Author : 우명제
	 * @description : 글 답글 처리 service
	 */
	public String qnaReply(CustomerQnaDTO qna, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		CommonsMultipartFile file = qna.getFile();
		String filenames = null;

		if (file != null && file.getSize() > 0) {

			String fname = file.getOriginalFilename();
			String path = request.getServletContext().getRealPath("/files/qnanotice");
			String fullpath = path + "\\" + fname;

			if (!fname.equals("")) {
				FileOutputStream fs = new FileOutputStream(fullpath);
				fs.write(file.getBytes());
				fs.close();
			}
			filenames = fname;

		}
		qna.setQna_file(filenames);

		QnaDAO qnaNoticeDao = sqlsession.getMapper(QnaDAO.class);

		qnaNoticeDao.qnaUpdateStep(qna);

		qna.setQna_step(qna.getQna_step() + 1);
		qna.setQna_depth(qna.getQna_depth() + 1);


		qnaNoticeDao.qnaInsertReply(qna); // 답변 실제 처리
		return "redirect:qnanotice.htm";
	}

	/*
	 * @method Name : qnaCmt 
	 * @Author : 최준호, 우명제 
	 * @description : 글 댓글 처리 service
	 */
	public int qnaCmt(String member_id, CustomerQnaReplyDTO rqna, Model model) {
		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);

		int result = qnaDao.insertComment(rqna);
		List<CustomerQnaReplyDTO> rqnaList = qnaDao.getReplyList(rqna.getQna_index());
		model.addAttribute("list", rqnaList);

		return result;
	}

	/*
	 * @method Name : qnaCmtList 
	 * @Author : 최준호, 우명제 
	 * @description : 글 댓글 리스트 service
	 */
	public List<CustomerQnaReplyDTO> qnaCmtList(int qna_index) {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);
		List<CustomerQnaReplyDTO> result = null;

		try {
			result = qnaDao.listComment(qna_index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * @method Name : qnaCmtDel 
	 * @Author : 최준호, 우명제
	 * @description : 글 댓글 삭제 service
	 */
	public int qnaCmtDel(int reply_index, int qna_index, Model model) {

		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);

		int result = qnaDao.qnaCmtDelete(reply_index);
		List<CustomerQnaReplyDTO> rqnaList = qnaDao.getReplyList(qna_index);
		model.addAttribute("list", rqnaList);
		return result;

	}
	
	/*
	 * @method Name : qnaCmtUpdate 
	 * @Author : 최준호
	 * @description : 글 댓글 수정 service
	 */
	public int qnaCmtUpdate(int reply_index, String reply_content, int qna_index, Model model) {
		QnaDAO qnaDao = sqlsession.getMapper(QnaDAO.class);

		int result = qnaDao.qnaCmtUpdate(reply_index,reply_content);
		List<CustomerQnaReplyDTO> rqnaList = qnaDao.getReplyList(qna_index);
		model.addAttribute("list", rqnaList);
		return result;
	}

}
