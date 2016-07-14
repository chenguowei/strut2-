package com.banche.vo;

import com.banche.model.Feedback;

/**
 * 
 * @author winter
 * @date 2016年4月15日下午1:20:05
 */
public class FeedbackVO extends Feedback {

	private String userName;
	
	private String timeVO;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTimeVO() {
		return timeVO;
	}

	public void setTimeVO(String timeVO) {
		this.timeVO = timeVO;
	}
}
