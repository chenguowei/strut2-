package com.dijing.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dijing.server.entity.IEntity;

/**
 * 模块的返回结果
 * @author winter
 * @date   2014-12-13下午4:47:36
 */
public class DJPage<T> {

	private int status;
	
	private List<T> data;
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
	
	private List<Integer> pageList;
	
	private boolean hasMoreForward;
	
	private boolean hasMoreForback;
	
	public DJPage(){
		this.data = new ArrayList<>();
		this.currentPage = 1;
		this.totalPage = 0;
		this.totalNumber = 0;
	}
	
	public DJPage(List<T> data, int totalPage, int currentPage, long totalNumber){
		this.data = data;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.totalNumber = totalNumber;
		formPageList();
	}
	
	public DJPage(DJPage<?> page, List<T> data){
		this.data = data;
		this.totalPage = page.totalPage;
		this.currentPage = page.currentPage;
		this.totalNumber = page.totalNumber;
		formPageList();
	}
	
	private void formPageList(){
		pageList = new ArrayList<>();
		if(totalPage <= 5){
			//前5页
			for(int i=1;i<=totalPage;i++){
				pageList.add(i);
			}
			this.hasMoreForback = false;
			this.hasMoreForward = false;
		}else if(totalPage - currentPage < 5){
			//后5页
			for(int i=0;i<5;i++){
				pageList.add(totalPage - 4 + i);
			}
			this.hasMoreForback = true;
			this.hasMoreForward = false;
		}else{
			if(currentPage - 2 > 0 && totalPage - currentPage > 1){
				pageList.add(currentPage - 2);
				pageList.add(currentPage - 1);
				pageList.add(currentPage);
				pageList.add(currentPage + 1);
				pageList.add(currentPage + 2);
				this.hasMoreForback = true;
				this.hasMoreForward = true;
			}else if(currentPage - 2 > 0){
				//后5页
				for(int i=0;i<5;i++){
					pageList.add(totalPage - 4 + i);
				}
				this.hasMoreForback = true;
				this.hasMoreForward = false;
			}else{
				for(int i=0;i<5;i++){
					pageList.add(i + 1);
				}
				this.hasMoreForback = false;
				this.hasMoreForward = true;
			}
		}
	}
	
	public int firstPage(){
		return 1;
	}
	
	public int lastPage(){
		return this.totalPage;
	}
	
	/**
	 * 有上一页
	 * @return
	 */
	public boolean hasForward(){
		return this.currentPage != 1;
	}
	
	/**
	 * 有下一页
	 * @return
	 */
	public boolean hasNext(){
		return this.currentPage < this.totalPage;
	}
	
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
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

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public boolean isHasMoreForward() {
		return hasMoreForward;
	}

	public void setHasMoreForward(boolean hasMoreForward) {
		this.hasMoreForward = hasMoreForward;
	}

	public boolean isHasMoreForback() {
		return hasMoreForback;
	}

	public void setHasMoreForback(boolean hasMoreForback) {
		this.hasMoreForback = hasMoreForback;
	}
}
