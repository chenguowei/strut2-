package com.banche.model;

import java.util.List;

import org.json.JSONArray;

import com.banche.Constants;
import com.banche.form.UserForm;
import com.banche.vo.ModuleAuthVO;
import com.dijing.server.dao.ANote;
import com.dijing.server.dao.ARelated;
import com.dijing.server.dao.DJModel;
import com.dijing.server.dao.Id;
import com.dijing.server.dao.Transparent;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午7:41:29
 */
@ANote(value="后台管理员账号")
public class User extends DJModel {

	@Id
	@ANote(value="用户id")
	private long id;
	
	@ANote(value="用户账号")
	private String account;
	
	@ANote(value="用户账号密码")
	private String password;
//	/**
//	 * 用户类型
//	 */
//	@ANote(value="用户类型")
//	private int type;
	
	@ANote(value="头像")
	private String portrait;
	
	@ANote(value="用户电话")
	private String tel;
	
	@ANote(value="备注")
	private String note;
	
	@ANote(value="用户角色")
	@ARelated(value="role.id")
	private Long roleId;
	
	@ANote(value="用户权限。json数组格式")
	private String auth;
	
	@Transparent
	private List<String> availableUrl;
	
	@Transparent
	private List<String> availablePage;
	
	@Transparent
	private List<ModuleAuthVO> authList;
	
	public User(){}
	
	public User(UserForm uf){
		setFormData(uf);
	}
	
	public void setFormData(UserForm uf){
		this.account = uf.getAccount();
		this.note = uf.getNote();
		this.password = uf.getPassword();
		this.roleId = uf.getRoleId();
		this.tel = uf.getTel();
		
		if(!Constants.ADMIN_AUTH.equals(this.auth)){
			if(uf.getAuthIds()==null || uf.getAuthIds().size()==0){
				this.auth = "[]";
			}else{
				JSONArray arr = new JSONArray();
				for(Integer i : uf.getAuthIds()){
					arr.put(i);
				}
				this.auth = arr.toString();
			}
		}		
	}
	
	public void setSimpleFormData(UserForm uf){
		this.account = uf.getAccount();
		this.password = uf.getPassword();
		this.tel = uf.getTel();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public List<String> getAvailableUrl() {
		return availableUrl;
	}

	public void setAvailableUrl(List<String> availableUrl) {
		this.availableUrl = availableUrl;
	}

	public List<String> getAvailablePage() {
		return availablePage;
	}

	public void setAvailablePage(List<String> availablePage) {
		this.availablePage = availablePage;
	}

	public List<ModuleAuthVO> getAuthList() {
		return authList;
	}

	public void setAuthList(List<ModuleAuthVO> authList) {
		this.authList = authList;
	}
}
