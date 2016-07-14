package com.banche.vo;

import java.util.List;

import com.banche.model.Agency;
import com.dijing.server.entity.EntityInfo;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.JsonUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午3:21:21
 */
public class AgencyVO extends Agency {

	private long id;
	
	private String contact;
	
	private String contactTel;
	
	private String sendAddr;
	
	private String receiveAddr;
	
	private String statusVO;
	
	private String registerVO;
	
	public AgencyVO(){}
	
	public AgencyVO(Agency agency){
		this.setId(agency.getId());
		this.setContact(agency.getContact());
		this.setContactTel(agency.getContactTel());
		this.setReceiveAddr(agency.getReceiveAddr());
		this.setRegisterVO(TimeUtils.getTimeFromLong2(agency.getRegisterTime()));
		this.setSendAddr(agency.getSendAddr());
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

	public String getStatusVO() {
		return statusVO;
	}

	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
	}

	public String getRegisterVO() {
		return registerVO;
	}

	public void setRegisterVO(String registerVO) {
		this.registerVO = registerVO;
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
}
