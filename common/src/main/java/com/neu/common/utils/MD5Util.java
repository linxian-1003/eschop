package com.neu.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	static char [] cache= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	public static String digest (String param){
		
		//TODO 
		//byte 
		// -128 ... -1 0 1 2 ...  127 128 129 ..... 255
		
		//
		
		
		
		//MessageDigest
		
		MessageDigest messageDigest=null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	 
		byte [] result = messageDigest.digest(param.getBytes());
		
		StringBuffer SB = new StringBuffer();
		for(int temp : result) {
			if(temp<0) {
				//负数 --->整数
				temp = temp+256;
			}
			
			int height = temp/16;
			int lower = temp%16;
			SB.append(cache[height]).append(cache[lower]);
		}
		//System.out.println(SB.toString().length());
		
		//202cb962ac59075b964b07152d234b70
		//202CB962AC59075B964B07152D234B70
		
		return SB.toString();
	}

}
