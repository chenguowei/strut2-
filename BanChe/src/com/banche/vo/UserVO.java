package com.banche.vo;

import com.banche.enums.RoleEnums;
import com.banche.model.User;
import com.dijing.server.entity.EntityUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午8:14:48
 */
public class UserVO extends User {

	/**
	 * 角色名称
	 */
	private String roleVO;
	
	public UserVO(User user){
		try {
			EntityUtils.entityCopy(user, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.roleVO = RoleEnums.getNameByCode(user.getRoleId());
	}

	public String getRoleVO() {
		return roleVO;
	}

	public void setRoleVO(String roleVO) {
		this.roleVO = roleVO;
	}	
}
