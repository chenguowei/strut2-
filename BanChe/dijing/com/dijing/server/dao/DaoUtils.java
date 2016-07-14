package com.dijing.server.dao;

import java.util.Collection;


/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月27日下午3:31:38
 */
public class DaoUtils {

	public static String in(String column, Collection<?> c){
		StringBuffer sb = new StringBuffer();
		sb.append(" and ").append(column).append(" in(");
		for(Object o : c){
			if("java.lang.String".equals(o.getClass().getTypeName())){
				sb.append("'").append(o).append("',");
			}else{
				sb.append(o).append(",");
			}
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
}
