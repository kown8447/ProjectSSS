package kr.or.initspring.dto.collegeRegister;

public class StudentSemesterStateDTO {
	private String semester_code;
	private int student_grade;
	private int student_semester;
	private int get_credit;
	private int request_credit;
	private String semesterYear;
	private String semesterType;

	public String getSemester_code() {
		return semester_code;
	}

	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}

	public int getStudent_grade() {
		return student_grade;
	}

	public void setStudent_grade(int student_grade) {
		this.student_grade = student_grade;
	}

	public int getStudent_semester() {
		return student_semester;
	}

	public void setStudent_semester(int student_semester) {
		this.student_semester = student_semester;
	}

	public int getGet_credit() {
		return get_credit;
	}

	public void setGet_credit(int get_credit) {
		this.get_credit = get_credit;
	}

	public int getRequest_credit() {
		return request_credit;
	}

	public void setRequest_credit(int request_credit) {
		this.request_credit = request_credit;
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
		return "StudentSemesterStateDTO [semester_code=" + semester_code + ", student_grade=" + student_grade
				+ ", student_semester=" + student_semester + ", get_credit=" + get_credit + ", request_credit="
				+ request_credit + ", semesterYear=" + semesterYear + ", semesterType=" + semesterType + "]";
	}

}
