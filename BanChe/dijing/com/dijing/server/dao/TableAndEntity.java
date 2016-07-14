package com.dijing.server.dao;

/**
 * 配置数据表名称与实体类名称的对应关系
 * @author winter
 * @date   2014-12-13下午3:31:24
 */
public class TableAndEntity {

	private static final String[][] relationship = {
			{}
	};
	
	/**
	 * 根据数据表的表名获取对应的实体类的类名
	 * @param table
	 * @return
	 */
	public static String getEntityName(String table){
		for(int i=0;i<relationship.length;i++){
			if(relationship[i][0].equals(table)){
				return relationship[i][1];
			}
		}
		return null;
	}
}
