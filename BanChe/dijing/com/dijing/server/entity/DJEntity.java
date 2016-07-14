package com.dijing.server.entity;

import java.lang.reflect.Field;

import org.json.JSONObject;

public class DJEntity implements IEntity {

	@Override
	public JSONObject toJSON() throws Exception {
		JSONObject json = new JSONObject();
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for(Field field : fields){
			json.put(field.getName(), field.get(this));
		}
		return json;
	}

}
