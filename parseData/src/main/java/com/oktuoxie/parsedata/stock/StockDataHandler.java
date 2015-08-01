package com.oktuoxie.parsedata.stock;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oktuoxie.demo.StartMain;
/**
 * var hq_str_sh601006="������·, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91, 26.92, 
22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89, 14300,
 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94, 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
����ַ������������ƴ����һ�𣬲�ͬ����������ö��Ÿ����ˣ����ճ���Ա��˼·��˳��Ŵ�0��ʼ��
0����������·������Ʊ���֣�
1����27.55�壬���տ��̼ۣ�
2����27.25�壬�������̼ۣ�
3����26.91�壬��ǰ�۸�
4����27.55�壬������߼ۣ�
5����26.20�壬������ͼۣ�
6����26.91�壬����ۣ�������һ�����ۣ�
7����26.92�壬�����ۣ�������һ�����ۣ�
8����22114263�壬�ɽ��Ĺ�Ʊ�������ڹ�Ʊ������һ�ٹ�Ϊ������λ��������ʹ��ʱ��ͨ���Ѹ�ֵ����һ�٣�
9����589824680�壬�ɽ�����λΪ��Ԫ����Ϊ��һĿ��Ȼ��ͨ���ԡ���Ԫ��Ϊ�ɽ����ĵ�λ������ͨ���Ѹ�ֵ����һ��
10����4695�壬����һ������4695�ɣ���47�֣�
11����26.91�壬����һ�����ۣ�
12����57590�壬�������
13����26.90�壬�������
14����14700�壬��������
15����26.89�壬��������
16����14300�壬�����ġ�
17����26.88�壬�����ġ�
18����15100�壬�����塱
19����26.87�壬�����塱
20����3100�壬����һ���걨3100�ɣ���31�֣�
21����26.92�壬����һ������
(22, 23), (24, 25), (26,27), (28, 29)�ֱ�Ϊ���������������ĵ������
30����2008-01-11�壬���ڣ�
31����15:05:32�壬ʱ�䣻
 * @author liyashuang
 * @time:2014��8��28�� ����9:46:42
 * @email liyashuang@voole.com
 */
public class StockDataHandler implements Runnable{
	Logger logger = LoggerFactory.getLogger(StockDataHandler.class);
	private String url;
	public StockDataHandler(String url){
		this.url=url;
	}
	public void run(){
		 try {  
		      URL u = new URL(url);  
		      byte[] b = new byte[256];  
		      InputStream in = null;  
		      ByteArrayOutputStream bo = new ByteArrayOutputStream();  
//		      while (true) {  
		          try {  
		              in = u.openStream();  
		              int i;  
		              while ((i = in.read(b)) != -1) {  
		                  bo.write(b, 0, i);  
		              }  
		              String result = bo.toString();  
		              String[] stocks = result.split(";"); 
		              for (String stock : stocks) {
						  String regEx = "=\"\""; //��ʾa��F
						  Pattern pat = Pattern.compile(regEx);
						  Matcher mat = pat.matcher(stock);
			              if(!mat.find()){
//			            	  logger.info(Thread.currentThread().getName()+"==="+new Date().toLocaleString()+stock);
			            	  logger.info(stock.split("=")[1]);
//			            	  logger.info(stock.split("=")[0].replace("var hq_str_", ""));
			              }
		                  String[] datas = stock.split(",");  
		                  //���ݶ����Լ���Ӧ����  
//		                  if(Integer.parseInt(datas[8])>100000){
//		                	  System.out.println(Thread.currentThread().getId()+"name:"+datas[0]+"��һ������"+datas[10]+"ʱ��:"+datas[31]);
//		                  }
		              }  
		              bo.reset();  
		          } catch (Exception e) {  
		        	  logger.error(e.getMessage());
		              System.out.println(e.getMessage());  
		          } finally {  
		              if (in != null) {  
		                  in.close();  
		              }
		          }
//		      }
		  } catch (Exception ex) { 
			  logger.error(ex.getMessage());
		      System.out.println(ex.getMessage());
		  } 
	}
}
