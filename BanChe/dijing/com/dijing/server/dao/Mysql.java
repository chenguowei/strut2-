package com.dijing.server.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dijing.server.Constants;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 用于对数据库进行增、删、查、改等操作。
 * 
 * @author winter
 * 
 */
public class Mysql {

	static Logger logger = LogManager.getLogger(Mysql.class.getName());

	private static Mysql mysql = new Mysql();

	// /**
	// * 数据库登陆用户
	// */
	// private static String USER = "root";
	// /**
	// * 数据库登陆密码
	// */
	// private static String PASSWORD =
	// "dijing123456";//"dijing_cles";//"1985425wei";
	//
	// private static String DRIVER = "com.mysql.jdbc.Driver";
	//
	// private static String DATABASE_URL = "jdbc:mysql://192.168.1.106:3306/";
	//
	// private static String CODE = "UTF-8";

	/**
	 * 用于非web环境时
	 */
	// static {
	// String path = System.getProperty("user.dir");
	// Properties p = new Properties();
	// InputStream in;
	// try {
	// in = new BufferedInputStream(new FileInputStream(path
	// + File.separator + "config" + File.separator
	// + "db.properties"));
	// p.load(in);
	// Mysql mysql = Mysql.getInstance();
	// mysql.setUser(p.getProperty("dbUser"));
	// mysql.setPassword(p.getProperty("dbPassword"));
	// mysql.setDriver(p.getProperty("dbDriver"));
	// mysql.setURL(p.getProperty("dbURL"));
	// mysql.setCode(p.getProperty("dbCode"));
	// mysql.setPrefix(p.getProperty("prefix"));
	// System.out.println("driver: " + p.getProperty("dbDriver"));
	// System.out.println("URL: " + p.getProperty("dbURL"));
	// in.close();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * 数据库登陆用户
	 */
	private String USER = "root";
	/**
	 * 数据库登陆密码
	 */
	private String PASSWORD = "dijing123456";// "dijing_cles";//"1985425wei";

	private String DRIVER = "com.mysql.jdbc.Driver";

	private String DATABASE_URL = "jdbc:mysql://192.168.1.106:3306/";

	private String CODE = "UTF-8";

	public String PREFIX = "";

	private Mysql() {
	}

	public static Mysql getInstance() {
		return mysql;
	}

	public void setUser(String user) {
		this.USER = user;
	}

	public void setPassword(String password) {
		this.PASSWORD = password;
	}

	public void setDriver(String driver) {
		this.DRIVER = driver;
	}

	public void setURL(String URL) {
		this.DATABASE_URL = URL;
	}

	public void setCode(String code) {
		this.CODE = code;
	}

	public void setPrefix(String prefix) {
		this.PREFIX = prefix;
	}

