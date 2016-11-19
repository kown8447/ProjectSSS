package kr.or.initspring.dto.commons;

public class RegisterDTO {
	
	/*
	* @Class: RegisterDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 등록
	*/
	
	private String student_code;	//학번
	private String semester_code;	//학기코드
	private int register_type;		//등록구분
	private int tuition;			//등록금
	private int register_state;		//등록여부
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getRegister_type() {
		return register_type;
	}
	public void setRegister_type(int register_type) {
		this.register_type = register_type;
	}
	public int getTuition() {
		return tuition;
	}
	public void setTuition(int tuition) {
		this.tuition = tuition;
	}
	public int getRegister_state() {
		return register_state;
	}
	public void setRegister_state(int register_state) {
		this.register_state = register_state;
	}
	@Override
	public String toString() {
		return "RegisterDTO [student_code=" + student_code + ", semester_code=" + semester_code + ", register_type="
				+ register_type + ", tuition=" + tuition + ", register_state=" + register_state + "]";
	}
	
	
}
