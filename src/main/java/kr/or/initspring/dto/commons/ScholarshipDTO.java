package kr.or.initspring.dto.commons;

import java.sql.Date;

public class ScholarshipDTO {
	
	/*
	* @Class: ScholarshipDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 장학
	*/
	
	private String scholarship_code;	//장학수혜코드
	private String student_code;		//student_Code
	private String sys_code;			//장학코드
	private String semester_code;		//학기코드
	private int scholarship_rcordavg;	//평점
	private Date scholarship_payday;	//지급일
	public String getScholarship_code() {
		return scholarship_code;
	}
	public void setScholarship_code(String scholarship_code) {
		this.scholarship_code = scholarship_code;
	}
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getScholarship_rcordavg() {
		return scholarship_rcordavg;
	}
	public void setScholarship_rcordavg(int scholarship_rcordavg) {
		this.scholarship_rcordavg = scholarship_rcordavg;
	}
	public Date getScholarship_payday() {
		return scholarship_payday;
	}
	public void setScholarship_payday(Date scholarship_payday) {
		this.scholarship_payday = scholarship_payday;
	}
	@Override
	public String toString() {
		return "ScholarshipDTO [scholarship_code=" + scholarship_code + ", student_code=" + student_code + ", sys_code="
				+ sys_code + ", semester_code=" + semester_code + ", scholarship_rcordavg=" + scholarship_rcordavg
				+ ", scholarship_payday=" + scholarship_payday + "]";
	}
}
	