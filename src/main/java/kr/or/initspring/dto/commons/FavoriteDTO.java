package kr.or.initspring.dto.commons;

public class FavoriteDTO {
	
	/*
	* @Class: FavoriteDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 즐겨찾기
	*/
	
	private String link_code;		//링크코드
	private String member_id;		//맴버ID
	public String getLink_code() {
		return link_code;
	}
	public void setLink_code(String link_code) {
		this.link_code = link_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "FavoriteDTO [link_code=" + link_code + ", member_id=" + member_id + "]";
	}
	
	
}
