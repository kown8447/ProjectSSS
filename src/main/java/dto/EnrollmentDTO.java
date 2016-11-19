	/*
	 * @class Name : EnrollmentDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 수강신청 테이블
	*/


package kr.or.initspring.commons;

public class EnrollmentDTO {

	private String student_code;	//학번
	private String subject_code;	//과목코드
	
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
		return "EnrollmentDTO [student_code=" + student_code + ", subject_code=" + subject_code + "]";
	}
	
	
	
}
