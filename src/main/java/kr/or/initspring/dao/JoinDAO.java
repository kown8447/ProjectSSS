package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.dto.join.MemberDTO;

public interface JoinDAO {
	public int insertMember(MemberDTO member);  //member 테이블에 insert 
	public int insertRole(String role_name, String userid); // 사용자 권한 insert 
	public int insertStudentTable(String student_code, String userid); // student table 에 학생등록
	public int insertProfessorTable(String professor_code, String userid); //교수테이블에 교수등록
	public int insertAdminTable(String admin_code, String userid); //관리자테이블에 관리자 등록 
	public Integer joinCheck1(CodeMgDTO codemg);  //신규가입자가 회원등록 할 수 있는지 확인
	public List<String> studentConfirm(); //등록된 학생인지 확인
	public List<String> professorConfirm(); //등록된 교수인지 확인
	public List<String>  adminConfirm(); //등록된 관리자인지 확인
	public int checkID(String userid); // 아이디 중복 검사
}
