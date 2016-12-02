package kr.or.initspring.dto.oprequest;

public class OpRequsetCheckDTO {
	
	private String subject_code; 
	private int success_check;
	private String professor_code;
	private String subject_name;
	private int subject_type;
	private int grade_limit;
	private String code_name;
	private String required_choice;
	
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public int getSuccess_check() {
		return success_check;
	}
	public void setSuccess_check(int success_check) {
		this.success_check = success_check;
	}
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public int getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(int subject_type) {
		this.subject_type = subject_type;
	}
	public int getGrade_limit() {
		return grade_limit;
	}
	public void setGrade_limit(int grade_limit) {
		this.grade_limit = grade_limit;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getRequired_choice() {
		return required_choice;
	}
	public void setRequired_choice(String required_choice) {
		this.required_choice = required_choice;
	}
	@Override
	public String toString() {
		return "OpRequsetCheckDTO [subject_code=" + subject_code + ", success_check=" + success_check
				+ ", professor_code=" + professor_code + ", subject_name=" + subject_name + ", subject_type="
				+ subject_type + ", grade_limit=" + grade_limit + ", code_name=" + code_name + ", required_choice="
				+ required_choice + "]";
	}
	
	
	
	
}
