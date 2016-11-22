package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentMajorDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 학생 전공구분, 학과,대학
* 
*/
public class StudentMajorDTO {
	private int mj_type; // 전공유형(전공, 복수전공)
	private String department_name;// 학과명
	private String department_code;// 학과코드
	private String college_name;// 대학명
	private String college_code;// 대학코드

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
