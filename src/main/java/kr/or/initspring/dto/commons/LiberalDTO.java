package kr.or.initspring.dto.commons;

public class LiberalDTO {
	/*
	   * @Class: LiberalDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 교양   
	   */
	private String subject_code;  //과목코드
	private String required_choice;  //필수 or 선택 
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getRequired_choice() {
		return required_choice;
	}
	public void setRequired_choice(String required_choice) {
		this.required_choice = required_choice;
	}
	@Override
	public String toString() {
		return "Liberal [subject_code=" + subject_code + ", required_choice=" + required_choice + "]";
	}
	
	
}
