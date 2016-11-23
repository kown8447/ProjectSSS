/*
 * @Class : RequestCourseService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 수강 신청 관련 제어 Service
*/
package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.RequestCourseDAO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.StStateDTO;
import kr.or.initspring.dto.commons.StudentDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.requestCourse.CustomClassRoomDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;

@Service
public class RequestCourseService {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : viewOpLecture
	 * @Author : 권기엽
	 * @description : 개설과목 리스트를 가져오는 함수
	*/	
	public List<OpenedLectureDTO> viewOpLecture(){
		List<OpenedLectureDTO> lists = new ArrayList<OpenedLectureDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		lists = requestCourseDao.getOpenedLectureList();
		for(OpenedLectureDTO dto : lists){
			dto.setPeriod(requestCourseDao.getPeriodBySubjectCode(dto.getSubject_code()));
			dto.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(dto.getProfessor_code()));
			dto.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(dto.getSubject_code()));
			dto.setRequired_choice(requestCourseDao.getRequiredChoice(dto.getSubject_code(), dto.getSubject_type()));
		}
		return lists;
	}
	
	/*
	 * @method Name : viewCollegeList
	 * @Author : 권기엽
	 * @description : 단과 대학 전체 목록을 가져옴
	*/	
	public List<CollegeDTO> viewCollegeList(){
		List<CollegeDTO> college = new ArrayList<CollegeDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		college = requestCourseDao.getCollegeList();
		return college;
	}
	
	/*
	 * @method Name : viewDepartmentList
	 * @Author : 권기엽
	 * @description : 단과 대학 코드에 따른 학과/학부 코드를 가져오는 함수.
	*/	
	public List<DepartmentDTO> viewDepartmentList(String college_code){
		List<DepartmentDTO> department = new ArrayList<DepartmentDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		department = requestCourseDao.getDepartmentList(college_code);
		return department;
	}
	
	/*
	 * @method Name : searchSubject
	 * @Author : 권기엽
	 * @description : 구분(과목명 / 과목코드)과 키워드에 따른 검색 결과 출력
	*/	
	public List<OpenedLectureDTO> searchSubject(HashMap<String, String> keyword){
		System.out.println("keyword : " + keyword.get("department_code"));
		List<OpenedLectureDTO> lists = new ArrayList<OpenedLectureDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		lists = requestCourseDao.getOpenedLectureListByKeyword(keyword);
		for(OpenedLectureDTO dto : lists){
			dto.setPeriod(requestCourseDao.getPeriodBySubjectCode(dto.getSubject_code()));
			dto.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(dto.getProfessor_code()));
			dto.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(dto.getSubject_code()));
			dto.setRequired_choice(requestCourseDao.getRequiredChoice(dto.getSubject_code(), dto.getSubject_type()));
		}
		System.out.println(lists.toString());
		return lists;
	}
	
	/*
	 * @method Name : possiblePreRegister
	 * @Author : 권기엽
	 * @description : 학년, 관리자 수강신청 시간에 따른 viewpage 설정
	*/	
	public String possiblePreRegister(String member_id){
		String viewpage="";
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		StStateDTO stStateDto;
		int enroll_active;
		try{
			stStateDto = requestCourseDao.getStStateByMemberId(member_id);
			enroll_active = requestCourseDao.getEnrollActiveByGrade(stStateDto.getGrade());
			if(enroll_active==0) { viewpage = "requestCourse.notRequestPeriod"; }
			else if(enroll_active==1) { viewpage = "requestCourse.preRegisterCourse";}
			else if(enroll_active==2) { viewpage = "requestCourse.before24Hours";}
		}catch(Exception e){
			System.out.println("RequestCourseService / possiblePreRegister : "+ e.getMessage());
			viewpage = "redirect:../index.htm";
		}finally{
			return viewpage;
		}
	}
	
	public List<OpenedLectureDTO> searchByKeyword(HashMap<String, String> map){
		List<OpenedLectureDTO> lists = new ArrayList<OpenedLectureDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		lists = requestCourseDao.getSubjectListByKeyword(map);
		for(OpenedLectureDTO dto : lists){
			dto.setPeriod(requestCourseDao.getPeriodBySubjectCode(dto.getSubject_code()));
			dto.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(dto.getProfessor_code()));
			dto.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(dto.getSubject_code()));
			dto.setRequired_choice(requestCourseDao.getRequiredChoice(dto.getSubject_code(), dto.getSubject_type()));
		}
		System.out.println(lists.toString());
		return lists;
	}
	
	/*
	 * @method Name : getOpSubjectInfoBySubjectCode
	 * @Author : 권기엽
	 * @description : 한과목의 상세 정보를 가져오는 함수
	*/	
	public OpenedLectureDTO getOpSubjectInfoBySubjectCode(String subject_code){
		OpenedLectureDTO subject_info = new OpenedLectureDTO();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		subject_info = requestCourseDao.getOpSubjectInfoBySubjectCode(subject_code);
		subject_info.setPeriod(requestCourseDao.getPeriodBySubjectCode(subject_info.getSubject_code()));
		subject_info.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(subject_info.getProfessor_code()));
		subject_info.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(subject_info.getSubject_code()));
		subject_info.setRequired_choice(requestCourseDao.getRequiredChoice(subject_info.getSubject_code(), subject_info.getSubject_type()));

		//강의실 정보 가져오기
		List<CustomClassRoomDTO> classroomInfo = requestCourseDao.getClassroomCodeBySubjectCode(subject_code);
		for(int i = 0; i < classroomInfo.size(); i++){
			CustomClassRoomDTO dto = new CustomClassRoomDTO();
			dto = requestCourseDao.getClassroomInfoByClassroomCode(classroomInfo.get(i).getClassroom_code());
			dto.setPeriodlist(requestCourseDao.getPeriodByClassroomCode(classroomInfo.get(i).getClassroom_code()));
			classroomInfo.get(i).setBuilding_code(dto.getBuilding_code());
			classroomInfo.get(i).setClassroom_code(dto.getClassroom_code());
			classroomInfo.get(i).setClassroom_name(dto.getClassroom_name());
			classroomInfo.get(i).setClassroom_type(dto.getClassroom_type());
			classroomInfo.get(i).setPeriodlist(dto.getPeriodlist());
			classroomInfo.get(i).setSeat(dto.getSeat());
		}
		subject_info.setCustomClassroomDTO(classroomInfo);
		
		System.out.println(subject_info.toString());
		return subject_info;
	}
	
	/*
	 * @method Name : checkGrade
	 * @Author : 권기엽
	 * @description : 사용자의 학년 정보를 가져옴. 과목의 제한 학년과 비교
	*/	
	public boolean checkGrade(String member_id, String subject_code){
		boolean result = false;
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		StudentDTO studentDto = requestCourseDao.getStudentByMemberid(member_id);
		StStateDTO ststateDto = requestCourseDao.getStstateByStudentCode(studentDto.getStudent_code());
		SubjectDTO subjectDto = requestCourseDao.getSubjectBySubjectCode(subject_code);
		
		if(ststateDto.getGrade() < subjectDto.getGrade_limit()){
			result = true;
		}
		
		return result;
	}
	
	/*
	 * @method Name : checkBeforeSubject
	 * @Author : 권기엽
	 * @description : 선수과목 존재 여부와 있을 경우, 사용자가 선수과목을 이수했는지 확인
	*/	
	public int checkBeforeSubject(String member_id, String subject_code){
		int result = 0;
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		BeforeSubjectDTO beforeSubjectDto = null;
		StudentDTO studentDto = null;
		int count = 0;
		try{
			beforeSubjectDto = requestCourseDao.getBeforeSubjectBySubjectCode(subject_code);
			studentDto = requestCourseDao.getStudentByMemberid(member_id);
			count = requestCourseDao.checkBeforeSubjectByRecord(beforeSubjectDto.getBefore_code(), studentDto.getStudent_code());
		}catch(NullPointerException e){
			System.out.println("RequestCourseService / checkBeforeSubject : " + e.getMessage());
			if(beforeSubjectDto == null){
				result = 0;	//선수강 과목이 없음
				return result;
			}
		}
		if(count > 0){
			result = 1;	//선수강 들은 학생
		}else{
			result = 2;	//선수강이 필요한 학생
		}
		
		return result;
	}
	
	/*
	 * @method Name : requestReserve
	 * @Author : 권기엽
	 * @description : 사용자가 요청한 시간표와 시간표 공유 여부를 받아서 저장하는 함수
	*/	
	@Transactional(rollbackFor={Exception.class, SQLException.class,NullPointerException.class, RuntimeException.class})
	public boolean requestReserve(String member_id, ArrayList<String> list, int timetable_share) throws Exception{
		
		boolean result = false;
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		StudentDTO studentDto = requestCourseDao.getStudentByMemberid(member_id);
		studentDto.setTimetable_share(timetable_share);
		
		int count = 0;
		try{
			requestCourseDao.updateTimetableShare(studentDto);
			requestCourseDao.deleteReserveByStudentCode(studentDto.getStudent_code());
			for(String subject_code : list){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("student_code", studentDto.getStudent_code());
				map.put("subject_code", subject_code);
				requestCourseDao.insertReserve(map);
				count++;
			}
		}catch(Exception e){
			throw e;
		}
		if(count == list.size()){
			result = true;
		}
		return result;
	}
	
	/*
	 * @method Name : getTimetableShare
	 * @Author : 권기엽
	 * @description : 사용자의 시간표 공유 여부 확인
	*/	
	public int getTimetableShare(String member_id){
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		int timetable_share = requestCourseDao.getTimetableShareByMemberid(member_id);
		return timetable_share;
	}
	
	/*
	 * @method Name : getSubjectCredit
	 * @Author : 권기엽
	 * @description : 과목의 배정 학점 확인
	*/	
	public int getSubjectCredit(String subject_code){
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		int subject_credit = requestCourseDao.getSubjectCredit(subject_code);
		return subject_credit;
	}
	
	/*
	 * @method Name : getPreTimetable
	 * @Author : 권기엽
	 * @description : 페이지 로딩 시 비동기화로 사용자의 예비수강 신청 시간표를 보여주는 함수
	*/	
	public List<OpenedLectureDTO> getPreTimetable(String member_id){
		List<OpenedLectureDTO> lists=null;
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		StudentDTO studentDto = requestCourseDao.getStudentByMemberid(member_id);
		lists = requestCourseDao.getPreTimetableByStudentCode(studentDto.getStudent_code());
		for(OpenedLectureDTO dto : lists){
			dto.setPeriod(requestCourseDao.getPeriodBySubjectCode(dto.getSubject_code()));
			dto.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(dto.getProfessor_code()));
			dto.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(dto.getSubject_code()));
			dto.setRequired_choice(requestCourseDao.getRequiredChoice(dto.getSubject_code(), dto.getSubject_type()));
		}
		return lists;
	}
	
}
