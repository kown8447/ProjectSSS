package kr.or.initspring.dto.commons;

public class SmStateDTO {
	
	/*
	* @Class: SmStateDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 재학기록
	*/
	
	private String student_code;		//학번
	private String semester_code;		//학기코드
	private int student_grade;			//학년
	private int student_semester;		//개인학기
	private int get_credit;				//해당학기 이수학점
	private int request_credit;			//해당학기 신청학점
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getStudent_grade() {
		return student_grade;
	}
	public void setStudent_grade(int student_grade) {
		this.student_grade = student_grade;
	}
	public int getStudent_semester() {
		return student_semester;
	}
	public void setStudent_semester(int student_semester) {
		this.student_semester = student_semester;
	}
	public int getGet_credit() {
		return get_credit;
	}
	public void setGet_credit(int get_credit) {
		this.get_credit = get_credit;
	}
	public int getRequest_credit() {
		return request_credit;
	}
	public void setRequest_credit(int request_credit) {
		this.request_credit = request_credit;
	}
	@Override
	public String toString() {
		return "SmStateDTO [student_code=" + student_code + ", semester_code=" + semester_code + ", student_grade="
				+ student_grade + ", student_semester=" + student_semester + ", get_credit=" + get_credit
				+ ", request_credit=" + request_credit + "]";
	}
	
	
}
