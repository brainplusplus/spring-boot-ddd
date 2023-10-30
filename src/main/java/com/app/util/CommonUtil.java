package com.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtil {
	public static boolean isEmpty(String str){
		return str==null || str.trim().equals(new String(""));
	}
	public static String encodeText(String text){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(text);
	}
	public static boolean matchesText(String text,String hashing){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(text, hashing);
	}
	public static List makeCollection(Iterable iter) {
	    List list = new ArrayList<>();
	    for (Object item : iter) {
	        list.add(item);
	    }
	    return list;
	}
        public static double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
}
