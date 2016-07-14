package com.banche.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.banche.model.Driver;
import com.banche.model.Order;
import com.dijing.server.dao.AutoCreateTable;
import com.dijing.server.dao.DJPage;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午8:28:49
 */
public class OrderDao {

	private Mysql mysql = Mysql.getInstance();

	private Class<Order> model = Order.class;

	{
		AutoCreateTable.checkAndCreate(model);
	}
	
	public List<Order> findAll(){
		String sql = "where 1=1";
		return mysql.queryListSql(model, sql);
	}

	public Order findById(long orderId) {
		String sql = "where id = " + orderId;
		return mysql.queryUnique(model, sql);
	}

	public DJPage<Order> findByStatus(int status, String search, int page,
			int pageSize) {
		String sql = "where status = " + status;
		if (!StringUtils.isEmpty(search)) {
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append(" sn like '%").append(search).append("%'");
			sb.append(" or send_contact like '%").append(search).append("%'");
			sb.append(" or receive_contact like '%").append(search).append("%'");
			DriverDao dao = new DriverDao();
			List<Driver> list = dao.findByName(search);
			if (list.size() > 0) {
				List<Long> driverIds = list.stream().map(m -> m.getId())
						.distinct().collect(Collectors.toList());
				sb.append(" or driver in (");
				for(Long id : driverIds){
					sb.append(id).append(",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append(")");
			}
			sb.append(")");
			sql += sb.toString();
		}
		sql += " order by id desc";
		return mysql.querySql(model, sql, page, pageSize);
	}

	public DJPage<Order> findByStatus(List<Integer> status, String search,
			int page, int pageSize) {
		if (status == null || status.size() == 0) {
			return new DJPage<>();
		}
		StringBuffer str = new StringBuffer();
		str.append("where status in (");
		for (Integer i : status) {
			str.append(i).append(",");
		}
		str = str.delete(str.length() - 1, str.length());
		str.append(") ");
		if (!StringUtils.isEmpty(search)) {
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			sb.append(" sn like '%").append(search).append("%'");
			sb.append(" or send_contact like '%").append(search).append("%'");
			sb.append(" or receive_contact like '%").append(search).append("%'");
			DriverDao dao = new DriverDao();
			List<Driver> list = dao.findByName(search);
			if (list.size() > 0) {
				List<Long> driverIds = list.stream().map(m -> m.getId())
						.distinct().collect(Collectors.toList());
				sb.append(" or driver in (");
				for(Long id : driverIds){
					sb.append(id).append(",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append(")");
			}
			sb.append(")");
			str.append(sb.toString());
		}
		str.append(" order by id desc");
		return mysql.querySql(model, str.toString(), page, pageSize);
	}
	
	public List<Order> findByCreatetimeRangeAndStatus(long startTime,long endTime){
		String sql = "where createtime>"+startTime+" and createtime<"+endTime;
		return mysql.queryListSql(model, sql);
	}

	public int countByStatus(List<Integer> status) {
		if (status == null || status.size() == 0) {
			return 0;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("where status in (");
		for (Integer i : status) {
			sb.append(i).append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());
		sb.append(") ");
		return mysql.countQuerySql(model, sb.toString());
	}
	

	/**
	 * 司机的订单数
	 * 
	 * @param driverId
	 * @return
	 */
	public int countOrderByDriver(long driverId) {
		String sql = "where driver = " + driverId;
		return mysql.countQuerySql(model, sql);
	}

}
