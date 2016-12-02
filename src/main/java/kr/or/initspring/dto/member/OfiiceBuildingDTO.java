package kr.or.initspring.dto.member;

public class OfiiceBuildingDTO {
	
	private String building_code;
	private String building_name;
	private String office_code;
	private String office_phone;
	private String office_name;
	private int office_possible;
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
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
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
		return "OfiiceBuildingDTO [building_code=" + building_code + ", building_name=" + building_name
				+ ", office_code=" + office_code + ", office_phone=" + office_phone + ", office_name=" + office_name
				+ ", office_possible=" + office_possible + "]";
	}
	
	
}
