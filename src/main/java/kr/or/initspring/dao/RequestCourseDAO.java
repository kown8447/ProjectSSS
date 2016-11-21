package kr.or.initspring.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.initspring.dto.commons.ClassroomDTO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.StStateDTO;
import kr.or.initspring.dto.requestCourse.CustomClassRoomDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;

public interface RequestCourseDAO {
	public List<OpenedLectureDTO> getOpenedLectureList();	//개설된 전체 과목의 일반정보
	public List<PeriodDTO> getPeriodBySubjectCode(String subject_code);	//과목코드를 통한 해당 과목의 시간정보
	public List<ClassroomDTO> getClassroomBySubjectCode(String subject_code);	//과목코드를 통한 해당 과목의 강의실 정보
	public String getProfessorNameByPfCode(String professor_code);	//교수 이름 확인
	public String getLecturePlanBySubjectCode(String subject_code);	//강의 계획서 파일명
	public Integer getRequiredChoice(String subject_code, int subject_type); //매개변수의 subject_type에 따라 필수.선택 뽑는 동적 쿼리
	public List<CollegeDTO> getCollegeList();	//단과 대학 전체 목록 가져오기
	public List<DepartmentDTO> getDepartmentList(String college_code);	//학부.학과 전체 목록 가져오기
	public List<OpenedLectureDTO> getOpenedLectureListByKeyword(HashMap<String, String> keyword); 	//학과 검색에 의한 개설과목 가져오기
	public int setErollStatus(HashMap<String, Integer> map);	//학년_시간에 따른 수강신청 활성 설정
	public Integer getEnrollActiveByGrade(int enroll_grade);	//학년에 따른 수강 신청 기간 상태 가져오기
	public StStateDTO getStStateByMemberId(String member_id);	//학번에 따른 학년 가져오기
	public List<OpenedLectureDTO> getSubjectListByKeyword(HashMap<String, String> keyword); 	//과목명_과목코드 검색에 의한 개설과목 가져오기
	public OpenedLectureDTO getOpSubjectInfoBySubjectCode(String subject_code);	//교과목 코드로 개설과목 정보 가져오기
	public List<CustomClassRoomDTO> getClassroomCodeBySubjectCode(String subject_code);	//교과목 코드로 중복제거된 강의실 정보 가져오기
	public CustomClassRoomDTO getClassroomInfoByClassroomCode(String classroom_code);	//강의실 코드로 강의실 정보 가져오기
	public List<PeriodDTO> getPeriodByClassroomCode(String classroom_code);	//강의실 코드로 해당 강의실의 강의 시간 가져오기
	
}
