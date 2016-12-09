package kr.or.initspring.dto.member;

import java.sql.Date;

/*
 * @Class : StudentCodeRegDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 학생코드 등록에 따른 전공 구분 DTO
*/

public class StudentCodeRegDTO {

	private String code;				//코드
	private int code_type;				//구분
	private String code_name;			//이름
	private String code_birth;			//생년월일
	private String department_code;		//학과 코드
	private int mj_type;				//전공 구분
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
