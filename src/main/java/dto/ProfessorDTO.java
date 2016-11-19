	/*
	 * @class Name : ProfessorDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @description : 교수 테이블
	*/

package kr.or.initspring.commons;

public class ProfessorDTO {
	
	private String professor_code;   //교수코드 
	private String member_id;		 //멤버 ID
	private int pf_state;			 //상태
	
	public String getProfessor_code() {
		return professor_code;
	}
	public void setProfessor_code(String professor_code) {
		this.professor_code = professor_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPf_state() {
		return pf_state;
	}
	public void setPf_state(int pf_state) {
		this.pf_state = pf_state;
	}
	@Override
	public String toString() {
		return "ProfessorDTO [professor_code=" + professor_code + ", member_id=" + member_id + ", pf_state=" + pf_state
				+ "]";
	}
	
	
}
