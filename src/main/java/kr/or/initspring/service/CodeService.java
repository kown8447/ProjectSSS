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

import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;

import kr.or.initspring.dao.CodeMgDAO;
import kr.or.initspring.dto.collegeRegister.StudentRegisterDTO;
import kr.or.initspring.dto.commons.Academic_CalendarDTO;
import kr.or.initspring.dto.commons.AdminDTO;
import kr.or.initspring.dto.commons.BuildingDTO;
import kr.or.initspring.dto.commons.ClassroomDTO;
import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.LaboratoryDTO;
import kr.or.initspring.dto.commons.MjRecordDTO;
import kr.or.initspring.dto.commons.OfficeDTO;
import kr.or.initspring.dto.commons.OpenedDTO;
import kr.or.initspring.dto.commons.PfMajorDTO;
import kr.or.initspring.dto.commons.RegisterDTO;
import kr.or.initspring.dto.commons.ScSystemDTO;
import kr.or.initspring.dto.commons.ScholarshipDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
import kr.or.initspring.dto.commons.SmStateDTO;
import kr.or.initspring.dto.commons.StStateDTO;
import kr.or.initspring.dto.member.ClassBuildingDTO;
import kr.or.initspring.dto.member.CollegeInfoDTO;
import kr.or.initspring.dto.member.DepartmentInfoDTO;
import kr.or.initspring.dto.member.DepartmentLeaderDTO;
import kr.or.initspring.dto.member.LabBuildingDTO;
import kr.or.initspring.dto.member.OfiiceBuildingDTO;
import kr.or.initspring.dto.member.OpenedInfoDTO;
import kr.or.initspring.dto.member.ProfessorCodeRegDTO;
import kr.or.initspring.dto.member.ScholarshipInfoDTO;
import kr.or.initspring.dto.member.StudentCodeRegDTO;

@Service
public class CodeService {

	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : insertMember
	 * 
	 * @Author : 권기엽
	 * 
	 * @description 관리자가 코드를 삽입하는 함수 하나의 Query가 실패할 시 모든 Query는 Rollback 됨
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertCode(CodeMgDTO code) {

		int insertResult = 0;

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);

		int result = codedao.insertCode(code);

