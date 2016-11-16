package kr.or.initspring.dao;

public interface MemberDAO {
	public Integer updatePwdByUserid(String member_id, String member_pwd);
}
