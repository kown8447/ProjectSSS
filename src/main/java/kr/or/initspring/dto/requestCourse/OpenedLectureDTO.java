/*
 * @Class : OpenedLectureDTO
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc : 개설강의 정보를 관리하기 위한 CustomDTO
*/

package kr.or.initspring.dto.requestCourse;

import java.util.List;

import kr.or.initspring.dto.commons.PeriodDTO;

public class OpenedLectureDTO {
	private String subject_code;
	private String semester_code;	//학기 코드
	private int registed_seat;		//수강 정원
	private String professor_code;
	private String subject_name;
	private int subject_credit;		//강의 배정 학점
	private int grade_limit;		//수강 대상(학년 제한)
	private int subject_state;		//과목 상태(0:개설안함, 1:개설신청, 2:개설)
	private int subject_seats;		//수강 정원
	private int subject_type;
	private List<PeriodDTO> period;	//과목 시간을 관리하기 위한 DTO(강의 시간은 복수이기 때문에 List)
	private String professor_name;
	private String subject_filesrc;	//강의 계획서 파일명
	private int required_choice;	//과목 필수_선택 여부
	private List<CustomClassRoomDTO> customClassroomDTO;	//강의실 정보(강의실이 가지고 있는 강의 시간정보도 포함)
	private int retake_check;	//재수강 여부 확인
	private int reserve_check;	//수강 신청 성공 실패 여부 확인
	
	
	public int getReserve_check() {
		return reserve_check;
	}
	public void setReserve_check(int reserve_check) {
		this.reserve_check = reserve_check;
	}
	public int getRetake_check() {
		return retake_check;
	}
	public void setRetake_check(int retake_check) {
		this.retake_check = retake_check;
	}
	public List<CustomClassRoomDTO> getCustomClassroomDTO() {
		return customClassroomDTO;
	}
	public void setCustomClassroomDTO(List<CustomClassRoomDTO> customClassroomDTO) {
		this.customClassroomDTO = customClassroomDTO;
	}
	public int getRequired_choice() {
		return required_choice;
	}
	public void setRequired_choice(int required_choice) {
		this.required_choice = required_choice;
	}
	public String getSubject_filesrc() {
		return subject_filesrc;
	}
	public void setSubject_filesrc(String subject_filesrc) {
		this.subject_filesrc = subject_filesrc;
	}
	public String getProfessor_name() {
		return professor_name;
	}
	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}
	public List<PeriodDTO> getPeriod() {
		return period;
	}
	public void setPeriod(List<PeriodDTO> period) {
		this.period = period;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getRegisted_seat() {
		return registed_seat;
	}
	public void setRegisted_seat(int registed_seat) {
		this.registed_seat = registed_seat;
	}
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public int getSubject_credit() {
		return subject_credit;
	}
	public void setSubject_credit(int subject_credit) {
		this.subject_credit = subject_credit;
	}
	public int getGrade_limit() {
		return grade_limit;
	}
	public void setGrade_limit(int grade_limit) {
		this.grade_limit = grade_limit;
	}
	public int getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(int subject_state) {
		this.subject_state = subject_state;
	}
	public int getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(int subject_seats) {
		this.subject_seats = subject_seats;
	}
	public int getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(int subject_type) {
		this.subject_type = subject_type;
	}
	@Override
	public String toString() {
		return "OpenedLectureDTO [subject_code=" + subject_code + ", semester_code=" + semester_code
				+ ", registed_seat=" + registed_seat + ", professor_code=" + professor_code + ", subject_name="
				+ subject_name + ", subject_credit=" + subject_credit + ", grade_limit=" + grade_limit
				+ ", subject_state=" + subject_state + ", subject_seats=" + subject_seats + ", subject_type="
				+ subject_type + ", period=" + period + ", professor_name=" + professor_name + ", subject_filesrc="
				+ subject_filesrc + ", required_choice=" + required_choice + ", customClassroomDTO="
				+ customClassroomDTO + ", retake_check=" + retake_check + ", reserve_check=" + reserve_check + "]";
	}
}
