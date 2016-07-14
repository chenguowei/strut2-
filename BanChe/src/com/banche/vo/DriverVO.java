package com.banche.vo;

import java.util.List;

import com.banche.Constants;
import com.banche.dao.OrderDao;
import com.banche.enums.StatusEnums;
import com.banche.model.Driver;
import com.dijing.server.entity.EntityInfo;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.JsonUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午5:49:24
 */
public class DriverVO extends Driver {

	private String statusVO;
	
	private long id;
	
	private String logistics;
	
	private String name;
	
	private String identity;
	
	private String gender;
	
	private String carNumber;
	
	private String tel;
	
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
	
	public DriverVO(){}
	
	public DriverVO(Driver d){
		DriverVO vo = this;
		try {
			EntityUtils.entityCopy(d, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setStatusVO(StatusEnums.getNameByCode(vo.getStatus()));
		vo.setTime(TimeUtils.getTimeFromLong2(vo.getRegisterTime()));
		
		OrderDao orderDao = new OrderDao();
		vo.setOrderNumber(orderDao.countOrderByDriver(d.getId()));
		
		vo.setDrivingLicense(Constants.UPLOAD + "/" + d.getDrivingLicense());
		vo.setDrivingLicenseBack(Constants.UPLOAD + "/" + d.getDrivingLicenseBack());
		vo.setIdentityBack(Constants.UPLOAD + "/" + d.getIdentityBack());
		vo.setIdentityFront(Constants.UPLOAD + "/" + d.getIdentityFront());
		vo.setVehicleLicense(Constants.UPLOAD + "/" + d.getVehicleLicense());
		vo.setVehicleLicenseBack(Constants.UPLOAD + "/" + d.getVehicleLicenseBack());
	}
	
	public DriverVO(Driver d, String statusVO){
		DriverVO vo = this;
		try {
			EntityUtils.entityCopy(d, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setStatusVO(statusVO);
		vo.setTime(TimeUtils.getTimeFromLong2(vo.getRegisterTime()));
		
		OrderDao orderDao = new OrderDao();
		vo.setOrderNumber(orderDao.countOrderByDriver(d.getId()));
		
		vo.setDrivingLicense(Constants.UPLOAD + "/" + d.getDrivingLicense());
		vo.setIdentityBack(Constants.UPLOAD + "/" + d.getIdentityBack());
		vo.setIdentityFront(Constants.UPLOAD + "/" + d.getIdentityFront());
		vo.setVehicleLicense(Constants.UPLOAD + "/" + d.getVehicleLicense());
	}
	
	public List<EntityInfo> parseEntity() throws Exception{
		List<EntityInfo> list = EntityUtils.parseEntity(this);
//		System.out.println(JsonUtils.formJsonArray(list).toString());
		return list;
	}
	
	/**
	 * json数组字符串形式
	 * @return
	 * @throws Exception
	 */
	public String parseEntityJson() throws Exception {
		return JsonUtils.formJsonArray(parseEntity()).toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
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
