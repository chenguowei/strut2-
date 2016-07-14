package com.banche.form;

import com.banche.model.Banner;
import com.dijing.server.form.FNumberic;
import com.dijing.server.form.FRequired;

/**
 * 
 * @author winter
 * @date 2016年3月30日下午1:21:54
 */
public class BannerForm extends Banner {

	@FRequired(name="banner图")
	private String image;
	
	@FNumberic(name="排序")
	private int showOrder;
	
	@FNumberic(name="发布状态")
	private int status;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
