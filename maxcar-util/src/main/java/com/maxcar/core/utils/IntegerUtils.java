package com.maxcar.core.utils;

public class IntegerUtils {
	public static boolean isNull(Integer i) {
		if(i == null || i.equals("") ) {
			return true;
		}
		return false;
	}
}
