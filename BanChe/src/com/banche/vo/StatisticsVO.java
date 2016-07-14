package com.banche.vo;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StatisticsVO {
	static Logger logger = LogManager.getLogger(StatisticsVO.class.getName());
	
	private String title;
	private String data;
	private String xticks;
	private double total;
	
	/**
	 * 
	 * @param datalist
	 * @param title
	 */
//	public StatisticsVO(List<?> datalist,String title){
//		this.title = title;
//		//生成data字符串
//		int index=0;
//		String dataStr = "[";
//		for(Object d:datalist){
//			try{
//			dataStr+=Double.valueOf(d.toString());
//			}
//			catch(Exception e){
//				logger.debug(e);
//			}
//			if(++index<datalist.size()){
//				dataStr+=",";
//			}
//		}
//		dataStr+="]";
//		this.data=dataStr;
//	}
	
	/**
	 * 
	 * @param datamap
	 * @param title
	 */
	public StatisticsVO(Map<?, ?> datamap,String title){
		this.title = title;
		
		//生成data字符串
		int index =0;
		String dataStr = "[";
		String xticksStr = "[";
		for(Map.Entry<?, ?> entry:datamap.entrySet()){
			try{
//				System.out.println("['"+entry.getKey().toString()+"',"+entry.getValue()+"]");
				dataStr+="['"+entry.getKey().toString()+"',"+entry.getValue()+"]";
				xticksStr+="'"+entry.getKey().toString()+"'";
			}
			catch(Exception e){
				logger.debug(e);
			}
			if(++index<datamap.size()){
				dataStr+=",";
				xticksStr+=",";
			}
		}
		this.data = dataStr+"]";
		this.xticks = xticksStr+"]";
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getXticks() {
		return xticks;
	}

	public void setXticks(String xticks) {
		this.xticks = xticks;
	}
}
