package kr.or.initspring.dao;

import java.security.Principal;
import java.util.List;

import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.PlanDTO;
import kr.or.initspring.dto.commons.ProfessorDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.lectureMg.CustomLectureMgDTO;

public interface LectureMgDAO {

	public List<CustomLectureMgDTO> Open_Subject();
	public List<CustomLectureMgDTO> request_List();
	public int insert_Subject(Principal principal,SubjectDTO dto);
	public int insert_Professor();
	public int insert_Filesrc();

}
