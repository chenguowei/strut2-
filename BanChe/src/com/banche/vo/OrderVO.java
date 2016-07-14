package com.banche.vo;

import com.banche.model.Order;

public class OrderVO extends Order {

	private String statusVO;
	
	private long id;
	
	private String sn;
	
	private double totalFee;
	
	private double earnest;
	
	private double originalPrice;
	
	private double driverShowPrice;
	
	/**
	 * 差钱金额
	 */
	private double different;
	
	/**
	 * 定金比例
	 */
	private double rate;
	
	private String sendContact;
	
	private String sendTel;
	
	private String sendCompany;
	
	private String sendAddr;
	
	private long sendAddrId;
	
	private String receiveContact;
	
	private String receiveTel;
	
	private String receiveCompany;
	
	private String receiveAddr;
	
	private long revAddrId;
	
	private String note;
	
	private long carValue;
	
	private String createTime;
	
	//车辆信息
	private String mold;
	
	private String autoname;
	
	private int autonum;

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public String getSendContact() {
		return sendContact;
	}

	public void setSendContact(String sendContact) {
		this.sendContact = sendContact;
	}

	public String getSendTel() {
		return sendTel;
	}

	public void setSendTel(String sendTel) {
		this.sendTel = sendTel;
	}

	public String getSendCompany() {
		return sendCompany;
	}

	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany;
	}

	public String getSendAddr() {
		return sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getReceiveContact() {
		return receiveContact;
	}

	public void setReceiveContact(String receiveContact) {
		this.receiveContact = receiveContact;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public String getReceiveCompany() {
		return receiveCompany;
	}

	public void setReceiveCompany(String receiveCompany) {
		this.receiveCompany = receiveCompany;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getEarnest() {
		return earnest;
	}

	public void setEarnest(double earnest) {
		this.earnest = earnest;
	}

	public long getCarValue() {
		return carValue;
	}

	public void setCarValue(long carValue) {
		this.carValue = carValue;
	}

	public long getSendAddrId() {
		return sendAddrId;
	}

	public void setSendAddrId(long sendAddrId) {
		this.sendAddrId = sendAddrId;
	}

	public long getRevAddrId() {
		return revAddrId;
	}

	public void setRevAddrId(long revAddrId) {
		this.revAddrId = revAddrId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public double getDriverShowPrice() {
		return driverShowPrice;
	}

	public void setDriverShowPrice(double driverShowPrice) {
		this.driverShowPrice = driverShowPrice;
	}

	public double getDifferent() {
		return different;
	}

	public void setDifferent(double different) {
		this.different = different;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}

	public String getAutoname() {
		return autoname;
	}

	public void setAutoname(String autoname) {
		this.autoname = autoname;
	}

	public int getAutonum() {
		return autonum;
	}

	public void setAutonum(int autonum) {
		this.autonum = autonum;
	}
}
