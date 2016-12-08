package kr.or.initspring.service;

import java.io.FileOutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.initspring.dao.LectureMgDAO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.BuildingDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.PfMajorDTO;
import kr.or.initspring.dto.commons.RecordDTO;
import kr.or.initspring.dto.commons.SemesterDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.lectureMg.CustomLectureMgDTO;

@Service
public class lectureService {

	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : Request_List
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 등록한 과목 리스트 출력
	 */
	public List<CustomLectureMgDTO> Request_List(Principal principal) {

		List<CustomLectureMgDTO> list = new ArrayList<CustomLectureMgDTO>();

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String professorcode = lecturedao.select_Professor(principal.getName());
		list = lecturedao.select_list(professorcode);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubject_type() == 0) {
				list.get(i).setRequired_choice(lecturedao.select_major(list.get(i).getSubject_code()));
			} else {
				list.get(i).setRequired_choice(lecturedao.select_liberal(list.get(i).getSubject_code()));
			}
		}

		return list;
	}

	/*
	 * @method Name : Insert_Subject
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 과목 등록
	 */

	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class,
			RuntimeException.class })
	public synchronized int insert_Subject(SubjectDTO dto, String subject_name, Principal principal,
			String required_choice, BeforeSubjectDTO beforedto, MajorDTO majordto, LiberalDTO liberdto,
			String department_code) throws Exception {

		int result = 0;
		int before = 0;

		String principalid = principal.getName();
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		String subject_codenum = lecturedao.maxSubject_code();
		String subject_code = "SJ_" + subject_codenum;

		dto.setSubject_code(subject_code);

		try {
			String insertparam = lecturedao.select_Professor(principalid);
			dto.setProfessor_code(insertparam);
			lecturedao.insert_Subject(dto);
			int subject_type = dto.getSubject_type();
			int required_select = 5;

			department_code = lecturedao.select_departmentcode(principalid).getDepartment_code();

			if (subject_type == 0) {
				required_select = lecturedao.insert_major(subject_code, required_choice, department_code);

				if (!subject_name.equals("0")) {
					before = lecturedao.insert_BeforeName(subject_code, subject_name);
				}
			} else if (subject_type == 1) {
				required_select = lecturedao.insert_Liberal(subject_code, required_choice);

				if (!subject_name.equals("0")) {
					before = lecturedao.insert_BeforeName(subject_code, "없음");
				}
			}

		} catch (Exception e) {
			System.out.println("장현 등록 트랜잭션 오류 : " + e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return result;
	}
	/*
	 * @method Name : selectBefore
	 * 
	 * @Author : 조장현
	 * 
	 * @description : before_code로 subject_name 호출
	 */

	public void selectBefore(String memberid, Model model) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		PfMajorDTO dto = lecturedao.select_departmentcode(memberid);
		List<String> before = lecturedao.select_Before(dto.getDepartment_code());
		model.addAttribute("before", before);

	}

	/*
	 * @method Name : subjectDetail
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 과목상세보기
	 */
	public CustomLectureMgDTO subjectDetail(String subject_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		CustomLectureMgDTO detail = lecturedao.subject_Detail(subject_code);
		String reject_reason = lecturedao.select_Rejection(subject_code);
		if (detail.getSubject_type() == 0) {
			detail = lecturedao.detail_major(subject_code);
			if (detail.getBefore_name() == null || detail.getBefore_name().equals("")) {
				detail.setBefore_name("없음");
			}

		} else if (detail.getSubject_type() == 1) {
			detail = lecturedao.detail_liberal(subject_code);
			detail.setBefore_name("없음");
		}

		detail.setReject_reason(reject_reason);

		return detail;
	}

	/*
	 * @method Name : selectperiod
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 교시 정보 조회
	 */

	public CustomLectureMgDTO selectperiod(String subject_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		CustomLectureMgDTO building = lecturedao.select_period_build(subject_code);
		return building;
	}

	/*
	 * @method Name : Request_List
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 과목수정
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class,
			RuntimeException.class })
	public int updatesubject(CustomLectureMgDTO dto) throws Exception {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		try {
			lecturedao.update_Subject(dto);
			lecturedao.update_before_subject(dto);

		} catch (Exception e) {
			System.out.println("장현 수정 트랜잭션 오류 : " + e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return 0;

	}

	/*
	 * @method Name : deleteSubject
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 과목삭제
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void deleteSubject(String subject_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		try {
			lecturedao.delete_Before(subject_code);
			lecturedao.delete_Liberal(subject_code);
			lecturedao.delete_Major(subject_code);
			lecturedao.delete_Plan(subject_code);
			lecturedao.delete_Ask_Time(subject_code);
			lecturedao.delete_Subject(subject_code);

		} catch (Exception e) {
			System.out.println("장현 삭제 트랜잭션 오류 : " + e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/*
	 * @method Name : RequestSubject
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 과목 신청하기
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class, NullPointerException.class,
			RuntimeException.class })
	public void RequestSubject(CustomLectureMgDTO dto, HttpServletRequest request, String success_check)
			throws Exception {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		// 재신청 할 경우
		if (success_check.equals("2")) {
			lecturedao.delete_Ask_Time(dto.getSubject_code());
			lecturedao.delete_Rejection(dto.getSubject_code());
			lecturedao.delete_Plan(dto.getSubject_code());
			lecturedao.delete_Oprequest(dto.getSubject_code());
		}

		String period_code = dto.getPeriod_code();

		String code[] = period_code.split(",");
		String subject_code = dto.getSubject_code();
		String classroom_code = dto.getClassroom_code();

		try {
			lecturedao.setOprequest(dto);

			for (int i = 0; i < code.length; i++) {
				lecturedao.setAskTime(classroom_code, code[i].trim(), subject_code);
			}

			CommonsMultipartFile file = dto.getSubject_filename();
			String filename = null;
			String filesrc = null;

			if (file != null && file.getSize() > 0) {
				String fname = file.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/files/lecture");
				String fullpath = path + "\\" + fname;
				if (!fname.equals("")) {
					FileOutputStream fs = new FileOutputStream(fullpath);
					fs.write(file.getBytes());
					fs.close();
				}
				filename = fname;
				filesrc = path;
			}

			dto.setSubject_filename(file);
			dto.setSubject_filesrc(filename);

			lecturedao.insert_Plan(dto);

		} catch (Exception e) {
			System.out.println("장현 신청 트랜잭션 오류 : " + e.getMessage());
			throw e;
		}

	}

	/*
	 * @method Name : viewtimetable
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 개설된 과목들 시간표에 불러오기
	 */
	public List<CustomLectureMgDTO> viewtimetable(String classroom_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);

		List<CustomLectureMgDTO> dto = lecturedao.viewtimetable(classroom_code);
		return dto;
	}

	/*
	 * @method Name : selectBuilding
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 건물명 조회
	 */
	public List<CustomLectureMgDTO> selectBuilding(String building_code) {
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<CustomLectureMgDTO> dto = lecturedao.selectAllBuilding(building_code);

		return dto;
	}

	/*
	 * @method Name : getPeriodList
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 시간표 교시 출력
	 */
	public List<PeriodDTO> getPeriodList() {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<PeriodDTO> dto = lecturedao.getPeriodList();

		return dto;
	}

	/*
	 * @method Name : getBuildingName
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 건물 이름 출력
	 */
	public List<BuildingDTO> getBuildingName() {
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<BuildingDTO> building = lecturedao.select_BuildingName();

		return building;
	}

	/*
	 * @method Name : getSemester
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 학기테이블 조회
	 */
	public List<SemesterDTO> getSemester() {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<SemesterDTO> dto = lecturedao.getSemester();

		return dto;
	}

	/*
	 * @method Name : selectMyclass
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 학생 성적등록시 내 과목 출력
	 */
	public List<CustomLectureMgDTO> selectMyclass(Principal principal) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String professor_code = lecturedao.select_Professor(principal.getName());

		List<CustomLectureMgDTO> dto = lecturedao.select_myClass(professor_code);

		return dto;

	}

	/*
	 * @method Name : select_Studentlist
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 내 과목 수강생 조회
	 */
	public List<CustomLectureMgDTO> select_Studentlist(String subject_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<CustomLectureMgDTO> dto = lecturedao.select_Studentlist(subject_code);
		if (dto != null || dto.size() != 0) {
			for (int i = 0; i < dto.size(); i++) {
				String recordLevel = lecturedao.select_recordlevel(dto.get(i).getStudent_code(),
						dto.get(i).getSemester_code(), subject_code);
				dto.get(i).setRecord_level(recordLevel);
			}
		} else {
			dto = new ArrayList<CustomLectureMgDTO>();
		}

		return dto;
	}

	/*
	 * @method Name : insertgrade
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 성적 입력
	 */
	public CustomLectureMgDTO insertgrade(CustomLectureMgDTO dto) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		String max_record_code = lecturedao.maxRecord_code();
		String subject_name = lecturedao.select_subjectname(dto.getSubject_code());

		dto.setRecord_code(max_record_code);
		dto.setSemester_code(lecturedao.getMaxSemesterCode());
		CustomLectureMgDTO state = lecturedao.select_stState(dto.getStudent_code());

		String record_code = lecturedao.select_Recordlevel(dto.getStudent_code(), lecturedao.getMaxSemesterCode(), dto.getSubject_code());

		if (record_code == null || record_code.equals("")) { // 현재 성적이 없다면(레코드 코드가 없다면)
			List<String> secondsubject = lecturedao.select_reStudy(subject_name, dto.getStudent_code());

			dto.setRecord_grade(state.getGrade());
			dto.setRecord_semester(state.getPersonal_semester());

			if (secondsubject != null) {
				for (int i = 0; i < secondsubject.size(); i++) {
					lecturedao.update_RetakeCheck(secondsubject.get(i));
				}
			}
			lecturedao.insert_record(dto);
		} else {
			dto.setRecord_code(record_code);
			lecturedao.update_record(dto);
		}

		return dto;
	}

	/*
	 * @method Name : deleteForReSubject
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 재신청 삭제
	 */

	public void deleteForReSubject(String subject_code) {

		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		lecturedao.delete_Ask_Time(subject_code);
		lecturedao.delete_Rejection(subject_code);
		lecturedao.delete_Plan(subject_code);
		lecturedao.delete_Oprequest(subject_code);
	}


	/*
	 * @method Name : selectMyTime
	 * 
	 * @Author : 조장현
	 * 
	 * @description : 교수가 강의하는 전체 시간 조회
	 */

	
		public List<PeriodDTO> selectMyTime(String professor_code){
		
		LectureMgDAO lecturedao = sqlsession.getMapper(LectureMgDAO.class);
		List<PeriodDTO> myclass = lecturedao.select_MyTime(professor_code);
		
		return myclass;
		

	}

}
