	/*
	 * @class Name : RoleTypeDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 권한종류 테이블
	*/


package kr.or.initspring.dto.commons;
public class RoleTypeDTO {
	
	private String role_name;	//권한명

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
		return "Role_TypeDTO [role_name=" + role_name + "]";
	}
	
	
	
	
}
