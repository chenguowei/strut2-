package com.banche.form;

import com.banche.model.Agency;
import com.dijing.server.form.FRequired;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午3:04:37
 */
public class AgencyForm extends Agency {

	@FRequired(name="经销商姓名")
	private String contact;
	
	@FRequired(name="联系电话")
	private String contactTel;

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
}
