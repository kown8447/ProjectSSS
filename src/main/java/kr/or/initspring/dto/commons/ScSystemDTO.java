package kr.or.initspring.dto.commons;


public class ScSystemDTO {
	 /*
	   * @Class: ScSystemDTO
	   * @Date: 2016. 11. 19
	   * @Author: 김영빈
	   * @Desc: 장학제도
	   */
	private String sys_code; //장학코드
	private String scholaship_name;//장학명
	private String scholaship_standard; //선발기준
	private String scholaship_member; //수혜인원
	private String scholaship_amount; //장학금액
	private String scholaship_note; //비고
	private int scholaship_use; //시행
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
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
		return "Sc_SystemDTO [sys_code=" + sys_code + ", scholaship_name=" + scholaship_name + ", scholaship_standard="
				+ scholaship_standard + ", scholaship_member=" + scholaship_member + ", scholaship_amount="
				+ scholaship_amount + ", scholaship_note=" + scholaship_note + ", scholaship_use=" + scholaship_use
				+ "]";
	}
	
	
	
	
}
