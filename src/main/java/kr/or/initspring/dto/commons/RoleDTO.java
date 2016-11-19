	/*
	 * @class Name : RoleDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 권한 테이블
	*/


package kr.or.initspring.dto.commons;

public class RoleDTO {
	
	private String role_name;	//권한명
	private String member_id;	//멤버ID
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "RoleDTO [role_name=" + role_name + ", member_id=" + member_id + "]";
	}
	
	
}
