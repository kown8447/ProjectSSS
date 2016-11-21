package kr.or.initspring.dto.collegeRegister;

public class RecordRequestDTO {
	private int grade;
	private int semester;
	private String student_code;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	@Override
	public String toString() {
		return "RecordRequestDTO [grade=" + grade + ", semester=" + semester + ", student_code=" + student_code + "]";
	}

}
