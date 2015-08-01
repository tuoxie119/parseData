package com.oktuoxie.parsedate.client;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.oktuoxie.parsedata.job.CommonJob;

public class quartzTest {

	public static void main(String[] args) throws Exception {
		new quartzTest().startSchedule();
	}
	 public void startSchedule() throws Exception{
	     Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	     
	     /**上午9点15-25和9点30到9点59*/
	     JobDetail jobDetail = new JobDetail("jobDetail", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger = new CronTrigger("trigger", "trigger", "*/2 15-24,30-59 09 * * ?");
	     scheduler.scheduleJob(jobDetail, trigger);
	     
	     /**上午10点到10点59*/
	     JobDetail jobDetail01 = new JobDetail("jobDetail01", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger01 = new CronTrigger("trigger01", "trigger01", "*/2 * 10 * * ?");
	     scheduler.scheduleJob(jobDetail01, trigger01);
	     
	     /**上午11点到11点29*/
	     JobDetail jobDetail02 = new JobDetail("jobDetail02", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger02 = new CronTrigger("trigger02", "trigger02", "*/2 0-29 11 * * ?");
	     scheduler.scheduleJob(jobDetail02, trigger02);
	     
	     /**下午定时任务13点到15点*/
	     JobDetail jobDetail03 = new JobDetail("jobDetail03", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger03 = new CronTrigger("trigger03", "trigger03", "*/2 * 13-14 * * ?");
	     scheduler.scheduleJob(jobDetail03, trigger03);
	     
	     /**测试定时任务*/
//	     JobDetail jobDetailtest = new JobDetail("jobDetailtest", Scheduler.DEFAULT_GROUP, CommonJob.class);
//	     CronTrigger triggertest = new CronTrigger("triggertest", "triggertest", "*/2 * 13-23 * * ?");
//	     scheduler.scheduleJob(jobDetailtest, triggertest);
	     
	     scheduler.start();
	 }
	 
	 /**
	  * 
0 0 12 * * ?	 每天12点触发
0 15 10 ? * *	 每天10点15分触发
0 15 10 * * ?	 每天10点15分触发
0 15 10 * * ? *	 每天10点15分触发
0 15 10 * * ? 2005	 2005年每天10点15分触发
0 * 14 * * ?	 每天下午的 2点到2点59分每分触发
0 0/5 14 * * ?	 每天下午的 2点到2点59分(整点开始，每隔5分触发)
0 0/5 14,18 * * ?	 每天下午的 2点到2点59分(整点开始，每隔5分触发)
					每天下午的 18点到18点59分(整点开始，每隔5分触发)
0 0-5 14 * * ?	 每天下午的 2点到2点05分每分触发
0 10,44 14 ? 3 WED	 3月分每周三下午的 2点10分和2点44分触发
0 15 10 ? * MON-FRI	 从周一到周五每天上午的10点15分触发
0 15 10 15 * ?	 每月15号上午10点15分触发
0 15 10 L * ?	 每月最后一天的10点15分触发
0 15 10 ? * 6L	 每月最后一周的星期五的10点15分触发
0 15 10 ? * 6L 2002-2005	 从2002年到2005年每月最后一周的星期五的10点15分触发
0 15 10 ? * 6#3	 每月的第三周的星期五开始触发
0 0 12 1/5 * ?	 每月的第一个中午开始每隔5天触发一次
0 11 11 11 11 ?	 每年的11月11号 11点11分触发(光棍节)
	  */
}
