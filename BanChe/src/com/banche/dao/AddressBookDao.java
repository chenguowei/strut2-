package com.banche.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banche.model.Addressbook;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月22日下午3:17:14
 */
public class AddressBookDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Addressbook> model = Addressbook.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public Addressbook findById(long id){
		String sql = "where id = " + id;
		return mysql.queryUnique(model, sql);
	}
	
	public Map<Long, Addressbook> findByIds(List<Long> ids){
		Map<Long, Addressbook> map = new HashMap<Long, Addressbook>();
		if(ids.size()==0){
			return map;
		}
		StringBuffer sb = new StringBuffer("where id in (");
		for(Long id : ids){
			sb.append(id).append(",");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		List<Addressbook> list = mysql.queryListSql(model, sb.toString());
		for(Addressbook ab : list){
			map.put(ab.getId(), ab);
		}
		return map;
	}
	
}
