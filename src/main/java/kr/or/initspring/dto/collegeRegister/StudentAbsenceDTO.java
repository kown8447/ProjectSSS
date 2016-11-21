package kr.or.initspring.dto.collegeRegister;

public class StudentAbsenceDTO {
	private int absenceIndex;
	private java.sql.Date absence_date;
	private int absence_term;
	private String absence_reason;
	private java.sql.Date return_date;

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
