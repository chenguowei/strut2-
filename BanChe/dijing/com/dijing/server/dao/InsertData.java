package com.dijing.server.dao;

import java.io.Serializable;

/**
 * 保存将要用于Mysql类中insert操作的输入数据。由两个字符串数组实现，一个保存column名，一个保存对应的值。
 * 这两个字符串数组的长度为20。
 * @author winter
 *
 */
public class InsertData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -683863876431312374L;
//	private final int MAX = 100;
//	private String[] column = new String[MAX];
//	private String[] value = new String[MAX];
//	private int count = 0;
	
	private StringBuffer data = new StringBuffer();
	
	public InsertData() {
		
	}
	
	public void setData(String column, String value){
//		if(count==MAX){
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value;
//		count++;
		if(value==null){
			return;
		}
		if(data.length() != 0)
			data.append(", ");
		data.append(column)
			.append("='")
			.append(value)
			.append("'");
	}
	public void setData(String column, long value){
//		if(count==MAX){
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value+"";
//		count++;
		
		if(data.length() != 0)
			data.append(", ");
		data.append(column)
			.append("=")
			.append(value);
	}
	public void setData(String column, double value){
//		if(count==MAX){
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value+"";
//		count++;
		
		if(data.length() != 0)
			data.append(", ");
		data.append(column)
			.append("=")
			.append(value);
	}
//	public String[] getColumnData(){
//		return column;
//	}
//	public String[] getValueData(){
//		return value;
//	}
//	public int getCount(){
//		return count;
//	}
	
	public String getInsertData(){
		return data.toString();
	}
}
