package kr.or.initspring.dto.oprequest;

public class OpRequestDetailDTO {
	
	private String subject_code;	// 과목코드
	private int grade_limit;		// 수강대상
	private int subject_credit; 	// 배정학점
	private int subject_type; 		// 과목유형
	private int subject_seats; 		// 과목 정원
	private String subject_name;	// 과목명
	private String subject_Filesrc;	// 첨부파일명
	private int subject_state; // 과목 상태
	private int success_check;	//관리자 개설 승인_거부 상태
	
	
	public int getSuccess_check() {
		return success_check;
	}
	public void setSuccess_check(int success_check) {
		this.success_check = success_check;
	}
	public int getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(int subject_state) {
		this.subject_state = subject_state;
	}
	public String getSubject_Filesrc() {
		return subject_Filesrc;
	}
	public void setSubject_Filesrc(String subject_Filesrc) {
		this.subject_Filesrc = subject_Filesrc;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public int getGrade_limit() {
		return grade_limit;
	}
	public void setGrade_limit(int grade_limit) {
		this.grade_limit = grade_limit;
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
	public int getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(int subject_seats) {
		this.subject_seats = subject_seats;
	}
	@Override
	public String toString() {
		return "OpRequestDetailDTO [subject_code=" + subject_code + ", grade_limit=" + grade_limit + ", subject_credit="
				+ subject_credit + ", subject_type=" + subject_type + ", subject_seats=" + subject_seats
				+ ", subject_name=" + subject_name + ", subject_Filesrc=" + subject_Filesrc + ", subject_state="
				+ subject_state + ", success_check=" + success_check + "]";
	}
	
}
