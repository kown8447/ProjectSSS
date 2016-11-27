package kr.or.initspring.dto.commons;

public class BeforeSubjectDTO {
	/*
	   * @Class: BeforeSubjectDTO 
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 수강과목
	   */
	private String subject_code;  //과목코드
	private String before_name;  //선수과목
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getBefore_name() {
		return before_name;
	}
	public void setBefore_name(String before_name) {
		this.before_name = before_name;
	}
	@Override
	public String toString() {
		return "BeforeSubjectDTO [subject_code=" + subject_code + ", before_name=" + before_name + "]";
	}
}
