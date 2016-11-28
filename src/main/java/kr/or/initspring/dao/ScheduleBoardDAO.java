package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.commons.Academic_CalendarDTO;

public interface ScheduleBoardDAO {
	public int insertScheduleBoard(Academic_CalendarDTO dto); // 학사일정 insert 
	public List<Academic_CalendarDTO> getScheduleBoardList(); //학사일정 전체리스트 뽑기
	public int deleteScheduleBoard(String code); //학사일정 삭제 
	public int updateScheduleBoard(Academic_CalendarDTO dto); // 학사일정 수정
}
