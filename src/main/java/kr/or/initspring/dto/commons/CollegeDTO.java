/*
 * @Class : CollegeDTO
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc : 단과대학 정보를 관리하는 DTO
*/

package kr.or.initspring.dto.commons;

public class CollegeDTO {
	private String college_code;	//단과대학 코드
	private String professor_code;	//교수번호
	private String office_code;		//사무실 코드
	private String college_name;	//단과대학 이름
	private String college_description;	//단과대학 설명
	public String getCollege_code() {
		return college_code;
	}
	public void setCollege_code(String college_code) {
		this.college_code = college_code;
	}
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getCollege_description() {
		return college_description;
	}
	public void setCollege_description(String college_description) {
		this.college_description = college_description;
	}
	@Override
	public String toString() {
		return "CollegeDTO [college_code=" + college_code + ", professor_code=" + professor_code + ", office_code="
				+ office_code + ", college_name=" + college_name + ", college_description=" + college_description + "]";
	}
}
