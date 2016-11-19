package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.RegisterSubjectDTO;
import kr.or.initspring.dto.SubjectDTO;

public interface LectureMgDAO {

	public List<RegisterSubjectDTO> Open_Subject();
	public List<SubjectDTO> Request_List();
	public RegisterSubjectDTO Detail_Subject();
	public RegisterSubjectDTO Update_Subject(RegisterSubjectDTO dto);
	
	
}
