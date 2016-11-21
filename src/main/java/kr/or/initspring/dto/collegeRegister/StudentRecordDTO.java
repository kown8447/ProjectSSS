package kr.or.initspring.dto.collegeRegister;

public class StudentRecordDTO {
	private String subject_code;
	private int record_grade;
	private int record_semster;
	private int retake_check;
	private String record_level;
	private String subject_name;
	private int subject_credit;
	private int subject_type;
	private String stringtype;

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
