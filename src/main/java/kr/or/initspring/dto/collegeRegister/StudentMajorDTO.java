package kr.or.initspring.dto.collegeRegister;

public class StudentMajorDTO {
	private int mj_type;
	private String department_name;
	private String department_code;
	private String college_name;
	private String college_code;


	public int getMj_type() {
		return mj_type;
	}



	public void setMj_type(int mj_type) {
		this.mj_type = mj_type;
	}



	public String getDepartment_name() {
		return department_name;
	}



	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}



	public String getDepartment_code() {
		return department_code;
	}



	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}



	public String getCollege_name() {
		return college_name;
	}



	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}



	public String getCollege_code() {
		return college_code;
	}



	public void setCollege_code(String college_code) {
		this.college_code = college_code;
	}



	@Override
	public String toString() {
		return "MjRecordDTO [mj_type=" + mj_type + ", department_name=" + department_name + ", department_code="
				+ department_code + ", college_name=" + college_name + ", college_code=" + college_code + "]";
	}

}
