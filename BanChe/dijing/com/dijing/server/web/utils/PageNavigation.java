package com.dijing.server.web.utils;

import java.util.List;

/**
 * 页码导航栏数据(分页数据)
 * @author winter
 * @date   2014-12-30下午4:27:36
 */
public class PageNavigation {

	/**
	 * 页码导航栏的上一页(一页页码导航栏为：x1，x2，x3，x4,即一页页码导航栏包含有多个页数)
	 */
	private Integer backward;
	/**
	 * 页码导航栏的下一页
	 */
	private Integer forward;
	/**
	 * 最后一页
	 */
	private Integer last;
	private List<Integer> pageList;
	/**
	 * 当前页数(第一页为1)
	 */
	private Integer currentPage;
	/**
	 * 跳转链接
	 */
	private String url;
	
	public Integer getBackward() {
		return backward;
	}
	public Integer getForward() {
		return forward;
	}
	public Integer getLast() {
		return last;
	}
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setBackward(Integer backward) {
		this.backward = backward;
	}
	public void setForward(Integer forward) {
		this.forward = forward;
	}
	public void setLast(Integer last) {
		this.last = last;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getLastPage() {
		if(getCurrentPage()-1 < 1){
			return 1;
		}
		return getCurrentPage()-1;
	}
	public Integer getNextPage() {
		if(getCurrentPage()+1 > getLast()){
			return getLast();
		}
		return getCurrentPage()+1;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
