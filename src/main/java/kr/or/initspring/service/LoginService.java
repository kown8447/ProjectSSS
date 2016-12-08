/*
 * @Class : LoginService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 로그인 서비스
*/

package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.LoginDAO;

@Service
public class LoginService {
	
	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : getRoleByUserid
	 * @Author : 권기엽
	 * @description : 사용자의 권한 정보를 얻는 함수
	*/	
	public String getRoleByUserid(String member_id){
		String role_name = null;
		
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		role_name = logindao.getRoleByUserid(member_id);
		
		return role_name;
	}
	
	/*
	 * @method Name : searchID
	 * @Author : 권기엽
	 * @description : 이름 + 이메일을 통해서 사용자 아이디를 가져오는 함수
	*/	
	public String searchID(String member_name, String member_email){
		String member_id = null;
		
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		try{
			member_id = logindao.searchID(member_name, member_email);
		}catch(Exception e){
			System.out.println("LoginService / searchID : " + e.getMessage());
		}
		
		return member_id;
	}
	
	/*
	 * @method Name : updatePwd
	 * @Author : 권기엽
	 * @description : 비밀번호를 변경하고 회원 상태를 임시비밀번호 발급 상태로 변경
	*/	
	@Transactional(rollbackFor={Exception.class, NullPointerException.class, SQLException.class})
	public boolean updatePwd(String member_id, String member_pwd) throws Exception{
		boolean result = false;
		int count = 0;
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		try{
			count = logindao.updatePwd(member_id, member_pwd);
		}catch(Exception e){
			System.out.println("LoginService / updatePwd : " + e.getMessage());
			throw e;
		}
		if(count > 0)	result = true;
		return result;
	}
	
	/*
	 * @method Name : getEmailByUserid
	 * @Author : 권기엽
	 * @description : 사용자 아이디를 매개변수로 이메일 주소 반환
	*/
	public String getEmailByUserid(String member_id){
		String email = "";
		LoginDAO logindao = sqlsession.getMapper(LoginDAO.class);
		email = logindao.getEmailByUserid(member_id);
		return email;
	}
}
