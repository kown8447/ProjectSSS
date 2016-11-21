package kr.or.initspring.dto.collegeRegister;

import java.sql.Date;

public class StudentInfoDTO {
	private String student_code;
	private String code_name;
	private String studentState;
	private String member_name;
	private String member_addr;
	private Date member_birth;
	private String member_phone;
	private String member_email;
	private String member_picture;
	private int enterYear;

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getStudentState() {
		return studentState;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_addr() {
		return member_addr;
	}

	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}

	public Date getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_picture() {
		return member_picture;
	}

	public void setMember_picture(String member_picture) {
		this.member_picture = member_picture;
	}

	public int getEnterYear() {
		return enterYear;
	}

	public void setEnterYear(int enterYear) {
		this.enterYear = enterYear;
	}

	@Override
	public String toString() {
		return "StudentInfoDTO [student_code=" + student_code + ", code_name=" + code_name + ", studentState="
				+ studentState + ", member_name=" + member_name + ", member_addr=" + member_addr + ", member_birth="
				+ member_birth + ", member_phone=" + member_phone + ", member_email=" + member_email
				+ ", member_picture=" + member_picture + ", enterYear=" + enterYear + "]";
	}

}
