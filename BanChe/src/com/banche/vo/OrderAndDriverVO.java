package com.banche.vo;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月26日上午10:56:12
 */
public class OrderAndDriverVO extends OrderVO {

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
	
	private String name;
	
	private String tel;
	
	private String carNumber;
	
	private String carTypeVO;
	
	private int commentLevel;
	
	private String comment;
	
	private String logistics;
	
	private String identity;
	
	private String gender;
	
	private String paymentAccount;
	
	private int orderNumber;
	
	private String time;
	
	private String drivingLicense;
	
	private String drivingLicenseBack;
	
	private String vehicleLicense;
	
	private String vehicleLicenseBack;
	
	private String identityFront;
	
	private String identityBack;
	
	private int level;
	
	private String vehicleType;
	
	private String createTime;
	
	//车辆信息
	private String mold;
	
	private String autoname;
	
	private int autonum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarTypeVO() {
		return carTypeVO;
	}

	public void setCarTypeVO(String carTypeVO) {
		this.carTypeVO = carTypeVO;
	}

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
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

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
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

	public double getEarnest() {
		return earnest;
	}

	public void setEarnest(double earnest) {
		this.earnest = earnest;
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

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getVehicleLicense() {
		return vehicleLicense;
	}

	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}

	public String getIdentityFront() {
		return identityFront;
	}

	public void setIdentityFront(String identityFront) {
		this.identityFront = identityFront;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getCarValue() {
		return carValue;
	}

	public void setCarValue(long carValue) {
		this.carValue = carValue;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public String getDrivingLicenseBack() {
		return drivingLicenseBack;
	}

	public void setDrivingLicenseBack(String drivingLicenseBack) {
		this.drivingLicenseBack = drivingLicenseBack;
	}

	public String getVehicleLicenseBack() {
		return vehicleLicenseBack;
	}

	public void setVehicleLicenseBack(String vehicleLicenseBack) {
		this.vehicleLicenseBack = vehicleLicenseBack;
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
