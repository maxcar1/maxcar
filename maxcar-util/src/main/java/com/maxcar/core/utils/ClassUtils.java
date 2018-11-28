package com.maxcar.core.utils;

import java.lang.reflect.Field;
import java.util.Date;

public class ClassUtils {
	/**
	 * 获取实体类对象属性的名称和类型
	 * @param model
	 * @throws Exception
	 */
	public static void testReflect(Object model) throws Exception{
		
		for (Field field : model.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			System.out.println(field.getName() + ":" + field.getType() );
		}
		}
	
	public static void main(String[] args) {
		System.out.println(new Date());
	}
	
}
