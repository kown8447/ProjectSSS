package kr.or.initspring.dto.commons;

import java.sql.Date;

public class NoticeDTO {
	
	/*
	* @Class: NoticeDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 공지사항
	*/
	
	private int notice_index;		//Q&A index
	private String admin_code;		//맴버ID
	private int notice_depth;		//depth
	private int notice_step;		//step
	private int notice_refer;		//refer
	private String notice_file;		//file
	private String notice_title;	//제목
	private String notice_content;	//내용
	private Date notice_date;		//날짜
	private int notice_count;		//조회수
	public int getNotice_index() {
		return notice_index;
	}
	public void setNotice_index(int notice_index) {
		this.notice_index = notice_index;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public int getNotice_depth() {
		return notice_depth;
	}
	public void setNotice_depth(int notice_depth) {
		this.notice_depth = notice_depth;
	}
	public int getNotice_step() {
		return notice_step;
	}
	public void setNotice_step(int notice_step) {
		this.notice_step = notice_step;
	}
	public int getNotice_refer() {
		return notice_refer;
	}
	public void setNotice_refer(int notice_refer) {
		this.notice_refer = notice_refer;
	}
	public String getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(String notice_file) {
		this.notice_file = notice_file;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public int getNotice_count() {
		return notice_count;
	}
	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}
	@Override
	public String toString() {
		return "NoticeDTO [notice_index=" + notice_index + ", admin_code=" + admin_code + ", notice_depth="
				+ notice_depth + ", notice_step=" + notice_step + ", notice_refer=" + notice_refer + ", notice_file="
				+ notice_file + ", notice_title=" + notice_title + ", notice_content=" + notice_content
				+ ", notice_date=" + notice_date + ", notice_count=" + notice_count + "]";
	}
	
}
