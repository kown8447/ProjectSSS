	/*
	 * @class Name : ReserveDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 예비수강신청 테이블
	*/

package kr.or.initspring.commons;

public class ReserveDTO {

	private String subject_code;	//과목코드
	private String student_code;	//학번
	private int reserve_check;		//신청성공여부
	
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getStudent_code() {
		return student_code;
	}
	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}
	public int getReserve_check() {
		return reserve_check;
	}
	public void setReserve_check(int reserve_check) {
		this.reserve_check = reserve_check;
	}
	@Override
	public String toString() {
		return "ReserveDTO [subject_code=" + subject_code + ", student_code=" + student_code + ", reserve_check="
				+ reserve_check + "]";
	}
	
	
}
