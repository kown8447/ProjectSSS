/*
* @Class: QnaReplyDTO
* @Date: 2016. 11. 25
* @Author: 우명제
* @Desc: 댓글 DTO
*/

package kr.or.initspring.dto.qna;

public class CustomerQnaReplyDTO {
		
	private int reply_index;	
	private String member_id;	
	private int reply_refer;	
	private int reply_depth;	
	private int reply_step;		
	private String reply_content;	
	
	private int qna_index;
	
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
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public int getQna_index() {
		return qna_index;
	}
	public void setQna_index(int qna_index) {
		this.qna_index = qna_index;
	}
	
	@Override
	public String toString() {
		return "CustomerQnaReplyDTO [reply_index=" + reply_index + ", member_id=" + member_id + ", reply_refer="
				+ reply_refer + ", reply_depth=" + reply_depth + ", reply_step=" + reply_step + ", reply_content="
				+ reply_content + ", qna_index=" + qna_index + "]";
	}
	
	
}

