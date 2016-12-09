package kr.or.initspring.dto.oprequest;

/*
 * @Class : OpClassRoomDTO
 * @Date : 2016.11.27
 * @Author : 성홍모
 * @Desc : 강의실 정보와 강의실에 따른 건물 정보를 출력하기 위한 DTO
*/

public class OpClassRoomDTO {
	
	private String building_code;		//건물코드
	private String building_name;		//건물명
	private String classroom_code;		//강의실 코드
	private String classroom_name;		//강의실 이름
	private int seat;					//정원
	private int classroom_type;			//강의실 형태
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
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
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
		return "OpClassRoomDTO [building_code=" + building_code + ", building_name=" + building_name
				+ ", classroom_code=" + classroom_code + ", classroom_name=" + classroom_name + ", seat=" + seat
				+ ", classroom_type=" + classroom_type + "]";
	}
	
	
}
