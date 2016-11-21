package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.collegeRegister.RecordRequestDTO;
import kr.or.initspring.dto.collegeRegister.StudentAbsenceDTO;
import kr.or.initspring.dto.collegeRegister.StudentInfoDTO;
import kr.or.initspring.dto.collegeRegister.StudentMajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentRecordDTO;
import kr.or.initspring.dto.collegeRegister.StudentRegisterDTO;
import kr.or.initspring.dto.collegeRegister.StudentScholarshipDTO;
import kr.or.initspring.dto.collegeRegister.StudentSemesterStateDTO;
import kr.or.initspring.dto.collegeRegister.StudentStateDTO;
import kr.or.initspring.dto.collegeRegister.SubjectMajorDTO;



public interface CollegeStudentDAO {

	public StudentInfoDTO getStudent(String userid);
	public List<StudentMajorDTO> getMajor(String student_code);
	public StudentStateDTO getStudentState(String student_code);
	public int semesterCount(String student_code);
	public int absenceCount(String student_code);
	public List<StudentRecordDTO> getRecordFullList(String student_code);
	public List<StudentRecordDTO> getRecordSelectList(RecordRequestDTO recordRequest);
	public SubjectMajorDTO majorEssentialCheck(String subject_code);
	public int liberalEssentialCheck(String subject_code);
	public List<StudentRegisterDTO> getStudentRegisterList(String student_code);
	public List<StudentSemesterStateDTO> getStudentSemesterList(String student_code);
	public List<StudentScholarshipDTO> getStudentScholarshipList(String student_code);
	public List<StudentAbsenceDTO> getStudentAbsenceList(String student_code);
	
}
