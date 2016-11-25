package kr.or.initspring.dto.commons;

public class AskTimeDTO {
	  /*
	   * @Class: AskTimeDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 신청과목 시간표  
	   */
	private String classroom_code; //강의실코드
	private String period_code;  //교시코드
	private String subject_code;   //과목코드
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
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	@Override
	public String toString() {
		return "Ask_TimeDTO [classroom_code=" + classroom_code + ", period_code=" + period_code + ", subject_code="
				+ subject_code + "]";
	}
	
}
