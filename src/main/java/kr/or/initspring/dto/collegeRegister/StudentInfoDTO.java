package kr.or.initspring.dto.collegeRegister;
/*
* @Class: StudentInfoDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 학생,회원
* 
*/

import java.sql.Date;

public class StudentInfoDTO {
	private String student_code; //학번
	private String code_name; //학생상태
	private String member_name;//회원 이름
	private String member_addr;//회원 주소
	private Date member_birth;//회원 생일
	private String member_phone;//회원 연락처
	private String member_email;//회원 이메일
	private String member_picture;//회원 사진(파일명)
	private int enterYear;//입학일

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
		return "StudentInfoDTO [student_code=" + student_code + ", code_name=" + code_name + ", member_name="
				+ member_name + ", member_addr=" + member_addr + ", member_birth=" + member_birth + ", member_phone="
				+ member_phone + ", member_email=" + member_email + ", member_picture=" + member_picture
				+ ", enterYear=" + enterYear + "]";
	}

	

}
