package kr.or.initspring.dto.commons;

public class LaboratoryDTO {
	
	/*
	* @Class: LaboratoryDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 연구실
	*/
	
	private String lab_code;		//교수연구실코드
	private String building_code;	//건물코드
	private String lab_name;		//연구실 이름
	private String lab_Phone;		//전화번호
	private int lab_possible;		//사용가능 여부
	public String getLab_code() {
		return lab_code;
	}
	public void setLab_code(String lab_code) {
		this.lab_code = lab_code;
	}
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
	public String getLab_Phone() {
		return lab_Phone;
	}
	public void setLab_Phone(String lab_Phone) {
		this.lab_Phone = lab_Phone;
	}
	public int getLab_possible() {
		return lab_possible;
	}
	public void setLab_possible(int lab_possible) {
		this.lab_possible = lab_possible;
	}
	@Override
	public String toString() {
		return "LaboratoryDTO [lab_code=" + lab_code + ", building_code=" + building_code + ", lab_name=" + lab_name
				+ ", lab_Phone=" + lab_Phone + ", lab_possible=" + lab_possible + "]";
	}
	
	
	
}
