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
import kr.or.initspring.dto.commons.RecordDTO;
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

		
		String subject_codenum = lecturedao.maxSubject_code();
		System.out.println(subject_codenum);
		String subject_code = "SJ_"+subject_codenum;
		
		System.out.println("수정한 서브젝트코드"+subject_code);

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
	/*
	 * @method Name : selectBefore
	 * @Author : 조장현
	 * @description : before_code로 subject_name 호출
	 */
	
	public void selectBefore(String memberid, Model model) {
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		PfMajorDTO dto = lecturedao.select_departmentcode(memberid);
		System.out.println(dto.toString());
		List<String> before = lecturedao.select_Before(dto.getDepartment_code());
		model.addAttribute("before", before);

	}
	
	/*
	 * @method Name : subjectDetail
	 * @Author : 조장현
	 * @description : 과목상세보기
	 */
	public CustomLectureMgDTO subjectDetail(String subject_code) {
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println("subjectDetail service subect_code :"+subject_code);
		
		CustomLectureMgDTO detail = lecturedao.subject_Detail(subject_code);
		String reject_reason = lecturedao.select_Rejection(subject_code);
		if(detail.getSubject_type() == 0){
			detail = lecturedao.detail_major(subject_code);	
			if(detail.getBefore_name() == null || detail.getBefore_name().equals("")){
				detail.setBefore_name("없음");
			}

		}else if(detail.getSubject_type() == 1){
			detail = lecturedao.detail_liberal(subject_code);
			detail.setBefore_name("없음");
		}
		
		detail.setReject_reason(reject_reason);

			return detail;
	}
	
	
	/*
	 * @method Name : selectperiod
	 * @Author : 조장현
	 * @description : 교시 정보 조회
	 */
	
	public CustomLectureMgDTO selectperiod(String subject_code){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		CustomLectureMgDTO building = lecturedao.select_period_build(subject_code);
		return building;
	}
	
	
	/*
	 * @method Name : Request_List
	 * @Author : 조장현
	 * @description : 과목수정
	 */
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
	
	/*
	 * @method Name : deleteSubject
	 * @Author : 조장현
	 * @description : 과목삭제
	 */
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
	
	/*
	 * @method Name : RequestSubject
	 * @Author : 조장현
	 * @description : 과목 신청하기
	 */
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
	
	/*
	 * @method Name : viewtimetable
	 * @Author : 조장현
	 * @description : 개설된 과목들 시간표에 불러오기
	 */
	public List<CustomLectureMgDTO> viewtimetable(String classroom_code){

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		
		List<CustomLectureMgDTO> dto = lecturedao.viewtimetable(classroom_code);
		return dto; 
	}
	
	/*
	 * @method Name : selectBuilding
	 * @Author : 조장현
	 * @description : 건물명 조회
	 */
	public List<CustomLectureMgDTO> selectBuilding(String building_code){
		System.out.println(building_code);
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<CustomLectureMgDTO> dto = lecturedao.selectAllBuilding(building_code);
		System.out.println(dto.toString());

		return dto;
	}
	
	/*
	 * @method Name : getPeriodList
	 * @Author : 조장현
	 * @description : 시간표 교시 출력
	 */
	public List<PeriodDTO> getPeriodList(){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<PeriodDTO> dto = lecturedao.getPeriodList();
		
		return dto;
	}

	/*
	 * @method Name : getBuildingName
	 * @Author : 조장현
	 * @description : 건물 이름 출력
	 */
	public List<String> getBuildingName(){
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<String> building = lecturedao.select_BuildingName();
		
		return building;		
	}
	
	/*
	 * @method Name : getSemester
	 * @Author : 조장현
	 * @description : 학기테이블 조회
	 */
	public List<SemesterDTO> getSemester(){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<SemesterDTO> dto = lecturedao.getSemester();
		
		return dto;
	}
	
	/*
	 * @method Name : selectMyclass
	 * @Author : 조장현
	 * @description : 학생 성적등록시 내 과목 출력
	 */
	public List<CustomLectureMgDTO> selectMyclass(Principal principal){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String professor_code = lecturedao.select_Professor(principal.getName());
		
		List<CustomLectureMgDTO> dto = lecturedao.select_myClass(professor_code);
		
		return dto;
		
	}
	
	
	/*
	 * @method Name : select_Studentlist
	 * @Author : 조장현
	 * @description : 내 과목 수강생 조회
	 */
	public List<CustomLectureMgDTO> select_Studentlist(String subject_code){
		System.out.println("스투던트서비스탐"+subject_code);
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<CustomLectureMgDTO> dto = lecturedao.select_Studentlist(subject_code);
		
		return dto;
	}
	
	/*
	 * @method Name : insertgrade
	 * @Author : 조장현
	 * @description : 성적 입력
	 */
	public CustomLectureMgDTO insertgrade(CustomLectureMgDTO dto){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		System.out.println(dto.getStudent_code());
		String record = lecturedao.maxRecord_code();
		String record_code = "RC_"+record;
		
		dto.setRecord_code(record_code);
		CustomLectureMgDTO state = lecturedao.select_stState(dto.getStudent_code());
	/*	dto.setRecord_code(record_code);*/
		System.out.println("꼭확인해야됨"+dto.toString());
		
		String inselectlevel = lecturedao.select_Recordlevel(dto.getStudent_code(), dto.getSubject_code());
		
		if(inselectlevel == null || inselectlevel.equals("")){  //현재 성적이 없다면
			List<String> secondsubject = lecturedao.select_reStudy(dto.getSubject_name(), dto.getStudent_code());
			/*if(secondsubject.equals("") || secondsubject == null){*/
			if(secondsubject != null){	
			for(int i = 0; i < secondsubject.size(); i++){
					System.out.println("재수강탔음");
					lecturedao.update_RetakeCheck(dto.getRecord_code());
				}
			}
			System.out.println("값이없으므로 인설트");
			lecturedao.insert_record(dto);
		}else{
			System.out.println("값이있으므로 수정쓰");
			lecturedao.update_record(dto);
		}
	
		System.out.println("인설트성공");
		
		
		return dto;
	}
	
}
