package kr.or.initspring.dto.commons;

public class DoubleDTO {
	 /*
	   * @Class: DoubleDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 복수전공 
	   */
	private String department_code; //학과코드 
	private int double_credit;  //복수전공 이수학점
	private int double_credit_avg; //복수전공 신청 기준 평점
	private int double_grade; //복수전공 신청 기준 학년
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public int getDouble_credit() {
		return double_credit;
	}
	public void setDouble_credit(int double_credit) {
		this.double_credit = double_credit;
	}
	public int getDouble_credit_avg() {
		return double_credit_avg;
	}
	public void setDouble_credit_avg(int double_credit_avg) {
		this.double_credit_avg = double_credit_avg;
	}
	public int getDouble_grade() {
		return double_grade;
	}
	public void setDouble_grade(int double_grade) {
		this.double_grade = double_grade;
	}
	@Override
	public String toString() {
		return "DoubleDTO [department_code=" + department_code + ", double_credit=" + double_credit
				+ ", double_credit_avg=" + double_credit_avg + ", double_grade=" + double_grade + "]";
	}
	
}
