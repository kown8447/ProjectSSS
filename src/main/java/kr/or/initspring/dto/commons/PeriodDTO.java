/*
 * @Class : PeriodDTO
 * @Date : 2016.11.19
 * @Author : 권기엽
 * @Desc : 강의 시간을 관리하는 DTO
*/
package kr.or.initspring.dto.commons;

public class PeriodDTO {
	private String period_code;	//교시 코드
	private String period_day;	//요일
	private String period_start;	//시작 시간
	private String period_end;		//종료 시간
	public String getPeriod_code() {
		return period_code;
	}
	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}
	public String getPeriod_day() {
		return period_day;
	}
	public void setPeriod_day(String period_day) {
		this.period_day = period_day;
	}
	public String getPeriod_start() {
		return period_start;
	}
	public void setPeriod_start(String period_start) {
		this.period_start = period_start;
	}
	public String getPeriod_end() {
		return period_end;
	}
	public void setPeriod_end(String period_end) {
		this.period_end = period_end;
	}
	@Override
	public String toString() {
		return "LectureTimeDTO [period_code=" + period_code + ", period_day=" + period_day + ", period_start="
				+ period_start + ", period_end=" + period_end + "]";
	}
}
