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
	public List<CustomLectureMgDTO> Request_List(Principal principal) {

		List<CustomLectureMgDTO> list = new ArrayList<CustomLectureMgDTO>();

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String professorcode = lecturedao.select_Professor(principal.getName());
		list = lecturedao.select_list(professorcode);


		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubject_type() == 0) {
				list.get(i).setRequired_choice(lecturedao.select_major(list.get(i).getSubject_code()));
			} else {
				list.get(i).setRequired_choice(lecturedao.select_liberal(list.get(i).getSubject_code()));
			}
		}

		return list;
	}

	/*
	 * @method Name : Insert_Subject
	 * @Author : 조장현
	 * @description : 과목 등록
	 */

	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insert_Subject(SubjectDTO dto, String before_code, Principal principal, String required_choice,
			BeforeSubjectDTO beforedto, MajorDTO majordto, LiberalDTO liberdto, String department_code) {

		int result = 0;
		int before = 0;
		String principalid = principal.getName();
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		try {
			String insertparam = lecturedao.select_Professor(principalid);
			dto.setProfessor_code(insertparam);
			lecturedao.insert_Subject(dto);

			String subject_code = dto.getSubject_code();
			int subject_type = dto.getSubject_type();
			

			if (!before_code.equals("0")) {
				before = lecturedao.insert_BeforeCode(subject_code, before_code);
			} 
			int required_select = 5; // 신경 ㄴㄴ염 숫자 5를좋아함 바이날둠이 5번임

			department_code = lecturedao.select_departmentcode(principalid).getDepartment_code();

			if (subject_type == 0) {
				required_select = lecturedao.insert_major(subject_code, required_choice, department_code);
			} else if (subject_type == 1) {
				required_select = lecturedao.insert_Liberal(subject_code, required_choice);
			}

		} catch (Exception e) {
			System.out.println("장현 트랜잭션 오류 : " + e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return result;
	}

	//before_code로 subject_name불러오는 함수
	public void selectBefore(String memberid, Model model) {
	
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		PfMajorDTO dto = lecturedao.select_departmentcode(memberid);
		System.out.println(dto.toString());
		List<String> before = lecturedao.select_Before(dto.getDepartment_code());
		model.addAttribute("before", before);

	}
	
	
	public CustomLectureMgDTO subjectDetail(String subject_code) {
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("subjectDetail service subect_code :"+subject_code);
	
		
		
		CustomLectureMgDTO detail = lecturedao.subject_Detail(subject_code);
		if(detail.getSubject_type() == 0){
			detail = lecturedao.detail_major(subject_code);			
			String beforecode = lecturedao.detail_beforecode(subject_code);
			String beforename = lecturedao.detail_beforename(beforecode);
			detail.setBefore_name(beforename);
			
		}else if(detail.getSubject_type() == 1){
			detail = lecturedao.detail_liberal(subject_code);
			detail.setBefore_name("없음");
			System.out.println("교양탓다");
			System.out.println(detail.toString());
		}
	
			return detail;
	}
	
	public CustomLectureMgDTO selectperiod(String subject_code){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		CustomLectureMgDTO building = lecturedao.select_period_build(subject_code);
		System.out.println("빌딩코드:"+building.getBuliding_code());
		return building;
	}
	
	
}
