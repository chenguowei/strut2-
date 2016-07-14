package com.banche.dao;

import java.util.ArrayList;
import java.util.List;

import com.banche.model.AuthModule;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午1:36:12
 */
public class AuthModuleDao {

	private Mysql mysql = Mysql.getInstance();
	
	private Class<AuthModule> model = AuthModule.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	/**
	 * 标记模块已被弃用
	 */
	private final int DROP = -1;
	
	public List<AuthModule> findAll(){
		return mysql.queryListSql(model, "where type != " + DROP + " order by seq desc");
	}
	
	public List<AuthModule> findByIds(List<Integer> ids){
		if(ids.size()==0){
			return new ArrayList<>();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("where id in (");
		for(Integer i : ids){
			sb.append(i).append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		sb.append(" and type != ").append(DROP);
		sb.append(" order by seq desc");
		return mysql.queryListSql(model, sb.toString());
	}
	
	public List<AuthModule> findByParent(int parent){
		String sql = "where parent = " + parent + " and type != " + DROP;
		return mysql.queryListSql(model, sql);
	}
}
