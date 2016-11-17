/*
 * @Class : MemberService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 사용자 정보 관련 서비스
*/

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
	
	/*
	 * @method Name : getEmailByUserid
	 * @Author : 권기엽
	 * @description : 비밀번호 변경, 변경이 될 경우 회원 비밀번호 상태를 임시에서 정상 상태( 0 ) 으로 변경
	*/
	@Transactional(rollbackFor={Exception.class, SQLException.class, NullPointerException.class})
	public boolean updatePwd(String member_id, String member_pwd) throws Exception{
		boolean result = false;
		int count = 0;
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		
		try{
			count = memberdao.updatePwdByUserid(member_id, member_pwd);
		}catch(Exception e){
			System.out.println("MemberService / updatePwd : " + e.getMessage());
			result = false;
			throw e;
		}
		if(count > 0) result = true;
		return result;
	}
}
