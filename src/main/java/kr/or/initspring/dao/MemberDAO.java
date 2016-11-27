package kr.or.initspring.dao;

import kr.or.initspring.dto.join.MemberDTO;

public interface MemberDAO {
	  public Integer updatePwdByUserid(String member_id, String member_pwd); //아이디로부터 비밀번호 업데이트 
	   public MemberDTO getStudent(String member_id); // 학생테이블로 부터  학번 뽑아낸다
	   public MemberDTO getProfessor(String member_id); // 교수테이블로부터 교수코드 뽑는다
	   public MemberDTO getAdmin(String member_id); // 관리자 테이블로부터 관리자 코드 뽑느다 .
	   public MemberDTO getMember(String member_id);
	   public String getRole(String member_id);  //  권한 얻는다
	   public String getFileName(String member_id); // 파일 이름 얻는다
	   public Integer updateMemberInfo(String member_id ,String member_pwd,String member_addr, String member_phone, String member_email, String file);  // 개인정보 수정 업데이트
	   public Integer updateStudentTimeShare(String member_id,int timetable_share); 

}