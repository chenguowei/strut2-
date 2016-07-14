package com.dijing.server.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Condition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1871129057841196636L;

	static Logger logger = LogManager.getLogger(Condition.class.getName());
	
//	private final int MAX = 20;
//	private String[] column = new String[MAX];
//	private String[] value = new String[MAX];
//	private int count = 0;
	private String OR = " or ";
	private String AND = " and ";
	private String OPERATOR = AND;
	private StringBuffer condition;
	
	private final boolean debug = true;
	
	private boolean newParentheses = false;
	/**
	 * 标记是否是第一个限制条件
	 * 如果是第一次限制条件，则自动补充 where
	 */
	private boolean isFirstWhere = true;
	
	/**
	 * 只构造sql语句的where部分
	 */
	public Condition(){
		condition = new StringBuffer();
	}
	
	/**
	 * 构造完整的sql语句
	 * @param table
	 * @param selection 需要查询的项
	 */
	public Condition(String table, String[] selection){
		condition = new StringBuffer();
		condition.append("select ");
		if(selection == null || selection.length == 0){
			condition.append("* from ")
				.append(table);
		}else{
			condition.append(selection[0]);
			for(int i=1;i<selection.length;i++){
				condition.append(",").append(selection[i]);
			}
			condition.append(" from ")
				.append(table);
		}
	}
	
	public void setOrOperator(boolean b){
		if(b){
			OPERATOR = OR;
		}else{
			OPERATOR = AND;
		}
	}
	
	/**
	 * 联表查询
	 * @param join like "right join b on b.ID = a.ID"
	 */
	public void setJoin(String join){
		condition.append(join);
	}
	
	/**
	 * 设置联表查询条件。column值包含在其他表的查询结果中。
	 * @param column
	 * @param otherTable 查询某张表的sql语句
	 */
	public void setInOtherResult(String column, Condition otherTable){
		setFromOtherResult(column, otherTable, "in");
	}
	
	/**
	 * 设置联表查询条件。column值不包含在其他表的查询结果中。
	 * @param column
	 * @param otherTable 查询某张表的sql语句
	 */
	public void setNotInOtherResult(String column, Condition otherTable){
		setFromOtherResult(column, otherTable, "not in");
	}
	
	public void setFromOtherResult(String column, Condition otherTable, String operator){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append(" ")
				.append(operator)
				.append(" (")
				.append(otherTable.getCondition())
				.append(")");
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append(" ")
				.append(operator)
				.append(" (")
				.append(otherTable.getCondition())
				.append(")");
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append(" ")
				.append(operator)
				.append(" (")
				.append(otherTable.getCondition())
				.append(")");
		}
	}
	
	public void setCondition(String column, String value){
//		if(count==20){
//			logger.error("too many condtions");
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value;
//		count++;
		if(value==null){
			return;
		}
		
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append("='")
				.append(value)
				.append("'");
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append("='")
				.append(value)
				.append("'");
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append("='")
				.append(value)
				.append("'");
		}
	}
	
	public void setLikeCondition(String column, String value){

		if(value==null){
			return;
		}
		
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append(" like '%")
				.append(value)
				.append("%'");
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append(" like '%")
				.append(value)
				.append("%'");
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append(" like '%")
				.append(value)
				.append("%'");
		}
	}
	
	public void setCondition(String column, long value){
//		if(count==MAX){
//			logger.error("too many condtions");
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value+"";
//		count++;
		
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append("=")
				.append(String.valueOf(value));
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append("=")
				.append(String.valueOf(value));
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append("=")
				.append(String.valueOf(value));
		}
	}
	
	/**
	 * 
	 * @param column
	 * @param year
	 * @param month
	 * @throws ParseException 
	 */
	public void setYearMonth(String column, int year, int month) throws ParseException{
		int startDate = 1;
		int endDate ;
		switch(month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			endDate = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			endDate = 30;
			break;
		case 2:
			if((year%4==0&&year%100!=0)||year%400==0){
				//是闰年
				endDate = 29;
			}else{
				endDate = 28;
			}
			break;
		default:
			endDate = 30;
			logger.error("不正确的月份："+month);
			return;
		}
		
		setTimeRegion(column, year+"-"+month+"-"+startDate+" 00:00:00", year+"-"+month+"-"+endDate+" 23:59:59");
	}
		
	/**
	 * 时间为某天的条件
	 * @param column
	 * @param date yyyy-mm-dd
	 * @throws ParseException 
	 */
	public void setDate(String column, String date) throws ParseException{
		if(date==null){
			return;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		//由string类型的时间获取long类型的时间值
		Date d = sd.parse(date);
		setDateLong(column, d.getTime());
	}
	
	/**
	 * 时间为某天的条件
	 * @param column
	 * @param date yyyy-mm-dd的long类型
	 */
	public void setDateLong(String column, long date){
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(date))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(date+86400000))
				.append(")");
		}else if(newParentheses){
			newParentheses = false;
			condition.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(date))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(date+86400000))
				.append(")");
		}else{
			condition.append(OPERATOR)
				.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(date))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(date+86400000))
				.append(")");
		}
	}
	
	/**
	 * 根据时间段筛选
	 * @param column
	 * @param start
	 * @param end
	 * @throws ParseException
	 */
	public void setTimeRegion(String column, String start, String end) throws ParseException{
		if(start==null || end==null){
			return;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//由string类型的时间获取long类型的时间值
		Date s = sd.parse(start);
		Date e = sd.parse(end);
		setTimeRegion(column, s.getTime(), e.getTime());
	}
	
	/**
	 * 根据时间段筛选
	 * @param column
	 * @param start
	 * @param end
	 */
	public void setTimeRegion(String column, long start, long end){
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(start))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(end))
				.append(")");
		}else if(newParentheses){
			newParentheses = false;
			condition.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(start))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(end))
				.append(")");
		}else{
			condition.append(OPERATOR)
				.append("(")
				.append(column)
				.append(">=")
				.append(String.valueOf(start))
				.append(AND)
				.append(column)
				.append("<=")
				.append(String.valueOf(end))
				.append(")");
		}
	}
	
	public void setCondition(String column, double value){
//		if(count==MAX){
//			logger.error("too many condtions");
//			return;
//		}
//		this.column[count] = column;
//		this.value[count] = value+"";
//		count++;
		
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append("=")
				.append(String.valueOf(value));
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append("=")
				.append(String.valueOf(value));
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append("=")
				.append(String.valueOf(value));
		}
	}
	
	public void setNotEqual(String column, String value){
		if(value==null){
			return;
		}
		
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append("!='")
				.append(value)
				.append("'");
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append("!='")
				.append(value)
				.append("'");
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append("!='")
				.append(value)
				.append("'");
		}
	}
	
	public void setNotEqual(String column, long value){
				
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where ")
				.append(column)
				.append("!=")
				.append(String.valueOf(value));
		}else if(newParentheses){
			newParentheses = false;
			condition.append(column)
				.append("!=")
				.append(String.valueOf(value));
		}else{
			condition.append(OPERATOR)
				.append(column)
				.append("!=")
				.append(String.valueOf(value));
		}
	}
	
	/**
	 * 添加左括号
	 */
	public void addLeftParentheses(){
		newParentheses = true;
//		if(condition.length()==0){
		if(isFirstWhere){
			isFirstWhere = false;
			condition.append(" where (");
		}else{
			condition.append(OPERATOR)
				.append("(");
		}
	}
	
	public void addRightParentheses(){
		condition.append(")");
	}
	
	public String getCondition(){
//		if(count==0){
//			return "";
//		}
//		
//		String condition = " where ";
//		
//		for(int i=0;i<count;i++){
//			if(i != 0){
//				condition += " " + OPERATOR + " ";
//			}
//			condition += column[i] + " = '" + value[i] + "'";
//		}
//		return condition;
		if(condition.length()==0){
			if(debug){
				logger.error("condition.length()==0");
				System.err.println("condition.length()==0");
			}
			return null;
		}
		return condition.toString();
	}
	
	public void clear(){
		condition = new StringBuffer();
		isFirstWhere = true;
	}
	
}
