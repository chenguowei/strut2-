package com.dijing.server.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月9日下午2:18:51
 */
public class ClientModel<T> {
	
	private T model;
	
	@SuppressWarnings("unchecked")
	public static <T> ClientModel<T> form(Class<?> clazz) throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
		String str = DJAction.receivePost(ServletActionContext.getRequest());
		JSONObject json = new JSONObject(str);
		
		Object obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for(Field field : fields){
			String type = field.getType().getName();
			switch(type){
			case "long":
				field.setLong(obj, json.optLong(field.getName()));
				break;
			case "java.lang.Long":
				field.set(obj, json.optLong(field.getName()));
				break;
			case "int":
				field.setInt(obj, json.optInt(field.getName()));
				break;
			case "java.lang.Integer":
				field.set(obj, json.optInt(field.getName()));
				break;
			case "boolean":
				field.setBoolean(obj, json.optBoolean(field.getName()));
				break;
			case "java.lang.Boolean":
				field.set(obj, json.optBoolean(field.getName()));
				break;
			case "double":
				field.setDouble(obj, json.optDouble(field.getName()));
				break;
			case "java.lang.Double":
				field.set(obj, json.optDouble(field.getName()));
				break;
			case "java.lang.String":
				field.set(obj, json.optString(field.getName()));
				break;
			default:
				System.out.println("unknown type: "+type);
				break;
			}			
		}
		ClientModel<T> model = new ClientModel<T>();
		model.setModel((T) obj);
		return model;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}
}
