package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentSemesterStateDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 재학기록
* 
*/
public class StudentSemesterStateDTO {
	private String semester_code; // 학기코드
	private int student_grade; // 학생학년
	private int student_semester; // 학생개인학기
	private int get_credit; //취득학점
	private int request_credit; //신청학점
	private String semesterYear; //학기년도
	private String semesterType; //학기 분류

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
