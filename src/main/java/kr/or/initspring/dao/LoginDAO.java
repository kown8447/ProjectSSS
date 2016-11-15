package kr.or.initspring.dao;

import kr.or.initspring.dto.MemberTestDTO;

public interface LoginDAO {
	
	public String getRoleByUserid(String userid);
	public String searchID(String name, String email);
	public Integer getMemberTempByUserid(String userid);
	public Integer isValidID(String userid);
	public String getPwdByUserid(String userid);
	public Integer updatePwd(String userid, String pwd);
	public String getEmailByUserid(String userid);
}
