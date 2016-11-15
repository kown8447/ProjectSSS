package kr.or.initspring.dto;

import java.util.Date;

public class CodeMgDTO {
	private String code;
	private int code_type;
	private String code_name;
	private Date code_birth;
	
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
		return "CodeMgDTO [code=" + code + ", code_type=" + code_type + ", code_name=" + code_name + ", code_birth="
				+ code_birth + "]";
	}
	
}
