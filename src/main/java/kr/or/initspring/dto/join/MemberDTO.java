/*
 * @Class : MemberDTO
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc : 회원 가입 시 사용하는 Custom DTO
*/
package kr.or.initspring.dto.join;

import java.util.Date;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MemberDTO {
	private String member_id;
	private String member_pwd;
	private String member_name;
	private String addr_num;
	private String member_addr;
	private String member_addr_detail;
	private Date member_birth;
	private String member_phone;
	private String admin_code;
	private String student_code;
	private String professor_code;
	private String member_email;
	private int member_sex;
	private String member_picture;	//회원 사진 file 명
	private int member_temp;		//임시 비밀번호 발급 상태
	private CommonsMultipartFile file;
	private String code;
	private int code_type;	//학생_교수_관리자 구분
	private int timetable_share;
	
	public int getTimetable_share() {
		return timetable_share;
	}

	public void setTimetable_share(int timetable_share) {
		this.timetable_share = timetable_share;
	}

	public String getAdmin_code() {
		return admin_code;
	}

	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getProfessor_code() {
		return professor_code;
	}

	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pwd() {
		return member_pwd;
	}

	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
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

	public int getMember_sex() {
		return member_sex;
	}

	public void setMember_sex(int member_sex) {
		this.member_sex = member_sex;
	}

	public String getMember_picture() {
		return member_picture;
	}

	public void setMember_picture(String member_picture) {
		this.member_picture = member_picture;
	}

	public int getMember_temp() {
		return member_temp;
	}

	public void setMember_temp(int member_temp) {
		this.member_temp = member_temp;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public int getCode_type() {
		return code_type;
	}

	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}
	public String getAddr_num() {
		return addr_num;
	}

	public void setAddr_num(String addr_num) {
		this.addr_num = addr_num;
	}

	public String getMember_addr_detail() {
		return member_addr_detail;
	}

	public void setMember_addr_detail(String member_addr_detail) {
		this.member_addr_detail = member_addr_detail;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_pwd=" + member_pwd + ", member_name=" + member_name
				+ ", addr_num=" + addr_num + ", member_addr=" + member_addr + ", member_addr_detail="
				+ member_addr_detail + ", member_birth=" + member_birth + ", member_phone=" + member_phone
				+ ", member_email=" + member_email + ", member_sex=" + member_sex + ", member_picture=" + member_picture
				+ ", member_temp=" + member_temp + ", file=" + file + ", code=" + code + ", code_type=" + code_type
				+ "]";
	}

	
}
