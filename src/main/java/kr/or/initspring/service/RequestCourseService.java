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
		DepartmentDTO dto = new DepartmentDTO();
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
}
