package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentStateDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 학적상태
* 
*/
public class StudentStateDTO {
	private int grade; // 현재 학년
	private int personal_semester;// 학생 개인 학기
	private int total_credit;// 총 이수 학점

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getPersonal_semester() {
		return personal_semester;
	}

	public void setPersonal_semester(int personal_semester) {
		this.personal_semester = personal_semester;
	}

	public int getTotal_credit() {
		return total_credit;
	}

	public void setTotal_credit(int total_credit) {
		this.total_credit = total_credit;
	}

	@Override
	public String toString() {
		return "StudentStateDTO [grade=" + grade + ", personal_semester=" + personal_semester + ", total_credit="
				+ total_credit + "]";
	}

}
