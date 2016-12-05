package kr.or.initspring.dto.commons;

public class BuildingDTO {
	
	/*
	* @Class: BuildingDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 건물
	*/
	
	private String building_code;	//건물코드
	private String building_name;	//건물명
	private String building_addr;	//주소
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_addr() {
		return building_addr;
	}
	public void setBuilding_addr(String building_addr) {
		this.building_addr = building_addr;
	}
	@Override
	public String toString() {
		return "BuildingDTO [building_code=" + building_code + ", building_name=" + building_name + ", building_addr="
				+ building_addr + "]";
	}
}
