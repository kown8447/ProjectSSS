package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.MemberDAO;

@Service
public class MemberService {

	@Autowired
	private SqlSession sqlsession;
	
	@Transactional(rollbackFor={Exception.class, SQLException.class, NullPointerException.class})
	public boolean updatePwd(String userid, String pwd) throws Exception{
		boolean result = false;
		int count = 0;
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		
		try{
			count = memberdao.updatePwdByUserid(userid, pwd);
		}catch(Exception e){
			System.out.println("MemberService / updatePwd : " + e.getMessage());
			result = false;
			throw e;
		}
		if(count > 0) result = true;
		return result;
	}
}
