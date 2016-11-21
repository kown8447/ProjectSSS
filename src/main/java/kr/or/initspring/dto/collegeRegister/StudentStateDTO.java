package kr.or.initspring.dto.collegeRegister;

public class StudentStateDTO {
	private int grade;
	private int personal_semester;
	private int total_credit;

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
