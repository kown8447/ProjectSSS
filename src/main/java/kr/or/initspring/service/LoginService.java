package kr.or.initspring.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.initspring.dao.LoginDAO;

@Service
public class LoginService {
	
	@Autowired
	private SqlSession sqlsession;
	
	public String getRoleByUserid(String userid){
		String role_name = null;
		
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		role_name = logindao.getRoleByUserid(userid);
		
		return role_name;
	}
	
	public int getJoinstateByUserid(String userid){
		int joinstate = -1;
		
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		try{
			
		}catch(Exception e){
			joinstate = logindao.getJoinstateByUserid(userid);
		}
		
		return joinstate;
	}
}
