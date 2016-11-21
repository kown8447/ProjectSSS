package kr.or.initspring.scheduler;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import kr.or.initspring.dao.RequestCourseDAO;

public class ModifyTermScheduler {

	@Autowired
	private SqlSession sqlsession;
	
	@Scheduled(cron="${wait.startDate}")
	public void waitStart() {	setEnrollActive(0, 2);	System.out.println("대기 시간..");}
	
	@Scheduled(cron="${firstGrade.startDate}")
	public void firstGradeStart() {	setEnrollActive(1, 1);	System.out.println("1학년 시작");}
	
	@Scheduled(cron="${firstGrade.endDate}")
	public void firstGradeEnd() { setEnrollActive(1, 0);	System.out.println("1학년 끝");}
	
	@Scheduled(cron="${secondGrade.startDate}")
	public void secondGradeStart() { setEnrollActive(2, 1);	System.out.println("2학년 시작");}
	
	@Scheduled(cron="${secondGrade.endDate}")
	public void secondGradeEnd() { setEnrollActive(2, 0);	System.out.println("2학년 끝");}
	
	@Scheduled(cron="${thirdGrade.startDate}")
	public void thirdGradeStart() { setEnrollActive(3, 1);	System.out.println("3학년 시작");}
	
	@Scheduled(cron="${thirdGrade.endDate}")
	public void thirdGradeEnd() { setEnrollActive(3, 0);	System.out.println("3학년 끝");}
	
	@Scheduled(cron="${fourthGrade.startDate}")
	public void fourthGradeStart() { setEnrollActive(4, 1);	System.out.println("4학년 시작");}
	
	@Scheduled(cron="${fourthGrade.endDate}")
	public void fourthGradeEnd() { setEnrollActive(4, 0);	System.out.println("4학년 끝");}
	
	@Scheduled(cron="${AllGrade.startDate}")
	public void AllGradeStart() { setEnrollActive(0, 1);	System.out.println("전학년 시작");}
	
	@Scheduled(cron="${AllGrade.endDate}")
	public void AllGradeEnd() { setEnrollActive(0, 0);	System.out.println("전학년 끝");}
	
	public void setEnrollActive(int enroll_grade, int enroll_active){
		RequestCourseDAO dao = sqlsession.getMapper(RequestCourseDAO.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("enroll_grade", enroll_grade);
		map.put("enroll_active", enroll_active);
		dao.setErollStatus(map);
	}
}
