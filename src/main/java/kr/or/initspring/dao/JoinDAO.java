package kr.or.initspring.dao;

import kr.or.initspring.dto.CodeMgDTO;
import kr.or.initspring.dto.MemberDTO;

public interface JoinDAO {
	
	public int insertMember(MemberDTO member);
	public int insertRole(String role_name, String userid);
	public int insertStudentTable(String student_code, String userid);
	public Integer joinCheck1(CodeMgDTO codemg);
	public String checkID(String userid);
}
