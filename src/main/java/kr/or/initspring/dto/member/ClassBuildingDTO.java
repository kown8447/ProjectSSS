package kr.or.initspring.dto.member;

public class ClassBuildingDTO {
	
	private String classroom_code;
	private String building_code;
	private String building_name;
	private String classroom_name;
	private int seat;
	private int classroom_type;
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
	}
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
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getClassroom_type() {
		return classroom_type;
	}
	public void setClassroom_type(int classroom_type) {
		this.classroom_type = classroom_type;
	}
	@Override
	public String toString() {
		return "ClassBuildingDTO [classroom_code=" + classroom_code + ", building_code=" + building_code
				+ ", building_name=" + building_name + ", classroom_name=" + classroom_name + ", seat=" + seat
				+ ", classroom_type=" + classroom_type + "]";
	}
	
	

}
