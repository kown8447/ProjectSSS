package kr.or.initspring.dto.commons;

public class BeforeSubjectDTO {
	/*
	   * @Class: BeforeSubjectDTO 
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 수강과목
	   */
	private String subject_code;  //과목코드
	private String before_code;  //선수강 과목 코드
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getBefore_code() {
		return before_code;
	}
	public void setBefore_code(String before_code) {
		this.before_code = before_code;
	}
	@Override
	public String toString() {
		return "Before_SubjectDTO [subject_code=" + subject_code + ", before_code=" + before_code + "]";
	}
	
}
