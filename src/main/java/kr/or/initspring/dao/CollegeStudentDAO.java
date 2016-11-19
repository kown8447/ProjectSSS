package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.MajorDTO;
import kr.or.initspring.dto.RecordDTO;
import kr.or.initspring.dto.StudentInfoDTO;
import kr.or.initspring.dto.StudentStateDTO;

public interface CollegeStudentDAO {

	public StudentInfoDTO getStudent(String userid);
	public List<MajorDTO> getMajor(String student_code);
	public StudentStateDTO getStudentState(String student_code);
	public int semesterCount(String student_code);
	public int absenceCount(String student_code);
	public List<RecordDTO> getRecordFullList(String student_code);
	public List<RecordDTO> getRecordSelectList(String student_code,int grade,int semester);
	public int majorEssentialCheck(String subject_code);
	public int liberalEssentialCheck(String subject_code);
}