		return result;
	}
	/*
	 * @method Name : insertStudent
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학생코드, 전공, 현재학적상태를 삽입하는 함수 하나의 Query가 실패할 시 모든 Query는 Rollback 됨
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertStudent(StudentCodeRegDTO student) {

		int result = 0;
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		student.setCode_type(0);

		try {
			dao.insertStudentCode(student);
			dao.insertstudentmj(student);
			dao.insertIntoStState(student.getCode());
			result = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = 0;
		}
		return result;
	}
	/*
	 * @method Name : insertProfessor
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 교수코드, 전공을 삽입하는 함수 하나의 Query가 실패할 시 모든 Query는 Rollback 됨
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertProfessor(ProfessorCodeRegDTO professor) {

		int result = 0;
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		professor.setCode_type(1);

		try {

			dao.insertProfessorCode(professor);
			dao.insertpfmajor(professor);
			result = 1;

		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/*
	 * @method Name : codelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드리스트를 불러오기 위한 함수
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public List<CodeMgDTO> codelist() {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		List<CodeMgDTO> codelist = codedao.codelist();

		return codelist;
	}
	/*
	 * @method Name : conditioncodelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 code_type으로 코드리스트를 불러오기 위한 함수(code_type이 0 = 학생, 1 = 교수, 2 = 관리자)
	 */
	public List<CodeMgDTO> conditioncodelist(int code_type, String keyword, String searchType) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<CodeMgDTO> codelist = new ArrayList<CodeMgDTO>();
		if (keyword == null || searchType == null) {
			codelist = dao.typeofcodelist(code_type);
		} else {
			codelist = dao.typeofcodelistSearch(code_type, keyword, searchType);
		}
		return codelist;
	}

	/*
	 * @method Name : detailcode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드를 상세보기 하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public CodeMgDTO detailcode(String code) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		CodeMgDTO codedto = codedao.codeDetail(code);

		return codedto;
	}

	/*
	 * @method Name : updateCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드를 수정하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int updateCode(String code, int code_type, String code_name, Date code_birth) {

		int result = 0;
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.editCode(code, code_type, code_name, code_birth);

		return result;
	}

	/*
	 * @method Name : insertExcel
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀을 사용하여 코드를 삽입하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertExcel(CodeMgDTO code) throws Exception {

		int result = 0;
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		result = codedao.insertExcel(code);

		return result;
	}

	/*
	 * @method Name : insertExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀을 사용하여 코드를 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void insertExcelList(MultipartHttpServletRequest request, Model model) throws Exception {
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("excel");

		CodeMgDTO codeMg = new CodeMgDTO();
		MjRecordDTO mj = new MjRecordDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				for (int i = 1; i <= last; i++) {
					Row row = sheet.getRow(i);

					String code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int code_type = 0;
					String code_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String StringBirth = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_code = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int mj_type = Integer.parseInt(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					if (code == null || code_name == null || StringBirth == null || department_code == null) {
						result = false;
						break;
					}
					if (code.equals("") || code_name.equals("") || StringBirth.equals("")
							|| department_code.equals("")) {
						result = false;
						break;
					}
					java.util.Date date = null;

					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = (java.util.Date) formatter.parse(StringBirth.trim());
					} catch (Exception e) {
						result = false;
					}

					java.sql.Date code_birth = new java.sql.Date(date.getTime());
					codeMg.setCode_type(code_type);
					codeMg.setCode(code);
					codeMg.setCode_name(code_name);
					codeMg.setCode_birth(code_birth);

					codedao.insertExcel(codeMg);

					mj.setStudent_code(code);
					mj.setMj_type(mj_type);
					mj.setDepartment_code(department_code);

					codedao.insertMjRecord(mj);

					codedao.insertIntoStState(code);
					result = true;

				}
			} catch (Exception e) {
				System.out.println("insertExcelList : " + e.getMessage());
				throw e;
			}
		}
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : professorinsertExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀을 사용하여 코드를 교수를 일괄 등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void professorinsertExcelList(MultipartHttpServletRequest request, Model model) throws Exception {
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("professorexcel");

		CodeMgDTO codeMg = new CodeMgDTO();
		PfMajorDTO mj = new PfMajorDTO();

		boolean result = false;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				for (int i = 1; i <= last; i++) {
					Row row = sheet.getRow(i);

					String code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int code_type = 1;
					String code_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String StringBirth = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_code = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					if (code == null || code_name == null || StringBirth == null || department_code == null) {
						break;
					}
					if (code.equals("") || code_name.equals("") || StringBirth.equals("")
							|| department_code.equals("")) {
						break;
					}
					java.util.Date date = null;

					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = (java.util.Date) formatter.parse(StringBirth.trim());
					} catch (Exception e) {

					}

					java.sql.Date code_birth = new java.sql.Date(date.getTime());
					codeMg.setCode_type(code_type);
					codeMg.setCode(code);
					codeMg.setCode_name(code_name);
					codeMg.setCode_birth(code_birth);

					codedao.insertExcel(codeMg);

					mj.setProfessor_code(code);
					mj.setDepartment_code(department_code);

					codedao.insertProfessorMajor(mj);
					result = true;
				}
			} catch (Exception e) {
				System.out.println("insertExcelList : " + e.getMessage());
				throw e;
			}
		}

		model.addAttribute("result", result);

	}

	/*
	 * @method Name : deleteCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드를 삭제하기 위한 매소드
	 */
	public int deleteCode(String code, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		int result = 0;
		try {
			result = codedao.deleteCode(code.trim());
		} catch (Exception e) {
			model.addAttribute("reason", "이미 가입되어 있는 회원 입니다.");
			return -1;
		}
		return result;
	}
	/*
	 * @method Name : insertBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물을 수기 등록하기 위한 매소드
	 */
	public int insertBuilding(BuildingDTO building) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertBuilding(building);
		return result;
	}

	public int insertAdmin(CodeMgDTO admin) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		admin.setCode_type(2);
		int result = dao.insertAdmin(admin);

		return result;
	}
	/*
	 * @method Name : buildingList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 건물리스트를 출력해주기 위한 매소드
	 */
	public List<BuildingDTO> buildingList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<BuildingDTO> buildingList = dao.buildingList();

		return buildingList;
	}
	/*
	 * @method Name : deleteBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 건물을 삭제하기 위한 매소드
	 */
	public int deleteBuilding(String building_code, Model model) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = 0;
		try {
			result = dao.deleteBuilding(building_code);
		} catch (Exception e) {
			model.addAttribute("reason", "이미 가입되어 있는 회원 입니다.");
			return -1;
		}

		return result;
	}
	/*
	 * @method Name : buildingExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 건물을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean buildingExcelList(MultipartHttpServletRequest request, Model model) {
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("bdexcel");

		BuildingDTO building = new BuildingDTO();
		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				for (int i = 1; i <= last; i++) {
					Row row = sheet.getRow(i);

					String building_name = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String building_addr = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					building.setBuilding_name(building_name);
					building.setBuilding_addr(building_addr);

					codedao.insertBuilding(building);

					result = true;
				}
			} catch (Exception e) {
				result = false;
			}

		}

		model.addAttribute("result", result);
		return result;
	}
	/*
	 * @method Name : classroomExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 강의실을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean classroomExcelList(MultipartHttpServletRequest request, Model model) {
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("clexcel");

		ClassroomDTO classroom = new ClassroomDTO();
		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String building_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String classroom_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String seat = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String classroom_type = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					classroom.setBuilding_code(building_code);
					classroom.setClassroom_name(classroom_name);
					classroom.setSeat(Integer.parseInt(seat));
					classroom.setClassroom_type(Integer.parseInt(classroom_type));

					codedao.insertClassroom(classroom);

				}
			} catch (Exception e) {
				model.addAttribute("error", "등록에 실패하였습니다.");
				result = false;
			}

		}
		model.addAttribute("result", result);
		return result;

	}
	/*
	 * @method Name : officeExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 사무실을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void officeExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("ofexcel");

		OfficeDTO office = new OfficeDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String building_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String office_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String office_phone = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					office.setBuilding_code(building_code);
					office.setOffice_phone(office_phone);
					office.setOffice_name(office_name);

					codedao.insertOffice(office);
				}
			} catch (Exception e) {
				result = false;
			}

		}

		model.addAttribute("result", result);

	}
	/*
	 * @method Name : laboratoryExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 연구실을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void laboratoryExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("lbexcel");

		LaboratoryDTO lab = new LaboratoryDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String building_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String lab_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String lab_phone = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					lab.setBuilding_code(building_code);
					lab.setLab_phone(lab_phone);
					lab.setLab_name(lab_name);

					codedao.insertLab(lab);
				}
			} catch (Exception e) {
				result = false;
			}

		}

		model.addAttribute("result", result);

	}
	/*
	 * @method Name : scSystemExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 장학제도를 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void scSystemExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("scsexcel");

		ScSystemDTO scsystem = new ScSystemDTO();

		boolean result = false;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String scholaship_name = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String scholaship_standard = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String scholaship_member = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String scholaship_amount = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String scholaship_note = row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					scsystem.setScholaship_name(scholaship_name);
					scsystem.setScholaship_standard(scholaship_standard);
					scsystem.setScholaship_member(scholaship_member);
					scsystem.setScholaship_amount(scholaship_amount);
					scsystem.setScholaship_note(scholaship_note);

					codedao.insertScSystem(scsystem);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		result = true;
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : scholarshipExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 장학을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void scholarshipExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("sclexcel");

		ScSystemDTO scsystem = new ScSystemDTO();
		ScholarshipDTO scholarship = new ScholarshipDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String student_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String sys_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String semester_code = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String StringPayday = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					java.util.Date date = null;

					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = (java.util.Date) formatter.parse(StringPayday.trim());
					} catch (Exception e) {
						e.printStackTrace();
					}

					java.sql.Date scholarship_payday = new java.sql.Date(date.getTime());

					scholarship.setStudent_code(student_code);
					scholarship.setSys_code(sys_code);
					scholarship.setSemester_code(semester_code);
					scholarship.setScholarship_payday(scholarship_payday);


					codedao.insertScholarship(scholarship);
				}
			} catch (Exception e) {
				result = false;
			}

		}
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : collegeExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 단과대학을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void collegeExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("colexcel");

		CollegeDTO college = new CollegeDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String office_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String college_name = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String college_description = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					college.setOffice_code(office_code);
					college.setCollege_name(college_name);
					college.setCollege_description(college_description);

					codedao.insertCollege(college);
					codedao.updateofficepossible(office_code);
				}
			} catch (Exception e) {
				result = false;
			}

		}

		model.addAttribute("result", result);

	}
	/*
	 * @method Name : departmentExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 학과를 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void departmentExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("depexcel");

		DepartmentDTO department = new DepartmentDTO();

		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String college_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String office_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_name = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_description = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int department_seat = Integer
							.parseInt(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int graduation_credit = Integer
							.parseInt(row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int double_possible = Integer
							.parseInt(row.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					department.setCollege_code(college_code);
					department.setOffice_code(office_code);
					department.setDepartment_name(department_name);
					department.setDepartment_description(department_description);
					department.setDepartment_seat(department_seat);
					department.setGraduation_credit(graduation_credit);
					department.setDouble_possible(double_possible);

					codedao.insertDepartment(department);
					codedao.updateofficepossible(office_code);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result = false;
			}

		}

		model.addAttribute("result", result);

	}
	/*
	 * @method Name : mjrecordExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 전공을 일괄등록하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void mjrecordExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("mjexcel");

		MjRecordDTO mjrecord = new MjRecordDTO();

		boolean result = false;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				for (int i = 1; i <= last; i++) {
					Row row = sheet.getRow(i);

					String student_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int mj_type = Integer.parseInt(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					mjrecord.setStudent_code(student_code);
					mjrecord.setDepartment_code(department_code);
					mjrecord.setMj_type(mj_type);

					codedao.insertMjRecord(mjrecord);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		result = true;
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : registerExcelList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 액셀파일을 사용하여 학생을 등록 일괄처리 하기 위한 매소드
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void registerExcelList(MultipartHttpServletRequest request, Model model) {

		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("regexcel");

		RegisterDTO register = new RegisterDTO();

		boolean result = false;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();

				for (int i = 1; i <= last; i++) {

					Row row = sheet.getRow(i);

					String student_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String semester_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int register_type = Integer.parseInt(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int tuition = Integer.parseInt(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int register_state = Integer
							.parseInt(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					register.setStudent_code(student_code);
					register.setSemester_code(semester_code);
					register.setRegister_type(register_type);
					register.setTuition(tuition);
					register.setRegister_state(register_state);

					codedao.insertRegister(register);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		result = true;
		model.addAttribute("result", result);

	}
	/*
	 * @method Name : selectBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물 코드를 통하여 건물을 선택하는 매소드
	 */
	public BuildingDTO selectBuilding(String building_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		BuildingDTO building = dao.selectBuilding(building_code);

		return building;
	}
	/*
	 * @method Name : updateBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 building_code, building_name, building_addr를 파라미터로 건물정보를 수정하는 매소드
	 */
	public int updateBuilding(String building_code, String building_name, String building_addr) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateBuilding(building_code, building_name, building_addr);

		return result;
	}
	/*
	 * @method Name : classList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 강의실 목록을 출력하기 위한 매소드 
	 */
	public List<ClassroomDTO> classList() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ClassroomDTO> classroomlist = dao.classlist();
		return classroomlist;
	}
	/*
	 * @method Name : selectClassroom
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 강의실 코드를 이용해서 강의실을 선택하는 매소드
	 */
	public ClassroomDTO selectClassroom(String classroom_code) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ClassroomDTO classroom = dao.selectClassroom(classroom_code);

		return classroom;
	}
	/*
	 * @method Name : insertClassroom
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 강의실을 등록하는 매소드 
	 */
	public int insertClassroom(ClassroomDTO classroom) throws Exception {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertClassroom(classroom);

		return result;
	}
	/*
	 * @method Name : updateClassroom
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 강의실을 수정하는 매소드 
	 */
	public int updateClassroom(ClassroomDTO classroom) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateClassroom(classroom);

		return result;
	}
	/*
	 * @method Name : insertOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사무실을 등록하는 매소드 
	 */
	public int insertOffice(OfficeDTO office) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertOffice(office);

		return result;
	}
	/*
	 * @method Name : officelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사무실 목록을 출력하기 위한 매소드 
	 */
	public List<OfficeDTO> officelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OfficeDTO> officelist = dao.officelist();

		return officelist;
	}
	/*
	 * @method Name : lablist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 연구실 목록을 출력하기 위한 매소드 
	 */
	public List<LaboratoryDTO> lablist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<LaboratoryDTO> lablist = dao.lablist();

		return lablist;
	}
	/*
	 * @method Name : insertLab
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 연구실 등록하기 위한 매소드 
	 */
	public int insertLab(LaboratoryDTO lab) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertLab(lab);

		return result;
	}
	/*
	 * @method Name : labDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 연구실 상세보기를 위한 매소드 
	 */
	public LaboratoryDTO labDetail(String lab_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		LaboratoryDTO lab = dao.labDetail(lab_code);

		return lab;
	}
	/*
	 * @method Name : scSystemList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학제도 목록을 출력하기 위한 매소드
	 */
	public List<ScSystemDTO> scSystemList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ScSystemDTO> scsystemlist = dao.scSystemList();

		return scsystemlist;
	}
	/*
	 * @method Name : detailScSystem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학제도 상세보기 위한 매소드
	 */
	public ScSystemDTO detailScSystem(String sys_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ScSystemDTO dto = dao.detailScSystem(sys_code);

		return dto;
	}
	/*
	 * @method Name : insertScSystem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학제도 등록을 위한 매소드
	 */
	public int insertScSystem(ScSystemDTO scsystem) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertScSystem(scsystem);

		return result;
	}
	/*
	 * @method Name : scholarshipList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학 목록을 위한 매소드
	 */
	public List<ScholarshipDTO> scholarshipList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ScholarshipDTO> scholarshipList = dao.scholarshipList();

		return scholarshipList;
	}
	/*
	 * @method Name : detailScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학 상세보기를 위한 매소드
	 */
	public ScholarshipDTO detailScholarship(String scholarship_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ScholarshipDTO dto = dao.detailScholarship(scholarship_code);

		return dto;
	}
	/*
	 * @method Name : semesterList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학기 목록을 출력하기 위한 매소드 
	 */
	public List<SemesterDTO> semesterList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<SemesterDTO> semesterList = dao.semesterList();

		return semesterList;
	}
	/*
	 * @method Name : insertSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학기 등록을 위한 매소드 
	 */
	public int insertSemester(SemesterDTO semester) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertSemester(semester);

		return result;
	}
	/*
	 * @method Name : detailSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학기 상세보기를 위한 매소드 
	 */
	public SemesterDTO detailSemester(String semester_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		SemesterDTO dto = dao.detailSemester(semester_code);

		return dto;
	}
	/*
	 * @method Name : academicCalendarList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정을 출력해주기 위한 매소드 
	 */
	public List<Academic_CalendarDTO> academicCalendarList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<Academic_CalendarDTO> academiclist = dao.academicCalendarList();

		return academiclist;
	}
	/*
	 * @method Name : insertAcademicCalendar
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정을 등록해주기 위한 매소드 
	 */
	public int insertAcademicCalendar(Academic_CalendarDTO academic) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		String calendar_code = dao.getMaxCalendarCode();

		String[] number = calendar_code.split("_");
		int conver_no = Integer.parseInt(number[1]);
		conver_no += 1;
		academic.setCalendar_code(number[0] + "_" + Integer.toString(conver_no));

		int result = dao.insertAcademic(academic);

		return result;
	}
	/*
	 * @method Name : academicCalendarDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정을 상세보기를 위한 매소드 
	 */
	public Academic_CalendarDTO academicCalendarDetail(String calendar_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		Academic_CalendarDTO academic = dao.academicCalendarDetail(calendar_code);

		return academic;
	}
	/*
	 * @method Name : selectOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 office_code를 파라미터로 사무실을 선택하기 위한 매소드 
	 */
	public OfficeDTO selectOffice(String office_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		OfficeDTO office = dao.selectOffice(office_code);

		return office;
	}
	/*
	 * @method Name : updateOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실을 수정하기 위한 매소드 
	 */
	public int updateOffice(OfficeDTO office) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateOffice(office);

		return result;
	}
	/*
	 * @method Name : updateLab
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 연구실을 수정하기 위한 매소드 
	 */
	public int updateLab(LaboratoryDTO lab) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateLab(lab);

		return result;
	}
	/*
	 * @method Name : updateAcademic
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학사일정을 수정하기 위한 매소드 
	 */
	public int updateAcademic(Academic_CalendarDTO academic) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateAcademic(academic);

		return result;
	}
	/*
	 * @method Name : updateScSytem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도를 수정하기 위한 매소드
	 */
	public int updateScSytem(ScSystemDTO scsytem) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateScSystemUpdate(scsytem);

		return result;
	}
	/*
	 * @method Name : insertScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학을 등록하기 위한 매소드 
	 */
	public int insertScholarship(ScholarshipDTO scholarship) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertScholarship(scholarship);

		return result;
	}
	/*
	 * @method Name : scholarshipUpdate
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학을 수정하기 위한 매소드 
	 */
	public int scholarshipUpdate(ScholarshipDTO scholarship) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateScholarship(scholarship);

		return result;
	}
	/*
	 * @method Name : updateSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학기를 수정하기 위한 매소드 
	 */
	public int updateSemester(SemesterDTO semester) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateSemester(semester);

		return result;
	}
	/*
	 * @method Name : collegelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학 리스트를 출력하기 위한 매소드 
	 */
	public List<CollegeDTO> collegelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<CollegeDTO> collegelist = dao.colleagelist();

		return collegelist;
	}
	/*
	 * @method Name : insertCollege
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학을 등록하기 위한 매소드 (등록한 강의실은 사용불가로 바뀐다)
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public synchronized int insertCollege(CollegeDTO college) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		String office_code = college.getOffice_code();
		int result = 0;

		try {
			int state = dao.getOfficeState(office_code);
			if (state == 0) {
				dao.insertCollege(college);
				result = dao.updateofficepossible(office_code);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

		return result;
	}
	/*
	 * @method Name : updateCollege
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학을 등록하기 위한 매소드 (등록한 강의실은 사용불가로 바뀐다, 그리고 이전에 등록 되었던 강의실은 사용가능으로 바뀐다.)
	 */
	@Transactional(rollbackFor = { Exception.class, NullPointerException.class, SQLException.class,
			RuntimeException.class })
	public int updateCollege(CollegeDTO college, String before_office_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		dao.officepossibleChange(before_office_code);
		dao.updateofficepossible(college.getOffice_code());
		int result = dao.updateCollege(college);

		return result;
	}
	/*
	 * @method Name : collegeDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학 상세보기를 위한 매소드
	 */
	public CollegeDTO collegeDetail(String college_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		CollegeDTO college = dao.selectCollege(college_code);

		return college;
	}
	/*
	 * @method Name : departmentList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학과 목록을 출력하는 매소드 
	 */
	public List<DepartmentDTO> departmentList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<DepartmentDTO> departmentlist = dao.departmentList();

		return departmentlist;
	}
	/*
	 * @method Name : insertDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학과를 등록하게 해주는 매소드 (등록한 사무실은 사용가능에서 불가능으로 바꿔준다)
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public synchronized int insertDepartment(DepartmentDTO department) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		String office_code = department.getOffice_code();
		int result = 0;

		try {
			int state = dao.getOfficeState(office_code);
			if (state == 0) {
				dao.insertDepartment(department);
				result = dao.updateofficepossible(office_code);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

		return result;

	}
	/*
	 * @method Name : selectDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과를 department_code를 파라미터로 학과를 선택하게 해주는 매소드
	 */
	public DepartmentDTO selectDepartment(String department_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		DepartmentDTO department = dao.selectDepartment(department_code);

		return department;
	}
	/*
	 * @method Name : updateDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과를 수정해주는 매소드(before_office_code를 파라미터로 사무실의 사용여부를 수정해준다)
	 */
	@Transactional(rollbackFor = { Exception.class, NullPointerException.class, SQLException.class,
			RuntimeException.class })
	public int updateDepartment(DepartmentDTO department, String before_office_code) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		
		try {
			if(department.getOffice_code() != before_office_code){
				dao.officepossibleChange(before_office_code);
				dao.updateofficepossible(department.getOffice_code());
			}
		} catch (Exception e) {
			
		}
		int result = dao.updateDepartment(department);

		return result;
	}
	/*
	 * @method Name : mjRecordList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 전공, 부전공 등록 목록을 출력해주는 매소드
	 */
	public List<MjRecordDTO> mjRecordList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<MjRecordDTO> mjrecordlist = dao.mjRecordList();

		return mjrecordlist;
	}
	/*
	 * @method Name : insertMjRecord
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 전공, 부전공을 등록해주는 매소드
	 */
	public int insertMjRecord(MjRecordDTO mjrecord) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertMjRecord(mjrecord);

		return result;
	}
	/*
	 * @method Name : openedList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 개설된 과목 목록을 출력해주는 매소드 
	 */
	public List<OpenedDTO> openedList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OpenedDTO> opendedlist = dao.openedlist();

		return opendedlist;
	}
	/*
	 * @method Name : openedInfoList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 개설된 과목 목록을 "OpenedInfoDTO"커스텀 DTO를 사용하여 출력해주는 매소드 
	 */
	public List<OpenedInfoDTO> openedInfoList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OpenedInfoDTO> openedInfoList = dao.openedInfolist();

		return openedInfoList;
	}

	/*
	 * @method Name : initSemester
	 * 
	 * @Author : 권기엽
	 * 
	 * @description 학기 초기화 버튼 클릭 시, 관련 테이블 삭제 및 ST_STATE TABLE 상태 업데이트 및
	 * SM_STATE TABLE INSERT 실행
	 */
	@Transactional(rollbackFor = { Exception.class, NullPointerException.class, SQLException.class,
			RuntimeException.class })
	public boolean initSemester() throws Exception {
		boolean result = true;
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<RegisterDTO> registerdto = null;
		List<StStateDTO> ststatedto = new ArrayList<StStateDTO>();

		try {
			registerdto = dao.getRegister();
			for (RegisterDTO dto : registerdto) {
				ststatedto.add(dao.getStState(dto.getStudent_code()));
			}

			for (StStateDTO dto : ststatedto) {
				SmStateDTO smstatedto = new SmStateDTO();
				int getCredit = 0;
				int currentcredit = 0;
				int totlaCredit = 0;

				try {
					getCredit = dao.getGetCreditBystudentCode(dto.getStudent_code());
					currentcredit = dao.getCurrentCreditByStudentCode(dto.getStudent_code());
				} catch (NullPointerException e2) {
					throw e2;
				}

				smstatedto.setGet_credit(getCredit);
				smstatedto.setRequest_credit(currentcredit);
				smstatedto.setStudent_code(dto.getStudent_code());
				smstatedto.setSemester_code(dao.getMaxSemesterCode());
				smstatedto.setStudent_grade(dto.getGrade());
				smstatedto.setStudent_semester(dto.getPersonal_semester());

				try {
					dao.insertIntoSmstate(smstatedto);
				} catch (Exception e3) {
					System.out.println("e3 : " + e3.getMessage());
					throw e3;
				}

				if (dto.getPersonal_semester() == 2) {
					dto.setPersonal_semester(1);
					dto.setGrade(dto.getGrade() + 1);
				} else {
					dto.setPersonal_semester(2);
				}

				try {
					totlaCredit = dao.getTotalCreditByStudentCode(dto.getStudent_code());
					dto.setTotal_credit(totlaCredit);
				} catch (NullPointerException e4) {
					throw e4;
				}
				try {
					dao.updateStstate(dto);
				} catch (Exception e5) {
					System.out.println("dao.updateStstate(dto) : " + e5.getMessage());
					throw e5;
				}

			}

			dao.deletePlan();
			dao.deleteTimetalbe();
			dao.deleteReserve();
			dao.deleteEnrollment();
			dao.deleteRejection();
			dao.deleteAskTime();
			dao.deleteLecture();
			dao.deleteOpened();
			dao.deleteOpRequest();

		} catch (Exception e) {
			System.out.println("initSemester : " + e.getMessage());
			result = false;
			throw e;
		}

		return result;
	}
	/*
	 * @method Name : registerlist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학생의 등록 히스토리를 출력하게 해주는 매소드 
	 */
	public List<RegisterDTO> registerlist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<RegisterDTO> registerlist = dao.registerlist();

		return registerlist;
	}
	/*
	 * @method Name : insertRegister
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학생을 등록하게 해주는 매소드 
	 */
	public int insertRegister(RegisterDTO register) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertRegister(register);

		return result;
	}
	/*
	 * @method Name : showclasslist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 강의실 목록을 "ClassBuildingDTO" 커스텀 DTO로 출력해주는 매소드 
	 */
	public List<ClassBuildingDTO> showclasslist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ClassBuildingDTO> classroomlsit = dao.classbuilding();

		return classroomlsit;

	}
	/*
	 * @method Name : showclasslist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 건물 목록을 "ClassBuildingDTO" 커스텀 DTO로 출력해주는 매소드 
	 */
	public List<LabBuildingDTO> showlablist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<LabBuildingDTO> lablist = dao.labbuilding();

		return lablist;
	}
	/*
	 * @method Name : showofficelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사무실 목록을 "OfiiceBuildingDTO" 커스텀 DTO로 출력해주는 매소드 
	 */
	public List<OfiiceBuildingDTO> showofficelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OfiiceBuildingDTO> officelist = dao.officebuilding();

		return officelist;
	}
	/*
	 * @method Name : getProfessorList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 교수 목록을 "ProfessorCodeRegDTO" 커스텀 DTO로 출력해주는 매소드 
	 */
	public List<ProfessorCodeRegDTO> getProfessorList(String department_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ProfessorCodeRegDTO> pf_list = dao.getProfessorListByDepartmentCode(department_code);
		return pf_list;
	}
	/*
	 * @method Name : setDepartmentLeader
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 교수중에서 학과장을 등록하고 이전 학과장을 RESET해주는 매소드 
	 */
	@Transactional(rollbackFor = { Exception.class, NullPointerException.class, SQLException.class,
			RuntimeException.class })
	public boolean setDepartmentLeader(DepartmentLeaderDTO leader) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = 0;
		int result2 = 0;
		try {
			result = dao.departmentLeaderReset(leader.getDepartment_code());
			result2 = dao.departmentLeaderSet(leader);
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		if (result == 0 || result2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	/*
	 * @method Name : getDepartmentLeaderList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과장 목록을 출력해주는 매소드 
	 */
	public List<DepartmentLeaderDTO> getDepartmentLeaderList() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<DepartmentLeaderDTO> list = dao.getDepartmentLeaderList();
		return list;
	}
	/*
	 * @method Name : doubleDepartmentlist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 부전공 목록을 출력해주는 매소드 
	 */
	public List<DepartmentDTO> doubleDepartmentlist() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<DepartmentDTO> doublistdepartmentlist = dao.doubleDepartment();
		return doublistdepartmentlist;

	}
	/*
	 * @method Name : departmentInfoList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학부 목록을 출력해주기 위한 매소드
	 */
	public List<DepartmentInfoDTO> departmentInfoList() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<DepartmentInfoDTO> departmentinfolist = dao.departmentinfolist();

		return departmentinfolist;
	}
	/*
	 * @method Name : scholarshipInfoList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학정보 목록을 출력해주기 위한 매소드
	 */
	public List<ScholarshipInfoDTO> scholarshipInfoList() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ScholarshipInfoDTO> scholarshipinfolist = dao.scholarshipinfolist();

		return scholarshipinfolist;
	}
	/*
	 * @method Name : collegeInfoList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 단대 정보 목록을 출력해주기 위한 매소드
	 */
	public List<CollegeInfoDTO> collegeInfoList() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<CollegeInfoDTO> collegeinfolist = dao.collegeinfolist();

		return collegeinfolist;
	}
	/*
	 * @method Name : possibleOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사용가능 강의실 목록을 출력해주기 위한 매소드
	 */
	public List<OfficeDTO> possibleOffice() {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OfficeDTO> officelist = dao.possibleOffice();

		return officelist;
	}
	/*
	 * @method Name : classroomBuildinSelect
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "buildingCode"를 파라미터로 "ClassBuildingDTO" 커스텀 DTO를 사용하여 강의실 목록을 출력해주는 매소드 
	 */
	public List<ClassBuildingDTO> classroomBuildinSelect(String buildingCode) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ClassBuildingDTO> list = null;
		if (buildingCode.equals("default")) {
			list = dao.classbuilding();
		} else {
			list = dao.classroomBuildinSelect(buildingCode);
		}
		return list;
	}
	/*
	 * @method Name : checkStudentCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "student_code"를 파라미터로 학생코드의 유효성을 판단해주는 매소드 
	 */
	public boolean checkStudentCode(String student_code){
		boolean result = false;
		
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int count = dao.checkStudentCode(student_code);
		
		if(count > 0)	result = true;
		
		return result;
	}

}
