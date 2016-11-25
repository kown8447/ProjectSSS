package kr.or.initspring.dto.commons;

public class RecordDTO {
	
	/*
	* @Class: RecordDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 성적
	*/
	
	private String record_code;		//성적코드
	private String student_code;	//학번
	private String subject_code;	//과목코드
	private String semester_code;	//학기코드
	private String record_level;	//성적
	private int record_grade;		//학년
	private int record_semester;	//학기
	private int retake_check;		//재수강
	public String getRecord_code() {
		return record_code;
	}
	public void setRecord_code(String record_code) {
		this.record_code = record_code;
	}
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
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public String getRecord_level() {
		return record_level;
	}
	public void setRecord_level(String record_level) {
		this.record_level = record_level;
	}
	public int getRecord_grade() {
		return record_grade;
	}
	public void setRecord_grade(int record_grade) {
		this.record_grade = record_grade;
	}
	public int getRecord_semester() {
		return record_semester;
	}
	public void setRecord_semester(int record_semester) {
		this.record_semester = record_semester;
	}
	public int getRetake_check() {
		return retake_check;
	}
	public void setRetake_check(int retake_check) {
		this.retake_check = retake_check;
	}
	@Override
	public String toString() {
		return "RecordDTO [record_code=" + record_code + ", student_code=" + student_code + ", subject_code="
				+ subject_code + ", semester_code=" + semester_code + ", record_level=" + record_level
				+ ", record_grade=" + record_grade + ", record_semester=" + record_semester + ", retake_check="
				+ retake_check + "]";
	}
	
	
}
