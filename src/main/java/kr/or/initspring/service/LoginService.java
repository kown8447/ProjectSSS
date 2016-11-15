package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public String searchID(String name, String email){
		String userid = null;
		
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		try{
			userid = logindao.searchID(name, email);
		}catch(Exception e){
			System.out.println("LoginService / searchID : " + e.getMessage());
		}
		
		return userid;
	}
	
	@Transactional(rollbackFor={Exception.class, NullPointerException.class, SQLException.class})
	public boolean updatePwd(String userid, String pwd) throws Exception{
		boolean result = false;
		int count = 0;
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		System.out.println("서비스 안에서의 아이디 비번 : " + userid +"/"+pwd);
		try{
			count = logindao.updatePwd(userid, pwd);
		}catch(Exception e){
			System.out.println("LoginService / updatePwd : " + e.getMessage());
			throw e;
		}
		if(count > 0)	result = true;
		return result;
	}
	
	public String getEmailByUserid(String userid){
		String email = "";
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		email = logindao.getEmailByUserid(userid);
		return email;
	}
}
