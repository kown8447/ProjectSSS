package kr.or.initspring.dto.collegeRegister;
/*
* @Class: RecordRequestDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* 학생 성적적보 요청에 필요한 데이터를 받기 위한 DTO
*/

public class RecordRequestDTO {
	private int grade; //학생 학년
	private int semester;//개인 학기
	private String student_code;//학번

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
