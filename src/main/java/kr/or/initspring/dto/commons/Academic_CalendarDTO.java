	/*
	 * @class Name : Academic_CalendarDTO
	 * @Date : 2016.11.19
	 * @Author : 조장현
	 * @Desc : 학사일정 테이블
	*/

package kr.or.initspring.dto.commons;

import java.sql.Date;

public class Academic_CalendarDTO {
	
	private String calendar_code;		//일정번호
	private String calendar_title;		//제목
	private String calendar_content;	//내용
	private Date calendar_start;		//시작일
	private Date calendar_end;			//종료일

	public String getCalendar_code() {
		return calendar_code;
	}
	public void setCalendar_code(String calendar_code) {
		this.calendar_code = calendar_code;
	}
	public String getCalendar_title() {
		return calendar_title;
	}
	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public Date getCalendar_start() {
		return calendar_start;
	}
	public void setCalendar_start(Date calendar_start) {
		this.calendar_start = calendar_start;
	}
	public Date getCalendar_end() {
		return calendar_end;
	}
	public void setCalendar_end(Date calendar_end) {
		this.calendar_end = calendar_end;
	}
	@Override
	public String toString() {
		return "Academic_CalendarDTO [calendar_code=" + calendar_code + ", calendar_title=" + calendar_title
				+ ", calendar_content=" + calendar_content + ", calendar_start=" + calendar_start + ", calendar_end="
				+ calendar_end + "]";
	}
}
