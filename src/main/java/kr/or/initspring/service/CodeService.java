package kr.or.initspring.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.CodeMgDAO;
import kr.or.initspring.dto.CodeMgDTO;

@Service
public class CodeService {

	@Autowired
	private SqlSession sqlsession;
	
	/*
	 * @method Name : insertMember
	 * @Author : 권기엽
	 * @description
	 * 관리자가 코드를 삽입하는 함수
	 * 하나의 Query가 실패할 시 모든 Query는 Rollback 됨
	*/	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertCode(CodeMgDTO code){
		
		int insertResult = 0;
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		
		insertResult = codedao.insertCode(code);
		
		return insertResult;
	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public List<CodeMgDTO> codelist(){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		List<CodeMgDTO> codelist = codedao.codelist();
		
		return codelist;
	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public CodeMgDTO detailcode(String code){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		CodeMgDTO codedto = codedao.codeDetail(code);
		
		return codedto;
	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateCode(String code, int code_type, String code_name, Date code_birth){
		
		int result = 0;
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.editCode(code, code_type, code_name, code_birth);
		
		return result;
	}
	
}
