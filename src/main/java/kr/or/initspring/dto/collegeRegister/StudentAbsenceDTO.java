package kr.or.initspring.dto.collegeRegister;
/*
* @Class: StudentAbsenceDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 휴학기록 테이블
* 휴학 정보 테이블에서 rownum으로 구한 순번을 담기위한 변수를 추가한 DTO
*/

public class StudentAbsenceDTO {
	private int absenceIndex;//휴학 순번
	private java.sql.Date absence_date;//휴학신청일
	private int absence_term;//휴학기간
	private String absence_reason;//휴학사유
	private java.sql.Date return_date;//복학예정일

	public int getAbsenceIndex() {
		return absenceIndex;
	}

	public void setAbsenceIndex(int absenceIndex) {
		this.absenceIndex = absenceIndex;
	}

	public java.sql.Date getAbsence_date() {
		return absence_date;
	}

	public void setAbsence_date(java.sql.Date absence_date) {
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

	public java.sql.Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.sql.Date return_date) {
		this.return_date = return_date;
	}

	@Override
	public String toString() {
		return "StudentAbsenceDTO [absenceIndex=" + absenceIndex + ", absence_date=" + absence_date + ", absence_term="
				+ absence_term + ", absence_reason=" + absence_reason + ", return_date=" + return_date + "]";
	}

}
