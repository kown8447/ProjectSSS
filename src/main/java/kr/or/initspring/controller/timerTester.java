package kr.or.initspring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timerTester {

	public static void main(String[] args) {
		String[] profArray={"00","16","18","4","12","?"};
		System.out.println("테스트!!");
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startday=null;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year);
		
		try {
			startday = sf.parse(year+"-"+profArray[4]+"-"+profArray[3]+" "+profArray[2]+":"+profArray[1]+":"+profArray[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("시간:"+startday.getTime());
		System.out.println(startday);
		long realTime=cal.getTime().getTime();
		System.out.println("시간:"+realTime);
		System.out.println(cal.getTime());
		long timeterm= startday.getTime()-realTime;
		System.out.println(timeterm);
		long m= timeterm/60000;
		System.out.println(m+1);
		int h=(int) (m/60);
		int mm=(int) (m%60);
		
		System.out.println(h+" / "+mm);
		
	}

}
