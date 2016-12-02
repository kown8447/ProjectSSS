package kr.or.initspring.dto.member;

public class LabBuildingDTO {

	private String building_name;
	private String building_code;
	private String lab_code;
	private String lab_name;
	private String lab_phone;
	private int lab_possible;
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
