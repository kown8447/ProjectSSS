package kr.or.initspring.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.or.initspring.dao.AsideDAO;
import kr.or.initspring.dao.MemberDAO;
import kr.or.initspring.dto.aside.AsideAdminDTO;
import kr.or.initspring.dto.aside.AsideProfessorDTO;
import kr.or.initspring.dto.aside.AsideStudentDTO;

@Service
public class AsideService {
	@Autowired
	private SqlSession sqlsession;

	public void persnalDataCall(String memberId, Model model) {
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		String userRole= memberdao.getRole(memberId);
		AsideDAO asidedao = sqlsession.getMapper(AsideDAO.class);
		if(userRole.equals("ROLE_STUDENT")){
			model.addAttribute("userType", "student");
			AsideStudentDTO student=asidedao.getStudentInfo(memberId);
			model.addAttribute("student", student);
		}else if(userRole.equals("ROLE_PROFESSOR")){
			model.addAttribute("userType", "professor");
			AsideProfessorDTO professor=asidedao.getProfessorInfo(memberId);
			model.addAttribute("professor",professor);
		}else if(userRole.equals("ROLE_ADMIN")){
			model.addAttribute("userType", "admin");
			AsideAdminDTO admin=asidedao.getAdminIfo(memberId);
			model.addAttribute("admin",admin);
		}
	}
}
