/*
 * @Class : NoticeDAO
 * @Date : 2016.11.18
 * @Author : 송아름
 * @Desc : 게시판 공지사항 관련 method
*/
package kr.or.initspring.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.initspring.dto.notice.CustomerNoticeDTO;
import kr.or.initspring.dto.qna.CustomerQnaDTO;

public interface NoticeDAO {
	// 게시물 갯수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 전체 게시물
	public List<CustomerNoticeDTO> getNotices(String field, String query, int start, int end)
			throws ClassNotFoundException, SQLException;
	
	// 게시물 삭제
	public int delete(int notice_index) throws ClassNotFoundException, SQLException;

	// 게시물 수정
	public int update(CustomerNoticeDTO noticedto) throws ClassNotFoundException, SQLException;
	
	//글 수정(파일 없을시)
	public int notFileUpdate(CustomerNoticeDTO noticedto) throws ClassNotFoundException, SQLException; 
	
	// 게시물 상세
	public CustomerNoticeDTO getNotice(int notice_index) throws ClassNotFoundException, SQLException;

	// 게시물 입력
	public int insert(CustomerNoticeDTO cn) throws ClassNotFoundException, SQLException;

	// 관리자 코드 가져오기
	public String selectAdmin(String amdinid);

	// 게시물 조회수
	public int increase(int notice_index) throws ClassNotFoundException, SQLException;

	//현재 답변의 단 게시물 보다 더 높은 스텝의 게시물이 있다면 스탭을 하나씩 상승시킴
	public void addStep(CustomerNoticeDTO cn) throws ClassNotFoundException, SQLException;
	
	//답글 달기
	public void replyWrite(CustomerNoticeDTO cn) throws ClassNotFoundException, SQLException;
	
	//지금 읽고있는 게시글을 작성자(admin_code) 가져오기
	public String getWriterCode(int notice_index) throws ClassNotFoundException, SQLException;
	
	// 글 삭제전 답글 업뎃
	public int noticeDeleteAndUpdate(int notice_refer)throws ClassNotFoundException, SQLException; 
}
