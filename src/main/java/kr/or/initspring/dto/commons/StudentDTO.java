	/*
	 * @class Name : StudentDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 학생 테이블
	*/


package kr.or.initspring.dto.commons;

public class StudentDTO {

	private String student_code;	//학번
	private String member_id;		//멤버ID
	private int state_code;			//학생상태코드
	private int timetable_share;	//시간표 공유 동의
	
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	public int getTimetable_share() {
		return timetable_share;
	}
	public void setTimetable_share(int timetable_share) {
		this.timetable_share = timetable_share;
	}
	@Override
	public String toString() {
		return "MemberDTO [student_code=" + student_code + ", member_id=" + member_id + ", state_code=" + state_code
				+ ", timetable_share=" + timetable_share + "]";
	}
	
	
}
