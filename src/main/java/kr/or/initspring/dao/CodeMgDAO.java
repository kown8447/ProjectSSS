package kr.or.initspring.dao;

import java.sql.Date;
import java.util.List;

import kr.or.initspring.dto.commons.Academic_CalendarDTO;
import kr.or.initspring.dto.commons.BuildingDTO;
import kr.or.initspring.dto.commons.ClassroomDTO;
import kr.or.initspring.dto.commons.CodeMgDTO;
import kr.or.initspring.dto.commons.CollegeDTO;
import kr.or.initspring.dto.commons.DepartmentDTO;
import kr.or.initspring.dto.commons.LaboratoryDTO;
import kr.or.initspring.dto.commons.MjRecordDTO;
import kr.or.initspring.dto.commons.OfficeDTO;
import kr.or.initspring.dto.commons.OpenedDTO;
import kr.or.initspring.dto.commons.RegisterDTO;
import kr.or.initspring.dto.commons.ScSystemDTO;
import kr.or.initspring.dto.commons.ScholarshipDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
import kr.or.initspring.dto.commons.StudentDTO;
import kr.or.initspring.dto.member.ClassBuildingDTO;
import kr.or.initspring.dto.member.LabBuildingDTO;
import kr.or.initspring.dto.member.OfiiceBuildingDTO;
import kr.or.initspring.dto.member.OpenedInfoDTO;

public interface CodeMgDAO {
	
	public int insertCode(CodeMgDTO code);
	public List<CodeMgDTO> codelist();
	public CodeMgDTO codeDetail(String code);
	public int editCode(String code, int code_type, String code_name, Date code_birth);
	public int insertExcel(CodeMgDTO code);
	public int deleteCode(String code) throws Exception;
	public int insertBuilding(BuildingDTO building);
	public List<BuildingDTO> buildingList();
	public int deleteBuilding(String building_code);
	public int updateBuilding(String building_code, String building_name,  String building_addr);
	public BuildingDTO selectBuilding(String building_code);
	public List<ClassroomDTO> classlist();
	public ClassroomDTO selectClassroom(String classroom_code);
	public int insertClassroom(ClassroomDTO classroom);
	public int updateClassroom(ClassroomDTO classroom);
	public int insertOffice(OfficeDTO office);
	public List<OfficeDTO> officelist();
	public List<LaboratoryDTO> lablist();
	public int insertLab(LaboratoryDTO lab);
	public LaboratoryDTO labDetail(String lab_code);
	public List<ScSystemDTO> scSystemList();
	public ScSystemDTO detailScSystem(String sys_code);
	public int insertScSystem(ScSystemDTO scsystem);
	public List<ScholarshipDTO> scholarshipList();
	public ScholarshipDTO detailScholarship(String scholarship_code);
	public List<SemesterDTO> semesterList();
	public SemesterDTO detailSemester();
	public int insertSemester(SemesterDTO semester);
	public SemesterDTO detailSemester(String semester_code);
	public List<Academic_CalendarDTO> academicCalendarList();
	public int insertAcademic(Academic_CalendarDTO academic);
	public Academic_CalendarDTO academicCalendarDetail(String calendar_code);
	public int updateOffice(OfficeDTO office);
	public OfficeDTO selectOffice(String office_code);
	public int updateLab(LaboratoryDTO lab);
	public int updateAcademic(Academic_CalendarDTO academic);
	public int updateScSystemUpdate(ScSystemDTO scsystem);
	public int updateScholarship(ScholarshipDTO scholarship);
	public int insertScholarship(ScholarshipDTO scholarship);
	public int updateSemester(SemesterDTO semester);
	public List<CollegeDTO> colleagelist();
	public int insertCollege(CollegeDTO college);
	public int updateCollege(CollegeDTO college);
	public CollegeDTO selectCollege(String college_code);
	public List<DepartmentDTO> departmentList();
	public int insertDepartment(DepartmentDTO department);
	public DepartmentDTO selectDepartment(String department_code);
	public int updateDepartment(DepartmentDTO department);
	public List<MjRecordDTO> mjRecordList();
	public int insertMjRecord(MjRecordDTO mjrecord);
	public List<OpenedDTO> openedlist();
	public List<OpenedInfoDTO> openedInfolist();
	public List<RegisterDTO> registerlist();
	public int insertRegister(RegisterDTO register);
	public List<ClassBuildingDTO> classbuilding();
	public List<LabBuildingDTO> labbuilding();
	public List<OfiiceBuildingDTO> officebuilding();
	
	
	//최대 코드번호 가져오기
	public String getMaxBuildingCode();	
	public String getMaxClassroomCode();
	public String getMaxOfficeCode();
	public String getMaxLaboratoryCode();
	public String getMaxScSystemCode();
	public String getMaxScholarshipCode();
	public String getMaxSemesterCode();
	public String getMaxCollegeCode();
	public String getMaxCalendarCode();
	public String getMaxDepartmentCode();
	
	
	
	
	///////////// 학기 초기화 시 Transaction 되는 삭제 함수들///////////////
	public int deleteTimetalbe();
	public int deleteOpened();
	public int deleteLecture();
	public int deleteAskTime();
	public int deleteOpRequest();
	public int deleteRejection();
	public int deleteReserve();
	public int deleteEnrollment();
	
}
