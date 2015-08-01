package com.oktuoxie.parsedata.job;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oktuoxie.parsedata.stock.StockDataHandler;

public class CommonJob implements Job {

	Logger logger = LoggerFactory.getLogger(CommonJob.class);
	public CommonJob(){}
	
	List<String> concurrentLinkedQueue = new ArrayList<String>();
	/**
	 * 调用线程池处理逻辑
	 * @Description:TODO
	 * @author: liyashuang
	 * @time:2014年8月31日 上午10:47:56
	 */
	public void deal(){
		StringBuffer url = new StringBuffer();
		url.append("http://hq.sinajs.cn/list=");
		String tem = "";
		for(int i=1;i<999;i++){
			if(i<10){
				tem = "00"+i;
			}else if(i<100){
				tem = "0"+i;
			}else{
				tem = ""+i;
			}
			url.append("sh600"+tem+",");
			url.append("sh601"+tem+",");
			url.append("sz300"+tem+",");
			url.append("sz000"+tem+",");
			url.append("sz002"+tem+",");
			if(i%100==0){
				concurrentLinkedQueue.add(url.toString());
//				System.out.println("[url]"+url.toString());
				url= new StringBuffer("http://hq.sinajs.cn/list=");
			}
		}
		
		/**多线程处理数据*/
		ExecutorService executorService = Executors.newFixedThreadPool(concurrentLinkedQueue.size());
		for(int i=0;i<concurrentLinkedQueue.size();i++){
			executorService.execute(new StockDataHandler(concurrentLinkedQueue.get(i)));
		}
		executorService.shutdown();
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		deal();
		
//		deatTest();
	}
	/**
	 * 测试程序
	 * @Description:TODO
	 * @author: liyashuang
	 * @time:2014年8月31日 下午12:27:53
	 */
	public void deatTest(){
		logger.info(new Date().toLocaleString());
	}
}
