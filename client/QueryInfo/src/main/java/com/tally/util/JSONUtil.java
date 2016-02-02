package com.tally.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

import com.tally.entity.StatisticEntity;
import com.tally.entity.UserEntity;


public class JSONUtil {
	private static SimpleDateFormat sft = new SimpleDateFormat("MM-dd");
	public static JSONObject entityToJsonStr(Object obj) throws UnsupportedEncodingException{
		JSONObject jsonObject = new JSONObject();
		Field[] fields = obj.getClass().getDeclaredFields();
		try
		{
			for (Field field : fields) {
				String fieldName = field.getName();
				Method method = obj.getClass().getMethod("get"+fieldName, new Class[] {});
				String value = null;
				if(field.getType().equals(Timestamp.class))
				{
					Timestamp ts = (Timestamp)method.invoke(obj, new Object[]{});
					value = sft.format(ts);
				}else{
					value = method.invoke(obj, new Object[]{}).toString();
				}
				jsonObject.put(fieldName, value);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return jsonObject;
	}
}
