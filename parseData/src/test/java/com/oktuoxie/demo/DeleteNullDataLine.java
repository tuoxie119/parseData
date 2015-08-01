package com.oktuoxie.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DeleteNullDataLine {

	public static void main(String []args){
		File file = new File("C:\\Users\\Administrator\\Desktop\\stock.properties");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
            String tempchar;
            while ((tempchar = reader.readLine()) != null) {
            	if(tempchar.equals(""));
            	else{
            		System.out.println(tempchar);
            	}
            }
            reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
