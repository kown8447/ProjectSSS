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
	//과목리스트 사용
	public String select_Professor(String principalid);
	public List<CustomLectureMgDTO> select_list(String professor_code);
	public String select_major(String subject_code);
	public String select_liberal(String subject_code);
	
	//과목등록 사용
	//위에 셀렉프로페서
	public int insert_Subject(SubjectDTO dto);
	public PfMajorDTO select_departmentcode(String memberid);
	public int insert_Liberal(String subject_code,String required_choice);
	public int insert_major(String subject_code,String required_choice,String department_code);
	public String maxSubject_code();
	
	//비포코드로 서브젝트네임불러옴
	//위에 셀렉디파트먼트
	public List<String> select_Before(String department_code);

	//상세보기
	public CustomLectureMgDTO detail_liberal(String subject_code);
	public CustomLectureMgDTO detail_major(String subjct_code);
	public CustomLectureMgDTO subject_Detail(String subject_code); 

	//수정하기
	public int update_Subject(CustomLectureMgDTO dto);
	public int update_before_subject(CustomLectureMgDTO dto);

	//삭제하기
	public void delete_Subject(String subject_code);
	public void delete_Plan(String subject_code);
	public void delete_Major(String subject_code);
	public void delete_Liberal(String subject_code);
	public void delete_Ask_Time(String subject_code);
	public void delete_Before(String subject_code);
	
	public int insert_BeforeName(String subject_code,String subject_name);
	
	public CustomLectureMgDTO select_period_build(String subject_code);
	
	

}
