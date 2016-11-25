package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentRegisterDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 등록, 학기
* 
*/

public class StudentRegisterDTO {
	private String student_code; //학번
	private String semester_code; //학기코드
	private String semester_Name; //학기 이름
	private java.sql.Date semester_start; //학기 시작일
	private java.sql.Date semester_end; //학기 종료일
	private int register_type; //등록유형 (일반, 계절학기, 졸업연기)
	private int tuition; // 등록금액
	private int register_state; //금액납부상태
	private String semesterYear; //학기년도
	private String semesterType; //학기구분(전반기, 후반기, 계절학기-여름, 계절학기-겨울)

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public String getSemester_code() {
		return semester_code;
	}

	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}

	public String getSemester_Name() {
		return semester_Name;
	}

	public void setSemester_Name(String semester_Name) {
		this.semester_Name = semester_Name;
	}

	public java.sql.Date getSemester_start() {
		return semester_start;
	}

	public void setSemester_start(java.sql.Date semester_start) {
		this.semester_start = semester_start;
	}

	public java.sql.Date getSemester_end() {
		return semester_end;
	}

	public void setSemester_end(java.sql.Date semester_end) {
		this.semester_end = semester_end;
	}

	public int getRegister_type() {
		return register_type;
	}

	public void setRegister_type(int register_type) {
		this.register_type = register_type;
	}

	public int getTuition() {
		return tuition;
	}

	public void setTuition(int tuition) {
		this.tuition = tuition;
	}

	public int getRegister_state() {
		return register_state;
	}

	public void setRegister_state(int register_state) {
		this.register_state = register_state;
	}

	public String getSemesterYear() {
		return semesterYear;
	}

	public void setSemesterYear(String semesterYear) {
		this.semesterYear = semesterYear;
	}

	public String getSemesterType() {
		return semesterType;
	}

	public void setSemesterType(String semesterType) {
		this.semesterType = semesterType;
	}

	@Override
	public String toString() {
		return "StudentRegisterDTO [student_code=" + student_code + ", semester_code=" + semester_code
				+ ", semester_Name=" + semester_Name + ", semester_start=" + semester_start + ", semester_end="
				+ semester_end + ", register_type=" + register_type + ", tuition=" + tuition + ", register_state="
				+ register_state + ", semesterYear=" + semesterYear + ", semesterType=" + semesterType + "]";
	}

}
