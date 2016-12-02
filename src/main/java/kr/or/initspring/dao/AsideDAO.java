package kr.or.initspring.dao;

import kr.or.initspring.dto.aside.AsideAdminDTO;
import kr.or.initspring.dto.aside.AsideProfessorDTO;
import kr.or.initspring.dto.aside.AsideStudentDTO;

public interface AsideDAO {

	public AsideStudentDTO getStudentInfo(String memberId);

	public AsideProfessorDTO getProfessorInfo(String memberId);

	public AsideAdminDTO getAdminIfo(String memberId);

}
