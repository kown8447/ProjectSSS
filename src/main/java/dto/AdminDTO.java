	/*
	 * @class Name : AdminDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 관리자 테이블
	*/


package kr.or.initspring.commons;

public class AdminDTO {

	private String admin_code;	//관리자코드
	private String member_id;	//멤버 ID
	private int ad_state;		//상태
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getAd_state() {
		return ad_state;
	}
	public void setAd_state(int ad_state) {
		this.ad_state = ad_state;
	}
	@Override
	public String toString() {
		return "AdminDTO [admin_code=" + admin_code + ", member_id=" + member_id + ", ad_state=" + ad_state + "]";
	}
	
	
	
}
