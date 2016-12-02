package kr.or.initspring.dto.lectureMg;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CustomLectureMgDTO {
	
	private String subject_code;	//과목코드
	private String professor_code;	//교수코드
	private String subject_name;	//과목명
	private Integer subject_credit;	//배정학점
	private Integer subject_state;	//과목상태
	private Integer subject_seats;	//정원
	private Integer subject_type;	//과목유형
	private String subject_filesrc;	//강의계획서
	private CommonsMultipartFile subject_filename;  //강의계획서 이름 
	private String department_code;	//학과코드  전공과목일시 생김
	private String required_choice;	//필수/선택
	private String semester_code;	//학기코드
	private String classroom_code;	//강의실코드
	private String period_code;		//교시코드
	private String building_code;	//건물코드
	private Integer seat;			//수용인원
	private Integer classroom_type;	//강의실타입
	private String building_name;	//건물명
	private String building_addr;	//건물주소
	private int record_grade;		//학년
	private int success_check; 		//신청상태
	private int grade_limit; //학년
	private String before_name; //선수과목이름
	private String classroom_name;	//교실이름
	private String member_id;		//멤버아이디
	private String member_name;		//멤버이름
	private String student_code;   //학생코드
	private String member_email; //학생이메일
	private String member_phone;  //학생폰번
	private String record_code;    //성적코드
	private String record_level;  //성적
	private int personal_Semester;
	private String reject_reason;
	
	

	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	public int getPersonal_Semester() {
		return personal_Semester;
	}
	public void setPersonal_Semester(int personal_Semester) {
		this.personal_Semester = personal_Semester;
	}
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getRecord_code() {
		return record_code;
	}
	public void setRecord_code(String record_code) {
		this.record_code = record_code;
	}
	public String getRecord_level() {
		return record_level;
	}
	public void setRecord_level(String record_level) {
		this.record_level = record_level;
	}
	public String getSubject_filesrc() {
		return subject_filesrc;
	}
	public void setSubject_filesrc(String subject_filesrc) {
		this.subject_filesrc = subject_filesrc;
	}
	public CommonsMultipartFile getSubject_filename() {
		return subject_filename;
	}
	public void setSubject_filename(CommonsMultipartFile subject_filename) {
		this.subject_filename = subject_filename;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getClassroom_name() {
		return classroom_name;
	}
	public void setClassroom_name(String classroom_name) {
		this.classroom_name = classroom_name;
	}
	public String getBefore_name() {
		return before_name;
	}
	public void setBefore_name(String before_name) {
		this.before_name = before_name;
	}
	public int getGrade_limit() {
		return grade_limit;
	}
	public void setGrade_limit(int grade_limit) {
		this.grade_limit = grade_limit;
	}
	public int getSuccess_check() {
		return success_check;
	}
	public void setSuccess_check(int success_check) {
		this.success_check = success_check;
	}
	public int getRecord_grade() {
		return record_grade;
	}
	public void setRecord_grade(int record_grade) {
		this.record_grade = record_grade;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
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
	public Integer getSubject_credit() {
		return subject_credit;
	}
	public void setSubject_credit(Integer subject_credit) {
		this.subject_credit = subject_credit;
	}
	public Integer getSubject_state() {
		return subject_state;
	}
	public void setSubject_state(Integer subject_state) {
		this.subject_state = subject_state;
	}
	public Integer getSubject_seats() {
		return subject_seats;
	}
	public void setSubject_seats(Integer subject_seats) {
		this.subject_seats = subject_seats;
	}
	public Integer getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(Integer subject_type) {
		this.subject_type = subject_type;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	
	public String getRequired_choice() {
		return required_choice;
	}
	public void setRequired_choice(String required_choice) {
		this.required_choice = required_choice;
	}
	public String getSemester_code() {
		return semester_code;
	}
	public void setSemester_code(String semester_code) {
		this.semester_code = semester_code;
	}
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
	}
	public String getPeriod_code() {
		return period_code;
	}
	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public Integer getClassroom_type() {
		return classroom_type;
	}
	public void setClassroom_type(Integer classroom_type) {
		this.classroom_type = classroom_type;
	}
	public String getBuilding_code() {
		return building_code;
	}
	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}
	public String getBuilding_name() {
		return building_name;
	}
	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	public String getBuilding_addr() {
		return building_addr;
	}
	public void setBuilding_addr(String building_addr) {
		this.building_addr = building_addr;
	}
	@Override
	public String toString() {
		return "CustomLectureMgDTO [subject_code=" + subject_code + ", professor_code=" + professor_code
				+ ", subject_name=" + subject_name + ", subject_credit=" + subject_credit + ", subject_state="
				+ subject_state + ", subject_seats=" + subject_seats + ", subject_type=" + subject_type
				+ ", subject_filesrc=" + subject_filesrc + ", subject_filename=" + subject_filename
				+ ", department_code=" + department_code + ", required_choice=" + required_choice + ", semester_code="
				+ semester_code + ", classroom_code=" + classroom_code + ", period_code=" + period_code
				+ ", building_code=" + building_code + ", seat=" + seat + ", classroom_type=" + classroom_type
				+ ", building_name=" + building_name + ", building_addr=" + building_addr + ", record_grade="
				+ record_grade + ", success_check=" + success_check + ", grade_limit=" + grade_limit + ", before_name="
				+ before_name + ", classroom_name=" + classroom_name + ", member_id=" + member_id + ", member_name="
				+ member_name + ", student_code=" + student_code + ", member_email=" + member_email + ", member_phone="
				+ member_phone + ", record_code=" + record_code + ", record_level=" + record_level
				+ ", personal_Semester=" + personal_Semester + "]";
	}
	
	
	

	

}
