package kr.or.initspring.dto.member;

/*
 * @Class : OpenedInfoDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 개설된 과목에 따른 과목 정보를 얻기 위한 DTO
*/

public class OpenedInfoDTO {
	
	private String subject_name;		//과목 이름
	private String semester_name;		//학기이름
	private int registed_seat;			//수강인원
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSemester_name() {
		return semester_name;
	}
	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
	}
	public int getRegisted_seat() {
		return registed_seat;
	}
	public void setRegisted_seat(int registed_seat) {
		this.registed_seat = registed_seat;
	}
	@Override
	public String toString() {
		return "OpenedInfoDTO [subject_name=" + subject_name + ", semester_name=" + semester_name + ", registed_seat="
				+ registed_seat + "]";
	}
	
	
}
