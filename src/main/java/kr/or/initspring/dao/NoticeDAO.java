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
	
	//마지막 스텝 찾기
	public int lastReplyStep(CustomerNoticeDTO cn) throws ClassNotFoundException, SQLException;

}
