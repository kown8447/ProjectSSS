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
import kr.or.initspring.dto.commons.MajorDTO;




public interface CollegeStudentDAO {

	public StudentInfoDTO getStudent(String userid);//학생 기본 정보 확인
	public List<StudentMajorDTO> getMajor(String student_code);//학생 전공 정보
	public StudentStateDTO getStudentState(String student_code);//학생 상태 정보
	public int absenceCount(String student_code);//학생 휴학 횟수 확인
	public List<StudentRecordDTO> getRecordFullList(String student_code);//학생성적 전체리스트
	public List<StudentRecordDTO> getRecordSelectList(RecordRequestDTO recordRequest);// 학생성적 학기별리스트
	public MajorDTO majorEssentialCheck(String subject_code);//전공 과목 필수 여부확인
	public int liberalEssentialCheck(String subject_code);//교양과목 필수 여부확인
	public List<StudentRegisterDTO> getStudentRegisterList(String student_code);//학생 등록기록 리스트
	public List<StudentSemesterStateDTO> getStudentSemesterList(String student_code);//학생 재학기록 리스트
	public List<StudentScholarshipDTO> getStudentScholarshipList(String student_code);//학생 장학기록 리스트
	public List<StudentAbsenceDTO> getStudentAbsenceList(String student_code);//학생 휴학 기록 리스트
	
}
