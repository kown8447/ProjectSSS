package kr.or.initspring.dto.commons;

public class LinkDTO {

	/*
	* @Class: LinkDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 전체 매뉴 링크 
	*/
	
	private String link_code;		//링크코드
	private String role_name;		//권한명
	private int link_type;			//링크타입
	private String link_addr;		//링크주소
	private String link_name;		//링크이름
	public String getLink_code() {
		return link_code;
	}
	public void setLink_code(String link_code) {
		this.link_code = link_code;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getLink_type() {
		return link_type;
	}
	public void setLink_type(int link_type) {
		this.link_type = link_type;
	}
	public String getLink_addr() {
		return link_addr;
	}
	public void setLink_addr(String link_addr) {
		this.link_addr = link_addr;
	}
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	@Override
	public String toString() {
		return "LinkDTO [link_code=" + link_code + ", role_name=" + role_name + ", link_type=" + link_type
				+ ", link_addr=" + link_addr + ", link_name=" + link_name + "]";
	}
	
	
	
}
