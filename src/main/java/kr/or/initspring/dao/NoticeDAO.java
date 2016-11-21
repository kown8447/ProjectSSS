/*
 * @Class : NoticeDAO
 * @Date : 2016.11.18
 * @Author : 송아름
 * @Desc : 게시판 공지사항 관련 method
*/
package kr.or.initspring.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.initspring.dto.commons.NoticeDTO;
import kr.or.initspring.dto.notice.CustomerNoticeDTO;

public interface NoticeDAO {
	// 게시물 개수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 전체 게시물
	public List<NoticeDTO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;

	// 게시물 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;

	// 게시물 수정
	public int update(NoticeDTO noticedto) throws ClassNotFoundException, SQLException;

	// 게시물 상세
	public NoticeDTO getNotice(String seq) throws ClassNotFoundException, SQLException;

	// 게시물 입력
	public int insert(CustomerNoticeDTO cn) throws ClassNotFoundException, SQLException;
}
