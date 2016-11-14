package kr.or.initspring.dao;

public interface LoginDAO {
	
	public String getRoleByUserid(String userid);
	public Integer getJoinstateByUserid(String userid);
}
