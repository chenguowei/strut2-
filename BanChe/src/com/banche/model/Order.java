package com.banche.model;

import com.dijing.server.dao.ANote;
import com.dijing.server.dao.ARelated;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 货单
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午8:10:37
 */
@ANote(value="货单")
public class Order extends DJModel {

	@Id
	private long id;
	/**
	 * 单号
	 */
	@ANote(value="单号")
	private String sn;
	
	@ANote(value="下单人id")
	@ARelated(value="agency.id")
	private long agencyId;
	/**
	 * 发货地址
	 */
	@ANote(value="发货地址")
	private String sendAddr;
	/**
	 * 发货公司
	 */
	@ANote(value="发货公司")
	private String sendCompany;
	/**
	 * 发货联系人
	 */
	@ANote(value="发货联系人")
	private String sendContact;
	/**
	 * 发货人电话
	 */
	@ANote(value="发货人电话")
	private String sendTel;
	/**
	 * 收货地址
	 */
	@ANote(value="收货地址")
	private String receiveAddr;
	/**
	 * 收货公司
	 */
	@ANote(value="收货公司")
	private String receiveCompany;
	/**
	 * 收货联系人
	 */
	@ANote(value="收货联系人")
	private String receiveContact;
	/**
	 * 收货人电话
	 */
	@ANote(value="收货人电话")
	private String receiveTel;
	/**
	 * 总费用
	 */
	@ANote(value="总费用")
	private double totalFee;
	
	@ANote(value="原价")
	private double originalPrice;
	/**
	 * 定金数
	 */
	@ANote(value="定金数")
	private double earnest;
	/**
	 * 承接车型
	 */
	@ANote(value="承接车型")
	private int carType;
	/**
	 * 承接司机
	 */
	@ANote(value="承接司机")
	@ARelated(value="driver.id")
	private long driver;
	/**
	 * 承接车牌号
	 */
	@ANote(value="承接车牌号")
	private String carNumber;
	/**
	 * 货单状态
	 */
	@ANote(value="货单状态")
	private int status;
	/**
	 * 货单位置
	 */
	@ANote(value="货单位置")
	private double longitude;
	/**
	 * 货单位置
	 */
	@ANote(value="货单位置")
	private double latitude;
	/**
	 * 评价星级
	 */
	@ANote(value="评价星级")
	private int commentLevel;
	/**
	 * 评价内容
	 */
	@ANote(value="评价内容")
	private String comment;
	/**
	 * 备忘
	 */
	@ANote(value="备忘")
	private String note;
	
	@ANote(value="车辆估值")
	private long carValue;
	
	@ANote(value="订单创建时间")
	private long createtime;
	
	@ANote(value="订单更新时间")
	private long updatetime;
	
	@ANote(value="司机端价格")
	private double driverShowPrice;

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

	public String getSendAddr() {
		return sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getSendCompany() {
		return sendCompany;
	}

	public void setSendCompany(String sendCompany) {
		this.sendCompany = sendCompany;
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

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getReceiveCompany() {
		return receiveCompany;
	}

	public void setReceiveCompany(String receiveCompany) {
		this.receiveCompany = receiveCompany;
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

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getEarnest() {
		return earnest;
	}

	public void setEarnest(double earnest) {
		this.earnest = earnest;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public long getDriver() {
		return driver;
	}

	public void setDriver(long driver) {
		this.driver = driver;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public long getCarValue() {
		return carValue;
	}

	public void setCarValue(long carValue) {
		this.carValue = carValue;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public double getDriverShowPrice() {
		return driverShowPrice;
	}

	public void setDriverShowPrice(double driverShowPrice) {
		this.driverShowPrice = driverShowPrice;
	}
}
