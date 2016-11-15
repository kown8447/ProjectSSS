package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.initspring.dao.JoinDAO;
import kr.or.initspring.dto.CodeMgDTO;
import kr.or.initspring.dto.MemberTestDTO;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;

	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean insertMember(MemberTestDTO member) throws Exception {

		int insertResult = 0;
		boolean result = false;

		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);

		try {
			joindao.insertMember(member);
			if (member.getCode_type() == 1) {
				joindao.insertStudentTable(member.getCode(), member.getUserid());
				insertResult = joindao.insertRole("ROLE_STUDENT", member.getUserid());
			} else if (member.getCode_type() == 2) {
				// 교수 table 삽입
			} else if (member.getCode_type() == 3) {
				// 관리자 table 삽입
			} else {
				// 졸업생 table 삽입
			}
		} catch (Exception e) {
			System.out.println("joinservice / insertmember : " + e.getMessage());
			throw e;
		}

		if (insertResult > 0)
			result = true;

		return result;
	}

	public boolean joinCheck1(CodeMgDTO codemg) {
		boolean result = false;
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		int count;

		count = joindao.joinCheck1(codemg);
		if (count > 0)
			result = true;

		return result;
	}

	public boolean checkID(String userid) {
		boolean result = false;
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		String checkID = "";

		checkID = joindao.checkID(userid);
		try {
			if (checkID.equals(userid)) {
				result = true;
			}
		} catch (NullPointerException e) {
			System.out.println("JoinService / checkid nullpointexception");
			result = false;
		}

		return result;
	}
}
