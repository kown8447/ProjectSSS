package kr.or.initspring.dto;

public class SubjectDTO {
	
	private String subject_code;
	private String professor_Code;
	private String subject_Name;
	private Integer subject_Credit;
	private Integer subject_state;
	private Integer subject_seats;
	private Integer subject_Type;
	
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getProfessor_Code() {
		return professor_Code;
	}
	public void setProfessor_Code(String professor_Code) {
		this.professor_Code = professor_Code;
	}
	public String getSubject_Name() {
		return subject_Name;
	}
	public void setSubject_Name(String subject_Name) {
		this.subject_Name = subject_Name;
	}
	public Integer getSubject_Credit() {
		return subject_Credit;
	}
	public void setSubject_Credit(Integer subject_Credit) {
		this.subject_Credit = subject_Credit;
	}
	public Integer getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(Integer subject_state) {
		this.subject_state = subject_state;
	}
	public Integer getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(Integer subject_seats) {
		this.subject_seats = subject_seats;
	}
	public Integer getSubject_Type() {
		return subject_Type;
	}
	public void setSubject_Type(Integer subject_Type) {
		this.subject_Type = subject_Type;
	}
	@Override
	public String toString() {
		return "SubjectDTO [subject_code=" + subject_code + ", professor_Code=" + professor_Code + ", subject_Name="
				+ subject_Name + ", subject_Credit=" + subject_Credit + ", subject_state=" + subject_state
				+ ", subject_seats=" + subject_seats + ", subject_Type=" + subject_Type + "]";
	}

}