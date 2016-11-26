package kr.or.initspring.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.ClassroomDTO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.OpenedDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.ReserveDTO;
import kr.or.initspring.dto.commons.StStateDTO;
import kr.or.initspring.dto.commons.StudentDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
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
	
	
	public List<OpenedLectureDTO> searchOpLectureOrderbySubjectName(HashMap<String, String> keyword); 	//과목명으로 정렬
	public List<OpenedLectureDTO> searchOpLectureOrderbyProfessorName(HashMap<String, String> keyword);	//교수명으로 정렬
	
	public int setErollStatus(HashMap<String, Integer> map);	//학년_시간에 따른 수강신청 활성 설정
	public Integer getEnrollActive(int enroll_grade, int enroll_type);	//학년_수강신청 타입에 따른 수강 신청 기간 상태 가져오기
	public StStateDTO getStStateByMemberId(String member_id);	//학번에 따른 학년 가져오기
	public List<OpenedLectureDTO> getSubjectListByKeyword(HashMap<String, String> keyword); 	//과목명_과목코드 검색에 의한 개설과목 가져오기
	public OpenedLectureDTO getOpSubjectInfoBySubjectCode(String subject_code);	//교과목 코드로 개설과목 정보 가져오기
	public List<CustomClassRoomDTO> getClassroomCodeBySubjectCode(String subject_code);	//교과목 코드로 중복제거된 강의실 정보 가져오기
	public CustomClassRoomDTO getClassroomInfoByClassroomCode(String classroom_code);	//강의실 코드로 강의실 정보 가져오기
	public List<PeriodDTO> getPeriodByClassroomCode(String classroom_code);	//강의실 코드로 해당 강의실의 강의 시간 가져오기
	public Integer getReserveSeatsBySubjectCode(String subject_code);	//예비 수강 신청 시의 신청 인원 계산
	
	public StudentDTO getStudentByMemberid(String member_id);	//학생의 학생테이블 정보(학번) 가져오기
	public StStateDTO getStstateByStudentCode(String student_code);	//학생의 현재 재학 정보 가져오기
	public SubjectDTO getSubjectBySubjectCode(String subject_code);	//교과목 코드로 과목 정보 가져오기(개설 과목아닌것도 포함)

	public BeforeSubjectDTO getBeforeSubjectBySubjectCode(String subject_code);	//교과목 코드로 해당 과목의 선수강 과목 가져오기
	public Integer checkBeforeSubjectByRecord(String before_code, String student_code);	//교과목 코드, 학번으로 해당 학생이 선수과목을 수강했는지 확인
	
	public Integer deleteReserveByStudentCode(String student_code);	//학번으로 예비수강 신청 테이블 데이터 삭제
	public Integer insertReserve(HashMap<String, String> map);	//학번 + 과목코드 예비수강 신청 테이블 삽입
	public Integer getSubjectCredit(String subject_code);	//과목번호로 배정학점 가져오기
	public void updateTimetableShare(StudentDTO studentDto);	//멤버 아이디로 시간표 공유 여부 수정
	public Integer getTimetableShareByMemberid(String memeber_id);	//멤버 아이디로 시간표 공유 여부 가져오기
	
	public List<OpenedLectureDTO> getPreTimetableByStudentCode(String student_code);	//학번으로 예비수강 신청 테이블 가져오기

	public int getRetakeCheck(String student_code, String subject_code);	//학번+과목코드로 성적 테이블을 검색해서 검색결과가 있을 경우 재수강으로 취급
	public Integer checkStudentCode(String student_code);	//학번이 있는지 체크
	public Integer checkOthersShareByStudentCode(String student_code);	//학번 기준으로 시간표 공유 여부 확인

	public StudentDTO getStudentByStudentCode(String student_code);	//학번으로 학생테이블 정보 가져오기
	
	public List<PeriodDTO> getPeriodList();	//시간을 가져오기 위해서 period 테이블 리스트를 가져옴
	
	///////////예비 수강신청 종료 후 정산/////////////
	public List<ReserveDTO> getReserveList();	//예비 수강신청 테이블에서 모든 List 가져오기
	public Integer countRequestSeat(String subject_code);	//예비 수강신청 테이블의 과목당 수강신청 인원 구하기
	public Integer setReserveCheck(HashMap<String, Object> map);	//산출된 인원을 바탕으로 수강 성공_실패 여부 업데이트
	public void updateRequestSeat(int registed_seat, String subject_code);		//정원이 넘지 않은 과목은 해당 인원을 수강인원으로 저장
	public Integer copyFromReserveToEnrollment();	//종료 후 Reserve Table을 ENROLLMENT Table 로 복사
	
	
	/////////본 수강 신청//////////
	public List<OpenedLectureDTO> getTableFromEnrollment(String student_code);	//Enrollment Table에서 과목 가지고 오기
	public List<OpenedLectureDTO> getFailedListByStudentCode(String student_code);	//예비 수강 신청 실패 과목 로딩
	public OpenedLectureDTO getRegistedSeat(String subject_code);	//현재까지의 과목 정원과 수강 신청 인원을 확인
	
	public Integer insertEnrollment(HashMap<String, String> map);	//Enrollment table에 데이터 삽입
	
	public Integer updateRegistedSeat(String subject_code);	//enrollment Table에 삽입 성공한 과목에 대해서 수강신청인원 +1
	public Integer checkAlreadyExistSubject(String subject_code, String student_code);	//예비 수강신청에서 실패했던 과목에 대해서 수강신청 할 경우를 확인
	public void deleteFromEnrollment(String student_code, String subject_code);	//선택 과목 삭제
	public void minusRegistedSeatBySubjectCode(String subject_code);	//정원 감소
}
