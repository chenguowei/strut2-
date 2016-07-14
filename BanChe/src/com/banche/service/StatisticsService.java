package com.banche.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.banche.dao.OrderDao;
import com.banche.enums.OrderStatus;
import com.banche.enums.StatististicsTimeEnums;
import com.banche.form.StatisticsForm;
import com.banche.model.Order;
import com.banche.vo.StatisticsVO;
import com.dijing.server.web.utils.TimeUtils;

public class StatisticsService {
	private OrderDao orderDao = new OrderDao();
	
	public StatisticsVO getOrderCount(){
		List<Order> orderlist = orderDao.findAll();
		
		int validCount = 0;//有效总数
		int invalidCount = 0;//无效总数
		if(orderlist!=null&&orderlist.size()>0){
			for(Order o :orderlist){
				if(o.getStatus()==OrderStatus.HDCX.getCode()){
					invalidCount +=1;
					continue;
				}
				if(o.getStatus()==OrderStatus.FAILURE.getCode()){
					invalidCount +=1;
					continue;
				}
				if(o.getStatus()==OrderStatus.ZCSB.getCode()){
					invalidCount +=1;
					continue;
				}
				if(o.getStatus()==OrderStatus.YSYW.getCode()){
					invalidCount +=1;
					continue;
				}
				if(o.getStatus()==OrderStatus.YSBTG.getCode()){
					invalidCount +=1;
					continue;
				}
				validCount+=1;
			}
		}
		else{
			return null;
		}
		Map<String, Integer> countmap = new HashMap<String, Integer>();
		countmap.put("有效货单", validCount);
		countmap.put("无效货单", invalidCount);
		
		StatisticsVO vo= new StatisticsVO(countmap,"总交易量统计");
		vo.setTotal(orderlist.size());
		return vo;
	}
	
	public StatisticsVO getOrderNew(StatisticsForm subform) throws Exception{
		Map<String, Integer> counterMap = new LinkedHashMap<String, Integer>();
		long datenow = TimeUtils.getTime2(TimeUtils.getTimeFromLong2(System.currentTimeMillis()))/1000;//获得今天的时间
		if(subform.getTimeType()==StatististicsTimeEnums.MONTH.getCode()){
			long oneDayMillis = 60*60*24;//每天秒数
			long datebefore = datenow-StatististicsTimeEnums.getTimeByCode(subform.getTimeType())*oneDayMillis;
			List<Order> orderlist = orderDao.findByCreatetimeRangeAndStatus(datebefore, datenow);
			
			for(int i=StatististicsTimeEnums.getTimeByCode(subform.getTimeType());i>0;i--){
				int index = i;
				List<Order> oList = orderlist.stream().filter(o->o.getCreatetime()>=datenow-oneDayMillis*index&&o.getCreatetime()<datenow-(index-1)*oneDayMillis).collect(Collectors.toList());
				counterMap.put(TimeUtils.getTimeFromLong2(datenow-oneDayMillis*index), oList.size());
			}
		}
		else if(subform.getTimeType()==StatististicsTimeEnums.YEAR.getCode()){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 0-StatististicsTimeEnums.YEAR.getTime());
			long datebefore = TimeUtils.getTimeMonth(TimeUtils.getDateTime2(cal.getTime()))/1000;
			List<Order> orderlist = orderDao.findByCreatetimeRangeAndStatus(datebefore, datenow);
			
			for(int i=StatististicsTimeEnums.getTimeByCode(subform.getTimeType());i>0;i--){
				Calendar calmonth = Calendar.getInstance();
				calmonth.add(Calendar.MONTH, 0-i);
				long monthstart = TimeUtils.getTimeMonth(TimeUtils.getDateTime2(calmonth.getTime()))/1000;
				calmonth.add(Calendar.MONTH, 1);
				long monthend = TimeUtils.getTimeMonth(TimeUtils.getDateTime2(calmonth.getTime()))/1000;
				List<Order> oList = orderlist.stream().filter(o->o.getCreatetime()>=monthstart&&o.getCreatetime()<monthend).collect(Collectors.toList());
				counterMap.put(TimeUtils.getTimeFromLong2(monthstart), oList.size());
			}
		}
		else if(subform.getTimeType()==StatististicsTimeEnums.DAY.getCode()){
			long now = System.currentTimeMillis()/1000;
			long oneHourMillis = 60*60;//每小时秒数
			long datebefore = now-StatististicsTimeEnums.getTimeByCode(subform.getTimeType())*oneHourMillis;
			List<Order> orderlist = orderDao.findByCreatetimeRangeAndStatus(datebefore, now);
			for(int i=StatististicsTimeEnums.getTimeByCode(subform.getTimeType());i>0;i--){
				int index = i;
				List<Order> oList = orderlist.stream().filter(o->o.getCreatetime()>=now-oneHourMillis*index&&o.getCreatetime()<now-(index-1)*oneHourMillis).collect(Collectors.toList());
				counterMap.put(TimeUtils.getTimeFromLong((now-oneHourMillis*index)*1000), oList.size());
			}
		}
		
		StatisticsVO vo= new StatisticsVO(counterMap, "新订单统计");
		return vo;
	}
}