	/**
	 * 用于查询数据库中某一项或几项的数值。
	 * 
	 * @param database
	 *            数据库的名称
	 * @param table
	 *            表的名称
	 * @param sort
	 *            查找范围：0，表示查找整个表all；1，表示根据usrid查找；2，表示根据username查找。 3，表示自定义查找条件。
	 * @param c
	 *            查找条件：当sort=0时，c应设为null；当sort=1时，c为具体的userid；当sort=2时，c为具体的
	 *            username；当sort=3时，c为自定义的查找条件，比如" where account = 'winter'"
	 * @param column
	 *            该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @return 为String[][]类型数组，数组的长度String[][].length为满足当前查询条件的结果数。二维数组
	 *         String[i][j]的列与column数组对应，记录第i行column对应的数值（即column的第i个结果）。
	 */
	public String[][] query(String database, String table, int sort, String c,
			String[] column) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		String condition = null;
		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;
		switch (sort) {
		case 0:
			condition = " order by id";// 查询所有数据时，按id排序。
			break;
		case 1:
			condition = " where userid = '" + c + "'";
			break;
		case 2:
			condition = " where username = '" + c + "'";
			break;
		case 3:
			condition = c;// 10-16添加。
			break;
		default:
			break;
		}
		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String count = "select count(*) from " + table + condition;
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			int number = countrs.getInt(1);
			if (column == null) {
				feedback = new String[number][0];
			} else {
				feedback = new String[number][column.length];
			}
			// feedback = new String[number][column.length+1];//10-16注释
			if (number == 0) {
				countrs.close();
				statement.close();
				conn.close();
				return feedback;
			}
			countrs.close();
			if (column != null) {
				String query = "select * from " + table + condition;
				System.out.println(query);
				ResultSet rs = statement.executeQuery(query);
				for (int j = 0; j < number; j++) {
					rs.absolute(j + 1);
					for (int i = 0; i < column.length; i++) {
						if (column[i].equals("usernstate")
								|| column[i].equals("id")) {
							feedback[j][i] = rs.getInt(column[i]) + "";
						} else {
							feedback[j][i] = rs.getString(column[i]);
						}
					}
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-85", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-89", e);
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-97", e);
			}

		}

		return feedback;
	}

	/**
	 * 用于查询数据库中某一项或几项的数值。
	 * 
	 * @param database
	 *            数据库的名称
	 * @param table
	 *            表的名称
	 * @param sort
	 *            查找范围：0，表示查找整个表all；1，表示根据usrid查找；2，表示根据username查找。 3，表示自定义查找条件。
	 * @param c
	 *            查找条件：当sort=0时，c应设为null；当sort=1时，c为具体的userid；当sort=2时，c为具体的
	 *            username；当sort=3时，c为自定义的查找条件，比如" where account = 'winter'"
	 * @param column
	 *            该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @return 为String[][]类型数组，数组的长度String[][].length为满足当前查询条件的结果数。二维数组
	 *         String[i][j]的列与column数组对应，记录第i行column对应的数值（即column的第i个结果）。
	 */
	public long[][] queryLong(String database, String table, int sort,
			String c, String[] column) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		String user = USER;
		String password = PASSWORD;
		String condition = null;
		long[][] feedback = null;
		Connection conn = null;
		Statement statement = null;
		switch (sort) {
		case 0:
			condition = " order by id";// 查询所有数据时，按id排序。
			break;
		case 1:
			condition = " where userid = '" + c + "'";
			break;
		case 2:
			condition = " where username = '" + c + "'";
			break;
		case 3:
			condition = c;// 10-16添加。
			break;
		default:
			break;
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String count = "select count(*) from " + table + condition;
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			int number = countrs.getInt(1);
			if (column == null) {
				feedback = new long[number][0];
			} else {
				feedback = new long[number][column.length];
			}
			// feedback = new String[number][column.length+1];//10-16注释
			if (number == 0) {
				countrs.close();
				statement.close();
				conn.close();
				return feedback;
			}
			countrs.close();
			if (column != null) {
				String query = "select * from " + table + condition;
				ResultSet rs = statement.executeQuery(query);
				for (int j = 0; j < number; j++) {
					rs.absolute(j + 1);
					for (int i = 0; i < column.length; i++) {

						feedback[j][i] = rs.getLong(column[i]);
						// 当该字段为null时，rs.getLong(column[i])的返回值为0
						// System.out.println(feedback[j][i]);
					}
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-85", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-89", e);
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-97", e);
			}

		}

		return feedback;
	}

	/**
	 * 根据条件，更新数据库中某一张表的某一项的值。
	 * 
	 * @param database
	 *            数据库名称
	 * @param table
	 *            表的名称
	 * @param sort
	 *            sort 更新范围：0，表示更新整个表all；1，表示根据usrid更新；2，表示根据username更新;
	 *            3,表示自定义更新条件。
	 * @param c
	 *            更新条件：当sort=0时，c应设为null；当sort=1时，c为具体的userid；当sort=2时，c为具体的
	 *            username；当sort=3时，c为自定义条件，比如" where id = 3"。
	 * @param column
	 *            更新项目的名称。
	 * @param value
	 *            将满足条件的项目的值更新为value。
	 * @return 当成功更新后，返回true；当更新异常或者满足更新条件的结果数为0时，返回false。
	 */
	public boolean update(String database, String table, int sort, String c,
			String column, String value) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		String user = USER;
		String password = PASSWORD;
		String condition = null;
		Connection conn = null;
		Statement statement = null;
		switch (sort) {
		case 0:
			condition = "";
			break;
		case 1:
			condition = " where userid = '" + c + "'";
			break;
		case 2:
			condition = " where username = '" + c + "'";
			break;
		case 3:
			condition = c;
			break;
		default:
			break;
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String count = "select count(*) from " + table + condition;
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			if (countrs.getInt(1) == 0) {
				countrs.close();
				statement.close();
				conn.close();
				// logger.error("no record on this condition.");
				return false;
			}
			countrs.close();
			String update = null;
			if (value == null) {
				update = "update " + table + " set " + column + " = " + value
						+ condition;
			} else {
				update = "update " + table + " set " + column + " ='" + value
						+ "'" + condition;
			}
			statement.execute(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-155", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				logger.error("SQLException-169", e1);
			}
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-160", e);
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-169", e);
			}
		}
		return true;
	}

	/**
	 * 根据条件，更新数据库中某一张表的某几项的值。如果InsertData输入的值为null，则认为是空。
	 * 
	 * @param database
	 * @param table
	 * @param sort
	 *            sort 更新范围：0，表示更新整个表all；1，表示根据usrid更新；2，表示根据username更新;
	 *            3,表示自定义更新条件。
	 * @param c
	 *            更新条件：当sort=0时，c应设为null；当sort=1时，c为具体的userid；当sort=2时，c为具体的
	 *            username；当sort=3时，c为自定义条件，比如" where id = 3"。
	 * @param data
	 *            InsertData类型数据。
	 * @return 当成功更新后，返回true；当更新异常或者满足更新条件的结果数为0时，返回false。
	 */
	public boolean updates(String database, String table, int sort, String c,
			InsertData data) {
		return updates(database, table, sort, c, data.getInsertData());
		// // String[] column = null;
		// // String[] value = null;
		// // try{
		// // column = data.getColumnData();
		// // value = data.getValueData();
		// // }catch(Exception e){
		// // e.printStackTrace();
		// // return false;
		// // }
		// // if(column.length==0){
		// // System.out.println("column.length==0");
		// // return false;
		// // }
		// String driver = DRIVER;
		// String url = DATABASE_URL+database;
		// //修改成如下形式，一遍能实现向mysql传入中文。
		// url =
		// url+"?user="+USER+"&password="+PASSWORD+"&useUnicode=true&characterEncoding="+CODE;
		//
		// String condition = null;
		// Connection conn = null;
		// Statement statement = null;
		// switch(sort){
		// case 0:
		// condition = "";
		// break;
		// case 1:
		// condition = " where userid = '"+c+"'";
		// break;
		// case 2:
		// condition = " where username = '"+c+"'";
		// break;
		// case 3:
		// condition = c;
		// break;
		// default:
		// break;
		// }
		// try{
		// Class.forName(driver);
		// conn = DriverManager.getConnection(url);
		// statement = conn.createStatement();
		// String count = "select count(*) from "+table+condition;
		// System.out.println(count);
		// ResultSet countrs = statement.executeQuery(count);
		// countrs.next();
		// if(countrs.getInt(1)==0){
		// countrs.close();
		// statement.close();
		// conn.close();
		// return false;
		// }
		// countrs.close();
		// String update = "update "+table+" set "+data.getInsertData();
		// // if(value[0]==null){
		// // update = "update "+table+" set "+column[0]+" ="+value[0];
		// // }else{
		// // update = "update "+table+" set "+column[0]+" ='"+value[0]+"'";
		// // }
		// // for(int i=1;i<data.getCount();i++){
		// // if(value[i]==null){
		// // update = update+", "+column[i]+"="+value[i];
		// // }else{
		// // update = update+", "+column[i]+"='"+value[i]+"'";
		// // }
		// // }
		// update = update+condition;
		// System.out.println(update);
		// statement.execute(update);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// try {
		// statement.close();
		// conn.close();
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// return false;
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return false;
		// }finally{
		// try {
		// statement.close();
		// conn.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// return true;
	}

	public boolean updates(String database, String table, int sort, String c,
			String updateData) {

		// String[] column = null;
		// String[] value = null;
		// try{
		// column = data.getColumnData();
		// value = data.getValueData();
		// }catch(Exception e){
		// e.printStackTrace();
		// return false;
		// }
		// if(column.length==0){
		// System.out.println("column.length==0");
		// return false;
		// }
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		// 修改成如下形式，一遍能实现向mysql传入中文。
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;

		String condition = null;
		Connection conn = null;
		Statement statement = null;
		switch (sort) {
		case 0:
			condition = "";
			break;
		case 1:
			condition = " where userid = '" + c + "'";
			break;
		case 2:
			condition = " where username = '" + c + "'";
			break;
		case 3:
			condition = c;
			break;
		default:
			break;
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			// String count = "select count(*) from "+table+condition;
			// System.out.println(count);
			// ResultSet countrs = statement.executeQuery(count);
			// countrs.next();
			// if(countrs.getInt(1)==0){
			// countrs.close();
			// statement.close();
			// conn.close();
			// return false;
			// }
			// countrs.close();
			String update = "update " + table + " set " + updateData;
			// if(value[0]==null){
			// update = "update "+table+" set "+column[0]+" ="+value[0];
			// }else{
			// update = "update "+table+" set "+column[0]+" ='"+value[0]+"'";
			// }
			// for(int i=1;i<data.getCount();i++){
			// if(value[i]==null){
			// update = update+", "+column[i]+"="+value[i];
			// }else{
			// update = update+", "+column[i]+"='"+value[i]+"'";
			// }
			// }
			update = update + condition;
			System.out.println(update);
			statement.execute(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				logger.error("", e);
			}
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("", e);
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("", e);
			}
		}
		return true;
	}

	public boolean updateBySql() {
		// TODO

		return true;
	}

	/**
	 * 向表中插入新数据。
	 * 
	 * @param database
	 * @param table
	 * @param data
	 *            InsertData类型数据。
	 * @return 插入成功，返回true；否则，返回false。
	 */
	public boolean insert(String database, String table, InsertData data) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		// 修改成如下形式，一遍能实现向mysql传入中文。
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String[] column = data.getColumnData();
		// String[] value = data.getValueData();
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String insert = "insert " + table + " set " + data.getInsertData();
			// for(int i=0;i<data.getCount();i++){
			// if(i==0){
			// insert = insert+column[i]+"='"+value[i]+"' ";
			// }else{
			// insert = insert+", "+column[i]+"='"+value[i]+"' ";
			// }
			// }
			statement.execute(insert);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException", e);
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// logger.error("SQLException", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				logger.error("SQLException-169", e1);
			}
			// System.out.println(e.getMessage());
			if (e.getMessage().contains("Duplicate entry")
					&& e.getMessage().contains("for key 'PRIMARY'")) {
				return true;
			}
			// e.printStackTrace();
			logger.error("", e);
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException", e);
			}
		}
		return true;
	}

	/**
	 * 
	 * @param database
	 * @param table
	 * @param data
	 * @return 异常时返回-1
	 */
	public long insertAndGetID(String database, String table, InsertData data) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		// 修改成如下形式，一遍能实现向mysql传入中文。
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String[] column = data.getColumnData();
		// String[] value = data.getValueData();
		Connection conn = null;
		Statement statement = null;
		long id = -1;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String insert = "insert " + table + " set " + data.getInsertData();
			// for(int i=0;i<data.getCount();i++){
			// if(i==0){
			// insert = insert+column[i]+"='"+value[i]+"' ";
			// }else{
			// insert = insert+", "+column[i]+"='"+value[i]+"' ";
			// }
			// }
			statement.execute(insert);

			ResultSet countrs = statement
					.executeQuery("select LAST_INSERT_ID();");
			countrs.next();
			id = countrs.getLong(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("ClassNotFoundException", e);
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// logger.error("SQLException", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				logger.error("SQLException-169", e1);
			}
			// System.out.println(e.getMessage());

			// e.printStackTrace();
			logger.error("", e);
			return -1;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException", e);
			}
		}
		return id;
	}

	/**
	 * 删除表中某一行的数据。
	 * 
	 * @param database
	 * @param table
	 * @param condition
	 *            检索条件，比如“ where userid = '0001'”,注意where前有一个空格。
	 * @return 当成功删除指定数据，则返回true；否则，返回false。
	 */
	public boolean delete(String database, String table, String condition) {
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String count = "select count(*) from " + table + " " + condition;
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			if (countrs.getInt(1) == 0) {
				countrs.close();
				statement.close();
				conn.close();
				// logger.error("can't find any data on this condition. "+condition);
				// return false;
				return true;// 140416
			}
			countrs.close();
			String delete = "delete from " + table + " " + condition;
			statement.execute(delete);
			// System.out.println("delete");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-259", e);
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-264", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-", e1);
			}
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-", e);
			}
		}
		return true;
	}

	/**
	 * to create a table if the name of the table hasn't been used in database.
	 * 
	 * @param database
	 *            name of database.
	 * @param table
	 *            the name of table which is going to be created.
	 * @param columns
	 *            like "(id integer unsigned not null auto_increment,operation
	 *            varchar(100) default null,primary key(id))",
	 *            <code>must include "()"</code>
	 * @return <strong>true</strong>, when the table is been created
	 *         successfully;otherwise, <strong>false</strong>.
	 */
	public boolean createTable(String database, String table, String columns) {

		if (database == null || table == null || columns == null) {
			return false;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String show_tables = "show tables";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int i = 1;
			int temp = 0;
			while (rs.absolute(i)) {
				if (table.equals(rs.getString(1))) {
					temp++;
					break;
				}
				i++;
			}
			if (temp != 0) {
				logger.debug("this table name has been used");
				// logger.info("[warn] this table name of ["+table+"]has been used");
				rs.close();
				statement.close();
				conn.close();
				return false;
			}
			rs.close();
			String command = "create table " + table + columns;
			statement.execute(command);
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-", e);
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-273", e1);
			}
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-273", e);
			}
		}
		return true;
	}

	public boolean dropTable(String database, String table) {

		if (database == null || table == null) {
			return false;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String show_tables = "show tables";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int i = 1;
			int temp = 0;
			while (rs.absolute(i)) {
				if (table.equals(rs.getString(1))) {
					temp++;
					break;
				}
				i++;
			}
			if (temp == 0) {
				System.out.println("the table of [" + table
						+ "] can't be found in this database");
				rs.close();
				statement.close();
				conn.close();
				return false;
			}
			rs.close();
			String command = "drop table " + table;
			statement.execute(command);
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("ClassNotFoundException-", e);
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// logger.error("SQLException-273", e1);
			}
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// logger.error("SQLException-273", e);
			}
		}
		return true;
	}

	/**
	 * to show the tables in database.
	 * 
	 * @param database
	 *            the name of the database.
	 * @return String[] if some tables has been found in the database;null,if
	 *         some error has occurred.
	 */
	public String[] showTables(String database) {

		if (database == null) {
			return null;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		String user = USER;
		String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		String[] tables = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String show_tables = "show tables";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int count = 1;
			while (rs.absolute(count)) {
				count++;
			}
			tables = new String[count - 1];
			for (int i = 0; i < tables.length; i++) {
				rs.absolute(i + 1);
				tables[i] = rs.getString(1);
			}
			rs.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("ClassNotFoundException-", e);
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// logger.error("SQLException-273", e1);
			}
			return null;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// logger.error("SQLException-273", e);
			}
		}
		return tables;
	}

	public boolean hasTableOf(String database, String table) {

		if (database == null) {
			return false;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		String user = USER;
		String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		// String[] tables =null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String show_tables = "show tables like '" + table + "'";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int count = 1;
			while (rs.absolute(count)) {
				count++;
			}
			rs.close();
			statement.close();
			conn.close();

			if ((count - 1) > 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("ClassNotFoundException-", e);
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// logger.error("SQLException-273", e1);
			}
			return false;
		}
	}

	/**
	 * 查询指定偏移量的指定条数
	 * 
	 * @param database
	 * @param table
	 * @param column
	 *            该项为String[]类型数组，记录需要查询的项目的名称。当需要查询满足条件的结果的数目时， 可将该项设为null。
	 * @param condition
	 *            Condition类型。当无限制条件时，该项设为null
	 * @param offset
	 *            偏移量，如果值为-1，并且amount的值也为-1，则表示查询所有满足条件的结果
	 * @param amount
	 *            查询条数,如果值为-1，并且offset的值也为-1，则表示查询所有满足条件的结果
	 * @param desc
	 *            降序排列（根据id）
	 * @return 为String[][]类型数组，数组的长度String[][].length为满足当前查询条件的结果数。二维数组
	 *         String[i][j]的列与column数组对应，记录第i行column对应的数值（即column的第i个结果）。
	 */
	public String[][] queryRegion(String database, String table,
			String[] column, Condition condition, int offset, int amount,
			boolean desc) {

		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		// String c = " order by id";//查询所有数据时，按id排序。
		StringBuffer c = new StringBuffer(" ");

		if (condition != null && condition.getCondition() != null) {
			c.append(condition.getCondition());
			// c += condition.getCondition();
		}

		if (desc) {
			c.append(" order by id desc");
			// c += " order by id desc";
		} else {
			c.append(" order by id");
			// c += " order by id";
		}

		if (offset == -1 || amount == -1) {

		} else {
			c.append(" limit ").append(String.valueOf(offset)).append(", ")
					.append(String.valueOf(amount));
			// c += " limit " + offset + ", " + amount;
		}

		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;

		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			// String count = "select count(*) from "+table+c;
			// ResultSet countrs = statement.executeQuery(count);
			// countrs.next();
			// int number = countrs.getInt(1);
			// if(column==null){
			// feedback = new String[number][0];
			// }else{
			// feedback = new String[number][column.length];
			// }
			// //feedback = new String[number][column.length+1];//10-16注释
			// if(number==0){
			// countrs.close();
			// statement.close();
			// conn.close();
			// return feedback;
			// }
			// countrs.close();
			if (column != null) {
				String query = "select * from " + table + c.toString();
				// if(Configuration.debug){
				// System.out.println(query);
				// }

				ResultSet rs = statement.executeQuery(query);
				int number;
				rs.last();
				number = rs.getRow();
				feedback = new String[number][column.length];
				for (int j = 0; j < number; j++) {
					rs.absolute(j + 1);
					for (int i = 0; i < column.length; i++) {
						// if(column[i].equals("usernstate")||column[i].equals("id")){
						// feedback[j][i] = rs.getInt(column[i])+"";
						// }else{
						feedback[j][i] = rs.getString(column[i]);
						// }
					}
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-85", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-89", e);
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-97", e);
			}

		}

		return feedback;
	}

	public String[][] queryRegion(String database, String table,
			String[] column, String condition, int offset, int amount,
			boolean desc) {

		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		// String c = " order by id";//查询所有数据时，按id排序。
		StringBuffer c = new StringBuffer(" ");

		if (condition != null) {
			c.append(condition);
			// c += condition.getCondition();
		}

		if (desc) {
			c.append(" order by id desc");
			// c += " order by id desc";
		} else {
			c.append(" order by id");
			// c += " order by id";
		}

		if (offset == -1 || amount == -1) {

		} else {
			c.append(" limit ").append(String.valueOf(offset)).append(", ")
					.append(String.valueOf(amount));
			// c += " limit " + offset + ", " + amount;
		}

		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;

		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			// String count = "select count(*) from "+table+c;
			// ResultSet countrs = statement.executeQuery(count);
			// countrs.next();
			// int number = countrs.getInt(1);
			// if(column==null){
			// feedback = new String[number][0];
			// }else{
			// feedback = new String[number][column.length];
			// }
			// //feedback = new String[number][column.length+1];//10-16注释
			// if(number==0){
			// countrs.close();
			// statement.close();
			// conn.close();
			// return feedback;
			// }
			// countrs.close();
			if (column != null) {
				String query = "select * from " + table + c.toString();
				// if(Configuration.debug){
				// System.out.println(query);
				// }

				ResultSet rs = statement.executeQuery(query);
				int number;
				rs.last();
				number = rs.getRow();
				feedback = new String[number][column.length];
				for (int j = 0; j < number; j++) {
					rs.absolute(j + 1);
					for (int i = 0; i < column.length; i++) {
						// if(column[i].equals("usernstate")||column[i].equals("id")){
						// feedback[j][i] = rs.getInt(column[i])+"";
						// }else{
						feedback[j][i] = rs.getString(column[i]);
						// }
					}
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-85", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-89", e);
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-97", e);
			}

		}

		return feedback;
	}

	public String[][] queryRegion(String database, String table,
			String[] column, String condition, int offset, int amount) {

		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		// String c = " order by id";//查询所有数据时，按id排序。
		StringBuffer c = new StringBuffer(" ");

		if (condition != null) {
			c.append(condition);
			// c += condition.getCondition();
		}

		if (offset == -1 || amount == -1) {

		} else {
			c.append(" limit ").append(String.valueOf(offset)).append(", ")
					.append(String.valueOf(amount));
			// c += " limit " + offset + ", " + amount;
		}

		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;

		try {
			Class.forName(driver);
			// conn = DriverManager.getConnection(url, user, password);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			// String count = "select count(*) from "+table+c;
			// ResultSet countrs = statement.executeQuery(count);
			// countrs.next();
			// int number = countrs.getInt(1);
			// if(column==null){
			// feedback = new String[number][0];
			// }else{
			// feedback = new String[number][column.length];
			// }
			// //feedback = new String[number][column.length+1];//10-16注释
			// if(number==0){
			// countrs.close();
			// statement.close();
			// conn.close();
			// return feedback;
			// }
			// countrs.close();
			if (column != null) {
				String query = "select * from " + table + c.toString();
				System.out.println(query);

				ResultSet rs = statement.executeQuery(query);
				int number;
				rs.last();
				number = rs.getRow();
				feedback = new String[number][column.length];
				for (int j = 0; j < number; j++) {
					rs.absolute(j + 1);
					for (int i = 0; i < column.length; i++) {
						// if(column[i].equals("usernstate")||column[i].equals("id")){
						// feedback[j][i] = rs.getInt(column[i])+"";
						// }else{
						feedback[j][i] = rs.getString(column[i]);
						// }
					}
				}
				rs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("ClassNotFoundException-85", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("SQLException-89", e);
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("SQLException-97", e);
			}

		}

		return feedback;
	}

	public String[][] query() {

		return null;
	}

	/**
	 * 
	 * @param database
	 *            数据库
	 * @param column
	 *            需要查询的字段
	 * @param sql
	 *            查询数据的sql语句
	 * @return
	 */
	public String[][] querySql(String database, String[] column, String sql) {
		if (column == null) {
			return null;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;

		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			// System.out.println(sql);
			int number;
			rs.last();
			number = rs.getRow();
			feedback = new String[number][column.length];
			for (int j = 0; j < number; j++) {
				rs.absolute(j + 1);
				for (int i = 0; i < column.length; i++) {
					feedback[j][i] = rs.getString(column[i]);
				}
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return feedback;
	}

	/**
	 * 
	 * @param database
	 *            数据库
	 * @param column
	 *            需要查询的字段
	 * @param sql
	 *            查询数据的sql语句
	 * @param page
	 *            页数（第一页为1）；-1表示查询所有
	 * @param amount
	 *            每页显示的数目
	 * @return
	 */
	public String[][] querySql(String database, String[] column, String sql,
			int page, int amount) {
		if (column == null) {
			return null;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;

		String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;
		try {
			if (page != -1) {
				sql += " limit " + String.valueOf((page - 1) * amount) + ", "
						+ String.valueOf(amount);
			}
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			int number;
			rs.last();
			number = rs.getRow();
			feedback = new String[number][column.length];
			for (int j = 0; j < number; j++) {
				rs.absolute(j + 1);
				for (int i = 0; i < column.length; i++) {
					feedback[j][i] = rs.getString(column[i]);
				}
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return feedback;
	}

	/**
	 * "select * from ..." sql语句必须以上述形式开始
	 * 
	 * @param database
	 * @param sql
	 * @return
	 */
	public long countQuerySql(String database, String sql) {
		if (!sql.contains("*")) {
			logger.error("!sql.contains('*')");
			return -1;
		}
		String driver = DRIVER;
		String url = DATABASE_URL + database;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			// String count = sql.replace("*", "count(*)");
			String count = sql.replaceFirst("[*]", "count(*)");
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			long result = countrs.getLong(1);
			countrs.close();
			statement.close();
			conn.close();
			return result;
		} catch (Exception e) {
			logger.error("", e);
			return -1;
		}
	}
	
	/**
	 * 
	 * @param clazz
	 * @param sql "where ...."
	 * @return
	 */
	public int countQuerySql(Class<?> entity, String sql) {
		
		String driver = DRIVER;
		String url = DATABASE_URL + Constants.DATABASE_NAME;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String count = "select count(*) from " + getTableName(entity) + " "
					+ sql;
			ResultSet countrs = statement.executeQuery(count);
			countrs.next();
			int result = countrs.getInt(1);
			countrs.close();
			statement.close();
			conn.close();
			return result;
		} catch (Exception e) {
			logger.error("", e);
			return -1;
		}
	}

	/**
	 * 获取总页数 "select * from ..." sql语句必须以上述形式开始
	 * 
	 * @param database
	 * @param sql
	 * @param amount
	 *            每页显示的数目
	 * @return
	 */
	public int countQuerySqlTotalPage(String database, String sql, int amount) {
		long totalNumber = countQuerySql(database, sql);
		return (int) ((totalNumber % amount) == 0 ? totalNumber / amount
				: totalNumber / amount + 1);
	}

	/**
	 * 根据结果的总数，得出总页数
	 * 
	 * @param totalNumber
	 * @param amount
	 *            每页显示的数目
	 * @return
	 */
	public int countQueryTotalPage(long totalNumber, int amount) {
		return (int) ((totalNumber % amount) == 0 ? totalNumber / amount
				: totalNumber / amount + 1);
	}

	/**
	 * 
	 * @param entity
	 * @param sqlWhere
	 *            "where ....."
	 * @param page
	 *            第一页为1；查所有时，参数为-1
	 * @param amount
	 * @return
	 */
	public <T> DJPage<T> querySql(Class<T> entity, String sqlWhere, int page,
			int amount) {

		String driver = DRIVER;
		String url = DATABASE_URL + Constants.DATABASE_NAME;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;

		// String[][] feedback = null;
		Connection conn = null;
		Statement statement = null;
		DJPage<T> result = new DJPage<T>();
		List<T> list = new ArrayList<T>();
		result.setData(list);
		if(page==0){
			page = 1;
		}
		result.setCurrentPage(page);
		String sql = "select * from " + getTableName(entity) + " " + sqlWhere;
		String sqlCount = "select count(*) from " + getTableName(entity) + " "
				+ sqlWhere;
		try {
			
			if (page != -1) {
				sql += " limit " + String.valueOf((page - 1) * amount) + ", "
						+ String.valueOf(amount);
			}
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			logger.debug(sql);
			ResultSet countrs = statement.executeQuery(sqlCount);
			countrs.next();
			long totalNumber = countrs.getLong(1);
			result.setTotalNumber(totalNumber);
			countrs.close();
			result.setTotalPage((int) (totalNumber % amount == 0 ? totalNumber
					/ amount : totalNumber / amount + 1));

			ResultSet rs = statement.executeQuery(sql);
			int number;
			rs.last();
			number = rs.getRow();
			// feedback = new String[number][column.length];

			for (int j = 0; j < number; j++) {
				rs.absolute(j + 1);
				// for (int i = 0; i < column.length; i++) {
				// feedback[j][i] = rs.getString(column[i]);
				// }
				Field[] fields = entity.getDeclaredFields();
				T e = (T) entity.newInstance();
				for (Field field : fields) {
					if (field.isAnnotationPresent(Transparent.class)) {
						continue;
					}
					String name = field.getName();
					name = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					String column = changeShape(name);
					String type = field.getType().getName();
					// System.out.println("name: "+name);
					// System.out.println("type: "+type);
					// System.out.println("------");
					Class<?> param = null;
					if ("long".equals(type)) {
						param = long.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getLong(column));
					} else if ("java.lang.Long".equals(type)) {
						param = Long.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getLong(column));
					} else if ("int".equals(type)) {
						param = int.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getInt(column));
					} else if ("java.lang.Integer".equals(type)) {
						param = Integer.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getInt(column));
					} else if ("java.lang.String".equals(type)) {
						param = String.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getString(column));
					} else if ("double".equals(type)) {
						param = double.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getDouble(column));
					} else if ("java.lang.Double".equals(type)) {
						param = Double.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getDouble(column));
					} else if ("java.util.Date".equals(type)) {
						param = java.util.Date.class;
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
//							java.sql.Date d = rs.getDate(column);
							long time = rs.getTimestamp(column).getTime();
							m.invoke(e, new java.util.Date(time));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else if ("java.sql.Date".equals(type)) {
						param = java.sql.Date.class;
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getDate(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else {
						logger.error("unknown type: "+type);
						param = Object.class;
					}
				}
				list.add(e);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}
	
	/**
	 * 通过一个字段查询一条记录全部信息或多条记录打的全部信息
	 * @param entity
	 * @param sqlWhere "where ....."
	 * @return
	 */
	public <T> List<T> queryListSql(Class<T> entity, String sqlWhere) {

		String driver = DRIVER;
		String url = DATABASE_URL + Constants.DATABASE_NAME;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;

		Connection conn = null;
		Statement statement = null;
		List<T> list = new ArrayList<T>();
		String sql = "select * from " + getTableName(entity) + " " + sqlWhere;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			logger.debug(sql);
			ResultSet rs = statement.executeQuery(sql);
			int number;
			rs.last();
			number = rs.getRow();

			for (int j = 0; j < number; j++) {
				rs.absolute(j + 1);
				Field[] fields = entity.getDeclaredFields();
				T e = (T) entity.newInstance();
				for (Field field : fields) {
					if (field.isAnnotationPresent(Transparent.class)) {
						continue;
					}
					String name = field.getName();
					name = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					String column = changeShape(name);
					String type = field.getType().getName();
					// System.out.println("name: "+name);
					// System.out.println("type: "+type);
					// System.out.println("------");
					Class<?> param = null;
					if ("long".equals(type)) {
						param = long.class;
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getLong(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}						
					} else if ("java.lang.Long".equals(type)) {
						param = Long.class;						
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getLong(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else if ("int".equals(type)) {
						param = int.class;						
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							Object value = rs.getObject(column);
							if(value==null){
								m.invoke(e, 0);
							}else{
								m.invoke(e, (int) value);
							}
						}catch(Exception e1){
							logger.debug("column: "+column, e1);
						}
					} else if ("java.lang.Integer".equals(type)) {
						param = Integer.class;						
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getInt(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else if ("java.lang.String".equals(type)) {
						param = String.class;
						Method m = e.getClass().getDeclaredMethod("set" + name,
								param);
						m.invoke(e, rs.getString(column));
					} else if ("double".equals(type)) {
						param = double.class;						
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							Object value = rs.getObject(column);
							if(value==null){
								m.invoke(e, 0);
							}else{
								m.invoke(e, (double) value);
							}
						}catch(Exception e1){
							logger.debug("column: "+column, e1);
						}
					} else if ("java.lang.Double".equals(type)) {
						param = Double.class;						
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getInt(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else if ("java.util.Date".equals(type)) {
						param = java.util.Date.class;
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							java.sql.Date d = rs.getDate(column);
							m.invoke(e, new java.util.Date(d.getTime()));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else if ("java.sql.Date".equals(type)) {
						param = java.sql.Date.class;
						try{
							Method m = e.getClass().getDeclaredMethod("set" + name,
									param);
							m.invoke(e, rs.getDate(column));
						}catch(Exception e1){
							logger.debug("", e1);
						}
					} else {
						param = Object.class;
					}
				}
				list.add(e);
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return list;
	}
	
	/**
	 * 
	 * @param entity
	 * @param sqlWheres "where ....."
	 * @return
	 */
	public <T> T queryUnique(Class<T> entity, String sqlWheres){
		List<T> list = queryListSql(entity, sqlWheres);
		if(list.size()==0){
			return null;
		}
		return list.get(0);	
	}
	
	public boolean hasTableOf(Class<?> clazz) {

		String driver = DRIVER;
		String url = DATABASE_URL + Constants.DATABASE_NAME;
		String user = USER;
		String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		// String[] tables =null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String show_tables = "show tables like '" + getSimpleTableName(clazz) + "'";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int count = 1;
			while (rs.absolute(count)) {
				count++;
			}
			rs.close();
			statement.close();
			conn.close();

			if ((count - 1) > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				e.printStackTrace();
				// logger.error("SQLException-273", e1);
			}
			return false;
		}
	}
	
	public boolean createTable(Class<?> clazz) {

		String driver = DRIVER;
		String url = DATABASE_URL + Constants.DATABASE_NAME;
		url = url + "?user=" + USER + "&password=" + PASSWORD
				+ "&useUnicode=true&characterEncoding=" + CODE;
		// String user = USER;
		// String password = PASSWORD;
		Connection conn = null;
		Statement statement = null;
		try {
			
			String table = getTableName(clazz);
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			String show_tables = "show tables";
			ResultSet rs = statement.executeQuery(show_tables);
			rs.next();
			int i = 1;
			int temp = 0;
			while (rs.absolute(i)) {
				if (getSimpleTableName(clazz).equals(rs.getString(1))) {
					temp++;
					break;
				}
				i++;
			}
			if (temp != 0) {
				System.out.println("this table name has been used");
				// logger.info("[warn] this table name of ["+table+"]has been used");
				rs.close();
				statement.close();
				conn.close();
				return false;
			}
			rs.close();
			
			Field[] fields = clazz.getDeclaredFields();
			String primaryKey = "id";
			StringBuffer sb = new StringBuffer();
			sb.append("(");
			for (Field field : fields) {
				if (field.isAnnotationPresent(Transparent.class)) {
					continue;
				}
				String name = field.getName();
				name = changeShape(name);
				String type = field.getType().getName();
				// System.out.println("name: "+name);
				// System.out.println("type: "+type);
				// System.out.println("------");
				sb.append(""+name+"");
				if(field.isAnnotationPresent(Id.class)){
					if ("long".equals(type) || "java.lang.Long".equals(type)) {
						sb.append(" bigint(20) not null auto_increment, ");
					} else if ("int".equals(type) || "java.lang.Integer".equals(type)) {
						sb.append(" int(10) not null auto_increment, ");
					} else if ("java.lang.String".equals(type)) {
						sb.append(" varchar(255), ");
					} else if("double".equals(type) || "java.lang.Double".equals(type)) {
						sb.append(" DOUBLE, ");
					} else if("java.util.Date".equals(type) || "java.sql.Date".equals(type)) {
						sb.append(" datetime, ");
					} else {
						logger.error("unsupport type, "+type);
						return false;
					}					
					primaryKey = name;
					continue;
				}
				
				if ("long".equals(type) || "java.lang.Long".equals(type)) {
					sb.append(" bigint(20) default 0, ");
				} else if ("int".equals(type) || "java.lang.Integer".equals(type)) {
					sb.append(" int(10) default 0, ");
				} else if ("java.lang.String".equals(type)) {
					sb.append(" varchar(255) default null, ");
				} else if("double".equals(type) || "java.lang.Double".equals(type)) {
					sb.append(" DOUBLE, ");
				} else if("java.util.Date".equals(type) || "java.sql.Date".equals(type)) {
					sb.append(" datetime, ");
				} else {
					logger.error("unsupport type, "+type);
					return false;
				}
			}
			sb.append("primary key ("+primaryKey+")");
			sb.append(")ENGINE=MyISAM DEFAULT CHARSET=gbk;");
			
			String command = "create table " + table + sb.toString();
			logger.debug(command);
			statement.execute(command);
			statement.close();
		} catch (Exception e) {
			logger.error("SQLException-", e);
			try {
				statement.close();
				conn.close();
			} catch (SQLException e1) {
				logger.error("SQLException-273", e1);
			}
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				logger.error("SQLException-273", e);
			}
		}
		return true;
	}
/**
 * 得到表名
 * @param clazz
 * @return
 */
	public static String getTableName(Class<?> clazz) {		
		return "`"+mysql.PREFIX + getSimpleTableName(clazz) + "`";
	}
	
	public static String getSimpleTableName(Class<?> clazz){
		String name = clazz.getSimpleName();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!Character.isLowerCase(c)) {
				if (i != 0) {
					sb.append("_");
				}
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 转化形式
	 * DemoForm 变为  demo_form
	 * @param name
	 * @return
	 */
	public static String changeShape(String name){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!Character.isLowerCase(c)) {
				if (i != 0) {
					sb.append("_");
				}
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
