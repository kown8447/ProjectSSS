package kr.or.initspring.dto.member;

public class DepartmentLeaderDTO {
	private String professor_code;
	private String member_name;
	private String department_code;
	private String department_name;
	private String college_name;

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
