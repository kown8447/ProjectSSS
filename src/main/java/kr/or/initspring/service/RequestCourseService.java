/*
 * @Class : RequestCourseService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 수강 신청 관련 제어 Service
*/
package kr.or.initspring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.initspring.dao.RequestCourseDAO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.StStateDTO;
import kr.or.initspring.dto.requestCourse.CustomClassRoomDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;

@Service
public class RequestCourseService {

	@Autowired
	private SqlSession sqlsession;
	
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
	
	public List<CollegeDTO> viewCollegeList(){
		List<CollegeDTO> college = new ArrayList<CollegeDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		college = requestCourseDao.getCollegeList();
		return college;
	}
	
	public List<DepartmentDTO> viewDepartmentList(String college_code){
		List<DepartmentDTO> department = new ArrayList<DepartmentDTO>();
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		department = requestCourseDao.getDepartmentList(college_code);
		return department;
	}
	
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
}
