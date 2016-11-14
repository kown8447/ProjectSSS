package kr.or.initspring.dao;

import kr.or.initspring.dto.MemberTestDTO;

public interface JoinDAO {
	
	public int insertMember(MemberTestDTO member);
	public int confirmMember(String userid, String randkey);
	public int updateJoinstate(String userid);
	public int insertRole(String role_name, String userid);
	public MemberTestDTO getMemberByUserid(String userid);
	public int insertStudentTable(String student_code, String userid);
}
