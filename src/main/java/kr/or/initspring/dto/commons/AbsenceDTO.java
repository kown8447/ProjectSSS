package kr.or.initspring.dto.commons;

import java.sql.Date;

public class AbsenceDTO {
	
	/*
	* @Class: AbsenceDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 휴학
	*/
	
	private String student_code; 	//학번
	private Date absence_date;		//휴학신청일
	private int absence_term;		//휴학기간(학기)
	private String absence_reason;	//휴학사유
	private Date return_date;		//복학예정일
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public Date getAbsence_date() {
		return absence_date;
	}
	public void setAbsence_date(Date absence_date) {
		this.absence_date = absence_date;
	}
	public int getAbsence_term() {
		return absence_term;
	}
	public void setAbsence_term(int absence_term) {
		this.absence_term = absence_term;
	}
	public String getAbsence_reason() {
		return absence_reason;
	}
	public void setAbsence_reason(String absence_reason) {
		this.absence_reason = absence_reason;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	@Override
	public String toString() {
		return "AbsenceDTO [student_code=" + student_code + ", absence_date=" + absence_date + ", absence_term="
				+ absence_term + ", absence_reason=" + absence_reason + ", return_date=" + return_date + "]";
	}
	
	
}
