package com.banche.model;

import com.banche.Constants;
import com.banche.form.DriverForm;
import com.dijing.server.dao.ANote;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;

/**
 * 司机
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午7:44:21
 */
@ANote(value="司机")
public class Driver extends DJModel {

	/**
	 * 登记id
	 */
	@Id
	@ANote(value="登记id")
	private long id;
	
	@ANote(value="司机账号id")
	private long accountid;
	/**
	 * 物流名称
	 */
	@ANote(value="物流名称")
	private String logistics;
	/**
	 * 照片
	 */
	@ANote(value="照片")
	private String portrait;
	/**
	 * 姓名
	 */
	@ANote(value="姓名")
	private String name;
	/**
	 * 身份证号
	 */
	@ANote(value="身份证号")
	private String identity;
	
	@ANote(value="身份证（正面）")
	private String identityFront;
	
	@ANote(value="身份证（背面）")
	private String identityBack;
	/**
	 * 性别
	 */
	@ANote(value="性别")
	private String gender;
	/**
	 * 车牌号
	 */
	@ANote(value="车牌号")
	private String carNumber;
	
	@ANote(value="车辆类型")
	private String vehicleType;
	
	/**
	 * 驾驶证(图片)
	 */
	@ANote(value="驾驶证(图片)")
	private String drivingLicense;
	
	@ANote(value="驾驶证(背面)")
	private String drivingLicenseBack;
	/**
	 * 行驶证(图片)
	 */
	@ANote(value="行驶证(图片)")
	private String vehicleLicense;
	
	@ANote(value="行驶证(背面)")
	private String vehicleLicenseBack;
	/**
	 * 电话
	 */
	@ANote(value="电话")
	private String tel;
	/**
	 * 评价等级
	 */
	@ANote(value="评价等级")
	private int level;
	/**
	 * 登记日期
	 */
	@ANote(value="登记日期")
	private long registerTime;
	/**
	 * 接单数
	 */
	@ANote(value="接单数")
	private int orderNumber;
	/**
	 * 收款账号
	 */
	@ANote(value="收款账号")
	private String paymentAccount;
	
	@ANote(value="账号状态")
	private int status;
	
	public Driver(DriverForm driverForm){
		setFormData(driverForm);
	}
	
	public Driver(){}
	
	public void setFormData(DriverForm driverForm){
		if(this.id==0){
			this.setId(driverForm.getId());
		}		
		this.setCarNumber(driverForm.getCarNumber());
		this.setDrivingLicense(driverForm.getDrivingLicense());
		this.setGender(driverForm.getGender());
		this.setIdentity(driverForm.getIdentity());
		this.setLogistics(driverForm.getLogistics());
		this.setName(driverForm.getName());
		this.setTel(driverForm.getTel());
		this.setVehicleLicense(driverForm.getVehicleLicense());
		this.setIdentityFront(driverForm.getIdentityFront());
		this.setPaymentAccount(driverForm.getPaymentAccount());
		
		this.setIdentityBack(driverForm.getIdentityBack());
		this.setDrivingLicenseBack(driverForm.getDrivingLicenseBack());
		this.setVehicleLicenseBack(driverForm.getVehicleLicenseBack());
		
		if (this.getIdentityFront() != null) {
			this.setIdentityFront(this.getIdentityFront().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
		if (this.getIdentityBack() != null) {
			this.setIdentityBack(this.getIdentityBack().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
		if (this.getDrivingLicense() != null) {
			this.setDrivingLicense(this.getDrivingLicense().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
		if (this.getDrivingLicenseBack() != null) {
			this.setDrivingLicenseBack(this.getDrivingLicenseBack().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
		if (this.getVehicleLicense() != null) {
			this.setVehicleLicense(this.getVehicleLicense().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
		if (this.getVehicleLicenseBack() != null) {
			this.setVehicleLicenseBack(this.getVehicleLicenseBack().replaceFirst(
					Constants.UPLOAD + "/", ""));
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
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

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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
}
