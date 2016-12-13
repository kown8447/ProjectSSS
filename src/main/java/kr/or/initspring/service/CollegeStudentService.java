/*
 * @Class : CollegeRegisterService
 * @Date : 2016.11.16
 * @Author : 최준호
 * @Desc
 * 학적 조회 서비스 
*/
package kr.or.initspring.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import kr.or.initspring.dao.CollegeStudentDAO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentMajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentRecordDTO;
import kr.or.initspring.dto.collegeRegister.StudentRegisterDTO;
import kr.or.initspring.dto.collegeRegister.StudentScholarshipDTO;
import kr.or.initspring.dto.collegeRegister.StudentSemesterStateDTO;
import kr.or.initspring.dto.collegeRegister.RecordRequestDTO;
import kr.or.initspring.dto.collegeRegister.StudentAbsenceDTO;
import kr.or.initspring.dto.collegeRegister.StudentInfoDTO;
import kr.or.initspring.dto.collegeRegister.StudentStateDTO;

@Service
public class CollegeStudentService {
	@Autowired
	private SqlSession sqlsession;

	/*
	 * @method Name : viewStudentInfo
	 * @Author : 최준호
	 * @description 학생이 자신의 개인정보 열람을 요청했을시 해당정보를 찾아주는 함수
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void viewStudentInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);
		student.setEnterYear(Integer.parseInt(student.getStudent_code().substring(0, 4)));
		String address = student.getMember_addr();
	    String [] array = address.split("\\?");
	    student.setMember_addr(array[1] +" "+ array[2]);

		model.addAttribute("student", student);

		List<StudentMajorDTO> major = collegestudentdao.getMajor(student.getStudent_code());
		for (int i = 0; i < major.size(); i++) {
			if (major.get(i).getMj_type() == 0) {
				model.addAttribute("mainMajor", major.get(i));
			} else {
				model.addAttribute("doubleMajor", major.get(i));
			}
		}
		StudentStateDTO state = collegestudentdao.getStudentState(student.getStudent_code());
		model.addAttribute("state", state);

		int semesterCount = ((state.getGrade() - 1) * 2) + (state.getPersonal_semester() - 1);

		model.addAttribute("semesterCount", semesterCount);

		int absenceCount = collegestudentdao.absenceCount(student.getStudent_code());
		model.addAttribute("absenceCount", absenceCount);

	}

	/*
	 * @method Name : viewStudentRecordInfo
	 * @Author : 최준호
	 * @description 학생이 자신의 성적정보 열람을 요청했을시 전체성적을 가져오는 함수
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void viewStudentRecordInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);
		model.addAttribute("student", student);
		List<StudentRecordDTO> recordList = collegestudentdao.getRecordFullList(student.getStudent_code());

		recordListModelSeting(collegestudentdao, model, recordList, student.getStudent_code());

	}

	/*
	 * @method Name : viewStudentRecordAjax
	 * @Author : 최준호
	 * @description 학생의 비동기 요청에 대해 학기별 성적정보를 찾아주는 함수
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void viewStudentRecordAjax(RecordRequestDTO recordrequest, String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);
		model.addAttribute("student", student);
		
		recordrequest.setStudent_code(student.getStudent_code());
		List<StudentRecordDTO> recordList = collegestudentdao.getRecordSelectList(recordrequest);

		recordListModelSeting(collegestudentdao, model, recordList, student.getStudent_code());
	}

	/*
	 * @method Name : recordListModelSeting
	 * @Author : 최준호
	 * @description 비동기 요청과 일반 요청의 공통 로직을 분리하여 정리
	 */
	public void recordListModelSeting(CollegeStudentDAO collegestudentdao, Model model,
			List<StudentRecordDTO> recordList, String student_code) {
		StudentStateDTO state = collegestudentdao.getStudentState(student_code);
		model.addAttribute("state", state);

		int majorCredit = 0;
		int doubleCredit = 0;
		int liberalCredit = 0;

		List<StudentMajorDTO> majorList = collegestudentdao.getMajor(student_code);

		boolean doubleMajor = false;
		String mainMajor = "";

		if (majorList.size() > 1) {
			for (int j = 0; j < majorList.size(); j++) {
				if (majorList.get(j).getMj_type() == 0) {
					mainMajor = majorList.get(j).getDepartment_code();
					model.addAttribute("mainMajor", majorList.get(j));
				}
			}
			doubleMajor = true;
		}else{
			model.addAttribute("mainMajor", majorList.get(0));
		}

		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getSubject_type() == 0) {
				MajorDTO major = collegestudentdao.majorEssentialCheck(recordList.get(i).getSubject_code());
				if (major.getRequired_choice().equals("0")) {
					recordList.get(i).setStringtype("전공 필수");
				} else {
					recordList.get(i).setStringtype("전공 선택");
				}

				if (recordList.get(i).getRetake_check() != 1 && !recordList.get(i).getRecord_level().equals("F")) {
					if (doubleMajor) {
						if (mainMajor.equals(major.getDepartment_code())) {
							majorCredit += recordList.get(i).getSubject_credit();
						} else {
							doubleCredit += recordList.get(i).getSubject_credit();
						}
					} else {
						majorCredit += recordList.get(i).getSubject_credit();
					}
				}

			} else {
				if (collegestudentdao.liberalEssentialCheck(recordList.get(i).getSubject_code()) == 0) {
					recordList.get(i).setStringtype("교양 필수");
				} else {
					recordList.get(i).setStringtype("교양 선택");
				}

				if (recordList.get(i).getRetake_check() != 1 && !recordList.get(i).getRecord_level().equals("F")) {
					liberalCredit += recordList.get(i).getSubject_credit();
				}
			}

		}
		float inF = creditCalculatorInF(recordList);
		float outF = creditCalculatorOutF(recordList);

		model.addAttribute("state", state);
		model.addAttribute("recordList", recordList);
		model.addAttribute("majorCredit", majorCredit);
		model.addAttribute("liberalCredit", liberalCredit);
		model.addAttribute("doubleCredit", doubleCredit);
		model.addAttribute("totalCredit", majorCredit + liberalCredit + doubleCredit);
		model.addAttribute("inF", inF);
		model.addAttribute("outF", outF);
	}

	/*
	 * @method Name : viewRegisterInfo
	 * @Author : 최준호
	 * @description 학생의 등록, 장학 정보를 열람하는 함수
	 */
	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void viewRegisterInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);

		List<StudentRegisterDTO> registerList = collegestudentdao.getStudentRegisterList(student.getStudent_code());
		for (int i = 0; i < registerList.size(); i++) {
			String[] semesterparts = registerList.get(i).getSemester_code().split("_");
			registerList.get(i).setSemesterYear(semesterparts[1] + "년");
			if (semesterparts[2].equals("1")) {
				registerList.get(i).setSemesterType("전반기");
			} else if (semesterparts[2].equals("2")) {
				registerList.get(i).setSemesterType("후반기");
			}
		}
		List<StudentSemesterStateDTO> studentSemesterList = collegestudentdao
				.getStudentSemesterList(student.getStudent_code());
		for (int i = 0; i < studentSemesterList.size(); i++) {
			String[] semesterparts = studentSemesterList.get(i).getSemester_code().split("_");
			studentSemesterList.get(i).setSemesterYear(semesterparts[1] + "년");
			if (semesterparts[2].equals("1")) {
				studentSemesterList.get(i).setSemesterType("전반기");
			} else if (semesterparts[2].equals("2")) {
				studentSemesterList.get(i).setSemesterType("후반기");
			}
		}
		List<StudentScholarshipDTO> scholarshipList = collegestudentdao
				.getStudentScholarshipList(student.getStudent_code());
		for (int i = 0; i < scholarshipList.size(); i++) {
			String[] semesterparts = scholarshipList.get(i).getSemester_code().split("_");
			scholarshipList.get(i).setSemesterYear(semesterparts[1] + "년");
			if (semesterparts[2].equals("1")) {
				scholarshipList.get(i).setSemesterType("전반기");
			} else if (semesterparts[2].equals("2")) {
				scholarshipList.get(i).setSemesterType("후반기");
			}
		}

		List<StudentAbsenceDTO> absenceList = collegestudentdao.getStudentAbsenceList(student.getStudent_code());

		model.addAttribute("registerList", registerList);
		model.addAttribute("studentSemesterList", studentSemesterList);
		model.addAttribute("scholarshipList", scholarshipList);
		model.addAttribute("absenceList", absenceList);

	}

	/*
	 * @method Name : creditCalculatorInF
	 * @Author : 최준호
	 * @description F학점을 포함한 학점평균을 구하는 함수
	 */
	public float creditCalculatorInF(List<StudentRecordDTO> recordList) {
		int totalCredit = 0;
		float totalScore = 0.0f;
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getRetake_check() == 0) {
				totalCredit += recordList.get(i).getSubject_credit();
				String level = recordList.get(i).getRecord_level();
				if (level.equals("A+")) {
					totalScore += 4.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("A")) {
					totalScore += 4.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("B+")) {
					totalScore += 3.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("B")) {
					totalScore += 3.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("C+")) {
					totalScore += 2.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("C")) {
					totalScore += 2.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("D+")) {
					totalScore += 1.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("D")) {
					totalScore += 1.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("F")) {
					totalScore += 0*recordList.get(i).getSubject_credit();;
				}
			}
		}
		if (totalScore == 0 || totalCredit == 0) {
			return 0;
		}

		return Math.round((totalScore / totalCredit) * 100f) / 100f;
	}

	/*
	 * @method Name : creditCalculatorOutF
	 * @Author : 최준호
	 * @description F학점을 제외한 학점평균을 구하는 함수
	 */
	public float creditCalculatorOutF(List<StudentRecordDTO> recordList) {
		int totalCredit = 0;
		float totalScore = 0.0f;
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getRetake_check() == 0) {
				String level = recordList.get(i).getRecord_level();
				if (level.equals("A+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 4.5f*recordList.get(i).getSubject_credit();
				} else if (level.equals("A")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 4.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("B+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 3.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("B")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 3.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("C+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 2.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("C")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 2.0f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("D+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 1.5f*recordList.get(i).getSubject_credit();;
				} else if (level.equals("D")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 1.0f*recordList.get(i).getSubject_credit();;
				}
			}
		}
		if (totalScore == 0 || totalCredit == 0) {
			return 0;
		}
		return Math.round((totalScore / totalCredit) * 100f) / 100f;
	}

}
