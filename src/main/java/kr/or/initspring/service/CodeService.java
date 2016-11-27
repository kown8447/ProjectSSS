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
	/*
	 * @method Name : codelist
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드리스트를 불러오기 위한 함수
	*/	
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public List<CodeMgDTO> codelist(){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		List<CodeMgDTO> codelist = codedao.codelist();
		
		return codelist;
	}
	/*
	 * @method Name : detailcode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드를 상세보기 하기 위한 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public CodeMgDTO detailcode(String code){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		CodeMgDTO codedto = codedao.codeDetail(code);
		
		return codedto;
	}
	/*
	 * @method Name : updateCode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드를 수정하기 위한 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateCode(String code, int code_type, String code_name, Date code_birth){
		
		int result = 0;
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.editCode(code, code_type, code_name, code_birth);
		
		return result;
	}
	/*
	 * @method Name : insertExcel
	 * @Author : 성홍모
	 * @description
	 * 관리자가 액셀을 사용하여 코드를 삽입하기 위한 매소드
	*/
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertExcel(CodeMgDTO code){
		
		int result = 0;
		System.out.println("액셀넣기 서비스");
		System.out.println(code.toString());
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.insertExcel(code);
		
		return result;
	}
	/*
	 * @method Name : insertExcelList
	 * @Author : 성홍모
	 * @description
	 * 관리자가 액셀을 사용하여 코드를 일괄등록하기 위한 매소드
	*/
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
				for(int i = 1; i<= last; i++){
				Row row = sheet.getRow(i);

				int code_type = Integer.parseInt(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue()); 
				String code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String code_name = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				String StringBirth = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
				java.util.Date date =null;
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					date = (java.util.Date)formatter.parse(StringBirth.trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				java.sql.Date code_birth = new java.sql.Date(date.getTime());				
				codeMg.setCode_type(code_type);
				codeMg.setCode(code);
				codeMg.setCode_name(code_name);
				codeMg.setCode_birth(code_birth);
				codedao.insertExcel(codeMg);
				}
			}catch(Exception e){
		
			}
		}
		result = true;
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : deleteCode
	 * @Author : 성홍모
	 * @description
	 * 관리자가 코드를 삭제하기 위한 매소드
	*/
	public int deleteCode(String code, Model model){
		
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		int result=0;
		try {
			result = codedao.deleteCode(code.trim());
		} catch (Exception e){
		model.addAttribute("reason", "이미 가입되어 있는 회원 입니다.");
		return -1;
		}
		return result;
	}
}
