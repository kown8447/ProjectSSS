package kr.or.initspring.dto.commons;

public class StStateDTO {
	
	/*
	* @Class: StStateDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 현재학적상태
	*/
	
	private String student_code;		//학번
	private int grade;					//학년
	private int personal_semester;		//개인 학기
	private int total_credit;			//이수학점
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getPersonal_semester() {
		return personal_semester;
	}
	public void setPersonal_semester(int personal_semester) {
		this.personal_semester = personal_semester;
	}
	public int getTotal_credit() {
		return total_credit;
	}
	public void setTotal_credit(int total_credit) {
		this.total_credit = total_credit;
	}
	@Override
	public String toString() {
		return "StStateDTO [student_code=" + student_code + ", grade=" + grade + ", personal_semester="
				+ personal_semester + ", total_credit=" + total_credit + "]";
	}
	
	
}
