	/*
	 * @class Name : Code_MgDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 코드관리 테이블
	*/


package kr.or.initspring.commons;

import java.sql.Date;

public class Code_MgDTO {
	private String code;		//코드
	private int code_type;		//구분
	private String code_name;	//이름
	private Date code_birth;	//생년월일
	
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
	public Date getCode_birth() {
		return code_birth;
	}
	public void setCode_birth(Date code_birth) {
		this.code_birth = code_birth;
	}
	@Override
	public String toString() {
		return "Code_MgDTO [code=" + code + ", code_type=" + code_type + ", code_name=" + code_name + ", code_birth="
				+ code_birth + "]";
	}
	
	
}
