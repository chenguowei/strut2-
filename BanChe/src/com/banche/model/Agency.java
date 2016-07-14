package com.banche.model;

import com.banche.form.AgencyForm;
import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 经销商
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午7:56:25
 */
@ANote(value="经销商")
public class Agency extends DJModel {

	/**
	 * 登记id
	 */
	@Id
	@ANote(value="登记id")
	private long id;
	
	@ANote(value="账号id")
	private long accountid;
	/**
	 * 联系人
	 */
	@ANote(value="联系人")
	private String contact;
	/**
	 * 联系人电话
	 */
	@ANote(value="联系人电话")
	private String contactTel;
	/**
	 * 常用发货地址
	 */
	@ANote(value="常用发货地址")
	private String sendAddr;
	/**
	 * 常用收货地址
	 */
	@ANote(value="常用收货地址")
	private String receiveAddr;
	/**
	 * 登记日期
	 */
	@ANote(value="登记日期")
	private long registerTime;
	/**
	 * 交易数
	 */
	@ANote(value="交易数")
	private int deals;
	
	public Agency(){}
	
	public Agency(AgencyForm agencyForm){
		setFormData(agencyForm);
		this.registerTime = System.currentTimeMillis() / 1000;
	}
	
	public void setFormData(AgencyForm agencyForm){
		if(id==0){
			id = agencyForm.getId();
		}
		this.contact = agencyForm.getContact();
		this.contactTel = agencyForm.getContactTel();
		this.receiveAddr = agencyForm.getReceiveAddr();
		this.sendAddr = agencyForm.getSendAddr();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getSendAddr() {
		return sendAddr;
	}
	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}
	public String getReceiveAddr() {
		return receiveAddr;
	}
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	public int getDeals() {
		return deals;
	}
	public void setDeals(int deals) {
		this.deals = deals;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
}
