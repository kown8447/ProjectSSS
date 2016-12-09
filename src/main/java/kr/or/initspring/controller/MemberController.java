/*
 * @Class : MemberContorller
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원정보과 관련된 로직을 처리하는 컨트롤러.
 * 회원 정보 열람, 수정, 삭제 등
*/

package kr.or.initspring.controller;

import java.io.File;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
import kr.or.initspring.dto.commons.RegisterDTO;
import kr.or.initspring.dto.commons.ScSystemDTO;
import kr.or.initspring.dto.commons.ScholarshipDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
import kr.or.initspring.service.CodeService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.join.MemberDTO;
import kr.or.initspring.dto.requestCourse.OpenedLectureDTO;
import kr.or.initspring.service.AsideService;
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
import kr.or.initspring.service.MemberService;

@Controller
@Secured({ "ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PROFESSOR" })
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MemberService memberservice;

	@Autowired
	private AsideService asideservice;

	@Autowired
	private CodeService codeservice;

	@Autowired
	private JavaMailSender mailSender; // Email 전송을 위한 클래스

	@Autowired
	private VelocityEngine velocityEngine; // HTML 파일을 통째로 전송하기 위한 velocity

	@RequestMapping(value = "updatePwd.htm", method = RequestMethod.GET)
	public String updatePwd() {
		return "member.updatePwd";
	}

	@Autowired
	private View jsonview; // 비동기 처리를 위한 jsonview 객체
	/*
	 * @method Name : editDetail
	 * 
	 * @Author : 김영빈
	 * 
	 * @description 사용자가 정보수정 클릭시 정보 상세내역들을 뿌려주기 위한 controller
	 */

	@RequestMapping(value = "edit.htm", method = RequestMethod.GET)
	public String editDetail(Model model, Principal principal) {

		String member_id = principal.getName();
		MemberDTO member = memberservice.getMember(member_id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(member.getMember_birth());
		int year = Integer.parseInt(birth.substring(0, 4));
		int month = Integer.parseInt(birth.substring(5, 7));

		int day = Integer.parseInt(birth.substring(8, 10));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); // <-- months start
											// at 0.
		cal.set(Calendar.DAY_OF_MONTH, day);
		Date date = new Date(cal.getTimeInMillis());

		String address = member.getMember_addr();
		String[] array = address.split("\\?");
		member.setAddr_num(array[0]);
		member.setMember_addr(array[1]);
		member.setMember_addr_detail(array[2]);
		member.setMember_birth(date);

		model.addAttribute("member", member);
		return "member.memberEdit";
	}

	/*
	 * @method Name : editComplete
	 * 
	 * @Author : 김영빈
	 * 
	 * @description 사용자가 정보 수정한 정보를 받아서 사용자의 개인정보 db update
	 */
	@RequestMapping(value = "edit.htm", method = RequestMethod.POST)
	public String editComplete(Principal principal, MemberDTO member_temp, HttpServletRequest request)
			throws Exception {
		String viewpage = "";
		String member_id = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(member_temp.getMember_pwd());
		member_temp.setMember_addr(member_temp.getAddr_num() + "?" + member_temp.getMember_addr() + "?"
				+ member_temp.getMember_addr_detail());

		String member_addr = member_temp.getMember_addr();
		String member_phone = member_temp.getMember_phone();
		String member_email = member_temp.getMember_email();
		int timetable_share = member_temp.getTimetable_share();
		boolean result = false;
		CommonsMultipartFile file = member_temp.getFile();
		String filename = file.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/profiles");
		String fullpath = path + "\\" + filename;

		if (!filename.equals("")) {
			// 서버에 파일 쓰기 작업
			FileOutputStream fs = new FileOutputStream(fullpath);
			fs.write(file.getBytes());
			fs.close();
		} else {
			filename = memberservice.getFileName(member_id);
		}
		if (memberservice.getRole(member_id).equals("ROLE_STUDENT")) {
			result = memberservice.updateStudentInfo(member_id, pwd, member_addr, member_phone, member_email, filename,
					timetable_share);
		} else {
			result = memberservice.updateMemberInfo(member_id, pwd, member_addr, member_phone, member_email, filename);
		}

		if (result == true) {
			viewpage = "redirect:/index.htm";
		} else {
			viewpage = "redirect:edit.htm";
		}
		return viewpage;
	}

	/*
	 * @method Name : updatePwd
	 * 
	 * @Author : 권기엽
	 * 
	 * @description 회원의 비밀번호 찾기 이후, 임시비밀번호에서 정식 비밀번호로 바꾸는 함수 해당 함수가 정상적으로 진행될
	 * 경우, 비밀번호 변경 및 member_temp 컬럼의 값이 0 으로 변경됨
	 */
	@RequestMapping(value = "updatePwd.htm", method = RequestMethod.POST)
	public String updatePwd(String member_pwd, Principal principal) throws Exception {
		String member_id = principal.getName();
		String pwd = bCryptPasswordEncoder.encode(member_pwd);
		boolean result = false;
		String viewpage = "";
		result = memberservice.updatePwd(member_id, pwd);

		if (result == true) {
			viewpage = "redirect:/index.htm";
		} else {
			viewpage = "redirect:updatePwd.htm";
		}
		return viewpage;
	}

	/*
	 * @method Name : codeMg
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 코드 관리뷰로 이동
	 */
	@RequestMapping("code.htm")
	public String codeMg(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		List<ScSystemDTO> scsystem = codeservice.scSystemList();
		List<SemesterDTO> semester = codeservice.semesterList();
		List<CollegeDTO> college = codeservice.collegelist();
		List<DepartmentDTO> department = codeservice.departmentList();
		List<OpenedInfoDTO> openedinfolist = codeservice.openedInfoList();
		List<OfficeDTO> officelist = codeservice.officelist();

		model.addAttribute("building", building);
		model.addAttribute("sc", scsystem);
		model.addAttribute("semester", semester);
		model.addAttribute("college", college);
		model.addAttribute("department", department);
		model.addAttribute("opened", openedinfolist);
		model.addAttribute("officelist", officelist);

		return "codemg.code";

	}
	/*
	 * @method Name : registerStructure
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 건물 등록 뷰로 이동
	 */
	@RequestMapping("registerstructure.htm")
	public String registerStructure(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		List<ScSystemDTO> scsystem = codeservice.scSystemList();
		List<SemesterDTO> semester = codeservice.semesterList();
		List<CollegeDTO> college = codeservice.collegelist();
		List<DepartmentDTO> department = codeservice.departmentList();
		List<OpenedInfoDTO> openedinfolist = codeservice.openedInfoList();
		List<OfficeDTO> officelist = codeservice.officelist();

		model.addAttribute("building", building);
		model.addAttribute("sc", scsystem);
		model.addAttribute("semester", semester);
		model.addAttribute("college", college);
		model.addAttribute("department", department);
		model.addAttribute("opened", openedinfolist);
		model.addAttribute("officelist", officelist);

		return "codemg.registerstructure";
	}

	/*
	 * @method Name : registerBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 빌딩 등록 뷰로 이동
	 */
	@RequestMapping("registerbuilding.htm")
	public String registerBuilding(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		model.addAttribute("building", building);

		return "codemg.registerbuilding";
	}

	/*
	 * @method Name : registerClassroom
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 강의실 등록 뷰로 이동
	 */
	@RequestMapping("registerclassroom.htm")
	public String registerClassroom(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		model.addAttribute("building", building);

		return "codemg.registerclassroom";
	}
	/*
	 * @method Name : registerOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 사무실 등록 뷰로 이동
	 */
	@RequestMapping("registeroffice.htm")
	public String registerOffice(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		model.addAttribute("building", building);

		return "codemg.registeroffice";
	}
	/*
	 * @method Name : registerLaboratory
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 연구실 등록 뷰로 이동
	 */
	@RequestMapping("registerlaboratory.htm")
	public String registerLaboratory(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		model.addAttribute("building", building);

		return "codemg.registerlaboratory";
	}
	/*
	 * @method Name : registerStudent
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 학생 등록 뷰로 이동
	 */
	@RequestMapping("registerstudent.htm")
	public String registerStudent(Model model) {

		List<DepartmentDTO> department = codeservice.departmentList();
		model.addAttribute("department", department);

		return "codemg.registerstudent";
	}
	/*
	 * @method Name : registerRegister
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 등록하는 등록 뷰로 이동
	 */
	@RequestMapping("registerregister.htm")
	public String registerRegister(Model model) {

		List<SemesterDTO> semester = codeservice.semesterList();
		model.addAttribute("semester", semester);

		return "codemg.registerregister";
	}
	/*
	 * @method Name : registerCollege
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 대학 등록 뷰로 이동
	 */
	@RequestMapping("registercollege.htm")
	public String registerCollege(Model model) {

		List<OfficeDTO> officelist = codeservice.possibleOffice();
		model.addAttribute("officelist", officelist);

		return "codemg.registercollege";
	}
	/*
	 * @method Name : registerDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 학과 등록 뷰로 이동
	 */
	@RequestMapping("registerdepartment.htm")
	public String registerDepartment(Model model) {

		List<CollegeDTO> college = codeservice.collegelist();
		List<OfficeDTO> officepossiblelist = codeservice.possibleOffice();
		model.addAttribute("college", college);
		model.addAttribute("officelist", officepossiblelist);

		return "codemg.registerdepartment";
	}
	/*
	 * @method Name : registerScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 장학 등록 뷰로 이동
	 */
	@RequestMapping("registerscholarship.htm")
	public String registerScholarship(Model model) {

		List<ScSystemDTO> scsystem = codeservice.scSystemList();
		List<SemesterDTO> semester = codeservice.semesterList();
		model.addAttribute("sc", scsystem);
		model.addAttribute("semester", semester);

		return "codemg.registerscholarship";
	}
	/*
	 * @method Name : registerMjRecord
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 전공 등록 뷰로 이동
	 */
	@RequestMapping("registermjrecord.htm")
	public String registerMjRecord(Model model) {

		List<DepartmentDTO> doubledepartmentlist = codeservice.doubleDepartmentlist();
		model.addAttribute("department", doubledepartmentlist);

		return "codemg.registermjrecord";
	}
	/*
	 * @method Name : registerScsystem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 장학제도 등록 뷰로 이동
	 */
	@RequestMapping("registerscsystem.htm")
	public String registerScsystem(Model model) {

		return "codemg.registerscsystem";
	}
	/*
	 * @method Name : registerProfessor
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 교수 등록 뷰로 이동
	 */
	@RequestMapping("registerprofessor.htm")
	public String registerProfessor(Model model) {

		List<DepartmentDTO> department = codeservice.departmentList();
		model.addAttribute("department", department);

		return "codemg.registerprofessor";
	}
	/*
	 * @method Name : registerOpen
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 개설승인 등록 뷰로 이동
	 */
	@RequestMapping("registeropen.htm")
	public String registerOpen(Model model) {

		List<BuildingDTO> building = codeservice.buildingList();
		List<ScSystemDTO> scsystem = codeservice.scSystemList();
		List<SemesterDTO> semester = codeservice.semesterList();
		List<CollegeDTO> college = codeservice.collegelist();
		List<DepartmentDTO> department = codeservice.departmentList();
		List<OpenedInfoDTO> openedinfolist = codeservice.openedInfoList();
		List<OfficeDTO> officelist = codeservice.officelist();

		model.addAttribute("building", building);
		model.addAttribute("sc", scsystem);
		model.addAttribute("semester", semester);
		model.addAttribute("college", college);
		model.addAttribute("department", department);
		model.addAttribute("opened", openedinfolist);
		model.addAttribute("officelist", officelist);

		return "codemg.registeropen";
	}
	/*
	 * @method Name : registerAdmin
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 관리자 등록 뷰로 이동
	 */
	@RequestMapping("registeradmin.htm")
	public String registerAdmin(Model model) {

		return "codemg.registeradmin";
	}
	/*
	 * @method Name : registerSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description : 관리자가 학기 등록 뷰로 이동
	 */
	@RequestMapping("registersemester.htm")
	public String registerSemester(Model model) {

		return "codemg.registersemester";
	}
	/*
	 * @method Name : registerCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학생 등록하는 컨트롤러
	 */
	@RequestMapping(value = "studentRegister.htm", method = RequestMethod.POST)
	public String registerCode(StudentCodeRegDTO student, Model model) throws Exception {

		String viewpage = "redirect:code.htm";

		int result = codeservice.insertStudent(student);

		if (result == 1) {

			viewpage = "redirect:typeofcodelist.htm?code_type=0";
		}

		return viewpage;
	}
	/*
	 * @method Name : registerProfessorCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 교수 등록하는 컨트롤러
	 */
	@RequestMapping(value = "professorCodeRegister.htm", method = RequestMethod.POST)
	public String registerProfessorCode(ProfessorCodeRegDTO professor) {

		String viewpage = "";

		int result = codeservice.insertProfessor(professor);

		if (result == 1) {
			viewpage = "redirect:typeofcodelist.htm?code_type=1";
		}

		return viewpage;
	}
	/*
	 * @method Name : viewCodeList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드 리스트 출력
	 */
	@RequestMapping("codelist.htm")
	public String viewCodeList(Model model) {

		List<CodeMgDTO> codelist = codeservice.codelist();
		model.addAttribute("codelist", codelist);
		return "codemg.codelist";
	}
	/*
	 * @method Name : viewtypecodelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드 리스트 출력
	 */
	@RequestMapping("typeofcodelist.htm")
	public String viewtypecodelist(String code_type, String keyword, String searchType, Model model) {
		List<CodeMgDTO> codelist = codeservice.conditioncodelist(Integer.parseInt(code_type), keyword, searchType);
		model.addAttribute("codelist", codelist);
		model.addAttribute("typeofcode", code_type);
		return "codemg.codelist";
	}

	/*
	 * @method Name : detailCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드 상세보기
	 */
	@RequestMapping("codedetail.htm")
	public String detailCode(Model model, String code) {

		CodeMgDTO codeDTO = codeservice.detailcode(code);
		model.addAttribute("code", codeDTO);
		return "codemg.codedetail";
	}
	/*
	 * @method Name : updateCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 코드 수정
	 */
	@RequestMapping(value = "updateCode.htm", method = RequestMethod.POST)
	public String updateCode(Model model, String code, int code_type, String code_name, Date code_birth) {

		int result = 0;
		String viewpage = "";
		result = codeservice.updateCode(code, code_type, code_name, code_birth);
		CodeMgDTO codeDTO = new CodeMgDTO();

		codeDTO.setCode(code);
		codeDTO.setCode_type(code_type);
		codeDTO.setCode_name(code_name);
		codeDTO.setCode_birth(code_birth);

		model.addAttribute("code", codeDTO);

		if (result == 1) {
			viewpage = "redirect:codelist.htm";
		}

		return viewpage;
	}

	/*
	 * @method Name : download
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 코드 등록 양식을 액셀로 다운로드 받는 매소드
	 */
	@RequestMapping("excel.htm")
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "student_code.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : professordownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 교수등록 기본 액셀 양식을 받는 컨트롤러
	 */
	@RequestMapping("professorexcel.htm")
	public ModelAndView professordownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "professor_code.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : excelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 액셀로 코드 등록을 일괄처리하는 매소드
	 */
	@RequestMapping(value = "compExcelUpload.htm", method = RequestMethod.POST)
	public View excelUpload(MultipartHttpServletRequest request, Model model) throws Exception {

		codeservice.insertExcelList(request, model);

		return jsonview;
	}
	/*
	 * @method Name : professorexcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 엑셀파일을 등록하여 교수를 일괄등록하는 매소드
	 */
	@RequestMapping(value = "professorExcelUpload.htm", method = RequestMethod.POST)
	public View professorexcelUpload(MultipartHttpServletRequest request, Model model) throws Exception {

		codeservice.professorinsertExcelList(request, model);

		return jsonview;
	}
	/*
	 * @method Name : deleteCode
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 코드를 삭제하는 매소드
	 */
	@RequestMapping(value = "codeDelete.htm", method = RequestMethod.POST)
	public View deleteCode(String code, Model model) {

		boolean result = false;

		int dbResult = codeservice.deleteCode(code, model);

		if (dbResult > 0) {
			result = true;
		} else if (dbResult == 0) {
			model.addAttribute("reason", "삭제대상이 이미 제거 되어 있습니다");
		}

		model.addAttribute("result", result);

		return jsonview;
	}

	/*
	 * @method Name : authEmail
	 * 
	 * @Author : 김영빈
	 * 
	 * @description 이메일 인증을 위한 함수. 사용자로부터 입력받은 메일을 테이블과 대조하여, 유효한 메일인지 확인. 확인이 된
	 * 메일은 인증번호(sessionID())를 html에 담아서 발송 -> 발송시에는 velocityEngine을 사용하여 통째로 전송
	 */
	@RequestMapping("authEmail.htm")
	public View authEmail(String member_email, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		final String sessionID = session.getId();
		final String address = member_email;

		final MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(address);
				message.setFrom("kown8447@gmail.com");
				message.setSubject("SSS 회원가입 인증 메일");
				final Map<String, Object> model = new HashMap<String, Object>();
				model.put("authkey", sessionID);
				final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "authMail.html",
						"UTF-8", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);

		model.addAttribute("mailresult", "success");
		model.addAttribute("sessionID", sessionID);
		return jsonview;
	}

	@RequestMapping("persnalDataCall.htm")
	public View persnalDataCall(Principal principal, Model model) {
		asideservice.persnalDataCall(principal.getName(), model);
		return jsonview;
	}
	/*
	 * @method Name : registerBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물을 등록하게 해주는 매소드
	 */
	@RequestMapping(value = "registerBuilding.htm", method = RequestMethod.POST)
	public String registerBuilding(BuildingDTO building) {

		codeservice.insertBuilding(building);
		return "redirect:buildingList.htm";
	}
	/*
	 * @method Name : adminRegister
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 관리자 코드를 등록하게 해주는 매소드
	 */
	@RequestMapping(value = "adminCodeRegister.htm", method = RequestMethod.POST)
	public String adminRegister(CodeMgDTO admin) {

		codeservice.insertAdmin(admin);
		return "redirect:typeofcodelist.htm?code_type=2";
	}
	/*
	 * @method Name : buildingList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물 목록을 보게 해주는 매소드
	 */
	@RequestMapping("buildingList.htm")
	public String buildingList(Model model) {

		List<BuildingDTO> buildingList = codeservice.buildingList();
		model.addAttribute("building", buildingList);
		return "codemg.buildinglist";
	}
	/*
	 * @method Name : deleteBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물을 삭제 해주는 매소드
	 */
	@RequestMapping("buildingDelete.htm")
	public View deleteBuilding(String building_code, Model model) {

		boolean result = false;

		int dbResult = codeservice.deleteBuilding(building_code, model);
		if (dbResult > 0) {
			result = true;
		} else if (dbResult == 0) {
			model.addAttribute("reason", "삭제대상이 이미 제거 되어 있습니다");
		}

		model.addAttribute("result", result);

		return jsonview;
	}
	/*
	 * @method Name : buildingdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("buildingexcel.htm")
	public ModelAndView buildingdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "building.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : classroomdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("classroomexcel.htm")
	public ModelAndView classroomdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "classroom.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : officedownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("ofexcel.htm")
	public ModelAndView officedownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "office.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : laboratorydownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 연구실등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("lbexcel.htm")
	public ModelAndView laboratorydownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "laboratory.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : scSystemdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("scsexcel.htm")
	public ModelAndView scSystemdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "sc_system.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : scholarshipdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("sclexcel.htm")
	public ModelAndView scholarshipdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "scholarship.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : collegedownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("colexcel.htm")
	public ModelAndView collegedownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");
		File downloadFile = new File(baseDir, "college.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : departmentdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학과 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("depexcel.htm")
	public ModelAndView departmentdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "department.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : mjrecorddownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 전공 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("mjexcel.htm")
	public ModelAndView mjrecorddownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "mjrecord.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : registerdownload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 등록 액셀 기본 양식을 다운받게 해주는 매소드
	 */
	@RequestMapping("regexcel.htm")
	public ModelAndView registerdownload(HttpServletRequest request, HttpServletResponse response) {

		String baseDir = request.getRealPath("/WEB-INF/Template");

		File downloadFile = new File(baseDir, "register.xlsx");

		return new ModelAndView("pageView", "downloadFile", downloadFile);
	}
	/*
	 * @method Name : buildingExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물등록 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "buildingExcelUpload.htm", method = RequestMethod.POST)
	public View buildingExcelUpload(MultipartHttpServletRequest request, Model model) {

		boolean result = false;

		result = codeservice.buildingExcelList(request, model);
		model.addAttribute("result", result);

		return jsonview;
	}
	/*
	 * @method Name : classroomExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "classroomExcelUpload.htm", method = RequestMethod.POST)
	public View classroomExcelUpload(MultipartHttpServletRequest request, Model model) {

		boolean result = false;

		result = codeservice.classroomExcelList(request, model);

		model.addAttribute("result", result);

		return jsonview;
	}
	/*
	 * @method Name : officeExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "ofExcelUpload.htm", method = RequestMethod.POST)
	public View officeExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.officeExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : laboratoryExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 연구실 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "lbExcelUpload.htm", method = RequestMethod.POST)
	public View laboratoryExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.laboratoryExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : scSystemExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "scsExcelUpload.htm", method = RequestMethod.POST)
	public View scSystemExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.scSystemExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : scholarshipExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "sclExcelUpload.htm", method = RequestMethod.POST)
	public View scholarshipExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.scholarshipExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : collegeExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 단과대학 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "colExcelUpload.htm", method = RequestMethod.POST)
	public View collegeExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.collegeExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : departmentExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학과 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "depExcelUpload.htm", method = RequestMethod.POST)
	public View departmentExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.departmentExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : mjrecordExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 전공 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "mjExcelUpload.htm", method = RequestMethod.POST)
	public View mjrecordExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.mjrecordExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : mjrecordExcelUpload
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 등록 액셀일괄 등록 해주는 매소드
	 */
	@RequestMapping(value = "regExcelUpload.htm", method = RequestMethod.POST)
	public View registerExcelUpload(MultipartHttpServletRequest request, Model model) {
		codeservice.registerExcelList(request, model);
		return jsonview;
	}
	/*
	 * @method Name : buildingDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물 상세 정보보기 해주는 매소드
	 */
	@RequestMapping("buildingDetail.htm")
	public String buildingDetail(String building_code, Model model) {

		BuildingDTO building = codeservice.selectBuilding(building_code);
		model.addAttribute("building", building);

		return "codemg.buildingdetail";
	}
	/*
	 * @method Name : updateBuilding
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 건물 수정하게 해주는 매소드
	 */
	@RequestMapping("updateBuilbilding.htm")
	public String updateBuilding(String building_code, String building_name, String building_addr) {

		String view = "";

		int result = codeservice.updateBuilding(building_code, building_name, building_addr);

		if (result == 1) {
			view = "redirect:buildingList.htm";
		}
		return view;
	}
	/*
	 * @method Name : classroomList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실 리스트를 보게 해주는 매소드
	 */
	@RequestMapping("classroomList.htm")
	public String classroomList(Model model) {

		List<ClassroomDTO> classlsit = codeservice.classList();
		model.addAttribute("classlist", classlsit);

		return "codemg.classroomlist";
	}
	/*
	 * @method Name : classroomList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실 수정을 하게 해주는 매소드
	 */
	@RequestMapping("classroomUpdate.htm")
	public String classroomDetail(String classroom_code, Model model) {

		String view = "";

		ClassroomDTO classroom = codeservice.selectClassroom(classroom_code);
		List<BuildingDTO> building = codeservice.buildingList();

		model.addAttribute("classroom", classroom);
		model.addAttribute("building", building);

		return "codemg.classroomdetail";
	}
	/*
	 * @method Name : classroomList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실 등록 하게 해주는 매소드
	 */
	@RequestMapping(value = "registerClassroom.htm", method = RequestMethod.POST)
	public String insertClassroom(ClassroomDTO classroom) throws Exception {

		int result = codeservice.insertClassroom(classroom);
		String view = "";

		if (result == 1) {
			view = "redirect:showclasslist.htm";
		}

		return view;
	}
	/*
	 * @method Name : updateClassroom
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 강의실 수정 하게 해주는 매소드
	 */
	@RequestMapping("updateClassroom.htm")
	public String updateClassroom(ClassroomDTO classroom) {

		int result = codeservice.updateClassroom(classroom);
		String view = "";

		if (result == 1) {
			view = "redirect:showclasslist.htm";
		}

		return view;
	}
	/*
	 * @method Name : insertOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실 등록 하게 해주는 매소드
	 */
	@RequestMapping(value = "insertOffice.htm", method = RequestMethod.POST)
	public String insertOffice(OfficeDTO office) {
		String view = "";
		int result = codeservice.insertOffice(office);

		if (result == 1) {
			view = "redirect:showofficelist.htm";
		}
		return view;
	}
	/*
	 * @method Name : officeList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실 리스트를 보게 해주는 매소드
	 */
	@RequestMapping("officeList.htm")
	public String officeList(Model model) {

		List<OfficeDTO> officelist = codeservice.officelist();
		List<BuildingDTO> building = codeservice.buildingList();

		model.addAttribute("officelist", officelist);
		model.addAttribute("building", building);

		return "codemg.officelist";
	}
	/*
	 * @method Name : detailOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실 상세보기 해주는 매소드
	 */
	@RequestMapping("detailOffice.htm")
	public String detailOffice(String office_code) {

		return null;
	}
	/*
	 * @method Name : labList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 사무실 상세보기 해주는 매소드
	 */
	@RequestMapping("labList.htm")
	public String labList(Model model) {

		List<LaboratoryDTO> lablist = codeservice.lablist();

		model.addAttribute("lablist", lablist);
		return "codemg.lablist";
	}
	/*
	 * @method Name : insertLab
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 연구실 등록 해주는 매소드
	 */
	@RequestMapping(value = "insertLab.htm", method = RequestMethod.POST)
	public String insertLab(LaboratoryDTO lab) {

		int result = codeservice.insertLab(lab);
		String view = "";

		if (result == 1) {
			view = "redirect:showlablist.htm";
		}

		return view;
	}
	/*
	 * @method Name : labDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 연구실 상세 보기 해주는 매소드
	 */
	@RequestMapping(value = "labDetail.htm")
	public String labDetail(String lab_code, Model model) {

		LaboratoryDTO lab = codeservice.labDetail(lab_code);
		List<BuildingDTO> building = codeservice.buildingList();

		model.addAttribute("lab", lab);
		model.addAttribute("building", building);

		return "codemg.labdetail";
	}
	/*
	 * @method Name : scSystemList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도 리스트 보기 해주는 매소드
	 */
	@RequestMapping("scSystemList.htm")
	public String scSystemList(Model model) {

		List<ScSystemDTO> scsystemlist = codeservice.scSystemList();
		model.addAttribute("scsystemlist", scsystemlist);

		return "codemg.scsystemlist";
	}
	/*
	 * @method Name : detailScSystem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도 상세보기 해주는 매소드
	 */
	@RequestMapping("detailScSystem.htm")
	public String detailScSystem(String sys_code, Model model) {

		ScSystemDTO dto = codeservice.detailScSystem(sys_code);
		model.addAttribute("scsystem", dto);

		return "codemg.scsystemdetail";
	}
	/*
	 * @method Name : insertScSystem
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학제도 등록 해주는 매소드
	 */
	@RequestMapping("insertScSystem.htm")
	public String insertScSystem(ScSystemDTO scsystem) {

		String view = "";
		int result = codeservice.insertScSystem(scsystem);

		if (result == 1) {
			view = "redirect:scSystemList.htm";
		}
		return view;
	}
	/*
	 * @method Name : scholarshipList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학 리스트 보게 해주는 매소드
	 */
	@RequestMapping("scholarshipList.htm")
	public String scholarshipList(Model model) {

		List<ScholarshipInfoDTO> scholarshipinfolist = codeservice.scholarshipInfoList();
		model.addAttribute("scholarshipList", scholarshipinfolist);

		return "codemg.scholarshiplist";
	}
	/*
	 * @method Name : detailScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 장학 상세보기를 해주는 매소드
	 */
	@RequestMapping("detailScholarship.htm")
	public String detailScholarship(String scholarship_code, Model model) {

		ScholarshipDTO scholarship = codeservice.detailScholarship(scholarship_code);
		List<ScSystemDTO> scsystem = codeservice.scSystemList();
		List<SemesterDTO> semester = codeservice.semesterList();

		model.addAttribute("scholarship", scholarship);
		model.addAttribute("scsystem", scsystem);
		model.addAttribute("semester", semester);

		return "codemg.detailscholarship";
	}
	/*
	 * @method Name : semesterList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학기 리스트를 보게 해주는 매소드
	 */
	@RequestMapping("semesterList.htm")
	public String semesterList(Model model) {

		List<SemesterDTO> semesterlist = codeservice.semesterList();
		model.addAttribute("semesterlist", semesterlist);

		return "codemg.semesterlist";
	}
	/*
	 * @method Name : insertSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학기 등록을 해주는 매소드
	 */
	@RequestMapping(value = "insertSemester.htm", method = RequestMethod.POST)
	public String insertSemester(SemesterDTO semester) {

		String view = "";
		int result = codeservice.insertSemester(semester);

		if (result == 1) {
			view = "redirect:semesterList.htm";
		}
		return view;
	}
	/*
	 * @method Name : detailSemester
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 관리자가 학기 상세보기 해주는 매소드
	 */
	@RequestMapping("detailSemester.htm")
	public String detailSemester(String semester_code, Model model) {

		SemesterDTO dto = codeservice.detailSemester(semester_code);
		model.addAttribute("semester", dto);

		return "codemg.semesterdetail";
	}
	/*
	 * @method Name : academicCalendarList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정 리스트를 보게 해주는 매소드
	 */
	@RequestMapping("academicCalendarList.htm")
	public String academicCalendarList(Model model) {

		List<Academic_CalendarDTO> academiclist = codeservice.academicCalendarList();
		model.addAttribute("academic", academiclist);

		return "codemg.academiccalendarlist";
	}
	/*
	 * @method Name : insertAcademicCalendar
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정 등록을 해주는 매소드
	 */
	@RequestMapping(value = "insertAcademicCalendar.htm", method = RequestMethod.POST)
	public String insertAcademicCalendar(Academic_CalendarDTO academic) {
		String view = "";
		int result = codeservice.insertAcademicCalendar(academic);

		if (result == 1) {
			view = "redirect:academicCalendarList.htm";
		}
		return view;
	}
	/*
	 * @method Name : academicCalendarDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정 상세보기를 해주는 매소드
	 */
	@RequestMapping("academicCalendarDetail.htm")
	public String academicCalendarDetail(String calendar_code, Model model) {

		Academic_CalendarDTO academic = codeservice.academicCalendarDetail(calendar_code);
		model.addAttribute("academic", academic);

		return "codemg.academicCalendarDetail";
	}
	/*
	 * @method Name : selectOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사무실 코드번호를 가져와서 사무실을 선택하는 매소드
	 */
	@RequestMapping("selectOffice.htm")
	public String selectOffice(String office_code, Model model) {

		OfficeDTO office = codeservice.selectOffice(office_code);
		List<BuildingDTO> building = codeservice.buildingList();

		model.addAttribute("office", office);
		model.addAttribute("building", building);

		return "codemg.officedetail";

	}
	/*
	 * @method Name : updateOffice
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사무실 정보를 수정해주는 매소드
	 */
	@RequestMapping("updateOffice.htm")
	public String updateOffice(OfficeDTO office) {

		String view = "";
		int result = codeservice.updateOffice(office);

		if (result == 1) {
			view = "redirect:showofficelist.htm";
		}
		return view;
	}
	/*
	 * @method Name : updateLab
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 연구실 정보를 수정해주는 매소드
	 */
	@RequestMapping("updateLab.htm")
	public String updateLab(LaboratoryDTO lab) {

		String view = "";
		int result = codeservice.updateLab(lab);

		if (result == 1) {
			view = "redirect:showlablist.htm";
		}
		return view;
	}
	/*
	 * @method Name : updateAcademic
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학사일정 정보를 수정해주는 매소드
	 */
	@RequestMapping("updateAcademic.htm")
	public String updateAcademic(Academic_CalendarDTO academic) {

		String view = "";
		int result = codeservice.updateAcademic(academic);

		if (result == 1) {
			view = "redirect:academicCalendarList.htm";
		}
		return view;
	}
	/*
	 * @method Name : updateScSystemUpdate
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학제도 정보를 수정해주는 매소드
	 */
	@RequestMapping("scsytemUpdate.htm")
	public String updateScSystemUpdate(ScSystemDTO scsytem) {

		String view = "";
		int result = codeservice.updateScSytem(scsytem);

		if (result == 1) {
			view = "redirect:scSystemList.htm";
		}
		return view;
	}
	/*
	 * @method Name : updateScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학 정보를 수정해주는 매소드
	 */
	@RequestMapping("updateScholarship.htm")
	public String updateScholarship(ScholarshipDTO scholarship) {

		String view = "";
		int result = codeservice.scholarshipUpdate(scholarship);

		if (result == 1) {
			view = "redirect:scholarshipList.htm";
		}
		return view;
	}
	/*
	 * @method Name : insertScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 장학을 등록하게 해주는 매소드
	 */
	@RequestMapping("insertScholarship.htm")
	public String insertScholarship(ScholarshipDTO scholarship){
		String view = "";
		int result = codeservice.insertScholarship(scholarship);

		if (result == 1) {
			view = "redirect:scholarshipList.htm";
		}
		return view;
	}
	/*
	 * @method Name : insertScholarship
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학기정보를 수정하게 해주는 매소드
	 */
	@RequestMapping("updateSemester.htm")
	public String updateSemester(SemesterDTO semester) {

		String view = "";
		int result = codeservice.updateSemester(semester);

		if (result == 1) {
			view = "redirect:semesterList.htm";
		}
		return view;
	}
	/*
	 * @method Name : colleageList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 단과대학 정보를 뿌려주는 매소드
	 */
	@RequestMapping("collegeList.htm")
	public String colleageList(Model model) {
		
		List<CollegeInfoDTO> collegeinfolist = codeservice.collegeInfoList();
		model.addAttribute("college", collegeinfolist);

		return "codemg.collegelist";
	}
	/*
	 * @method Name : insertCollege
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 단과대학을 등록하게 해주는 매소드
	 */
	@RequestMapping(value = "insertCollege.htm", method = RequestMethod.POST)
	public String insertCollege(CollegeDTO college) {

		String view = "";
		int result = codeservice.insertCollege(college);

		if (result == 1) {
			view = "redirect:collegeList.htm";
		}

		return view;
	}
	/*
	 * @method Name : updateCollege
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 단과대학을 수정하게 해주는 매소드
	 */
	@RequestMapping("updateColleage.htm")
	public String updateCollege(CollegeDTO college, String before_office_code, Model model) {

		String view = "";
		int result = codeservice.updateCollege(college, before_office_code);

		List<OfficeDTO> officelist = codeservice.possibleOffice();
		model.addAttribute("officelist", officelist);

		if (result == 1) {
			view = "redirect:collegeList.htm";
		}

		return view;
	}
	/*
	 * @method Name : collegeDetail
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 단과대학 정보를 상세보기 하게 해주는 매소드
	 */
	@RequestMapping("selectCollege.htm")
	public String collegeDetail(String college_code, Model model) {

		CollegeDTO college = codeservice.collegeDetail(college_code);
		List<OfficeDTO> officelist = codeservice.possibleOffice();
		officelist.add(codeservice.selectOffice(college.getOffice_code()));
		model.addAttribute("college", college);
		model.addAttribute("officelist", officelist);
		return "codemg.collegedetail";
	}
	/*
	 * @method Name : departmentList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과 리스트를 뿌려주게 하는 매소드
	 */
	@RequestMapping("departmentlist.htm")
	public String departmentList(Model model) {

		List<DepartmentDTO> departmentlist = codeservice.departmentList();

		List<DepartmentInfoDTO> departmentinfolist = codeservice.departmentInfoList();
		model.addAttribute("department", departmentinfolist);

		return "codemg.departmentlist";
	}
	/*
	 * @method Name : inesrtDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과를 등록하게 해주는 매소드
	 */
	@RequestMapping(value = "insertDepartment.htm", method = RequestMethod.POST)
	public String inesrtDepartment(DepartmentDTO department) {
		String view = "";
		int result = codeservice.insertDepartment(department);

		if (result == 1) {
			view = "redirect:departmentlist.htm";
		}
		return view;
	}
	/*
	 * @method Name : selectDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 사용가능한 사무실 리스트를 뿌려줘서 사무실 코드를 가져와 사무실을 선택할수 있게 해주는 매소드
	 */
	@RequestMapping("departmentDetail.htm")
	public String selectDepartment(String department_code, Model model) {

		DepartmentDTO department = codeservice.selectDepartment(department_code);
		List<OfficeDTO> officeslist = codeservice.possibleOffice();
		officeslist.add(codeservice.selectOffice(department.getOffice_code()));
		model.addAttribute("department", department);
		model.addAttribute("office", officeslist);

		return "codemg.departmentdetail";
	}
	/*
	 * @method Name : updateDepartment
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학과 정보를 수정하게 해주는 매소드
	 */
	@RequestMapping("updateDepartment.htm")
	public String updateDepartment(DepartmentDTO department, String before_office_code) {

		String view = "";
		int result = codeservice.updateDepartment(department, before_office_code);

		if (result == 1) {
			view = "redirect:departmentlist.htm";
		}
		return view;
	}
	/*
	 * @method Name : mjRecordList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 전공 정보 리스트를 출력하게 해주는 매소드
	 */
	@RequestMapping("mjRecordList.htm")
	public String mjRecordList(Model model) {

		List<MjRecordDTO> mjrecordlist = codeservice.mjRecordList();
		model.addAttribute("mjrecord", mjrecordlist);

		return "codemg.mjrecordlist";
	}
	/*
	 * @method Name : insertMjRecord
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 학생에게 전공 또는 부전공을 등록해주는 매소드
	 */
	@RequestMapping(value = "insertMjRecord.htm", method = RequestMethod.POST)
	public String insertMjRecord(MjRecordDTO mjrecord) {

		String view = "";
		int result = codeservice.insertMjRecord(mjrecord);

		if (result == 1) {
			view = "redirect:mjRecordList.htm";
		}
		return view;
	}

	/*
	 * MethodName : initSemester author : 성홍모, 권기엽 Desc : 학기 초기화 함수. Timetable,
	 * Lecture, Opened, Ask_time, Oprequest, rejection, reserve, enrollment 삭제
	 */
	@RequestMapping("initSemester.htm")
	public View initSemester(Model model) {

		boolean result = false;
		try {
			result = codeservice.initSemester();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result)
			model.addAttribute("result", "success");
		else
			model.addAttribute("result", "fail");

		return jsonview;
	}
	/*
	 * @method Name : registerlist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 등록 리스트를 출력하게 해주는 매소드 
	 */
	@RequestMapping("registerlist.htm")
	public String registerlist(Model model) {

		List<RegisterDTO> registerlist = codeservice.registerlist();
		model.addAttribute("register", registerlist);

		return "codemg.registerlist";
	}
	/*
	 * @method Name : insertRegister
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 등록을 삽입할수 있게 해주는 매소드 
	 */
	@RequestMapping("insertRegister.htm")
	public String insertRegister(RegisterDTO register) {

		String view = "";
		int result = codeservice.insertRegister(register);

		if (result == 1) {
			view = "redirect:registerlist.htm";
		}
		return view;
	}
	/*
	 * @method Name : showclasslist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "ClassBuildingDTO" 커스텀 DTO를 사용하여 강의실 정보와 건물 정보를 같이 출력해주는 매소드 
	 */
	@RequestMapping("showclasslist.htm")
	public String showclasslist(Model model) {
		List<ClassBuildingDTO> classlist = codeservice.showclasslist();
		model.addAttribute("classlist", classlist);
		List<BuildingDTO> buildingList = codeservice.buildingList();
		model.addAttribute("buildingList", buildingList);
		return "codemg.classroomlist";
	}
	/*
	 * @method Name : showlablist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "LabBuildingDTO" 커스텀 DTO를 사용하여 연구실 정보와 건물 정보를 같이 출력해주는 매소드 
	 */
	@RequestMapping("showlablist.htm")
	public String showlablist(Model model) {

		List<LabBuildingDTO> lablist = codeservice.showlablist();
		model.addAttribute("lablist", lablist);

		return "codemg.lablist";
	}
	/*
	 * @method Name : showofficelist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "OfiiceBuildingDTO" 커스텀 DTO를 사용하여 사무실 정보와 건물 정보를 같이 출력해주는 매소드 
	 */
	@RequestMapping("showofficelist.htm")
	public String showofficelist(Model model) {

		List<OfiiceBuildingDTO> office = codeservice.showofficelist();
		model.addAttribute("officelist", office);

		return "codemg.officelist";
	}
	/*
	 * @method Name : getProfessorList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description "ProfessorCodeRegDTO" 커스텀 DTO를 사용하여 교수 정보와 코드 정보를 같이 출력해주는 매소드 
	 */
	@RequestMapping("getProfessorList.htm")
	public View getProfessorList(Model model, String department_code) {

		List<ProfessorCodeRegDTO> pf_list = codeservice.getProfessorList(department_code);

		model.addAttribute("pf_list", pf_list);

		return jsonview;
	}
	/*
	 * @method Name : departmentLeaderRegist
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 교수중에서 학과장을 선택하게 해주는 매소드  
	 */
	@RequestMapping("departmentLeaderRegist.htm")
	public String departmentLeaderRegist(DepartmentLeaderDTO leader) {
		String viewPage = "redirect:code.htm";

		boolean result = codeservice.setDepartmentLeader(leader);
		if (result) {
			viewPage = "redirect:departmentLeaderList.htm";
		}
		return viewPage;
	}
	/*
	 * @method Name : callDepartmentLeaderList
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 교수중에서 학과장 리스트를 출력하게 해주는 매소드 
	 */
	@RequestMapping("departmentLeaderList.htm")
	public String callDepartmentLeaderList(Model model) {

		List<DepartmentLeaderDTO> list = codeservice.getDepartmentLeaderList();
		model.addAttribute("departmentLeaderList", list);
		return "codemg.departmentLeaderList";
	}
	/*
	 * @method Name : classroomBuildingSelect
	 * 
	 * @Author : 성홍모
	 * 
	 * @description 건물 리스트를 출력하여 건물 코드를 가져와서 원하는 건물을 선택하게 해주는 매소드 
	 */
	@RequestMapping("classroomBuildingSelect.htm")
	public View classroomBuildingSelect(String buildingCode, Model model) {
		List<ClassBuildingDTO> classroomList = new ArrayList<ClassBuildingDTO>();
		List<ClassBuildingDTO> list = codeservice.classroomBuildinSelect(buildingCode);
		if (list != null && list.size() != 0) {
			classroomList = list;
		}
		model.addAttribute("classroomList", classroomList);
		return jsonview;
	}
	/*
	 * @method Name : checkStudentCode
	 * 
	 * @Author : 권기엽
	 * 
	 * @description 학생코드를 가져와서 유효한 학생 코드인지를 판별하게 해주는 매소드 
	 */
	@RequestMapping("check_student_code.htm")
	public View checkStudentCode(Model model, String student_code){
		
		boolean result = codeservice.checkStudentCode(student_code);
		
		if(result)	model.addAttribute("result", "success");
		else	model.addAttribute("result", "fail");
		
		return jsonview;
	}
}
