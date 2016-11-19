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
import kr.or.initspring.dto.MajorDTO;
import kr.or.initspring.dto.RecordDTO;
import kr.or.initspring.dto.StudentInfoDTO;
import kr.or.initspring.dto.StudentStateDTO;

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

		List<MajorDTO> major = collegestudentdao.getMajor(student.getStudent_code());
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

		List<RecordDTO> recordList = collegestudentdao.getRecordFullList(student.getStudent_code());

		int majorCredit=0;
		int doubleCredit=0;
		int liberalCredit=0;
		List<MajorDTO> majorList = collegestudentdao.getMajor(student.getStudent_code());
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).getSubject_type() == 0) {
				
				
				if (collegestudentdao.majorEssentialCheck(recordList.get(i).getSubject_code()) == 0) {
					recordList.get(i).setStringtype("전공 필수");
				} else {
					recordList.get(i).setStringtype("전공 선택");
				}
				
				if(majorList.size()<2){
					majorCredit+=recordList.get(i).getSubject_credit();
				}else{
					for(int j=0;j<majorList.size();j++){
						if(){
							majorCredit+=recordList.get(i).getSubject_credit();
						}else{
							doubleCredit+=recordList.get(i).getSubject_credit();
						}
					}
				}
				
				
			} else {
				if (collegestudentdao.liberalEssentialCheck(recordList.get(i).getSubject_code()) == 0) {
					recordList.get(i).setStringtype("교양 필수");
				} else {
					recordList.get(i).setStringtype("교양 선택");
				}
				liberalCredit+=recordList.get(i).getSubject_credit();
			}
		}

		model.addAttribute("recordList", recordList);

	}

	public void viewRegisterInfo(String userid, Model model) {
		CollegeStudentDAO collegestudentdao = sqlsession.getMapper(CollegeStudentDAO.class);

	}
}
