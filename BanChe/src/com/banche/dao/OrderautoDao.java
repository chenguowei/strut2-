package com.banche.dao;

import java.util.ArrayList;
import java.util.List;

import com.banche.model.Order;
import com.banche.model.Orderauto;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

public class OrderautoDao {
private Mysql mysql = Mysql.getInstance();
	
	private Class<Orderauto> model = Orderauto.class;
	
	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public Orderauto findById(long id){
		String sql = "where id = " + id;
		return mysql.queryUnique(model, sql);
	}	
	
	public List<Orderauto> findByOrderList(List<Order> orderlist){
		if(orderlist==null||orderlist.size()<=0){
			return new ArrayList<Orderauto>();
		}
		else{
			StringBuffer sb = new StringBuffer("where 1=1");
			sb.append(" and orderid in(");
			for(Order o:orderlist){
				sb.append("'").append(o.getId()).append("',");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append(")");
			return mysql.queryListSql(model, sb.toString());
		}
	}
}
