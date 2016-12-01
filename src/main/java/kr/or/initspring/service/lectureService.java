package kr.or.initspring.service;

import java.io.FileOutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dao.LectureMgDAO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.PfMajorDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
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
	public synchronized int insert_Subject(SubjectDTO dto, String subject_name, Principal principal, String required_choice,
			BeforeSubjectDTO beforedto, MajorDTO majordto, LiberalDTO liberdto, String department_code) {

		int result = 0;
		int before = 0;
		
		String principalid = principal.getName();
		System.out.println(principalid);
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		String subject_code = lecturedao.maxSubject_code();

		String code[] = subject_code.split("_");
		String first = code[0];
		String second = code[1];
		
		System.out.println(first+"/"+second);
		
		int change = Integer.parseInt(second);
		change += 1;

		subject_code = first+"_"+change;
		dto.setSubject_code(subject_code);
		
		
		try {
			String insertparam = lecturedao.select_Professor(principalid);
			dto.setProfessor_code(insertparam);
			lecturedao.insert_Subject(dto);
		
			int subject_type = dto.getSubject_type();
			int required_select = 5; 

			department_code = lecturedao.select_departmentcode(principalid).getDepartment_code();

			if (subject_type == 0) {
				required_select = lecturedao.insert_major(subject_code, required_choice, department_code);

				if (!subject_name.equals("0")) {
					before = lecturedao.insert_BeforeName(subject_code, subject_name);
				} 
			} else if (subject_type == 1) {
				required_select = lecturedao.insert_Liberal(subject_code, required_choice);

				if (!subject_name.equals("0")) {
					before = lecturedao.insert_BeforeName(subject_code, "없음");
				} 
			}

		} catch (Exception e) {
			System.out.println("장현 등록 트랜잭션 오류 : " + e.getMessage());
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
	
	//상세보기
	public CustomLectureMgDTO subjectDetail(String subject_code) {
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("subjectDetail service subect_code :"+subject_code);
	
		CustomLectureMgDTO detail = lecturedao.subject_Detail(subject_code);
		
		if(detail.getSubject_type() == 0){
			detail = lecturedao.detail_major(subject_code);	
			if(detail.getBefore_name() == null || detail.getBefore_name().equals("")){
				detail.setBefore_name("없음");
			}

		}else if(detail.getSubject_type() == 1){
			detail = lecturedao.detail_liberal(subject_code);
			detail.setBefore_name("없음");
		}
	
			return detail;
	}
	
	public CustomLectureMgDTO selectperiod(String subject_code){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		CustomLectureMgDTO building = lecturedao.select_period_build(subject_code);
		return building;
	}
	
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updatesubject(CustomLectureMgDTO dto){
	
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		System.out.println(dto.toString());
		try{
				lecturedao.update_Subject(dto);
				lecturedao.update_before_subject(dto);
		
		} catch (Exception e) {
			System.out.println("장현 수정 트랜잭션 오류 : " + e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
			
		return 0;

	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void deleteSubject(String subject_code){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		try{
			lecturedao.delete_Before(subject_code);
			lecturedao.delete_Liberal(subject_code);
			lecturedao.delete_Major(subject_code);
			lecturedao.delete_Plan(subject_code);
			lecturedao.delete_Ask_Time(subject_code);
			lecturedao.delete_Subject(subject_code);
		 
	} catch (Exception e) {
		System.out.println("장현 삭제 트랜잭션 오류 : " + e.getMessage());
		try {
			throw e;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class, RuntimeException.class})
	public void RequestSubject(CustomLectureMgDTO dto,HttpServletRequest request,String success_check) throws Exception{
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("이것이 작업할 :"+success_check);
		
		if(success_check.equals("2")){
			lecturedao.delete_Ask_Time(dto.getSubject_code());
			lecturedao.delete_Oprequest(dto.getSubject_code());
			lecturedao.delete_Rejection(dto.getSubject_code());
		}
		
		
		
		String period_code = dto.getPeriod_code();

		String code[] = period_code.split(",");
		String subject_code = dto.getSubject_code();
		String classroom_code = dto.getClassroom_code();
		System.out.println(classroom_code+code[0]+subject_code);
	
		try{
			lecturedao.setOprequest(dto);
			
			for(int i = 0; i < code.length ; i++){
			System.out.println(code[i]);
			lecturedao.setAskTime(classroom_code,code[i].trim(),subject_code);
			}
			
			CommonsMultipartFile file = dto.getSubject_filename();
			String filename = null;
			String filesrc = null;

		      if (file != null && file.getSize() > 0) {
		         String fname = file.getOriginalFilename();
		         String path = request.getServletContext().getRealPath("/files/lecture");
		         String fullpath = path + "\\" + fname;
		         if (!fname.equals("")) {
		            FileOutputStream fs = new FileOutputStream(fullpath);
		            fs.write(file.getBytes());
		            fs.close();
		         }
		         filename = fname;
		         filesrc = path;
		      }

		      dto.setSubject_filename(file);
		      dto.setSubject_filesrc(filename);
		      System.out.println(file+"/"+dto.getSubject_code());
		      
		      lecturedao.insert_Plan(dto);
	 
		} catch (Exception e) {
		System.out.println("장현 신청 트랜잭션 오류 : " + e.getMessage());
		throw e;
	}
		
	}
	
	
	public List<CustomLectureMgDTO> viewtimetable(String classroom_code){

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		List<CustomLectureMgDTO> dto = lecturedao.viewtimetable(classroom_code);
		return dto; 
	}
	
	public List<CustomLectureMgDTO> selectBuilding(String building_code){
		System.out.println(building_code);
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<CustomLectureMgDTO> dto = lecturedao.selectAllBuilding(building_code);
		System.out.println(dto.toString());

		return dto;
	}
	
	public List<PeriodDTO> getPeriodList(){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<PeriodDTO> dto = lecturedao.getPeriodList();
		
		return dto;
	}
	
	public List<SemesterDTO> getSemester(){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<SemesterDTO> dto = lecturedao.getSemester();
		
		return dto;
	}
	
	public List<CustomLectureMgDTO> selectMyclass(Principal principal){
		
		System.out.println("마이클래스");
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String professor_code = lecturedao.select_Professor(principal.getName());
		
		System.out.println("셀렉마이클래스"+professor_code);
		
		List<CustomLectureMgDTO> dto = lecturedao.select_myClass(professor_code);
		System.out.println("ㅣㄸ띠띠띠띠오생성");
		return dto;
		
	}
	
	
	
	public CustomLectureMgDTO select_Studentlist(String subject_code){
		System.out.println("스투던트서비스탐"+subject_code);
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		CustomLectureMgDTO dto = lecturedao.select_Studentlist(subject_code);
		
		return dto;
	}
	
	
}
