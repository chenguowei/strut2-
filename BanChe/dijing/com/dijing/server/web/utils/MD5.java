package com.dijing.server.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author winter
 * @date   2015-4-12上午11:59:03
 */
public class MD5 {

	public static String encryption(String plainText) { 
		if(plainText==null){
			return null;
		}
		String ciphertext = null;
		
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 
	
			int i; 
	
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = (b[offset]&0xff);
				
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 
			
			ciphertext = buf.toString();
	
		} catch (NoSuchAlgorithmException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		
		return ciphertext;
	} 
}
