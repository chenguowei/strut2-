package com.dijing.server.web.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页模型
 * @author winter
 * @date   2014-12-31上午8:33:32
 */
public class DJPageUtil {

	/**
	 * 获取分页模型
	 * @param page 当前页数
	 * @param totalPage 总页数
	 * @param NUM 模型中每页显示的页数（常量）
	 * @return
	 */
	public static PageNavigation getPageModule(int page, int totalPage, final int NUM){
		if(totalPage==0){
			totalPage = 1;
		}
		int index = (int) (page % NUM == 0 ? page / NUM : page / NUM + 1);
		List<Integer> pageList = new ArrayList<Integer>();
		if(totalPage <= NUM){
			for(int i=0;i<totalPage;i++){
				pageList.add(i + 1);
			}
		}else if((index*NUM) > totalPage){
			for(int i=0;i<NUM;i++){
				pageList.add((int) (totalPage - (NUM - i - 1)));
			}
		}else{
			for(int i=0;i<NUM;i++){
				pageList.add(i + 1 + NUM * (index-1));
			}
		}
		int backward = pageList.get(0) == 1 ? 1 : pageList.get(0) - 1;			
		int forward = pageList.get(pageList.size()-1);
		forward = (forward + 1) > totalPage ? totalPage : (forward + 1);
		PageNavigation pn = new PageNavigation();
		pn.setPageList(pageList);
		pn.setBackward(backward);
		pn.setForward(forward);
		pn.setLast(totalPage);
		pn.setCurrentPage(page);
		return pn;
	}
}
