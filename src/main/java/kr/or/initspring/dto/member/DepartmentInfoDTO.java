package kr.or.initspring.dto.member;

/*
 * @Class : DepartmentInfoDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 학과 정보와 그에 따른 단대 정보, 사무실정보, 건물 정보를 얻기 위한 DTO
*/

public class DepartmentInfoDTO {
		
	private String department_code;			//학과 학부 코드
	private String professor_code;			//교수 번호
	private String office_code;				//사무실 번호
	private String department_name;			//학과 학부 이름
	private String department_description;	//학과 학부 설명
	private int department_seat;			//학과 학부 정원
	private int graduation_credit;			//졸업 학점(전공, 교양)
	private int double_possible;			//복수전공 가능 여부
	private int department_exist;			//학과 존재 여부
	private String college_code;			//단대 코드
	private String college_name;			//단대 이름
	private String office_name;				//사무실이름
	private String building_name;			//건물 이름
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
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
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_description() {
		return department_description;
	}
	public void setDepartment_description(String department_description) {
		this.department_description = department_description;
	}
	public int getDepartment_seat() {
		return department_seat;
	}
	public void setDepartment_seat(int department_seat) {
		this.department_seat = department_seat;
	}
	public int getGraduation_credit() {
		return graduation_credit;
	}
	public void setGraduation_credit(int graduation_credit) {
		this.graduation_credit = graduation_credit;
	}
	public int getDouble_possible() {
		return double_possible;
	}
	public void setDouble_possible(int double_possible) {
		this.double_possible = double_possible;
	}
	public int getDepartment_exist() {
		return department_exist;
	}
	public void setDepartment_exist(int department_exist) {
		this.department_exist = department_exist;
	}
	public String getCollege_code() {
		return college_code;
	}
	public void setCollege_code(String college_code) {
		this.college_code = college_code;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	@Override
	public String toString() {
		return "DepartmentInfoDTO [department_code=" + department_code + ", professor_code=" + professor_code
				+ ", office_code=" + office_code + ", department_name=" + department_name + ", department_description="
				+ department_description + ", department_seat=" + department_seat + ", graduation_credit="
				+ graduation_credit + ", double_possible=" + double_possible + ", department_exist=" + department_exist
				+ ", college_code=" + college_code + ", college_name=" + college_name + ", office_name=" + office_name
				+ "]";
	}
	
	
}
