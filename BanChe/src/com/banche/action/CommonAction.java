package com.banche.action;

import java.util.UUID;

import com.banche.DJActionSupport;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月26日下午3:18:17
 */
public class CommonAction extends DJActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * bc_Common_todayDate
	 * 获取今天的日期
	 * @return
	 * @throws Exception
	 */
	public String todayDate() throws Exception {
		OutputStringAction out = new OutputStringAction();
		String date = TimeUtils.getTimeFromLong2(System.currentTimeMillis());
		out.jsonResultSuccess(date);
		return null;
	}

}
