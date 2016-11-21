package kr.or.initspring.dto.collegeRegister;

public class StudentScholarshipDTO {
	private int scholashipIndex;
	private String semester_code;
	private String scholaship_name;
	private int scholaship_amount;
	private String scholaship_standard;
	private String semesterYear;
	private String semesterType;
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
	public int getScholaship_amount() {
		return scholaship_amount;
	}
	public void setScholaship_amount(int scholaship_amount) {
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
