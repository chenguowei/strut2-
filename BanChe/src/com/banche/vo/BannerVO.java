package com.banche.vo;

import com.banche.model.Banner;

/**
 * 
 * @author winter
 * @date 2016年3月30日下午1:20:40
 */
public class BannerVO extends Banner {

	private String timeVO;
	
	private String statusVO;

	public String getTimeVO() {
		return timeVO;
	}

	public void setTimeVO(String timeVO) {
		this.timeVO = timeVO;
	}

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
	}
}
