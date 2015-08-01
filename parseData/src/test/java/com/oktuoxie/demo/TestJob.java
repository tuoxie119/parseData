package com.oktuoxie.demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	public TestJob(){}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		String name = Context.getJobDetail().getJobDataMap().getString("name");
		System.out.println("job executing...");   
	}

}
