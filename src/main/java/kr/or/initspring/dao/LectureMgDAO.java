package kr.or.initspring.dao;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PfMajorDTO;
import kr.or.initspring.dto.commons.PlanDTO;
import kr.or.initspring.dto.commons.ProfessorDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.lectureMg.CustomLectureMgDTO;

public interface LectureMgDAO {

	public List<CustomLectureMgDTO> Open_Subject();
	
	public List<CustomLectureMgDTO> request_liberal();
	public List<CustomLectureMgDTO> request_major();
	
	public CustomLectureMgDTO detail_Value();
	public List<String> select_Before(String department_code);
	public String select_Professor(String principalid);
	public int insert_Subject(SubjectDTO dto);
	public int insert_Plan(PlanDTO dto);
	public int insert_BeforeCode(String subject_code,String before_code);
	public CustomLectureMgDTO subject_Detail(String subject_code); 
	public String detail_BeforeSubject(String before_code);
	public int update_Subject();

	

	////////////////////////////////////
	public PfMajorDTO select_departmentcode(String memberid);
	public int insert_Liberal(String subject_code,String required_choice);
	public int insert_major(String subject_code,String required_choice,String department_code);
	public List<CustomLectureMgDTO> select_list(String professor_code);
	public String select_major(String subject_code);
	public String select_liberal(String subject_code);
	
	public String detail_beforecode(String subject_code);
	public String detail_beforename(String before_code);
	
	public CustomLectureMgDTO detail_liberal(String subject_code);
	public CustomLectureMgDTO detail_major(String subjct_code);
	public CustomLectureMgDTO select_period_build(String subject_code);
	
	

}
