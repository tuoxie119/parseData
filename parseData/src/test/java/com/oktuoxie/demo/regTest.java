package com.oktuoxie.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regTest {

	public static void main(String []args){
		
		String s = "";
		String regEx = "=\"\""; //��ʾa��F
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s);
		boolean rs = mat.find();
		
	}
}
