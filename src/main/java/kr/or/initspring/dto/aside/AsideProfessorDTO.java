package kr.or.initspring.dto.aside;

public class AsideProfessorDTO {
	private String professor_code;
	private String member_name;
	private String college_name;
	private String department_name;
	private String member_picture;

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

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getMember_picture() {
		return member_picture;
	}

	public void setMember_picture(String member_picture) {
		this.member_picture = member_picture;
	}

	@Override
	public String toString() {
		return "AsideProfessorDTO [professor_code=" + professor_code + ", member_name=" + member_name
				+ ", college_name=" + college_name + ", department_name=" + department_name + ", member_picture="
				+ member_picture + "]";
	}

}
