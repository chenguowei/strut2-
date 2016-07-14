package com.dijing.server.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.dijing.server.Constants;
import com.dijing.server.entity.EntityInfo;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.JsonUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter
 * @Date: 2015-9-18上午10:29:04
 */
public class DJModel {
	
	static Logger logger = LogManager.getLogger(DJModel.class.getName());

	protected Mysql mysql = Mysql.getInstance();
	
	public List<EntityInfo> parseEntity() throws Exception{
		List<EntityInfo> list = EntityUtils.parseEntity(this);
//		System.out.println(JsonUtils.formJsonArray(list).toString());
		return list;
	}
	
	/**
	 * json数组字符串形式
	 * @return
	 * @throws Exception
	 */
	public String parseEntityJson() throws Exception {
		return JsonUtils.formJsonArray(parseEntity()).toString();
	}

	public boolean save() {
		try {
			InsertData data = new InsertData();
			Field[] fields = this.getClass().getDeclaredFields();
			boolean isAutoIncrement = false;
			Field keyField = null;
			
			Condition condition = new Condition();
			for (Field field : fields) {				
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String column = Mysql.changeShape(name);
				String type = field.getType().getName();
				Method m = this.getClass().getDeclaredMethod("get" + name);
				if (field.isAnnotationPresent(Id.class)) {
					keyField = field;
					isAutoIncrement = field.isAnnotationPresent(AutoIncrement.class);
					
					if ("long".equals(type) || "java.lang.Long".equals(type)) {
						condition.setCondition(column, (Long) m.invoke(this));
					} else if ("int".equals(type)
							|| "java.lang.Integer".equals(type)) {
						condition
								.setCondition(column, (Integer) m.invoke(this));
					} else if ("java.lang.String".equals(type)) {
						condition.setCondition(column, (String) m.invoke(this));
					} else if ("double".equals(type)
							|| "java.lang.Double".equals(type)) {
						condition
								.setCondition(column, (Double) m.invoke(this));
					} else if("java.util.Date".equals(type)) {
						Date d = (Date) m.invoke(this);
						condition.setCondition(column, TimeUtils.getDateTime(d));
					} else if("java.sql.Date".equals(type)) {
						java.sql.Date d = (java.sql.Date) m.invoke(this);
						condition.setCondition(column, TimeUtils.getDateTime(d));
					} else {

					}
				}
			}
			if (condition.getCondition() == null) {
				logger.error("can't find key column");
				return false;
			}
			List<?> list = mysql.queryListSql(this.getClass(), condition.getCondition());
			if(list.size() > 0){
				return update(condition);
			}
			
			for (Field field : fields) {
				if (field.isAnnotationPresent(Transparent.class)) {
					continue;
				}
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String column = Mysql.changeShape(name);
				String type = field.getType().getName();
				Method m = this.getClass().getDeclaredMethod("get" + name);
//				logger.debug("method: "+"get" + name);
				if ("long".equals(type)) {
					data.setData(column, (Long) m.invoke(this));
				} else if ("java.lang.Long".equals(type)) {
					Object object = m.invoke(this);
					if(object!=null){
						data.setData(column, (Long) object);
					}					
				} else if ("int".equals(type)) {
					data.setData(column, (Integer) m.invoke(this));
				} else if ("java.lang.Integer".equals(type)) {
					Object object = m.invoke(this);
					if(object!=null){
						data.setData(column, (Integer) object);
					}					
				} else if ("java.lang.String".equals(type)) {
					data.setData(column, (String) m.invoke(this));
				} else if ("double".equals(type)) {
					data.setData(column, (Double) m.invoke(this));
				} else if ("java.lang.Double".equals(type)) {
					Object object = m.invoke(this);
					if(object!=null){
						data.setData(column, (Double) object);
					}					
				} else if("java.util.Date".equals(type)) {
					Date d = (Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else if ("java.sql.Date".equals(type)) {
					java.sql.Date d = (java.sql.Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else {

				}
			}
			if(isAutoIncrement){
				long result = mysql.insertAndGetID(Constants.DATABASE_NAME, getTableName(), data);
				if(result==-1){
					return false;
				}else{
					String name = keyField.getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					String type = keyField.getType().getName();
					Class<?> param = null;
					if ("long".equals(type)) {
						param = long.class;
						Method m = this.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(this, result);
					} else if ("java.lang.Long".equals(type)) {
						param = Long.class;
						Method m = this.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(this, result);
					} else if ("int".equals(type)) {
						param = int.class;
						Method m = this.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(this, (int)result);
					} else if ("java.lang.Integer".equals(type)) {
						param = Integer.class;
						Method m = this.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(this, (int)result);
					}
					return true;
				}
			}
			return mysql.insert(Constants.DATABASE_NAME, getTableName(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Condition condition) {
		try {
			InsertData data = new InsertData();
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Transparent.class)) {
					continue;
				}
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String column = Mysql.changeShape(name);
				String type = field.getType().getName();
				Method m = this.getClass().getDeclaredMethod("get" + name);
				if ("long".equals(type)) {
					data.setData(column, (Long) m.invoke(this));
				} else if ("java.lang.Long".equals(type)) {
					Object object = m.invoke(this);
					if(object!=null){
						data.setData(column, (Long) object);
					}					
				} else if ("int".equals(type)) {
					data.setData(column, (Integer) m.invoke(this));
				} else if ("java.lang.Integer".equals(type)) {
					Object object = m.invoke(this);
					if(object!=null){
						data.setData(column, (Integer) object);
					}					
				} else if ("java.lang.String".equals(type)) {
					data.setData(column, (String) m.invoke(this));
				} else if ("java.util.Date".equals(type)){
					Date d = (Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else if ("java.sql.Date".equals(type)) {
					java.sql.Date d = (java.sql.Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else {

				}
			}
			return mysql.updates(Constants.DATABASE_NAME, getTableName(), 3,
					condition.getCondition(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update() {
		try {
			Condition condition = new Condition();
			InsertData data = new InsertData();
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Transparent.class)) {
					continue;
				}
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String column = Mysql.changeShape(name);
				String type = field.getType().getName();

				Method m = this.getClass().getDeclaredMethod("get" + name);
				if ("long".equals(type)) {
					data.setData(column, (Long) m.invoke(this));
				} else if ("java.lang.Long".equals(type)) {
					data.setData(column, (Long) m.invoke(this));
				} else if ("int".equals(type)) {
					data.setData(column, (Integer) m.invoke(this));
				} else if ("java.lang.Integer".equals(type)) {
					data.setData(column, (Integer) m.invoke(this));
				} else if ("java.lang.String".equals(type)) {
					data.setData(column, (String) m.invoke(this));
				} else if ("double".equals(type)) {
					data.setData(column, (Double) m.invoke(this));
				} else if ("java.lang.Double".equals(type)) {
					data.setData(column, (Double) m.invoke(this));
				} else if("java.util.Date".equals(type)) {
					Date d = (Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else if ("java.sql.Date".equals(type)) {
					java.sql.Date d = (java.sql.Date) m.invoke(this);
					data.setData(column, TimeUtils.getDateTime(d));
				} else {

				}

				if (field.isAnnotationPresent(Id.class)) {
					if ("long".equals(type) || "java.lang.Long".equals(type)) {
						condition.setCondition(column, (Long) m.invoke(this));
					} else if ("int".equals(type)
							|| "java.lang.Integer".equals(type)) {
						condition
								.setCondition(column, (Integer) m.invoke(this));
					} else if ("java.lang.String".equals(type)) {
						condition.setCondition(column, (String) m.invoke(this));
					} else if ("java.util.Date".equals(type)) {
						Date d = (Date) m.invoke(this);
						condition.setCondition(column, TimeUtils.getDateTime(d));
					} else if ("java.sql.Date".equals(type)) {
						java.sql.Date d = (java.sql.Date) m.invoke(this);
						condition.setCondition(column, TimeUtils.getDateTime(d));
					} else {

					}
				}
			}
			if (condition.getCondition() == null) {
				return false;
			}
			return mysql.updates(Constants.DATABASE_NAME, getTableName(), 3,
					condition.getCondition(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Condition condition) {
		return mysql.delete(Constants.DATABASE_NAME, getTableName(),
				condition.getCondition());
	}
	
	public boolean delete() {
		try {
			Condition condition = new Condition();
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Transparent.class)) {
					continue;
				}
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String column = Mysql.changeShape(name);
				String type = field.getType().getName();

				Method m = this.getClass().getDeclaredMethod("get" + name);
				
				if (field.isAnnotationPresent(Id.class)) {
					if ("long".equals(type) || "java.lang.Long".equals(type)) {
						condition.setCondition(column, (Long) m.invoke(this));
					} else if ("int".equals(type)
							|| "java.lang.Integer".equals(type)) {
						condition
								.setCondition(column, (Integer) m.invoke(this));
					} else if ("java.lang.String".equals(type)) {
						condition.setCondition(column, (String) m.invoke(this));
					} else {

					}
					break;
				}
			}
			if (condition.getCondition() == null) {
				return false;
			}
			return delete(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected String getTableName() {
		return Mysql.getTableName(this.getClass());
	}

	public JSONObject toJSON() throws Exception {
		JSONObject json = new JSONObject();
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field : fields) {
			json.put(field.getName(), field.get(this));
		}
		return json;
	}
}
