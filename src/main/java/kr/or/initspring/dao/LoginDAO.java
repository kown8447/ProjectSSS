package kr.or.initspring.dao;

import kr.or.initspring.dto.join.MemberDTO;

public interface LoginDAO {
	
	public String getRoleByUserid(String member_id);
	public String searchID(String member_name, String member_email);
	public Integer getMemberTempByUserid(String member_id);
	public Integer isValidID(String member_id);
	public String getPwdByUserid(String member_id);
	public Integer updatePwd(String member_id, String member_pwd);
	public String getEmailByUserid(String member_id);
}
