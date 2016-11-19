package kr.or.initspring.dto.commons;

public class RejectionDTO {
	/*
	   * @Class: RejectionDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 거절사유 
	   */
	private String subject_code;  //과목코드
	private String reject_reason;  //사유 
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	@Override
	public String toString() {
		return "RejectionDTO [subject_code=" + subject_code + ", reject_reason=" + reject_reason + "]";
	}
	
}
