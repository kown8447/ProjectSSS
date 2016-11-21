package kr.or.initspring.dto.commons;

public class LectureDTO {
	/*
	   * @Class: LectureDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 강의시간  
	   */
	private String subject_code;   //과목코드
	private String classroom_code;   //강의실코드
	private String period_code;   //교시코드
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
	}
	public String getPeriod_code() {
		return period_code;
	}
	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}
	@Override
	public String toString() {
		return "LectureDTO [subject_code=" + subject_code + ", classroom_code=" + classroom_code + ", period_code="
				+ period_code + "]";
	}
	
}
