package kr.or.initspring.dto.oprequest;

public class OpRequestPeriodDTO {
	
	private String classroom_code; 	//강의실코드
	private String period_code; 	//교시코드
	private String subject_code;    //과목코드
	private String period_day;		//요일
	private String period_start;	//시작 시간
	private String period_end;		//종료 시간
	public String getClassroom_code() {
		return classroom_code;
	}
	public void setClassroom_code(String classroom_code) {
		this.classroom_code = classroom_code;
	}
	public String getPeriod_code() {
		return period_code;
	}
	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
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
		return "OpRequestPeriodDTO [classroom_code=" + classroom_code + ", period_code=" + period_code
				+ ", subject_code=" + subject_code + ", period_day=" + period_day + ", period_start=" + period_start
				+ ", period_end=" + period_end + "]";
	}
	
	
}
