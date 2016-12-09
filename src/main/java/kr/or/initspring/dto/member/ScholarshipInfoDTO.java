package kr.or.initspring.dto.member;

import java.sql.Date;

/*
 * @Class : ScholarshipInfoDTO
 * @Date : 2016.11.22
 * @Author : 성홍모
 * @Desc : 장학생에 따른 장학 제도, 학기, 코드 정보를 얻기 위한 DTO
*/

public class ScholarshipInfoDTO {
	
	private String scholarship_code;			//장학수혜코드
	private String student_code;				//student_Code
	private String sys_code;					//장학코드
	private String semester_code;				//학기코드
	private int scholarship_rcordavg;			//평점
	private Date scholarship_payday;			//지급일
	private String semester_name;				//학기이름
	private Date semester_start; 				//학기시작일
	private Date semester_end; 					//학기 종료일 
	private String code;						//코드
	private int code_type;						//구분
	private String code_name;					//이름
	private Date code_birth;					//생년월일
	private String scholaship_name;				//장학명
	private String scholaship_standard; 		//선발기준
	private String scholaship_member; 			//수혜인원
	private String scholaship_amount; 			//장학금액
	private String scholaship_note; 			//비고
	private int scholaship_use; 				//시행
	public String getScholarship_code() {
		return scholarship_code;
	}
	public void setScholarship_code(String scholarship_code) {
		this.scholarship_code = scholarship_code;
	}
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public int getScholarship_rcordavg() {
		return scholarship_rcordavg;
	}
	public void setScholarship_rcordavg(int scholarship_rcordavg) {
		this.scholarship_rcordavg = scholarship_rcordavg;
	}
	public Date getScholarship_payday() {
		return scholarship_payday;
	}
	public void setScholarship_payday(Date scholarship_payday) {
		this.scholarship_payday = scholarship_payday;
	}
	public String getSemester_name() {
		return semester_name;
	}
	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
	}
	public Date getSemester_start() {
		return semester_start;
	}
	public void setSemester_start(Date semester_start) {
		this.semester_start = semester_start;
	}
	public Date getSemester_end() {
		return semester_end;
	}
	public void setSemester_end(Date semester_end) {
		this.semester_end = semester_end;
	}
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
	public String getScholaship_name() {
		return scholaship_name;
	}
	public void setScholaship_name(String scholaship_name) {
		this.scholaship_name = scholaship_name;
	}
	public String getScholaship_standard() {
		return scholaship_standard;
	}
	public void setScholaship_standard(String scholaship_standard) {
		this.scholaship_standard = scholaship_standard;
	}
	public String getScholaship_member() {
		return scholaship_member;
	}
	public void setScholaship_member(String scholaship_member) {
		this.scholaship_member = scholaship_member;
	}
	public String getScholaship_amount() {
		return scholaship_amount;
	}
	public void setScholaship_amount(String scholaship_amount) {
		this.scholaship_amount = scholaship_amount;
	}
	public String getScholaship_note() {
		return scholaship_note;
	}
	public void setScholaship_note(String scholaship_note) {
		this.scholaship_note = scholaship_note;
	}
	public int getScholaship_use() {
		return scholaship_use;
	}
	public void setScholaship_use(int scholaship_use) {
		this.scholaship_use = scholaship_use;
	}
	@Override
	public String toString() {
		return "ScholarshipInfoDTO [scholarship_code=" + scholarship_code + ", student_code=" + student_code
				+ ", sys_code=" + sys_code + ", semester_code=" + semester_code + ", scholarship_rcordavg="
				+ scholarship_rcordavg + ", scholarship_payday=" + scholarship_payday + ", semester_name="
				+ semester_name + ", semester_start=" + semester_start + ", semester_end=" + semester_end + ", code="
				+ code + ", code_type=" + code_type + ", code_name=" + code_name + ", code_birth=" + code_birth
				+ ", scholaship_name=" + scholaship_name + ", scholaship_standard=" + scholaship_standard
				+ ", scholaship_member=" + scholaship_member + ", scholaship_amount=" + scholaship_amount
				+ ", scholaship_note=" + scholaship_note + ", scholaship_use=" + scholaship_use + "]";
	}
	
}

