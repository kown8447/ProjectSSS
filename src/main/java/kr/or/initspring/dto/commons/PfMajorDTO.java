	/*
	 * @class Name : PfMajorDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 교수 전공구분 테이블
	*/

package kr.or.initspring.dto.commons;

public class PfMajorDTO {
	
	private String professor_code;	//교수코드
	private String department_code;	//학과코드


	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}

	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	@Override
	public String toString() {
		return "PfMajorDTO [professor_code=" + professor_code + ", department_code=" + department_code + "]";

	}
}
