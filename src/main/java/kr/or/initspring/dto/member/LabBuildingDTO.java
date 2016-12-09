package kr.or.initspring.dto.member;

/*
 * @Class : LabBuildingDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 연구실에 따른 건물 정보를 얻기 위한 DTO
*/

public class LabBuildingDTO {

	private String building_name;		//건물 이름
	private String building_code;		//건물 코드
	private String lab_code;			//연구실 코드
	private String lab_name;			//연구실 이름
	private String lab_phone;			//연구실 전화번호
	private int lab_possible;			//연구실 사용가능 여부
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}
	public String getLab_code() {
		return lab_code;
	}
	public void setLab_code(String lab_code) {
		this.lab_code = lab_code;
	}
	public String getLab_name() {
		return lab_name;
	}
	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}
	public String getLab_phone() {
		return lab_phone;
	}
	public void setLab_phone(String lab_phone) {
		this.lab_phone = lab_phone;
	}
	public int getLab_possible() {
		return lab_possible;
	}
	public void setLab_possible(int lab_possible) {
		this.lab_possible = lab_possible;
	}
	@Override
	public String toString() {
		return "LabBuildingDTO [building_name=" + building_name + ", building_code=" + building_code + ", lab_code="
				+ lab_code + ", lab_name=" + lab_name + ", lab_phone=" + lab_phone + ", lab_possible=" + lab_possible
				+ "]";
	}
	
	
}
