package com.banche.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.banche.enums.StatusEnums;
import com.banche.model.Driver;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午12:39:49
 */
public class DriverDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<Driver> model = Driver.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public DJPage<Driver> findList(int page, int pageSize){
		String sql = "";
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public Driver findById(long driverId){
		String sql = "where id = " + driverId;
		return mysql.queryUnique(model, sql);
	}
	
	public Driver findByTel(String tel){
		String sql = "where tel = '" + tel + "'";
		return mysql.queryUnique(model, sql);
	}
	
	public List<Driver> findByIds(List<Long> ids){
		if(ids.size()==0){
			return new ArrayList<Driver>();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("where id in (");
		for(Long id : ids){
			sb.append(id).append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		return mysql.queryListSql(model, sb.toString());
	}
	
	public DJPage<Driver> findByName(String search, int page, int pageSize){
		String sql = "where status = " + StatusEnums.PASS.getCode();
		if(!StringUtils.isEmpty(search)){
			sql += " and name like '%" + search + "%'";
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public List<Driver> findByName(String search){
		String sql = "where name like '%" + search + "%'";
		return mysql.queryListSql(model, sql);
	}
	
	public DJPage<Driver> findByNameAndIds(String search, List<Long> driverIds, int page, int pageSize){
		String sql = "where status = " + StatusEnums.PASS.getCode();
		if(!StringUtils.isEmpty(search)){
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append("id = '").append(search).append("' or ");
			sb.append("logistics like '%").append(search).append("%' or ");
			sb.append("name like '%").append(search).append("%' or ");
			sb.append("identity = '").append(search).append("' or ");
			sb.append("car_number like '%").append(search).append("%' or ");
			sb.append("tel = '").append(search).append("'");
			sb.append(")");
			sql += sb.toString();
		}
		if(driverIds.size() > 0){
			StringBuffer sb = new StringBuffer();
			sb.append(" and id in (");
			for(Long s : driverIds){
				sb.append(s).append(",");
			}
			sb = sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			sql += sb.toString();
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public DJPage<Driver> findByNameAndIdsNotIn(String search, List<Long> driverIds, int page, int pageSize){
		String sql = "where status = " + StatusEnums.PASS.getCode();
		if(!StringUtils.isEmpty(search)){			
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append("id = '").append(search).append("' or ");
			sb.append("logistics like '%").append(search).append("%' or ");
			sb.append("name like '%").append(search).append("%' or ");
			sb.append("identity = '").append(search).append("' or ");
			sb.append("car_number like '%").append(search).append("%' or ");
			sb.append("tel = '").append(search).append("'");
			sb.append(")");
			sql += sb.toString();
		}
		if(driverIds.size() > 0){
			StringBuffer sb = new StringBuffer();
			sb.append(" and id not in (");
			for(Long s : driverIds){
				sb.append(s).append(",");
			}
			sb = sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			sql += sb.toString();
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public DJPage<Driver> findUnpassedByName(String search, int page, int pageSize){
		String sql = "where status = " + StatusEnums.NEW.getCode();
		if(!StringUtils.isEmpty(search)){
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append("id = '").append(search).append("' or ");
			sb.append("logistics like '%").append(search).append("%' or ");
			sb.append("name like '%").append(search).append("%' or ");
			sb.append("identity = '").append(search).append("' or ");
			sb.append("car_number like '%").append(search).append("%' or ");
			sb.append("tel = '").append(search).append("'");
			sb.append(")");
			sql += sb.toString();
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public int countUnpassed(){
		String sql = "where status = " + StatusEnums.NEW.getCode();
		return mysql.countQuerySql(model, sql);
	}
	
}
