package com.oktuoxie.demo;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author liyashuang 
* @Description: TODO
* @date 2014年8月29日 下午6:01:03 
*/
public class StartMain {

	/**
	 * 加载log4j的配置文件
	 */
	public void loadLog4j(){
		/**log4j初始化*/
		System.out.println("log4j 初始化成功");
		PropertyConfigurator.configure("resource/log4j.properties");
	}
	
	public void loadStock(){
		Properties properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/stock.properties");
		try {   
			properties.load(in);
			System.out.println(properties.keySet().size());
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
	}
	public void init(){
		loadLog4j();
	}
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(StartMain.class);
		new StartMain().loadStock();
	}

}
