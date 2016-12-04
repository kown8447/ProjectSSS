package kr.or.initspring.dto.member;

import java.sql.Date;

public class StudentCodeRegDTO {

	private String code;
	private int code_type;
	private String code_name;
	private String code_birth;
	private String department_code;
	private int mj_type;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCode_type() {
		return code_type;
	}
	public void setCode_type(int code_type) {
		this.code_type = code_type;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_birth() {
		return code_birth;
	}
	public void setCode_birth(String code_birth) {
		this.code_birth = code_birth;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public int getMj_type() {
		return mj_type;
	}
	public void setMj_type(int mj_type) {
		this.mj_type = mj_type;
	}
	@Override
	public String toString() {
		return "studentRegisterDTO [code=" + code + ", code_type=" + code_type + ", code_name=" + code_name
				+ ", code_birth=" + code_birth + ", department_code=" + department_code + ", mj_type=" + mj_type + "]";
	}
	
	
}
