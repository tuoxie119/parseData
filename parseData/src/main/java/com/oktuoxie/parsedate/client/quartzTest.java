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
	     
	     /**����9��15-25��9��30��9��59*/
	     JobDetail jobDetail = new JobDetail("jobDetail", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger = new CronTrigger("trigger", "trigger", "*/2 15-24,30-59 09 * * ?");
	     scheduler.scheduleJob(jobDetail, trigger);
	     
	     /**����10�㵽10��59*/
	     JobDetail jobDetail01 = new JobDetail("jobDetail01", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger01 = new CronTrigger("trigger01", "trigger01", "*/2 * 10 * * ?");
	     scheduler.scheduleJob(jobDetail01, trigger01);
	     
	     /**����11�㵽11��29*/
	     JobDetail jobDetail02 = new JobDetail("jobDetail02", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger02 = new CronTrigger("trigger02", "trigger02", "*/2 0-29 11 * * ?");
	     scheduler.scheduleJob(jobDetail02, trigger02);
	     
	     /**���綨ʱ����13�㵽15��*/
	     JobDetail jobDetail03 = new JobDetail("jobDetail03", Scheduler.DEFAULT_GROUP, CommonJob.class);
	     CronTrigger trigger03 = new CronTrigger("trigger03", "trigger03", "*/2 * 13-14 * * ?");
	     scheduler.scheduleJob(jobDetail03, trigger03);
	     
	     /**���Զ�ʱ����*/
//	     JobDetail jobDetailtest = new JobDetail("jobDetailtest", Scheduler.DEFAULT_GROUP, CommonJob.class);
//	     CronTrigger triggertest = new CronTrigger("triggertest", "triggertest", "*/2 * 13-23 * * ?");
//	     scheduler.scheduleJob(jobDetailtest, triggertest);
	     
	     scheduler.start();
	 }
	 
	 /**
	  * 
0 0 12 * * ?	 ÿ��12�㴥��
0 15 10 ? * *	 ÿ��10��15�ִ���
0 15 10 * * ?	 ÿ��10��15�ִ���
0 15 10 * * ? *	 ÿ��10��15�ִ���
0 15 10 * * ? 2005	 2005��ÿ��10��15�ִ���
0 * 14 * * ?	 ÿ������� 2�㵽2��59��ÿ�ִ���
0 0/5 14 * * ?	 ÿ������� 2�㵽2��59��(���㿪ʼ��ÿ��5�ִ���)
0 0/5 14,18 * * ?	 ÿ������� 2�㵽2��59��(���㿪ʼ��ÿ��5�ִ���)
					ÿ������� 18�㵽18��59��(���㿪ʼ��ÿ��5�ִ���)
0 0-5 14 * * ?	 ÿ������� 2�㵽2��05��ÿ�ִ���
0 10,44 14 ? 3 WED	 3�·�ÿ��������� 2��10�ֺ�2��44�ִ���
0 15 10 ? * MON-FRI	 ����һ������ÿ�������10��15�ִ���
0 15 10 15 * ?	 ÿ��15������10��15�ִ���
0 15 10 L * ?	 ÿ�����һ���10��15�ִ���
0 15 10 ? * 6L	 ÿ�����һ�ܵ��������10��15�ִ���
0 15 10 ? * 6L 2002-2005	 ��2002�굽2005��ÿ�����һ�ܵ��������10��15�ִ���
0 15 10 ? * 6#3	 ÿ�µĵ����ܵ������忪ʼ����
0 0 12 1/5 * ?	 ÿ�µĵ�һ�����翪ʼÿ��5�촥��һ��
0 11 11 11 11 ?	 ÿ���11��11�� 11��11�ִ���(�����)
	  */
}
