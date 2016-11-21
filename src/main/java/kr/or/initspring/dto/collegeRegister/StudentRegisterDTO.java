package kr.or.initspring.dto.collegeRegister;

public class StudentRegisterDTO {
	private String student_code;
	private String semester_code;
	private String semester_Name;
	private java.sql.Date semester_start;
	private java.sql.Date semester_end;
	private int register_Type;
	private int tuition;
	private int register_state;
	private String semesterYear;
	private String semesterType;

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

	public int getRegister_Type() {
		return register_Type;
	}

	public void setRegister_Type(int register_Type) {
		this.register_Type = register_Type;
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
				+ semester_end + ", register_Type=" + register_Type + ", tuition=" + tuition + ", register_state="
				+ register_state + ", semesterYear=" + semesterYear + ", semesterType=" + semesterType + "]";
	}

}
