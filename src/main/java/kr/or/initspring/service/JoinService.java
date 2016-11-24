/*
 * @Class : JoinService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원 가입 서비스
*/

package kr.or.initspring.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.RE;

import kr.or.initspring.dao.JoinDAO;
import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.dto.join.MemberDTO;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : insertMember
	 * @Author : 권기엽
	 * @description
	 * 회원 정보를 삽입하는 함수
	 * member table 삽입 -> student/admin/professor 중 하나의 table 삽입 -> 권한 지정 까지를 하나의 트랜잭션으로 처리.
	 * 하나의 Query가 실패할 시 모든 Query는 Rollback 됨
	*/	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean insertMember(MemberDTO member) throws Exception {

		int insertResult = 0;
		boolean result = false;

		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);

		try {
			joindao.insertMember(member);
			if (member.getCode_type() == 0) {
				joindao.insertStudentTable(member.getCode(), member.getMember_id());
				insertResult = joindao.insertRole("ROLE_STUDENT", member.getMember_id());
			} else if (member.getCode_type() == 1) {
				// 교수 table 삽입
			} else if (member.getCode_type() == 2) {
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

	/*
	 * @method Name : joinCheck1
	 * @Author : 권기엽
	 * @description
	 * CODE_MG 테이블의 정보와 사용자가 입력한 정보가 일치하는 지 확인하는 함수
	*/
	public boolean joinCheck1(CodeMgDTO codemg) {
		boolean result = false;
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		int count;

		count = joindao.joinCheck1(codemg);
		if (count > 0)
			result = true;

		return result;
	}

	/*
	 * @method Name : checkID
	 * @Author : 권기엽
	 * @description : 아이디 중복을 체크하는 함수
	*/
	public boolean checkID(String member_id) {
		boolean result = false;
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		String checkID = "";

		checkID = joindao.checkID(member_id);
		try {
			if (checkID.equals(member_id)) {
				result = true;
			}
		} catch (NullPointerException e) {
			System.out.println("JoinService / checkid nullpointexception");
			result = false;
		}

		return result;
	}
}
