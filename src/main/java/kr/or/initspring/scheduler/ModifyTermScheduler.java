package kr.or.initspring.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

public class ModifyTermScheduler {

	@Scheduled(cron="${firstGrade.startDate}")
	public void test() {
		System.out.println("스케줄러 텧스트!! 제발요...");
	}
}
