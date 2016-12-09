package kr.or.initspring.dto.member;

/*
 * @Class : ProfessorCodeRegDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 교수코드 등록과 교수 전공등록을 위한 DTO
*/

public class ProfessorCodeRegDTO {
	
	private String code;					//코드
	private int code_type;					//구분
	private String code_name;				//이름
	private String code_birth;				//생년월일
	private String department_code;			//학과 코드
	
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
	
	@Override
	public String toString() {
		return "ProfessorCodeRegDTO [code=" + code + ", code_type=" + code_type + ", code_name=" + code_name
				+ ", code_birth=" + code_birth + ", department_code=" + department_code + "]";
	}
}
