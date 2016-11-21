package kr.or.initspring.service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.LectureMgDAO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.PlanDTO;
import kr.or.initspring.dto.lectureMg.CustomLectureMgDTO;

@Service
public class lectureService {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : Request_List
	 * @Author : 조장현
	 * @description : 등록한 과목 리스트 출력 
	*/	
	public List<CustomLectureMgDTO> Request_List(){
		
		List<CustomLectureMgDTO> list = new ArrayList<CustomLectureMgDTO>();
		//SubjectDTO dto = new SubjectDTO();
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		list = lecturedao.request_List();
	
		return list;
	}
	
	/*
	 * @method Name : Insert_Subject
	 * @Author : 조장현
	 * @description : 과목 등록
	*/	
	
	public int insert_Subject(SubjectDTO dto ,Principal principal){
	
		int result = 0;
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("프린시펄 서비스에서 : "+principal.getName());
		lecturedao.insert_Subject(principal, dto);
		System.out.println("1번탐"+result);
		lecturedao.insert_Professor();
		System.out.println("2번탐");
		lecturedao.insert_Filesrc();
		System.out.println("3번탐");
		
		
		
		return result;
	}
	
}
