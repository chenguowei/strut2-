package com.banche.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.banche.model.Agency;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午2:00:58
 */
public class AgencyDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Agency> model = Agency.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}

	public DJPage<Agency> findList(int page, int pageSize) {
		String sql = "";
		return mysql.querySql(model, sql, page, pageSize);
	}

	public Agency findById(long agencyId) {
		String sql = "where id = " + agencyId;
		return mysql.queryUnique(model, sql);
	}
	
	public List<Agency> findByIds(List<Long> ids){
		if(ids.size()==0){
			return new ArrayList<>();
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
	
	public Agency findByTel(String tel){
		String sql = "where contact_tel = '" + tel + "'";
		return mysql.queryUnique(model, sql);
	}

	public DJPage<Agency> findByNameAndIdsNotIn(String search,
			List<Long> agencyIds, int page, int pageSize) {
		String sql = "where 1=1";
		if(!StringUtils.isEmpty(search)){
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append("id = '").append(search).append("' or ");
			sb.append("contact like '%").append(search).append("%' or ");
			sb.append("contact_tel = '").append(search).append("'");
			sb.append(")");
			sql += sb.toString();
		}
		if(agencyIds.size() > 0){
			StringBuffer sb = new StringBuffer();
			sb.append(" and id not in (");
			for(Long s : agencyIds){
				sb.append(s).append(",");
			}
			sb = sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			sql += sb.toString();
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
	
	public DJPage<Agency> findByNameAndIdsIn(String search,
			List<Long> agencyIds, int page, int pageSize) {
		String sql = "where 1=1";
		if(!StringUtils.isEmpty(search)){
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append("id = '").append(search).append("' or ");
			sb.append("contact like '%").append(search).append("%' or ");
			sb.append("contact_tel = '").append(search).append("'");
			sb.append(")");
			sql += sb.toString();
		}
		if(agencyIds.size() > 0){
			StringBuffer sb = new StringBuffer();
			sb.append(" and id in (");
			for(Long s : agencyIds){
				sb.append(s).append(",");
			}
			sb = sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			sql += sb.toString();
		}
		return mysql.querySql(model, sql, page, pageSize);
	}
}
