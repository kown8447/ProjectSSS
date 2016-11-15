package kr.or.initspring.dto;

import java.util.Date;

public class MemberTestDTO {
	private String userid;
	private String pwd;
	private String name;
	private String email;
	private String role_name;
	private String code;
	private int code_type;
	private Date birth;
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getCode_type() {
		return code_type;
	}
	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberTestDTO [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", email=" + email
				+ ", role_name=" + role_name + ", code=" + code + ", code_type=" + code_type + ", birth=" + birth + "]";
	}

}
