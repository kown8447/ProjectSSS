package kr.or.initspring.dto.commons;

public class OfficeDTO {
	
	/*
	* @Class: OfficeDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 사무실
	*/
	
	private String office_code;		//사무실코드
	private String building_code;	//건물코드
	private String office_phone;	//전화번호
	private String office_name;;	//사무실 이름
	private int office_possible;	//사용가능 여부
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public int getOffice_possible() {
		return office_possible;
	}
	public void setOffice_possible(int office_possible) {
		this.office_possible = office_possible;
	}
	@Override
	public String toString() {
		return "OfficeDTO [office_code=" + office_code + ", building_code=" + building_code + ", office_phone="
				+ office_phone + ", office_name=" + office_name + ", office_possible=" + office_possible + "]";
	}
	
	

}
