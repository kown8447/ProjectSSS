package kr.or.initspring.dto.commons;

public class OpRequestDTO {
	/*
	   * @Class: OpRequestDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 신청테이블
	   */
	private String subject_code;  //과목코드
	private String semester_code;  //학기코드
	private int success_check;  //신청상태
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
	public int getSuccess_check() {
		return success_check;
	}
	public void setSuccess_check(int success_check) {
		this.success_check = success_check;
	}
	@Override
	public String toString() {
		return "Op_Request [subject_code=" + subject_code + ", semester_code=" + semester_code + ", success_check="
				+ success_check + "]";
	}
	
}
