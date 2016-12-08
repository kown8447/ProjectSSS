package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentScholarshipDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 장학, 장학제도, 학기
* 
*/
public class StudentScholarshipDTO {
	private int scholashipIndex; // 장학번호
	private String semester_code; // 학기코드
	private String scholaship_name; // 장학제도명
	private String scholaship_amount; // 장학 금액
	private String scholaship_standard; // 선발기준
	private String semesterYear; // 학기 년도
	private String semesterType; // 학기 구분

	public int getScholashipIndex() {
		return scholashipIndex;
	}

	public void setScholashipIndex(int scholashipIndex) {
		this.scholashipIndex = scholashipIndex;
	}

	public String getSemester_code() {
		return semester_code;
	}

	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}

	public String getScholaship_name() {
		return scholaship_name;
	}

	public void setScholaship_name(String scholaship_name) {
		this.scholaship_name = scholaship_name;
	}

	public String getScholaship_amount() {
		return scholaship_amount;
	}

	public void setScholaship_amount(String scholaship_amount) {
		this.scholaship_amount = scholaship_amount;
	}

	public String getScholaship_standard() {
		return scholaship_standard;
	}

	public void setScholaship_standard(String scholaship_standard) {
		this.scholaship_standard = scholaship_standard;
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
		return "StudentScholarshipDTO [scholashipIndex=" + scholashipIndex + ", semester_code=" + semester_code
				+ ", scholaship_name=" + scholaship_name + ", scholaship_amount=" + scholaship_amount
				+ ", scholaship_standard=" + scholaship_standard + ", semesterYear=" + semesterYear + ", semesterType="
				+ semesterType + "]";
	}

}
