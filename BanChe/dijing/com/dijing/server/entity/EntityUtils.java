package com.dijing.server.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年12月18日上午9:19:14
 */
public class EntityUtils {

	static Logger logger = LogManager.getLogger(EntityUtils.class.getName());

	public static void entityCopy(Object src, Object dst)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> srcClass = src.getClass();
		Class<?> dstClass = dst.getClass();
		Field[] srcFields = srcClass.getDeclaredFields();
		for (Field field : srcFields) {
			String type = field.getType().getName();
			String name = field.getName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method srcM = srcClass.getDeclaredMethod("get" + name);
			Class<?> param = null;
			if ("long".equals(type)) {
				param = long.class;
			} else if ("java.lang.Long".equals(type)) {
				param = Long.class;
			} else if ("int".equals(type)) {
				param = int.class;
			} else if ("java.lang.Integer".equals(type)) {
				param = Integer.class;
			} else if ("java.lang.String".equals(type)) {
				param = String.class;
			} else if ("double".equals(type)) {
				param = double.class;
			} else if ("java.lang.Double".equals(type)) {
				param = Double.class;
			} else if ("java.util.Date".equals(type)) {
				param = java.util.Date.class;
			} else {
				logger.error("unsupport type: " + type);
				continue;
			}
			for (Class<?> clazz = dstClass; clazz != Object.class; clazz = clazz
					.getSuperclass()) {
				try {
					Method dstM = clazz.getDeclaredMethod("set" + name, param);
					dstM.invoke(dst, srcM.invoke(src));
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 不遍历父类
	 * @param o
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static List<EntityInfo> parseEntity(Object o) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<EntityInfo> list = new ArrayList<>();
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			EntityInfo ei = new EntityInfo();
			ei.setFlag(field.getName());
			String name = ei.getFlag().substring(0, 1).toUpperCase()
					+ ei.getFlag().substring(1);
			Method m = o.getClass().getDeclaredMethod("get" + name);
			ei.setValue(m.invoke(o));
			String type = field.getType().getName();
			if("java.lang.String".equals(type)){
				if(ei.getValue()==null){
					ei.setValue("");
				}
			}
			list.add(ei);
		}
		return list;
	}
}
