package kr.or.initspring.dao;

import java.sql.Date;
import java.util.List;

import kr.or.initspring.dto.commons.CodeMgDTO;

public interface CodeMgDAO {
	
	public int insertCode(CodeMgDTO code);
	public List<CodeMgDTO> codelist();
	public CodeMgDTO codeDetail(String code);
	public int editCode(String code, int code_type, String code_name, Date code_birth);
	public int insertExcel(CodeMgDTO code);
	public int deleteCode(String code) throws Exception;
}
