/*
 * @Class : MemberService
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 사용자 정보 관련 서비스
*/

package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.initspring.dao.MemberDAO;
import kr.or.initspring.dao.RequestCourseDAO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.StudentDTO;
import kr.or.initspring.dto.join.MemberDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;

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
	/*
	    * @method Name : getMember
	    * @Author : 김영빈
	    * @description : 학번이나 교수코드, 관리자코드를 따로 권한에 따라 뽑아내는 service
	   */
	public MemberDTO getMember(String member_id){
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		String member_role = memberdao.getRole(member_id);
		MemberDTO member;
		System.out.println("member_role : "+ member_role);
		if(member_role.equals("ROLE_STUDENT")){
			member=memberdao.getStudent(member_id);
		}else if(member_role.equals("ROLE_PROFESSOR")){
			member=memberdao.getProfessor(member_id);
		}else if(member_role.equals("ROLE_ADMIN")){
			member =memberdao.getAdmin(member_id);
		}else{
			member= null;
		}
	
		return member;
		
	}
	/*
	    * @method Name : getRole
	    * @Author : 김영빈
	    * @description : 아이디에 따라 권한 뽑는 service
	   */
	public String getRole(String member_id){
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		String member_role = memberdao.getRole(member_id);
		return member_role;
	}
	/*
	    * @method Name : getFileName
	    * @Author : 김영빈
	    * @description : 개인정보 수정에서 파일을 수정하지 않을 시 파일 이름을 뽑는 service
	   */
	public String getFileName(String member_id){
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		return memberdao.getFileName(member_id);
	}
	/*
	    * @method Name : updateMemberInfo
	    * @Author : 김영빈
	    * @description : 교수, 관리자 개인정보 수정  service
	   */
	@Transactional(rollbackFor={Exception.class, SQLException.class, NullPointerException.class})
	public boolean updateMemberInfo(String member_id, String member_pwd, String member_addr, String member_phone, String member_email,String file) throws Exception{
		boolean result = false;
		int count =0; 
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		try{
			count = memberdao.updateMemberInfo(member_id, member_pwd,member_addr, member_phone, member_email,file);
		}catch(Exception e){
			System.out.println("MemberService / updatePwd : " + e.getMessage());
			result = false;
			throw e;
		}
		if(count > 0) result = true;
		
		return result;
	}
	/*
	    * @method Name : updateStudentInfo
	    * @Author : 김영빈
	    * @description : 학생 개인정보 수정  service
	   */
	@Transactional(rollbackFor={Exception.class, SQLException.class, NullPointerException.class})
	public boolean updateStudentInfo(String member_id, String member_pwd, String member_addr, String member_phone, String member_email,String file ,int timetable_share) throws Exception{
		boolean result = false;
		int count =0; 
		int count2= 0;
		MemberDAO memberdao = sqlsession.getMapper(MemberDAO.class);
		try{
			count = memberdao.updateMemberInfo(member_id, member_pwd,member_addr, member_phone, member_email,file);
			count2 = memberdao.updateStudentTimeShare(member_id, timetable_share);
		}catch(Exception e){
			System.out.println("MemberService / updatePwd : " + e.getMessage());
			result = false;
			throw e;
		}
		if(count > 0 && count2>0) result = true;
		
		return result;
	}
	
	/*
	 * @method Name : viewCurrentTimetable
	 * @Author : 권기엽
	 * @description : ID 기준으로 해당 학생의 시간표 출력
	 */
	public List<OpenedLectureDTO> viewCurrentTimetable(String member_id){
		
		List<OpenedLectureDTO> lists = new ArrayList<OpenedLectureDTO>();
		
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		StudentDTO studentDto = requestCourseDao.getStudentByMemberid(member_id);
		lists = requestCourseDao.getCurrentTimetableByStudentCode(studentDto.getStudent_code());
		for(OpenedLectureDTO dto : lists){
			dto.setPeriod(requestCourseDao.getPeriodBySubjectCode(dto.getSubject_code()));
			dto.setProfessor_name(requestCourseDao.getProfessorNameByPfCode(dto.getProfessor_code()));
			dto.setSubject_filesrc(requestCourseDao.getLecturePlanBySubjectCode(dto.getSubject_code()));
			dto.setRequired_choice(requestCourseDao.getRequiredChoice(dto.getSubject_code(), dto.getSubject_type()));
			dto.setRetake_check(requestCourseDao.getRetakeCheck(studentDto.getStudent_code(), dto.getSubject_code()));
		}
		return lists;
	}
	/*
	 * @method Name : getPeriodList
	 * @Author : 권기엽
	 * @description : 시간표의 시간을 뿌려주기 위한 list
	 */
	public List<PeriodDTO> getPeriodList(){
		RequestCourseDAO requestCourseDao = sqlsession.getMapper(RequestCourseDAO.class);
		List<PeriodDTO> periodList = requestCourseDao.getPeriodList();
		return periodList;
	}

}
