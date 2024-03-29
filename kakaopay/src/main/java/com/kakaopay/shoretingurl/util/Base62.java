package com.kakaopay.shoretingurl.util;

public class Base62 {

	static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	public static String encodeToLong(long value) {
		
		final StringBuilder sb = new StringBuilder();
		
		do{
			int i = (int)(value % 62);
			sb.append(BASE62[i]);
			value /= 62;
		}while (value > 0) ;
		
		return sb.toString();
		
	}

	public static long decodeToLong(String value) {
		
		long result=0;
		long power=1;
		
		for (int i = 0; i < value.length(); i++) {
			int digit = new String(BASE62).indexOf(value.charAt(i));
			result += digit * power;
			power *= 62;
		}
		
		return result;
	}
	
}
