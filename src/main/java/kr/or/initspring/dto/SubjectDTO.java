package kr.or.initspring.dto;

public class SubjectDTO {
	private String subject_code;
	private String professor_code;
	private String subject_name;
	private int subject_credit;
	private int grade_limit;
	private int subject_state;
	private int subject_seats;
	private int subject_type;
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public int getSubject_credit() {
		return subject_credit;
	}
	public void setSubject_credit(int subject_credit) {
		this.subject_credit = subject_credit;
	}
	public int getGrade_limit() {
		return grade_limit;
	}
	public void setGrade_limit(int grade_limit) {
		this.grade_limit = grade_limit;
	}
	public int getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(int subject_state) {
		this.subject_state = subject_state;
	}
	public int getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(int subject_seats) {
		this.subject_seats = subject_seats;
	}
	public int getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(int subject_type) {
		this.subject_type = subject_type;
	}
	@Override
	public String toString() {
		return "SubjectDTO [subject_code=" + subject_code + ", professor_code=" + professor_code + ", subject_name="
				+ subject_name + ", subject_credit=" + subject_credit + ", grade_limit=" + grade_limit
				+ ", subject_state=" + subject_state + ", subject_seats=" + subject_seats + ", subject_type="
				+ subject_type + "]";
	}
}
