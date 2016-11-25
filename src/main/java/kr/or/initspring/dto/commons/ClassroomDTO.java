/*
 * @Class : ClassroomDTO
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc : 강의실 정보를 담는 DTO
*/

package kr.or.initspring.dto.commons;

public class ClassroomDTO {
	private String classroom_code;
	private String building_code;
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
		return "ClassroomDTO [classroom_code=" + classroom_code + ", building_code=" + building_code
				+ ", classroom_name=" + classroom_name + ", seat=" + seat + ", classroom_type=" + classroom_type + "]";
	}

}
