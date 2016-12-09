package kr.or.initspring.dto.member;

/*
 * @Class : CollegeInfoDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 단과대학 정보와 그에 따른 건물 정보, 사무실 정보를 얻기 위한 DTO
*/

public class CollegeInfoDTO {
	
	private String college_code;	//단과대학 코드
	private String professor_code;	//교수번호
	private String office_code;		//사무실 코드
	private String college_name;	//단과대학 이름
	private String college_description;	//단과대학 설명
	private String building_code;	//건물코드
	private String office_phone;	//전화번호
	private String office_name;;	//사무실 이름
	private int office_possible;	//사용가능 여부
	private String building_name;	//건물명
	private String building_addr;	//주소
	public String getCollege_code() {
		return college_code;
	}
	public void setCollege_code(String college_code) {
		this.college_code = college_code;
	}
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getCollege_description() {
		return college_description;
	}
	public void setCollege_description(String college_description) {
		this.college_description = college_description;
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
		return "CollegeInfoDTO [college_code=" + college_code + ", professor_code=" + professor_code + ", office_code="
				+ office_code + ", college_name=" + college_name + ", college_description=" + college_description
				+ ", building_code=" + building_code + ", office_phone=" + office_phone + ", office_name=" + office_name
				+ ", office_possible=" + office_possible + ", building_name=" + building_name + ", building_addr="
				+ building_addr + "]";
	}
	
	
}
