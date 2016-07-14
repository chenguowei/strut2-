package com.banche.form;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午5:47:33
 */
public class SearchForm {

	private String search;
	
	private int page = 1;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
