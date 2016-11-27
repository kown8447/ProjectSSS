package kr.or.initspring.dto.collegeRegister;

/*
* @Class: StudentRecordDTO
* @Date: 2016. 11. 21
* @Author: 최준호
* @Desc : 성적, 과목
* 
*/
public class StudentRecordDTO {
	private String subject_code; // 과목코드
	private int record_grade; // 수강당시 학년
	private int record_semster; // 수강당시 학기
	private int retake_check; // 재수강 체크
	private String record_level; //성적 등급 (A~F)
	private String subject_name; //과목명
	private int subject_credit; //과목 학점
	private int subject_type; //과목 타입(전공, 교양)
	private String stringtype; //과목 타입, 문자 (전공필수, 전공선택, 교양필수, 교양선택)

	public String getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}

	public int getRecord_grade() {
		return record_grade;
	}

	public void setRecord_grade(int record_grade) {
		this.record_grade = record_grade;
	}

	public int getRecord_semster() {
		return record_semster;
	}

	public void setRecord_semster(int record_semster) {
		this.record_semster = record_semster;
	}

	public int getRetake_check() {
		return retake_check;
	}

	public void setRetake_check(int retake_check) {
		this.retake_check = retake_check;
	}

	public String getRecord_level() {
		return record_level;
	}

	public void setRecord_level(String record_level) {
		this.record_level = record_level;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public int getSubject_credit() {
		return subject_credit;
	}

	public void setSubject_credit(int subject_credit) {
		this.subject_credit = subject_credit;
	}

	public int getSubject_type() {
		return subject_type;
	}

	public void setSubject_type(int subject_type) {
		this.subject_type = subject_type;
	}

	public String getStringtype() {
		return stringtype;
	}

	public void setStringtype(String stringtype) {
		this.stringtype = stringtype;
	}

	@Override
	public String toString() {
		return "RecordDTO [subject_code=" + subject_code + ", record_grade=" + record_grade + ", record_semster="
				+ record_semster + ", retake_check=" + retake_check + ", record_level=" + record_level
				+ ", subject_name=" + subject_name + ", subject_credit=" + subject_credit + ", subject_type="
				+ subject_type + ", stringtype=" + stringtype + "]";
	}

}
