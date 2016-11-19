package kr.or.initspring.dto.commons;

import java.sql.Date;

public class QnaDTO {
	
	/*
	* @Class: AbsenceDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: Q&A
	*/
	
	private int qna_index;		//Q&A index
	private String member_id;	//맴버ID
	private int qna_depth;		//depth
	private int qna_step;		//step
	private int qna_refer;		//refer
	private String qna_file;	//file
	private String qna_title;	//제목
	private String qna_content;	//내용
	private Date qna_date;		//날짜
	private int qna_count;		//조회수
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
	@Override
	public String toString() {
		return "QnaDTO [qna_index=" + qna_index + ", member_id=" + member_id + ", qna_depth=" + qna_depth
				+ ", qna_step=" + qna_step + ", qna_refer=" + qna_refer + ", qna_file=" + qna_file + ", qna_title="
				+ qna_title + ", qna_content=" + qna_content + ", qna_date=" + qna_date + ", qna_count=" + qna_count
				+ "]";
	}
	
	
}
