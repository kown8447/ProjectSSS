package kr.or.initspring.dto.commons;

public class TimeTableDTO {
	/*
	   * @Class: TimeTableDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 재학시간표  
	   */
	private String student_code;  //학번
	private String subject_code;  //과목코드 
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	@Override
	public String toString() {
		return "TimeTableDTO [student_code=" + student_code + ", subject_code=" + subject_code + "]";
	}
	
	
}
