	/*
	 * @class Name : MemberDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 회원 테이블
	*/

package kr.or.initspring.dto.commons;

import java.sql.Date;

public class MemberDTO {
	
	private String member_id;		//멤버ID
	private String member_pwd;		//비밀번호
	private String member_name;		//이름
	private String member_addr;		//주소
	private Date member_birth;		//생년월일
	private String member_phone;	//전화번호
	private String member_email;	//이메일
	private int member_sex;			//성별
	private String member_picture;	//사진
	private int member_temp;		//임시비밀번호 상태
	
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
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", member_pwd=" + member_pwd + ", member_name=" + member_name
				+ ", member_addr=" + member_addr + ", member_birth=" + member_birth + ", member_phone=" + member_phone
				+ ", member_email=" + member_email + ", member_sex=" + member_sex + ", member_picture=" + member_picture
				+ ", member_temp=" + member_temp + "]";
	}
	
	
	
}
