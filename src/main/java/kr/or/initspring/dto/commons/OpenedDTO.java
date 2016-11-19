package kr.or.initspring.dto.commons;

public class OpenedDTO {
	/*
	   * @Class: OpenedDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 개설과목
	   */
	private String subject_code;   //과목코드
	private String semester_code;   //학기코드
	private int registed_seat;		//수강인원
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getRegisted_seat() {
		return registed_seat;
	}
	public void setRegisted_seat(int registed_seat) {
		this.registed_seat = registed_seat;
	}
	@Override
	public String toString() {
		return "Opened [subject_code=" + subject_code + ", semester_code=" + semester_code + ", registed_seat="
				+ registed_seat + "]";
	}
	
}
