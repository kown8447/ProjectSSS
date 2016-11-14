package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.initspring.dao.JoinDAO;
import kr.or.initspring.dto.MemberTestDTO;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;
	
	@Transactional(rollbackFor={Exception.class, SQLException.class})
	public boolean insertMember(MemberTestDTO member) throws Exception{
		
		int memberResult = 0;
		int stTableResult = 0;
		boolean result = false;
		
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		
		try{
			memberResult = joindao.insertMember(member);
			if(member.getCode_type() == 1){
				stTableResult = joindao.insertStudentTable(member.getCode(), member.getUserid());
			}else if(member.getCode_type() == 2){
				//교수 table 삽입
			}else if(member.getCode_type() == 3){
				//관리자 table 삽입
			}else{
				//졸업생 table 삽입
			}
		}catch(Exception e){
			System.out.println("joinservice / insertmember : "+ e.getMessage());
			throw e;
		}
		
		if(stTableResult > 0)	result = true;
		
		return result;
	}
	
	@Transactional(rollbackFor={Exception.class, SQLException.class})
	public boolean confirmMember(String userid, String randkey, int code_type) throws Exception{
		int updateresult = 0;
		String role_name=null;
		boolean result = false;
		
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		MemberTestDTO dto = joindao.getMemberByUserid(userid);
		
		if(code_type == 1){
			role_name = "ROLE_STUDENT";
		}else if(code_type == 2){
			role_name = "ROLE_PROFESSOR";
		}else if(code_type == 3){
			role_name = "ROLE_ADMIN";
		}else if(code_type == 4){
			role_name = "ROLE_GRADUATE";
		}
		
		try{
				joindao.confirmMember(userid, randkey);
				joindao.updateJoinstate(userid);
				updateresult = joindao.insertRole(role_name, userid);
		}catch(Exception e){
			System.out.println("joinservice / confirmMember : " + e.getMessage());
			throw e;
		}
		
		if(updateresult > 0)	result = true;
		
		return result;
	}
}
