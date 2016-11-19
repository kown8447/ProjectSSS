	/*
	 * @class Name : StStateCodeDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 학생상태 테이블
	*/


package kr.or.initspring.dto.commons;

public class StStateCodeDTO {

	private int state_code;		//학생상태코드
	private String code_name;	//상태이름
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	@Override
	public String toString() {
		return "St_State_CodeDTO [state_code=" + state_code + ", code_name=" + code_name + "]";
	}
	
	
}
