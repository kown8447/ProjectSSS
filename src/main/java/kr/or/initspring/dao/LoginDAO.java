package kr.or.initspring.dao;

public interface LoginDAO {
	
	public String getRoleByUserid(String member_id);	//유저 권한 가져오기
	public String searchID(String member_name, String member_email);	//유저 ID 검색
	public Integer getMemberTempByUserid(String member_id);		//회원 임시 비밀번호 부여 상태 확인
	public Integer isValidID(String member_id);	//아이디 유효 검증
	public String getPwdByUserid(String member_id);	//회원 비밀번호 가져오기
	public Integer updatePwd(String member_id, String member_pwd);	//비밀번호 수정
	public String getEmailByUserid(String member_id);	//유저 Email 가져오기
}
