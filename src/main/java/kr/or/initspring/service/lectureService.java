package kr.or.initspring.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.initspring.dao.LectureMgDAO;
import kr.or.initspring.dto.SubjectDTO;

@Service
public class lectureService {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : Request_List
	 * @Author : 조장현
	 * @description : 등록한 과목 리스트 출력 
	*/	
	
	public List<SubjectDTO> Request_List(){
		
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		System.out.println("리스트서비스");
		SubjectDTO dto = new SubjectDTO();
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("매퍼타냐?");
		
		list = lecturedao.Request_List();
		System.out.println(dto.getSubject_Name()+"/"+dto.getSubject_state());
		
		return list;
	}
}
