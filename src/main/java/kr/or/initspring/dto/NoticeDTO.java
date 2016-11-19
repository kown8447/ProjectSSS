/*
* @Class: NoticeDTO
* @Date: 2016. 11. 18
* @Author: 송아름
* @Desc 
* 게시판 공지사항 DTO, DB컬럼명과 동일
* (단, DB 컬럼에 없는 private CommonsMultipartFile file 실제파일 추가 ) 
*/

package kr.or.initspring.dto;

import java.sql.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class NoticeDTO { 
	
	private int notice_Index; 				//글번호
	private String admin_Code; 				//관리자 코드
	private int notice_Depth; 				//들여쓰기
	private int notice_Step;  				//답글 정렬
	private int notice_Refer;  				//참조글(	글의 묶음)
	private String notice_File; 			//파일이름
	private CommonsMultipartFile file; 		//실제파일
	private String notice_Title; 			//글제목	
	private String notice_Content; 			//글내용
	private int notice_Count; 				//조회수
	private Date notice_Date;				//작성날짜
	
	
	public int getNotice_Index() {
		return notice_Index;
	}
	public void setNotice_Index(int notice_Index) {
		this.notice_Index = notice_Index;
	}
	public String getAdmin_Code() {
		return admin_Code;
	}
	public void setAdmin_Code(String admin_Code) {
		this.admin_Code = admin_Code;
	}
	public int getNotice_Depth() {
		return notice_Depth;
	}
	public void setNotice_Depth(int notice_Depth) {
		this.notice_Depth = notice_Depth;
	}
	public int getNotice_Step() {
		return notice_Step;
	}
	public void setNotice_Step(int notice_Step) {
		this.notice_Step = notice_Step;
	}
	public int getNotice_Refer() {
		return notice_Refer;
	}
	public void setNotice_Refer(int notice_Refer) {
		this.notice_Refer = notice_Refer;
	}
	public String getNotice_File() {
		return notice_File;
	}
	public void setNotice_File(String notice_File) {
		this.notice_File = notice_File;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getNotice_Title() {
		return notice_Title;
	}
	public void setNotice_Title(String notice_Title) {
		this.notice_Title = notice_Title;
	}
	public String getNotice_Content() {
		return notice_Content;
	}
	public void setNotice_Content(String notice_Content) {
		this.notice_Content = notice_Content;
	}
	public int getNotice_Count() {
		return notice_Count;
	}
	public void setNotice_Count(int notice_Count) {
		this.notice_Count = notice_Count;
	}
	public Date getNotice_Date() {
		return notice_Date;
	}
	public void setNotice_Date(Date notice_Date) {
		this.notice_Date = notice_Date;
	}
	
}
