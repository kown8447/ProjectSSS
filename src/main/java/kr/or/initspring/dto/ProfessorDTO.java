package kr.or.initspring.dto;

public class ProfessorDTO {
	
	private String professor_Code;
	private String member_Id;
	private Integer pf_State;
	
	public String getProfessor_Code() {
		return professor_Code;
	}
	public void setProfessor_Code(String professor_Code) {
		this.professor_Code = professor_Code;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public Integer getPf_State() {
		return pf_State;
	}
	public void setPf_State(Integer pf_State) {
		this.pf_State = pf_State;
	}
	
	
	
}
