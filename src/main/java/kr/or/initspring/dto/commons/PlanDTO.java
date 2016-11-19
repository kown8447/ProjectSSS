package kr.or.initspring.dto.commons;

public class PlanDTO {
	/*
	   * @Class: PlanDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 계획서  
	   */
	private String subject_code;  //과목코드
	private String subject_filesrc;  //파일명 (pdf)
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getSubject_filesrc() {
		return subject_filesrc;
	}
	public void setSubject_filesrc(String subject_filesrc) {
		this.subject_filesrc = subject_filesrc;
	}
	@Override
	public String toString() {
		return "PlanDTO [subject_code=" + subject_code + ", subject_filesrc=" + subject_filesrc + "]";
	}

}
