	/*
	 * @class Name : Mj_RecordDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 전공구분 테이블
	*/

package kr.or.initspring.commons;

public class Mj_RecordDTO {
	
	private String student_code;		//학번
	private String department_code;		//학과코드
	private int mj_type;				//전공구분
	
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public int getMj_type() {
		return mj_type;
	}
	public void setMj_type(int mj_type) {
		this.mj_type = mj_type;
	}
	@Override
	public String toString() {
		return "Mj_RecordDTO [student_code=" + student_code + ", department_code=" + department_code + ", mj_type="
				+ mj_type + "]";
	}
	
	
}
