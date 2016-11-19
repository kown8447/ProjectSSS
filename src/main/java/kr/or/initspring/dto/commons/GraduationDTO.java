package kr.or.initspring.dto.commons;

import java.sql.Date;

public class GraduationDTO {
	
	/*
	* @Class: GraduationDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 졸업
	*/
	
	private String student_code;	//학번
	private String semester_code;	//학기코드
	private int graduate_grade;		//학년
	private Date graduate_date;		//졸업일
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getGraduate_grade() {
		return graduate_grade;
	}
	public void setGraduate_grade(int graduate_grade) {
		this.graduate_grade = graduate_grade;
	}
	public Date getGraduate_date() {
		return graduate_date;
	}
	public void setGraduate_date(Date graduate_date) {
		this.graduate_date = graduate_date;
	}
	@Override
	public String toString() {
		return "GraduationDTO [student_code=" + student_code + ", semester_code=" + semester_code + ", graduate_grade="
				+ graduate_grade + ", graduate_date=" + graduate_date + "]";
	}
	
	
}
