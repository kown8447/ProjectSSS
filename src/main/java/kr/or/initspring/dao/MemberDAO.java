package kr.or.initspring.dao;

import kr.or.initspring.dto.MemberDTO;

public interface MemberDAO {
	public Integer updatePwdByUserid(String member_id, String member_pwd);
	public MemberDTO getMember(String member_id);
}
