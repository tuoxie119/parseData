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
* @date 2014��8��29�� ����6:01:03 
*/
public class StartMain {

	/**
	 * ����log4j�������ļ�
	 */
	public void loadLog4j(){
		/**log4j��ʼ��*/
		System.out.println("log4j ��ʼ���ɹ�");
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
