package kr.or.initspring.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CustomSubjectDTO {
	
	private String subject_code;
	private String professor_code;
	private String subject_name;
	private Integer subject_credit;
	private Integer subject_state;
	private Integer subject_seats;
	private Integer subject_type;
	private CommonsMultipartFile subjcet_filesrc;
	private String before_code;
	private String department_code;
	private String required_choice;
	private String semester_code;
	private String classroom_code;
	private String period_code;
	private String buliding_code;
	private String classroon_name;
	private Integer seat;
	private Integer classroom_type;
	private String buliding_name;
	private String buliding_addr;
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
	public String getClassroon_name() {
		return classroon_name;
	}
	public void setClassroon_name(String classroon_name) {
		this.classroon_name = classroon_name;
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
