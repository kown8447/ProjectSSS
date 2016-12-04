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
import kr.or.initspring.dto.member.ClassBuildingDTO;
import kr.or.initspring.dto.member.LabBuildingDTO;
import kr.or.initspring.dto.member.OfiiceBuildingDTO;
import kr.or.initspring.dto.member.OpenedInfoDTO;
import kr.or.initspring.dto.member.ProfessorCodeRegDTO;
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

	// 학생코드등록
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public int insertStudent(StudentCodeRegDTO student){

		int result = 0;
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		System.out.println(student.toString());
		student.setCode_type(0);

		try {
			dao.insertStudentCode(student);
			dao.insertstudentmj(student);
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	// 교수코드 등록
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

	public List<CodeMgDTO> conditioncodelist(int code_type) {

		System.out.println("서비스에서의 코드 타입" + code_type);

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<CodeMgDTO> codelist = dao.typeofcodelist(code_type);

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
		System.out.println("액셀넣기 서비스");
		System.out.println(code.toString());
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

		System.out.println("학생 액셀일괄등록 서비스");

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
					
					
					if(code==null||code_name==null||StringBirth==null||department_code==null){
						System.out.println("null에러 걸림");
						System.out.println("Code" + code + "code_type" + code_type + "code_name" + code_name + "StringBirth" + StringBirth 
								+ "department_code" + department_code + "mj_type" + mj_type);
						result=false;
						break;
					}
					if(code.equals("")||code_name.equals("")||StringBirth.equals("")||department_code.equals("")){
						System.out.println("null에러 걸림");
						System.out.println("Code" + code + "code_type" + code_type + "code_name" + code_name + "StringBirth" + StringBirth 
								+ "department_code" + department_code + "mj_type" + mj_type);
						result=false;
						break;
					}
					java.util.Date date = null;

					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = (java.util.Date) formatter.parse(StringBirth.trim());
					} catch (Exception e) {
						result=false;
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
					result = true;
					
				}
			} catch (Exception e) {
				System.out.println("insertExcelList : " + e.getMessage());
				throw e;
			}
		}
		model.addAttribute("result", result);

	}

	// 교수 일괄등록
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void professorinsertExcelList(MultipartHttpServletRequest request, Model model) throws Exception {
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("professorexcel");

		System.out.println("액셀일괄등록 서비스");

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
						System.out.println("null Check 걸림");
						break;
					}
					if (code.equals("") || code_name.equals("") || StringBirth.equals("")
							|| department_code.equals("")) {
						System.out.println("문자열 길이 없음");
						break;
					}
					System.out.println("StringBirth = " + StringBirth);
					java.util.Date date = null;

					try {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = (java.util.Date) formatter.parse(StringBirth.trim());
					} catch (Exception e) {

					}

					java.sql.Date code_birth = new java.sql.Date(date.getTime());
					System.out.println("code_birth = " + code_birth);
					codeMg.setCode_type(code_type);
					codeMg.setCode(code);
					codeMg.setCode_name(code_name);
					codeMg.setCode_birth(code_birth);

					codedao.insertExcel(codeMg);

					System.out.println("코드 = " + code);
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

	public int insertBuilding(BuildingDTO building) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertBuilding(building);
		return result;
	}

	public int insertAdmin(CodeMgDTO admin) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertAdmin(admin);

		return result;
	}

	public List<BuildingDTO> buildingList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<BuildingDTO> buildingList = dao.buildingList();

		return buildingList;
	}

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

	// 빌딩 일괄등록
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
					System.out.println(building.toString());

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

	// 강의실 일괄등록
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public boolean classroomExcelList(MultipartHttpServletRequest request, Model model){

		System.out.println("파일일괄등록 서비스");
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("clexcel");

		ClassroomDTO classroom = new ClassroomDTO();
		boolean result = true;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				System.out.println("Last = " + last);
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

	// 사무실 일괄등록
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

	// 연구실 일괄등록
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
				result= false;
			}

		}
	
		model.addAttribute("result", result);

	}

	// 장학제도 일괄등록
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

	// 장학금 일괄등록
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
					int scholarship_rcordavg = Integer
							.parseInt(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					String StringPayday = row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

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
					scholarship.setScholarship_rcordavg(scholarship_rcordavg);
					scholarship.setScholarship_payday(scholarship_payday);

					System.out.println(scholarship.toString());

					codedao.insertScholarship(scholarship);
				}
			} catch (Exception e) {
				result = false;
			}

		}
		model.addAttribute("result", result);

	}

	// 단과대학 일괄등록
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

					String professor_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String office_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String college_name = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String college_description = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();

					college.setProfessor_code(professor_code);
					college.setOffice_code(office_code);
					college.setCollege_name(college_name);
					college.setCollege_description(college_description);

					codedao.insertCollege(college);
				}
			} catch (Exception e) {
				result = false;
			}

		}

		model.addAttribute("result", result);

	}

	// 학부 일괄등록
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
					String professor_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String office_code = row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_name = row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_description = row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int department_seat = Integer
							.parseInt(row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int graduation_credit = Integer
							.parseInt(row.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					int double_possible = Integer
							.parseInt(row.getCell(7, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					department.setCollege_code(college_code);
					department.setProfessor_code(professor_code);
					department.setOffice_code(office_code);
					department.setDepartment_name(department_name);
					department.setDepartment_description(department_description);
					department.setDepartment_seat(department_seat);
					department.setGraduation_credit(graduation_credit);
					department.setDouble_possible(double_possible);

					codedao.insertDepartment(department);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result = false;
			}

		}
		
		model.addAttribute("result", result);

	}

	// 전공 일괄등록
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void mjrecordExcelList(MultipartHttpServletRequest request, Model model) {

		System.out.println("전공 파일일괄등록 서비스");
		CodeMgDAO codedao = sqlsession.getMapper(CodeMgDAO.class);
		MultipartFile file = request.getFile("mjexcel");

		MjRecordDTO mjrecord = new MjRecordDTO();

		boolean result = false;

		if (file != null && file.getSize() > 0) {
			try {
				Workbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				int last = sheet.getLastRowNum();
				System.out.println("Last = " + last);
				for (int i = 1; i <= last; i++) {
					System.out.println("학부 여긴타늬");
					Row row = sheet.getRow(i);

					String student_code = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					String department_code = row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
					int mj_type = Integer.parseInt(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

					mjrecord.setStudent_code(student_code);
					mjrecord.setDepartment_code(department_code);
					mjrecord.setMj_type(mj_type);

					System.out.println(mjrecord.toString());

					codedao.insertMjRecord(mjrecord);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		result = true;
		model.addAttribute("result", result);

	}

	// 등록 일괄등록
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

	public BuildingDTO selectBuilding(String building_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		BuildingDTO building = dao.selectBuilding(building_code);

		return building;
	}

	public int updateBuilding(String building_code, String building_name, String building_addr) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		System.out.println(building_code + "/" + building_name + "/" + building_addr);
		int result = dao.updateBuilding(building_code, building_name, building_addr);

		return result;
	}

	public List<ClassroomDTO> classList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ClassroomDTO> classroomlist = dao.classlist();
		return classroomlist;
	}

	public ClassroomDTO selectClassroom(String classroom_code) {

		System.out.println("서비씅");
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ClassroomDTO classroom = dao.selectClassroom(classroom_code);
		System.out.println(classroom.toString());

		return classroom;
	}

	public int insertClassroom(ClassroomDTO classroom) throws Exception {

		System.out.println("서비스");
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertClassroom(classroom);

		return result;
	}

	public int updateClassroom(ClassroomDTO classroom) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateClassroom(classroom);

		return result;
	}

	public int insertOffice(OfficeDTO office) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertOffice(office);

		return result;
	}

	public List<OfficeDTO> officelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OfficeDTO> officelist = dao.officelist();

		return officelist;
	}

	public List<LaboratoryDTO> lablist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<LaboratoryDTO> lablist = dao.lablist();

		return lablist;
	}

	public int insertLab(LaboratoryDTO lab) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertLab(lab);

		return result;
	}

	public LaboratoryDTO labDetail(String lab_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		LaboratoryDTO lab = dao.labDetail(lab_code);

		return lab;
	}

	public List<ScSystemDTO> scSystemList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ScSystemDTO> scsystemlist = dao.scSystemList();

		return scsystemlist;
	}

	public ScSystemDTO detailScSystem(String sys_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ScSystemDTO dto = dao.detailScSystem(sys_code);

		return dto;
	}

	public int insertScSystem(ScSystemDTO scsystem) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertScSystem(scsystem);

		return result;
	}

	public List<ScholarshipDTO> scholarshipList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ScholarshipDTO> scholarshipList = dao.scholarshipList();

		return scholarshipList;
	}

	public ScholarshipDTO detailScholarship(String scholarship_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		ScholarshipDTO dto = dao.detailScholarship(scholarship_code);

		return dto;
	}

	public List<SemesterDTO> semesterList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<SemesterDTO> semesterList = dao.semesterList();

		return semesterList;
	}

	public int insertSemester(SemesterDTO semester) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertSemester(semester);

		return result;
	}

	public SemesterDTO detailSemester(String semester_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		SemesterDTO dto = dao.detailSemester(semester_code);

		return dto;
	}

	public List<Academic_CalendarDTO> academicCalendarList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<Academic_CalendarDTO> academiclist = dao.academicCalendarList();

		return academiclist;
	}

	public int insertAcademicCalendar(Academic_CalendarDTO academic) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		/*
		 * String office_code = dao.getMaxOfficeCode();
		 * 
		 * String[] number = office_code.split("_"); int conver_no =
		 * Integer.parseInt(number[1]); conver_no += 1;
		 * office.setOffice_code(number[0] + "_" + Integer.toString(conver_no));
		 */

		String calendar_code = dao.getMaxCalendarCode();

		String[] number = calendar_code.split("_");
		int conver_no = Integer.parseInt(number[1]);
		conver_no += 1;
		academic.setCalendar_code(number[0] + "_" + Integer.toString(conver_no));

		int result = dao.insertAcademic(academic);

		return result;
	}

	public Academic_CalendarDTO academicCalendarDetail(String calendar_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		Academic_CalendarDTO academic = dao.academicCalendarDetail(calendar_code);

		return academic;
	}

	public OfficeDTO selectOffice(String office_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		OfficeDTO office = dao.selectOffice(office_code);

		return office;
	}

	public int updateOffice(OfficeDTO office) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateOffice(office);

		return result;
	}

	public int updateLab(LaboratoryDTO lab) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateLab(lab);

		return result;
	}

	public int updateAcademic(Academic_CalendarDTO academic) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateAcademic(academic);

		return result;
	}

	public int updateScSytem(ScSystemDTO scsytem) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateScSystemUpdate(scsytem);

		return result;
	}

	public int insertScholarship(ScholarshipDTO scholarship) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertScholarship(scholarship);

		return result;
	}

	public int scholarshipUpdate(ScholarshipDTO scholarship) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateScholarship(scholarship);

		return result;
	}

	public int updateSemester(SemesterDTO semester) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateSemester(semester);

		return result;
	}

	public List<CollegeDTO> collegelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<CollegeDTO> collegelist = dao.colleagelist();

		return collegelist;
	}

	public int insertCollege(CollegeDTO college) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertCollege(college);

		return result;
	}

	public int updateCollege(CollegeDTO college) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateCollege(college);

		return result;
	}

	public CollegeDTO collegeDetail(String college_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		CollegeDTO college = dao.selectCollege(college_code);

		return college;
	}

	public List<DepartmentDTO> departmentList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<DepartmentDTO> departmentlist = dao.departmentList();

		return departmentlist;
	}

	public int insertDepartment(DepartmentDTO department) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);

		int result = dao.insertDepartment(department);

		return result;
	}

	public DepartmentDTO selectDepartment(String department_code) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		DepartmentDTO department = dao.selectDepartment(department_code);

		return department;
	}

	public int updateDepartment(DepartmentDTO department) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.updateDepartment(department);

		return result;
	}

	public List<MjRecordDTO> mjRecordList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<MjRecordDTO> mjrecordlist = dao.mjRecordList();

		return mjrecordlist;
	}

	public int insertMjRecord(MjRecordDTO mjrecord) {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		int result = dao.insertMjRecord(mjrecord);

		return result;
	}

	public List<OpenedDTO> openedList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OpenedDTO> opendedlist = dao.openedlist();

		return opendedlist;
	}

	public List<OpenedInfoDTO> openedInfoList() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OpenedInfoDTO> openedInfoList = dao.openedInfolist();

		return openedInfoList;
	}

	@Transactional(rollbackFor = { Exception.class, NullPointerException.class, SQLException.class,
			RuntimeException.class })
	public boolean initSemester() throws Exception {
		boolean result = true;
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		try {
			dao.deleteRejection();
			dao.deleteAskTime();
			dao.deleteEnrollment();
			dao.deleteLecture();
			dao.deleteOpened();
			dao.deleteOpRequest();
			dao.deleteReserve();
			dao.deleteTimetalbe();
		} catch (Exception e) {
			System.out.println("initSemester : " + e.getMessage());
			result = false;
			throw e;
		}

		return result;
	}

	public List<RegisterDTO> registerlist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<RegisterDTO> registerlist = dao.registerlist();

		return registerlist;
	}

	public int insertRegister(RegisterDTO register) {
		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		System.out.println(register.toString());
		int result = dao.insertRegister(register);

		return result;
	}

	public List<ClassBuildingDTO> showclasslist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<ClassBuildingDTO> classroomlsit = dao.classbuilding();

		return classroomlsit;

	}

	public List<LabBuildingDTO> showlablist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<LabBuildingDTO> lablist = dao.labbuilding();

		return lablist;
	}

	public List<OfiiceBuildingDTO> showofficelist() {

		CodeMgDAO dao = sqlsession.getMapper(CodeMgDAO.class);
		List<OfiiceBuildingDTO> officelist = dao.officebuilding();

		return officelist;
	}
}
