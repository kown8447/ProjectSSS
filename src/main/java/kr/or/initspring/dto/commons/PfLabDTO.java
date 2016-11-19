package kr.or.initspring.dto.commons;

public class PfLabDTO {

	/*
	* @Class: PfLabDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 교수 연구실
	*/
	
	private String professor_Code;		//교수코드
	private String lab_Code;			//교수연구실코드
	public String getProfessor_Code() {
		return professor_Code;
	}
	public void setProfessor_Code(String professor_Code) {
		this.professor_Code = professor_Code;
	}
	public String getLab_Code() {
		return lab_Code;
	}
	public void setLab_Code(String lab_Code) {
		this.lab_Code = lab_Code;
	}
	@Override
	public String toString() {
		return "PfLabDTO [professor_Code=" + professor_Code + ", lab_Code=" + lab_Code + "]";
	}
	
	
}
