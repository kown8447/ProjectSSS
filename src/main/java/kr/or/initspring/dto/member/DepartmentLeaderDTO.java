package kr.or.initspring.dto.member;

/*
 * @Class : DepartmentLeaderDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 학과장의 정보를 출력하기위해 교수정보, 단대정보, 학과 정보를 얻기 위한 DTO
*/

public class DepartmentLeaderDTO {
	private String professor_code;		//교수 코드
	private String member_name;			//회원 이름
	private String department_code;		//학과 코드
	private String department_name;		//학과 이름
	private String college_name;		//단대 이름

	public String getProfessor_code() {
		return professor_code;
	}

	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	@Override
	public String toString() {
		return "DepartmentLeaderDTO [professor_code=" + professor_code + ", member_name=" + member_name
				+ ", department_code=" + department_code + ", department_name=" + department_name + ", college_name="
				+ college_name + "]";
	}

}
