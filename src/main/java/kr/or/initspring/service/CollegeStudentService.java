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
import kr.or.initspring.dto.collegeRegister.SubjectMajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentMajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentRecordDTO;
import kr.or.initspring.dto.collegeRegister.RecordRequestDTO;
import kr.or.initspring.dto.collegeRegister.StudentInfoDTO;
import kr.or.initspring.dto.collegeRegister.StudentStateDTO;

@Service
public class CollegeStudentService {
	@Autowired
	private SqlSession sqlsession;

	@Transactional(rollbackFor = { Exception.class, SQLException.class })
	public void viewStudentInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);
		student.setEnterYear(Integer.parseInt(student.getStudent_code().substring(0, 4)));

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

		int semesterCount = collegestudentdao.semesterCount(student.getStudent_code());
		model.addAttribute("semesterCount", semesterCount);

		int absenceCount = collegestudentdao.absenceCount(student.getStudent_code());
		model.addAttribute("absenceCount", absenceCount);

	}

	public void viewStudentRecordInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);

		StudentStateDTO state = collegestudentdao.getStudentState(student.getStudent_code());
		model.addAttribute("state", state);

		List<StudentRecordDTO> recordList = collegestudentdao.getRecordFullList(student.getStudent_code());

		int majorCredit = 0;
		int doubleCredit = 0;
		int liberalCredit = 0;

		List<StudentMajorDTO> majorList = collegestudentdao.getMajor(student.getStudent_code());

		boolean doubleMajor = false;
		String mainMajor = "";

		if (majorList.size() > 1) {
			for (int j = 0; j < majorList.size(); j++) {
				if (majorList.get(j).getMj_type() == 0) {
					mainMajor = majorList.get(j).getDepartment_code();
				}
			}
			doubleMajor = true;
		}

		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getSubject_type() == 0) {
				SubjectMajorDTO major = collegestudentdao.majorEssentialCheck(recordList.get(i).getSubject_code());
				if (major.getRequired_choice() == 0) {
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

		System.out.println(state.toString());
		model.addAttribute("state", state);
		model.addAttribute("recordList", recordList);
		model.addAttribute("majorCredit", majorCredit);
		model.addAttribute("liberalCredit", liberalCredit);
		model.addAttribute("doubleCredit", doubleCredit);
		model.addAttribute("totalCredit", majorCredit + liberalCredit + doubleCredit);
		model.addAttribute("inF", inF);
		model.addAttribute("outF", outF);
	}

	public void viewStudentRecordAjax(RecordRequestDTO recordrequest, String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

		StudentInfoDTO student = collegestudentdao.getStudent(userid);

		StudentStateDTO state = collegestudentdao.getStudentState(student.getStudent_code());
		model.addAttribute("state", state);
		recordrequest.setStudent_code(student.getStudent_code());

		List<StudentRecordDTO> recordList = collegestudentdao.getRecordSelectList(recordrequest);
		int majorCredit = 0;
		int doubleCredit = 0;
		int liberalCredit = 0;

		List<StudentMajorDTO> majorList = collegestudentdao.getMajor(student.getStudent_code());

		boolean doubleMajor = false;
		String mainMajor = "";

		if (majorList.size() > 1) {
			for (int j = 0; j < majorList.size(); j++) {
				if (majorList.get(j).getMj_type() == 0) {
					mainMajor = majorList.get(j).getDepartment_code();
				}
			}
			doubleMajor = true;
		}

		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getSubject_type() == 0) {
				SubjectMajorDTO major = collegestudentdao.majorEssentialCheck(recordList.get(i).getSubject_code());
				if (major.getRequired_choice() == 0) {
					recordList.get(i).setStringtype("전공 필수");
				} else {
					recordList.get(i).setStringtype("전공 선택");
				}

				if (recordList.get(i).getRetake_check() == 0) {
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

				if (recordList.get(i).getRetake_check() == 0) {
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
		System.out.println(inF);
		model.addAttribute("inF", inF);
		model.addAttribute("outF", outF);
	}

	public void viewRegisterInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

	}

	public float creditCalculatorInF(List<StudentRecordDTO> recordList) {
		int totalCredit = 0;
		float totalScore = 0.0f;
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getRetake_check() == 0) {
				totalCredit += recordList.get(i).getSubject_credit();
				String level = recordList.get(i).getRecord_level();
				if (level.equals("A+")) {
					totalScore += 4.5f;
				} else if (level.equals("A")) {
					totalScore += 4.0f;
				} else if (level.equals("B+")) {
					totalScore += 3.5f;
				} else if (level.equals("B")) {
					totalScore += 3.0f;
				} else if (level.equals("C+")) {
					totalScore += 2.5f;
				} else if (level.equals("C")) {
					totalScore += 2.0f;
				} else if (level.equals("D+")) {
					totalScore += 1.5f;
				} else if (level.equals("D")) {
					totalScore += 1.0f;
				} else if (level.equals("F")) {
					totalScore += 0;
				}
			}
		}
		if (totalScore == 0 || totalCredit == 0) {
			return 0;
		}
		return totalScore / totalCredit;
	}

	public float creditCalculatorOutF(List<StudentRecordDTO> recordList) {
		int totalCredit = 0;
		float totalScore = 0.0f;
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getRetake_check() == 0) {
				String level = recordList.get(i).getRecord_level();
				if (level.equals("A+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 4.5f;
				} else if (level.equals("A")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 4.0f;
				} else if (level.equals("B+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 3.5f;
				} else if (level.equals("B")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 3.0f;
				} else if (level.equals("C+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 2.5f;
				} else if (level.equals("C")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 2.0f;
				} else if (level.equals("D+")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 1.5f;
				} else if (level.equals("D")) {
					totalCredit += recordList.get(i).getSubject_credit();
					totalScore += 1.0f;
				}
			}
		}
		if (totalScore == 0 || totalCredit == 0) {
			return 0;
		}
		return totalScore / totalCredit;
	}

}
