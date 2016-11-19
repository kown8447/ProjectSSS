package kr.or.initspring.dto.commons;

import java.sql.Date;

public class SemesterDTO {
	 /*
	   * @Class: SemesterDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 학기
	   */
	private String semester_code; //학기코드
	private String semester_name;//학기이름
	private Date semester_start; //학기시작일
	private Date semester_end; //학기 종료일 
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public String getSemester_name() {
		return semester_name;
	}
	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
	}
	public Date getSemester_start() {
		return semester_start;
	}
	public void setSemester_start(Date semester_start) {
		this.semester_start = semester_start;
	}
	public Date getSemester_end() {
		return semester_end;
	}
	public void setSemester_end(Date semester_end) {
		this.semester_end = semester_end;
	}
	@Override
	public String toString() {
		return "Semester [semester_code=" + semester_code + ", semester_name=" + semester_name + ", semester_start="
				+ semester_start + ", semester_end=" + semester_end + "]";
	}
	
	 
}
