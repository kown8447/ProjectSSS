/*
 * @Interface : QnaNoticeDAO
 * @Date : 2016.11.18
 * @Author : 우명제
 * @Desc
 * Qna 게시물 Interface
*/
package kr.or.initspring.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.initspring.dto.qna.CustomerQnaDTO;
import kr.or.initspring.dto.qna.CustomerQnaReplyDTO;


public interface QnaDAO {
	

	public List<CustomerQnaDTO> getQnaMul(String field, String field1, String query, int start, int end); //제목+내용 검색 및 리스트
	public List<CustomerQnaDTO> getQna(String field, String query, int start, int end); //제목, 내용 검색 및 리스트
	public int getCount(String field, String query);  //전체 글 수
	public int qnaInsert(CustomerQnaDTO qna) throws ClassNotFoundException, SQLException; //글 등록
	public CustomerQnaDTO qnaDetail(int qna_index) throws ClassNotFoundException, SQLException; // 상세 글보기
	public int qnaDelete(int qna_index) throws ClassNotFoundException, SQLException; // 글 삭제
	public int qnaDeleteAndUpdate(int qna_refer)throws ClassNotFoundException, SQLException; // 글 삭제전 답글 업뎃
	public int qnaUpdate(CustomerQnaDTO qna) throws ClassNotFoundException, SQLException; //글 수정
	public void qnaCount(int qna_index) throws ClassNotFoundException, SQLException;  //조회수 증가	
	public void qnaInsertReply(CustomerQnaDTO qna); //답글 구분
	public void qnaUpdateStep(CustomerQnaDTO qna);  //답글 순번 처리 
	public int qnaDeleteBeforeWork(int qna_index); // 글 삭제 전 댓글 삭제 
	
	public int insertComment(CustomerQnaReplyDTO rqna); // 코멘트(댓글) 등록
	public List<CustomerQnaReplyDTO> listComment(int qna_index)throws Exception; // 코멘트 조회
	public List<CustomerQnaReplyDTO> getReplyList(int qna_index);	//인덱스로 코멘트 조회
	public int qnaCmtDelete(int reply_index); //댓글 삭제
	public int qnaCmtUpdate(int reply_index, String reply_content); //댓글 수정
	
}
