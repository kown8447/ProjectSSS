package kr.or.initspring.dto.aside;

public class AsideStudentDTO {
	private String student_code;
	private String member_name;
	private int grade;
	private int personal_semester;
	private String department_name;
	private String college_name;
	private String member_picture;

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
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

	public String getMember_picture() {
		return member_picture;
	}

	public void setMember_picture(String member_picture) {
		this.member_picture = member_picture;
	}

	@Override
	public String toString() {
		return "AsideStudentDTO [student_code=" + student_code + ", member_name=" + member_name + ", grade=" + grade
				+ ", personal_semester=" + personal_semester + ", department_name=" + department_name
				+ ", college_name=" + college_name + ", member_picture=" + member_picture + "]";
	}

}
