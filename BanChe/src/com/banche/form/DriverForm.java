package com.banche.form;

import com.banche.model.Driver;
import com.dijing.server.form.FPhone;
import com.dijing.server.form.FRequired;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日上午9:04:13
 */
public class DriverForm extends Driver {

	private String logistics;
	
	@FRequired(name="联系人姓名")
	private String name;
	
	@FRequired(name="身份证号")
	private String identity;
	
	@FRequired(name="身份证照片(正面)")
	private String identityFront;
	
	@FRequired(name="身份证照片(背面)")
	private String identityBack;
	
	@FRequired(name="性别")
	private String gender;
	
	private String carNumber;
	
	@FPhone
	@FRequired(name="电话")
	private String tel;
	
	private String paymentAccount;
	
	private String drivingLicense;
	
	private String drivingLicenseBack;
	
	private String vehicleLicense;
	
	private String vehicleLicenseBack;

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
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

	public String getIdentityFront() {
		return identityFront;
	}

	public void setIdentityFront(String identityFront) {
		this.identityFront = identityFront;
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

	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
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
}
