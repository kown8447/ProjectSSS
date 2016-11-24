package kr.or.initspring.dto.lectureMg;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CustomLectureMgDTO {
	
	private String subject_code;	//과목코드
	private String professor_code;	//교수코드
	private String subject_name;	//과목명
	private Integer subject_credit;	//배정학점
	private Integer subject_state;	//과목상태
	private Integer subject_seats;	//정원
	private Integer subject_type;	//과목유형
	private CommonsMultipartFile subjcet_filesrc;	//강의계획서
	private String before_code;		//선수과목
	private String department_code;	//학과코드  전공과목일시 생김
	private String required_choice;	//필수/선택
	private String semester_code;	//학기코드
	private String classroom_code;	//강의실코드
	private String period_code;		//교시코드
	private String buliding_code;	//건물코드
	private Integer seat;			//수용인원
	private Integer classroom_type;	//강의실타입
	private String buliding_name;	//건물명
	private String buliding_addr;	//건물주소
	private int record_grade;		//학년
	private int success_check; 		//신청상태
	
	public int getRecord_grade() {
		return record_grade;
	}
	public void setRecord_grade(int record_grade) {
		this.record_grade = record_grade;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
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
	public Integer getSubject_credit() {
		return subject_credit;
	}
	public void setSubject_credit(Integer subject_credit) {
		this.subject_credit = subject_credit;
	}
	public Integer getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(Integer subject_state) {
		this.subject_state = subject_state;
	}
	public Integer getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(Integer subject_seats) {
		this.subject_seats = subject_seats;
	}
	public Integer getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(Integer subject_type) {
		this.subject_type = subject_type;
	}
	public CommonsMultipartFile getSubjcet_filesrc() {
		return subjcet_filesrc;
	}
	public void setSubjcet_filesrc(CommonsMultipartFile subjcet_filesrc) {
		this.subjcet_filesrc = subjcet_filesrc;
	}
	public String getBefore_code() {
		return before_code;
	}
	public void setBefore_code(String before_code) {
		this.before_code = before_code;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	
	public String getRequired_choice() {
		return required_choice;
	}
	public void setRequired_choice(String required_choice) {
		this.required_choice = required_choice;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
	}
	public String getPeriod_code() {
		return period_code;
	}
	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}
	public String getBuliding_code() {
		return buliding_code;
	}
	public void setBuliding_code(String buliding_code) {
		this.buliding_code = buliding_code;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public Integer getClassroom_type() {
		return classroom_type;
	}
	public void setClassroom_type(Integer classroom_type) {
		this.classroom_type = classroom_type;
	}
	public String getBuliding_name() {
		return buliding_name;
	}
	public void setBuliding_name(String buliding_name) {
		this.buliding_name = buliding_name;
	}
	public String getBuliding_addr() {
		return buliding_addr;
	}
	public void setBuliding_addr(String buliding_addr) {
		this.buliding_addr = buliding_addr;
	}
	
	
	
}
