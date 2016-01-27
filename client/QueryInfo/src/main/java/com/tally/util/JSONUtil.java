package com.tally.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.json.JSONObject;

import com.tally.entity.UserEntity;


public class JSONUtil {
	public static String entityToJsonStr(Object obj){
		JSONObject jsonObject = new JSONObject();
		Field[] fields = obj.getClass().getDeclaredFields();
		try
		{
			for (Field field : fields) {
				String fieldName = field.getName();
				Method method = obj.getClass().getMethod("get"+fieldName, new Class[] {});
				String value = method.invoke(obj, new Object[]{}).toString();
				jsonObject.put(fieldName, value);
			}
		}
		catch(Exception ex){
			
		}
		
		return jsonObject.toString();
	}
}
