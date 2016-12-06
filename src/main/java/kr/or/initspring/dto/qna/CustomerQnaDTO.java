/*
 * @Class : QnaNoticeDTO
 * @Date : 2016.11.18
 * @Author : 우명제
 * @Desc
 * Qna 게시물 변수 지정
 * 실제 업로드할 변수 등록 private CommonsMultipartFile file; (DB 컬럼x)
*/
package kr.or.initspring.dto.qna;


import java.sql.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CustomerQnaDTO {
	private int qna_index;    
	private String member_id; 
	private int qna_depth; 	  
	private int qna_step;   
	private int qna_refer;   
	private String qna_file;   //파일이름
	private CommonsMultipartFile file; //실제파일
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private int qna_count;     
	private int qna_status;
	private int qna_cmtCount;
	public int getQna_index() {
		return qna_index;
	}
	public void setQna_index(int qna_index) {
		this.qna_index = qna_index;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getQna_depth() {
		return qna_depth;
	}
	public void setQna_depth(int qna_depth) {
		this.qna_depth = qna_depth;
	}
	public int getQna_step() {
		return qna_step;
	}
	public void setQna_step(int qna_step) {
		this.qna_step = qna_step;
	}
	public int getQna_refer() {
		return qna_refer;
	}
	public void setQna_refer(int qna_refer) {
		this.qna_refer = qna_refer;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	public int getQna_count() {
		return qna_count;
	}
	public void setQna_count(int qna_count) {
		this.qna_count = qna_count;
	}
	public int getQna_status() {
		return qna_status;
	}
	public void setQna_status(int qna_status) {
		this.qna_status = qna_status;
	}
	public int getQna_cmtCount() {
		return qna_cmtCount;
	}
	public void setQna_cmtCount(int qna_cmtCount) {
		this.qna_cmtCount = qna_cmtCount;
	}
	
	@Override
	public String toString() {
		return "CustomerQnaDTO [qna_index=" + qna_index + ", member_id=" + member_id + ", qna_depth=" + qna_depth
				+ ", qna_step=" + qna_step + ", qna_refer=" + qna_refer + ", qna_file=" + qna_file + ", file=" + file
				+ ", qna_title=" + qna_title + ", qna_content=" + qna_content + ", qna_date=" + qna_date
				+ ", qna_count=" + qna_count + ", qna_status=" + qna_status + ", qna_cmtCount=" + qna_cmtCount + "]";
	}
		
}
