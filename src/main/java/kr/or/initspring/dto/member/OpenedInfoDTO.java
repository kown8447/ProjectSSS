package kr.or.initspring.dto.member;

public class OpenedInfoDTO {
	
	private String subject_name;
	private String semester_name;
	private int registed_seat;
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSemester_name() {
		return semester_name;
	}
	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
	}
	public int getRegisted_seat() {
		return registed_seat;
	}
	public void setRegisted_seat(int registed_seat) {
		this.registed_seat = registed_seat;
	}
	@Override
	public String toString() {
		return "OpenedInfoDTO [subject_name=" + subject_name + ", semester_name=" + semester_name + ", registed_seat="
				+ registed_seat + "]";
	}
	
	
}
