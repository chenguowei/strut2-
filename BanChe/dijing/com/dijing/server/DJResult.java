package com.dijing.server;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dijing.server.entity.IEntity;

/**
 * 模块的返回结果
 * @author winter
 * @date   2014-12-13下午4:47:36
 */
public class DJResult<T> {

	private int status;
	
	private T data;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页数
	 */
	private int currentPage;
	/**
	 * 结果的总数目
	 */
	private long totalNumber;
	
	/**
	 * 将查询结果转化为json对象
	 * 查询结果的实体类必须实现IEntity接口
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() throws Exception{
		List<IEntity> entityList = (List<IEntity>) getData();
		if(entityList==null){
			return null;
		}
		JSONObject json = new JSONObject();
		json.put("totalPage", getTotalPage());
		json.put("currentPage", getCurrentPage());
		json.put("totalNumber", getTotalNumber());
		JSONArray arr = new JSONArray();
		for(int i=0;i<entityList.size();i++){
			arr.put(entityList.get(i).toJSON());
		}
		json.put("list", arr);
		return json;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
}
