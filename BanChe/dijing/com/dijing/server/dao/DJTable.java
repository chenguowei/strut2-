package com.dijing.server.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public abstract class DJTable {

	static Logger logger = LogManager.getLogger(DJTable.class.getName());
	
	private final static String H = "ITEM_";
	
	public final static String TID = H + "派工单号";
	/**
	 * 设备id
	 */
	public final static String EID = "PARENT";
	
	public final static String SCAN = H + "扫描记录";
	
//	protected abstract String getDatabase();
	public static final String database = "cles";
	
	protected Mysql mysql = Mysql.getInstance();
	
	protected abstract String getTable();
	
	public boolean insert(InsertData data) {
//		Mysql mysql = new Mysql();
		return mysql.insert(database, getTable(), data);
	}
	
	/**
	 * 
	 * @param data
	 * @return 异常时返回-1
	 */
	public long insertAndGetID(InsertData data) {
//		Mysql mysql = new Mysql();
		return mysql.insertAndGetID(database, getTable(), data);
	}

	
	public boolean delete(Condition condition) {
//		Mysql mysql = new Mysql();
		return mysql.delete(database, getTable(), condition.getCondition());
	}
	
	public boolean delete(String condition) {
		return mysql.delete(database, getTable(), condition);
	}

	/**
	 * 
	 * @param column 该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @param condition
	 * @return
	 */
	public String[][] query(String[] column, Condition condition) {
//		Mysql mysql = new Mysql();
		return mysql.query(database, getTable(), 3, condition.getCondition(), column);
	}
	
	
	/**
	 * 
	 * @param column 该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @param condition
	 * @return 返回结果为json数据形式
	 */
	public JSONArray queryJSON(String[] column, Condition condition){
		return this.queryJSON(column, condition.toString());
	}
	
	public JSONArray queryJSON(String[] column, String condition){
//		return this.queryRegionJSON(column, condition, -1, -1, false);
		return this.queryRegionJSON(column, condition, -1, -1);
	}

	/**
	 * 
	 * @param column 该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @return
	 */
	public String[][] queryAll(String[] column) {
//		Mysql mysql = new Mysql();
		return mysql.query(database, getTable(), 0, null, column);
	}
	
	/**
	 * 
	 * @param column 该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @return 返回结果为json数据形式
	 */
	public JSONArray queryAllJSON(String[] column){
		return this.queryRegionJSON(column, new Condition(), -1, -1, false);
	}
	
	/**
	 * 获取记录的总页数
	 * @param amount 每页的记录数.-1表示要在一页上显示所有内容
	 * @param condition 限制条件。为null时，表示无限制条件。
	 * @return 异常时，返回-1.
	 */
	public int getTotalPage(int amount, Condition condition){
		if(amount==-1){
			return 1;
		}
//		Mysql mysql = new Mysql();
		String[][] result = null;
		if(condition==null || condition.getCondition()==null){
			result = mysql.query(database, getTable(), 0, null, null);
		}else{
			result = mysql.query(database, getTable(), 3, condition.getCondition(), null);
		}
		if(result==null){
			if(Configuration.debug){
				System.out.println("result==null");
				System.out.println("table: "+getTable());
			}
			return -1;
		}else{
			int page;
			if(result.length%amount != 0){
				page = result.length/amount + 1;
			}else{
				page = result.length/amount;
			}
					
			return page;
		}
	}
	
	public long getTotalNumber(String condition){
		String[][] result = null;
		if(condition==null){
			result = mysql.query(database, getTable(), 0, null, null);
		}else{
			result = mysql.query(database, getTable(), 3, condition, null);
		}
		if(result==null){
			if(Configuration.debug){
				logger.error("result==null");
				logger.error("table: "+getTable());
			}
			return -1;
		}else{		
			return result.length;
		}
	}
	
	public boolean update(String updateData, String condition){
		return mysql.updates(database, getTable(), 3, condition, updateData);
	}

	public boolean update(InsertData data, String condition){
		return mysql.updates(database, getTable(), 3, condition, data);
	}
	
	public boolean update(InsertData data, Condition condition) {
//		Mysql mysql = new Mysql();
		return mysql.updates(database, getTable(), 3, condition.getCondition(), data);
	}
	
	/**
	 * 查找数据表中某几行的数据
	 * @param column 该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @param condition Condition类型。当无限制条件时，该项设为null
	 * @param offset 偏移量
	 * @param amount 查找的数目
	 * @return
	 */
	public String[][] queryRegion(String[] column, Condition condition,
			int offset, int amount, boolean desc) {
//		Mysql mysql = new Mysql();
		return mysql.queryRegion(database, getTable(), column, condition, offset, amount, desc);
	}
	
	/**
	 * 查找数据表中某几行的数据
	 * @param column 该参数不能为null！
	 * @param condition Condition类型。当无限制条件时，该项设为null
	 * @param offset 偏移量，如果值为-1，并且amount的值也为-1，则表示查询所有满足条件的结果
	 * @param amount 查询条数,如果值为-1，并且offset的值也为-1，则表示查询所有满足条件的结果
	 * @param desc 降序排列（根据id）
	 * @return 返回JSONArray格式的数据，以列名为key名
	 */
	public JSONArray queryRegionJSON(String[] column, Condition condition,
			int offset, int amount, boolean desc){
//		Mysql mysql = new Mysql();
		String[][] result = mysql.queryRegion(database, getTable(), column, condition, offset, amount, desc);
		if(result==null){
			if(Configuration.debug){
				System.out.println("DJTable result==null");
			}
			return null;
		}
		JSONArray arr = new JSONArray();
//		for(int i=0;i<result.length;i++){
//			JSONObject json = new JSONObject();
//			for(int k=0;k<column.length;k++){
//				json.put(column[k], result[i][k]);
//			}
//			arr.put(json);
//		}
		onAddData(arr, column, result);
		return arr;
	}
	
	public JSONArray queryRegionJSON(String[] column, String condition,
			int offset, int amount, boolean desc){
//		Mysql mysql = new Mysql();
		String[][] result = mysql.queryRegion(database, getTable(), column, condition, offset, amount, desc);
		if(result==null){
			if(Configuration.debug){
				System.out.println("DJTable result==null");
			}
			return null;
		}
		JSONArray arr = new JSONArray();
		onAddData(arr, column, result);
		return arr;
	}
	
	public JSONArray queryRegionJSON(String[] column, String condition,
			int offset, int amount){
//		Mysql mysql = new Mysql();
		String[][] result = mysql.queryRegion(database, getTable(), column, condition, offset, amount);
		if(result==null){
			if(Configuration.debug){
				System.out.println("DJTable result==null");
			}
			return null;
		}
		JSONArray arr = new JSONArray();
		onAddData(arr, column, result);
		return arr;
	}
	
	/**
	 * 根据sql语句查询数据库，并将查询结果封装成json数组形式。数组的元素为有单条查询结果构成的json对象。
	 * @param column
	 * @param sql
	 * @return
	 */
	public JSONArray querySqlJSON(String[] column, String sql){
		String[][] result = mysql.querySql(database, column, sql);
		if(result==null){
			if(Configuration.debug){
				System.out.println("DJTable result==null");
			}
			return null;
		}
		JSONArray arr = new JSONArray();
		onAddData(arr, column, result);
		return arr;
	}
	
	/**
	 * 将查询结果组成JSONObject，再组成JSONArray
	 * @param arr
	 * @param column
	 * @param result
	 */
	protected void onAddData(JSONArray arr, String[] column, String[][] result){
		for(int i=0;i<result.length;i++){
			JSONObject json = new JSONObject();
			for(int k=0;k<column.length;k++){
				json.put(column[k], result[i][k]);
			}
			arr.put(json);
		}
	}
	
	
}
