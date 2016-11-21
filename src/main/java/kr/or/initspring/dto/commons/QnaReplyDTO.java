package kr.or.initspring.dto.commons;

public class QnaReplyDTO {
	
	/*
	* @Class: QnaReplyDTO
	* @Date: 2016. 11. 19
	* @Author: 성홍모
	* @Desc: 답글
	*/
	
	private int reply_index;	//reply index
	private String member_id;	//맴버ID
	private int reply_refer;	//refer
	private int reply_depth;	//depth
	private int reply_step;		//step
	private int reply_content;	//내용
	public int getReply_index() {
		return reply_index;
	}
	public void setReply_index(int reply_index) {
		this.reply_index = reply_index;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getReply_refer() {
		return reply_refer;
	}
	public void setReply_refer(int reply_refer) {
		this.reply_refer = reply_refer;
	}
	public int getReply_depth() {
		return reply_depth;
	}
	public void setReply_depth(int reply_depth) {
		this.reply_depth = reply_depth;
	}
	public int getReply_step() {
		return reply_step;
	}
	public void setReply_step(int reply_step) {
		this.reply_step = reply_step;
	}
	public int getReply_content() {
		return reply_content;
	}
	public void setReply_content(int reply_content) {
		this.reply_content = reply_content;
	}
	@Override
	public String toString() {
		return "QnaReplyDTO [reply_index=" + reply_index + ", member_id=" + member_id + ", reply_refer=" + reply_refer
				+ ", reply_depth=" + reply_depth + ", reply_step=" + reply_step + ", reply_content=" + reply_content
				+ "]";
	}
	
	
}
