package kr.or.initspring.scheduler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import kr.or.initspring.dao.RequestCourseDAO;
import kr.or.initspring.dto.commons.ReserveDTO;
import kr.or.initspring.dto.commons.SubjectDTO;

public class ModifyTermScheduler {

	@Autowired
	private SqlSession sqlsession;

	//////////////예비 수강 신청 24시간 전//////////////////
	
	@Scheduled(cron="${wait.pre.firstStartDate}")
	public void waitFirstStart() {	setEnrollActive(1, 0, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.pre.secondStartDate}")
	public void waitSecondStart() {	setEnrollActive(2, 0, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.pre.thirdStartDate}")
	public void waitThirdStart() {	setEnrollActive(3, 0, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.pre.fourthStartDate}")
	public void waitFourthStart() {	setEnrollActive(4, 0, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.pre.commonStartDate}")
	public void waitCommonStart() {	setEnrollActive(0, 0, 2);System.out.println("대기 시간..");}
	
	
	//////////////예비 수강 신청 기간///////////////////
	
	@Scheduled(cron="${pre.firstGrade.startDate}")
	public void firstGradeStart() throws Exception {	
		setEnrollActive(1,0, 1);	
		setReserveCheck(); 
		System.out.println("1학년 시작");
	}
	
	@Scheduled(cron="${pre.firstGrade.endDate}")
	public void firstGradeEnd() throws Exception { 
		setEnrollActive(1,0, 0);	
		setReserveCheck();
		System.out.println("1학년 끝");
	}
	
	@Scheduled(cron="${pre.secondGrade.startDate}")
	public void secondGradeStart() throws Exception { 
		setEnrollActive(2,0, 1);	
		setReserveCheck();
		System.out.println("2학년 시작");
	}
	
	@Scheduled(cron="${pre.secondGrade.endDate}")
	public void secondGradeEnd() throws Exception { 
		setEnrollActive(2,0, 0);	
		setReserveCheck();
		System.out.println("2학년 끝");
	}
	
	@Scheduled(cron="${pre.thirdGrade.startDate}")
	public void thirdGradeStart() throws Exception { 
		setEnrollActive(3,0, 1);	
		setReserveCheck();
		System.out.println("3학년 시작");
	}
	
	@Scheduled(cron="${pre.thirdGrade.endDate}")
	public void thirdGradeEnd() throws Exception { 
		setEnrollActive(3,0, 0);
		setReserveCheck();
		System.out.println("3학년 끝");
	}
	
	@Scheduled(cron="${pre.fourthGrade.startDate}")
	public void fourthGradeStart() throws Exception { 
		setEnrollActive(4,0, 1);	
		setReserveCheck();
		System.out.println("4학년 시작");
	}
	
	@Scheduled(cron="${pre.fourthGrade.endDate}")
	public void fourthGradeEnd() throws Exception { 
		setEnrollActive(4,0, 0);	
		setReserveCheck();
		System.out.println("4학년 끝");
	}
	
	@Scheduled(cron="${pre.AllGrade.startDate}")
	public void AllGradeStart() throws Exception { 
		setEnrollActive(0,0, 1);	
		setReserveCheck();
		System.out.println("전학년 시작");
	}
	
	@Scheduled(cron="${pre.AllGrade.endDate}")
	public void AllGradeEnd() throws Exception { 
		setEnrollActive(0,0, 0);
		setReserveCheck();
		System.out.println("전학년 끝");
	}
	
	
	//////////////본 수강 신청 24시간 전//////////////////
	
	@Scheduled(cron="${wait.mid.firstStartDate}")
	public void waitFirstMidStart() {	setEnrollActive(1, 1, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.mid.secondStartDate}")
	public void waitSecondMidStart() {	setEnrollActive(2, 1, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.mid.thirdStartDate}")
	public void waitThirdMidStart() {	setEnrollActive(3, 1, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.mid.fourthStartDate}")
	public void waitFourthMidStart() {	setEnrollActive(4, 1, 2);System.out.println("대기 시간..");}
	
	@Scheduled(cron="${wait.mid.commonStartDate}")
	public void waitCommonMidStart() {	setEnrollActive(0, 1, 2);System.out.println("대기 시간..");}
	
	
	
	
	////////////////////////////////본수강 신청 기간 설정///////////////////////////////
	
	@Scheduled(cron="${mid.firstGrade.startDate}")
	public void firstGradeMidStart() throws Exception {	
		setEnrollActive(1,1, 1);	
		System.out.println("1학년 시작");
	}
	
	@Scheduled(cron="${mid.firstGrade.endDate}")
	public void firstGradeMidEnd() throws Exception { 
		setEnrollActive(1,1, 0);	
		System.out.println("1학년 끝");
	}
	
	@Scheduled(cron="${mid.secondGrade.startDate}")
	public void secondGradeMidStart() throws Exception { 
		setEnrollActive(2,1, 1);	
		System.out.println("2학년 시작");
	}
	
	@Scheduled(cron="${mid.secondGrade.endDate}")
	public void secondGradeMidEnd() throws Exception { 
		setEnrollActive(2,1, 0);	
		System.out.println("2학년 끝");
	}
	
	@Scheduled(cron="${mid.thirdGrade.startDate}")
	public void thirdGradeMidStart() throws Exception { 
		setEnrollActive(3,1, 1);	
		System.out.println("3학년 시작");
	}
	
	@Scheduled(cron="${mid.thirdGrade.endDate}")
	public void thirdGradeMidEnd() throws Exception { 
		setEnrollActive(3,1, 0);
		System.out.println("3학년 끝");
	}
	
	@Scheduled(cron="${mid.fourthGrade.startDate}")
	public void fourthGradeMidStart() throws Exception { 
		setEnrollActive(4,1, 1);	
		System.out.println("4학년 시작");
	}
	
	@Scheduled(cron="${mid.fourthGrade.endDate}")
	public void fourthGradeMidEnd() throws Exception { 
		setEnrollActive(4,1, 0);	
		System.out.println("4학년 끝");
	}
	
	@Scheduled(cron="${mid.AllGrade.startDate}")
	public void AllGradeMidStart() throws Exception { 
		setEnrollActive(0,1, 1);	
		System.out.println("전학년 시작");
	}
	
	@Scheduled(cron="${mid.AllGrade.endDate}")
	public void AllGradeMidEnd() throws Exception { 
		setEnrollActive(0,1, 0);
		System.out.println("전학년 끝");
	}
	
	
	////////////// 수강 정정 24시간 전//////////////////
		
	@Scheduled(cron="${wait.after.StartDate}")
	public void waitAllAfterStart() {setEnrollActive(0, 2, 2);System.out.println("대기 시간..");}

	
	
	///////////////////수강 정정 기간 ///////////////////
	
	@Scheduled(cron="${correction.AllGrade.startDate}")
	public void correctionStart() throws Exception { 
		setEnrollActive(0,2, 1);	
		System.out.println("전학년 시작");
	}
	
	@Scheduled(cron="${correction.AllGrade.endDate}")
	public void correctionEnd() throws Exception { 
		setEnrollActive(0,2, 0);
		copyToTimeTable();
		System.out.println("전학년 끝");
	}
	
	
	public void setEnrollActive(int enroll_grade, int enroll_type, int enroll_active){
		RequestCourseDAO dao = sqlsession.getMapper(RequestCourseDAO.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("enroll_grade", enroll_grade);
		map.put("enroll_active", enroll_active);
		map.put("enroll_type", enroll_type);
		dao.setErollStatus(map);
	}
	
	@Transactional(rollbackFor={Exception.class, NullPointerException.class, SQLException.class, RuntimeException.class})
	public void setReserveCheck() throws Exception{
		RequestCourseDAO dao = sqlsession.getMapper(RequestCourseDAO.class);
		List<ReserveDTO> reserveList = dao.getReserveList();
		try{
			for(ReserveDTO dto : reserveList){
				int request_seats = 0;
				SubjectDTO subjectDto = null;
				request_seats = dao.countRequestSeat(dto.getSubject_code());
				subjectDto = dao.getSubjectBySubjectCode(dto.getSubject_code());
				if(request_seats > subjectDto.getSubject_seats()){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("result", 1);
					map.put("subject_code", dto.getSubject_code());
					map.put("student_code", dto.getStudent_code());
					dao.setReserveCheck(map);
				}else{
					dao.updateRequestSeat(request_seats, dto.getSubject_code());	//수강인원 수 등록
				}
			}
			dao.copyFromReserveToEnrollment();	//테이블 복사하기
		}catch(Exception e){
			System.out.println("ModifyTermScheduler / setReserveCheck : " + e.getMessage());
			throw e;
		}
	}
	
	public void copyToTimeTable(){
		RequestCourseDAO dao = sqlsession.getMapper(RequestCourseDAO.class);
		dao.copyToTimeTable();
	}
}
