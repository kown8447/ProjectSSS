package kr.or.initspring.dto;

public class MajorDTO {
	private int mj_type;
	private String department_name;
	private String college_name;

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

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	@Override
	public String toString() {
		return "MajorDTO [mj_type=" + mj_type + ", department_name=" + department_name + ", college_name="
				+ college_name + "]";
	}

}
