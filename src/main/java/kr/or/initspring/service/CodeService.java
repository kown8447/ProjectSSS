package kr.or.initspring.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.initspring.dao.CodeMgDAO;
import kr.or.initspring.dto.commons.CodeMgDTO;

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
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertExcel(CodeMgDTO code){
		
		int result = 0;
		System.out.println("액셀넣기 서비스");
		System.out.println(code.toString());
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.insertExcel(code);
		
		return result;
	}
	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void insertExcelList(MultipartHttpServletRequest request, Model model){
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("excel");
		
		
		

		CodeMgDTO codeMg = new CodeMgDTO();
		boolean result = false;
		
		
	
		if(file != null && file.getSize() > 0){
			try{
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				
				Sheet sheet = workbook.getSheetAt(0);
				
				int last = sheet.getLastRowNum();
				System.out.println("Last(마지막열) = " + last);
				for(int i = 1; i<= last; i++){
				Row row = sheet.getRow(i);
				
				//System.out.println("Row = " + row + "i =" +i);
				
				int code_type = Integer.parseInt(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue()); 
				String code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String code_name = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String StringBirth = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				
				//System.out.println(StringBirth);
				//System.out.println(StringBirth.trim());
				java.util.Date date =null;
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					date = (java.util.Date)formatter.parse(StringBirth.trim());
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//System.out.println(date.getTime());
				//System.out.println(date);
				java.sql.Date code_birth = new java.sql.Date(date.getTime());
				
				
				System.out.println("code_type = " + code_type);
				System.out.println("code = " + code);
				System.out.println("code_name = " + code_name);
				System.out.println("code_birth = " + code_birth);
				
				codeMg.setCode_type(code_type);
				codeMg.setCode(code);
				codeMg.setCode_name(code_name);
				codeMg.setCode_birth(code_birth);
				System.out.println("codeMg = " + codeMg.toString());

				codedao.insertExcel(codeMg);
				
				}
			}catch(Exception e){
		
			}
		}
		result = true;
		model.addAttribute("result", result);

	}
	

	public int deleteCode(String code, Model model){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		System.out.println("삭제대상 : "+code);
		int result=0;
		try {
			result = codedao.deleteCode(code.trim());
		} catch (Exception e) {
			
		model.addAttribute("reason", "이미 가입되어 있는 회원 입니다.");
		return -1;
		}
		System.out.println("삭제결과 : "+result);
	
		return result;
	}
}
