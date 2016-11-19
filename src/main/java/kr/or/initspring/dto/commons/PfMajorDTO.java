	/*
	 * @class Name : PfMajorDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 교수 전공구분 테이블
	*/

package kr.or.initspring.dto.commons;

public class PfMajorDTO {
	
	private String professor_code;	//교수코드
	private String departmemt_code;	//학과코드
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getDepartmemt_code() {
		return departmemt_code;
	}
	public void setDepartmemt_code(String departmemt_code) {
		this.departmemt_code = departmemt_code;
	}
	@Override
	public String toString() {
		return "Pf_MajorDTO [professor_code=" + professor_code + ", departmemt_code=" + departmemt_code + "]";
	}
	
	
	
	
}
