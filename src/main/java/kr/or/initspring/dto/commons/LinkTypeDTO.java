package kr.or.initspring.dto.commons;

public class LinkTypeDTO {
	
	/*
	* @Class: LinkTypeDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 링크 타입
	*/

	private int link_type;		//링크타입
	private String type_name;	//타입명칭
	public int getLink_type() {
		return link_type;
	}
	public void setLink_type(int link_type) {
		this.link_type = link_type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	@Override
	public String toString() {
		return "LinkTypeDTO [link_type=" + link_type + ", type_name=" + type_name + "]";
	}
	
	
	
}
