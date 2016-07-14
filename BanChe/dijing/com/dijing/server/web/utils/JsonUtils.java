package com.dijing.server.web.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月9日下午4:59:06
 */
public class JsonUtils {

	public static JSONObject formJson(Object obj) throws JSONException, IllegalArgumentException, IllegalAccessException{
		Class<?> clazz = obj.getClass();
		JSONObject json = new JSONObject();
		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for(Field field : fields){
			json.put(field.getName(), field.get(obj));
		}
		return json;
	}
	
	public static JSONArray formJsonArray(Collection<?> collection) throws JSONException, IllegalArgumentException, IllegalAccessException{
		JSONArray arr = new JSONArray();
		if(collection==null || collection.size()==0){
			return arr;
		}
		Iterator<?> it = collection.iterator();
		while(it.hasNext()){
			Object object = it.next();
			if(object==null){
				continue;
			}
			String type = object.getClass().getTypeName();
			switch(type){
			case "java.lang.String":
			case "java.lang.Integer":
			case "java.lang.Double":
			case "java.lang.Boolean":
			case "java.lang.Long":
				arr.put(object);
				break;
			default:
				arr.put(formJson(object));
				break;
			}
			
		}
		return arr;
	}
}
