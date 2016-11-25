/*
 * @Class : JoinService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원 가입 서비스
*/

package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
				joindao.insertProfessorTable(member.getCode(), member.getMember_id());
				insertResult = joindao.insertRole("ROLE_PROFESSOR", member.getMember_id());
			} else if (member.getCode_type() == 2) {
				joindao.insertAdminTable(member.getCode(), member.getMember_id());
				insertResult = joindao.insertRole("ROLE_ADMIN", member.getMember_id());
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
	    * @method Name : joinCheck2
	    * @Author : 김영빈
	    * @description
	    * 이미 회원가입한 회원인지 확인
	   */
	public boolean joinCheck2(CodeMgDTO codemg) {
		boolean result = false;
		JoinDAO joindao = sqlsession.getMapper(JoinDAO.class);
		int count =0;
		if(codemg.getCode_type()==0){
			List<String> check = joindao.studentConfirm();
			for(int i=0; i<check.size() ; i++){
				if(check.get(i).equals(codemg.getCode())){
					count=0;
					break;
				}else {count =1;}
			}
			
		}else if(codemg.getCode_type()==1){
			List<String> check  = joindao.professorConfirm();
			for(int i=0; i<check.size() ; i++){
				if(check.get(i).equals(codemg.getCode())){
					count=0;
					break;
				}else {count =1;}
			}
		}else{
			List<String> check  = joindao.adminConfirm();
			for(int i=0; i<check.size() ; i++){
				if(check.get(i).equals(codemg.getCode())){
					count=0;
					break;
				}else {count =1;}
			}
		}
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
