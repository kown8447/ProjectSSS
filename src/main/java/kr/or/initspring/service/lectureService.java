package kr.or.initspring.service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.set.SynchronizedSet;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dao.LectureMgDAO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PfMajorDTO;
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
		
		list = lecturedao.request_liberal();
		
	
		return list;
	}
	
public List<CustomLectureMgDTO> Request_List2(){
		
		List<CustomLectureMgDTO> list = new ArrayList<CustomLectureMgDTO>();
		//SubjectDTO dto = new SubjectDTO();
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		list = lecturedao.request_major();
		
	
		return list;
	}
	
	/*
	 * @method Name : Insert_Subject
	 * @Author : 조장현
	 * @description : 과목 등록
	*/	
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insert_Subject(SubjectDTO dto,String before_code,Principal principal,String required_choice,
							  BeforeSubjectDTO beforedto,MajorDTO majordto,LiberalDTO liberdto,
							  String department_code){
	  
		int result = 0;
		int before = 0;
		String principalid = principal.getName();
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("디팔트먼트꼬드 : "+department_code);
		System.out.println("프린씨펄아이디는 : "+principalid);
		try{
		String insertparam = lecturedao.select_Professor(principalid);
		dto.setProfessor_code(insertparam);
		lecturedao.insert_Subject(dto);
		
		String subject_code = dto.getSubject_code();
		int subject_type = dto.getSubject_type();
		System.out.println("@@@@@@@서브젝트타입입니당@@@@@@"+subject_type);
		System.out.println("이거슨 서비스의 서브젝트코드 : "+subject_code);
		System.out.println("이것은 서비스에서의 비포어코드"+before_code);
	
		if(!before_code.equals("0")){
		before = lecturedao.insert_BeforeCode(subject_code,before_code);
		System.out.println("선수과목이 있스문다");
		}else{
			System.out.println("선수과목 없슴니당");
		}
		
		System.out.println("비포탓냐"+before);
		
		System.out.println("그럼이제 리콰이어드초이스를 해볼까"+required_choice);
	    int required_select = 5;  //신경 ㄴㄴ염 숫자 5를좋아함 바이날둠이 5번임
	    
	    department_code = lecturedao.select_departmentcode(principalid).getDepartment_code();
	    System.out.println("다시 디팔트먼트코드"+department_code);
	    System.out.println("이것은 리콰이어드초이스입니다 제발찍혀라"+required_choice);
	    

		System.out.println("이게실제로 들어갈 친구들임 subject_code,required_code,department_code 순임 /////"+subject_code+"/"+required_choice+"/"+department_code);
		
	    if(subject_type == 0){
	    	System.out.println("전공임 전공쿼리를탄다 메이저메이저 예아");
	    	System.out.println("메이저 서브젝트코드 : "+subject_code+"리콰이어초쓰 :"+required_choice+"디파먼코:"+department_code);
	    	required_select = lecturedao.insert_major(subject_code,required_choice,department_code);
	    }else if(subject_type == 1){
	    	System.out.println("교양쿼리를탄다 교양있는 장현");
	    	System.out.println("리버럴에 들어갈 섭젝코드: "+subject_code+"리콰이어코:"+required_choice);
	    	required_select = lecturedao.insert_Liberal(subject_code,required_choice);
	    }
		
		
		}catch(Exception e){
			System.out.println("장현 트랜잭션 오류 : "+e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return result;
	}

	
	public void selectBefore(String memberid, Model model) {
		System.out.println("선수과목서비스들어옴");
		System.out.println(memberid);
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("매퍼생성");
		PfMajorDTO dto = lecturedao.select_departmentcode(memberid);
		System.out.println(dto.toString());
		List<String> before = lecturedao.select_Before(dto.getDepartment_code());	
		System.out.println("선수과목 배열담음");
		model.addAttribute("before",before);
		
	}
	
	
	
	public CustomLectureMgDTO subjectDetail(String subject_name){
		System.out.println("서비스들어옴");
		LectureMgDAO lecturedto = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("매퍼통과");
		System.out.println(subject_name);
		CustomLectureMgDTO param = lecturedto.detail_Value();
		CustomLectureMgDTO detail= lecturedto.subject_Detail(param,subject_name);
		
		return detail;
	}
}
