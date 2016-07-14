package com.banche.dao;

import java.util.ArrayList;
import java.util.List;

import com.banche.model.Automold;
import com.banche.model.Order;
import com.banche.model.Orderauto;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

public class AutomoldDao{
	private Mysql mysql = Mysql.getInstance();
	
	private Class<Automold> model = Automold.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public Automold findById(long id){
		String sql = "where id = " + id;
		return mysql.queryUnique(model, sql);
	}	
	
	public List<Automold> findByautoList(List<Orderauto> autoList){
		if(autoList==null||autoList.size()<=0){
			return new ArrayList<Automold>();
		}
		else{
			StringBuffer sb = new StringBuffer("where 1=1");
			sb.append(" and id in(");
			for(Orderauto o:autoList){
				sb.append("'").append(o.getMoldid()).append("',");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			return mysql.queryListSql(model, sb.toString());
		}
	}
}
