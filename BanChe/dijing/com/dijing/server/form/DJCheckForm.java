package com.dijing.server.form;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.dijing.server.web.utils.DJFormatUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午1:01:05
 */
public class DJCheckForm {

	public static CheckFormResult check(Object form) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Field[] fields = form.getClass().getDeclaredFields();
		for(Field field : fields){
			String name = field.getName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method m = form.getClass().getDeclaredMethod("get" + name);
			Object value = m.invoke(form);
			
			FRequired fr = field.getAnnotation(FRequired.class);
			if(fr!=null){				
				if(value==null){
					return new CheckFormResult(field.getName(), false, fr.name() + " 不能为空");
				}
				String type = field.getType().getName();
				if ("java.lang.String".equals(type)){
					if("".equals((String)value)){
						return new CheckFormResult(field.getName(), false, fr.name() + " 不能为空");
					}
				}
			}
			
			FEmail fe = field.getAnnotation(FEmail.class);
			if(fe!=null && value!=null){
				String e = (String) value;
				if(!"".equals(e) && !DJFormatUtils.isEmail(e)){
					return new CheckFormResult(field.getName(), false, "邮箱格式不正确");
				}
			}
			
			FPhone fp = field.getAnnotation(FPhone.class);
			if(fp!=null && value!=null){
				String phone = (String) value;
				if(!"".equals(phone) && !DJFormatUtils.isPhoneNumber(phone)){
					return new CheckFormResult(field.getName(), false, "手机格式不正确");
				}
			}
			
			FNumberic fn = field.getAnnotation(FNumberic.class);
			if(fn!=null && value!=null){
				String type = field.getType().getName();
				String number = null;
				if("java.lang.String".equals(type)){
					number = (String) value;					
				}else if("int".equals(type) || "java.lang.Integer".equals(type)){
					number = (int) value + "";
				}else if("long".equals(type) || "java.lang.Long".equals(type)){
					number = (long) value + "";
				}else if("double".equals(type) || "java.lang.Double".equals(type)){
					number = (double) value + "";
				}else{
					
				}
				
				if(!"".equals(number) && !StringUtils.isNumeric(number)){
					return new CheckFormResult(field.getName(), false, fn.name() + "必须为数字");
				}
			}
		}
		return new CheckFormResult("", true, "");
	}
}
