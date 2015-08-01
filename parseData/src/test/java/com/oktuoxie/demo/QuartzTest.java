package com.oktuoxie.demo;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

	public static void main(String[] args) {
		 QuartzTest test = new QuartzTest();
		 	try{
		      test.startSchedule();
	    	}
		  catch (Exception e){
		     e.printStackTrace();
		  }
		 }
		 public void startSchedule() throws Exception{
		     Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		     JobDetail jobDetail = new JobDetail("testJob", Scheduler.DEFAULT_GROUP, TestJob.class);
		    //ִ��10�Σ�ÿ3��ִ��һ�Σ���9������
		     CronTrigger trigger = new CronTrigger("myQuartz", "mygroup", "0 01 23 * * ?");
		     scheduler.scheduleJob(jobDetail, trigger);
		     scheduler.start();
		 }

}
