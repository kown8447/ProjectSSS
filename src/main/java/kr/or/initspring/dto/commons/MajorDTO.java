package kr.or.initspring.dto.commons;

public class MajorDTO {
	/*
	 * @Class: MajorDTO
	 * 
	 * @Date: 2016. 11. 19
	 * 
	 * @Author: 김영빈
	 * 
	 * @Desc: 전공과목
	 */
	private String subject_code; // 과목코드
	private String department_code; // 학과코드
	private int required_choice; // 필수 or 선택

	public String getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public int getRequired_choice() {
		return required_choice;
	}

	public void setRequired_choice(int required_choice) {
		this.required_choice = required_choice;
	}

	@Override
	public String toString() {
		return "MajorDTO [subject_code=" + subject_code + ", department_code=" + department_code + ", required_choice="
				+ required_choice + "]";
	}

}
