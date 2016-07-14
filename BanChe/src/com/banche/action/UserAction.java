package com.banche.action;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.dao.UserDao;
import com.banche.enums.RoleEnums;
import com.banche.form.SearchForm;
import com.banche.form.UserForm;
import com.banche.model.User;
import com.banche.service.AuthService;
import com.banche.service.HintService;
import com.banche.service.UserService;
import com.banche.vo.ModuleAuthVO;
import com.banche.vo.UserVO;
import com.dijing.server.form.CheckFormResult;
import com.dijing.server.form.DJCheckForm;
import com.dijing.server.web.DJAction;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.JsonUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月13日下午10:59:45
 */
public class UserAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(UserAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AuthService authService = new AuthService();

	private HintService hintService = new HintService();

	private UserForm userForm;

	private SearchForm searchForm;

	private long userId;

	/**
	 * 添加用户的页面 bc_User_addUserPage
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUserPage() throws Exception {

		List<ModuleAuthVO> authList = authService.getAllModuleInfo();
		logger.debug("size: " + authList.size());
		logger.debug(JsonUtils.formJsonArray(authList));
		ModuleAuthVO driver = null;
		ModuleAuthVO agency = null;
		ModuleAuthVO order = null;
		for (ModuleAuthVO auth : authList) {
			if ("司机管理".equals(auth.getName())) {
				driver = auth;
			} else if ("经销商管理".equals(auth.getName())) {
				agency = auth;
			} else if ("货单管理".equals(auth.getName())) {
				order = auth;
			} else {
				OutputStringAction out = new OutputStringAction();
				out.jsonResultError("存在未知模块：" + auth.getName() + ", 请检查数据库数据.");
				return null;
			}
		}
		if (driver == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到司机模块的权限信息");
			return null;
		}
		if (agency == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到经销商模块的权限信息");
			return null;
		}
		if (order == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到货单模块的权限信息");
			return null;
		}
		ActionContext.getContext().put("driver", driver);
		ActionContext.getContext().put("agency", agency);
		ActionContext.getContext().put("order", order);
		ActionContext.getContext().put("isAdd", true);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}

	/**
	 * bc_User_userList 成员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String userList() throws Exception {
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		UserService userService = new UserService();
		List<UserVO> list = userService.getBySearch(searchForm);
		logger.debug("size: " + list.size());
		logger.debug(JsonUtils.formJsonArray(list));

		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}

	/**
	 * bc_User_editUserPage 编辑用户信息的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUserPage() throws Exception {
		UserDao dao = new UserDao();
		User user = dao.findById(userId);
		if (user == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("找不到用户信息");
			return null;
		}
		List<ModuleAuthVO> authList = authService.getUserModule(user);
		logger.debug(JsonUtils.formJsonArray(authList));
		ModuleAuthVO driver = null;
		ModuleAuthVO agency = null;
		ModuleAuthVO order = null;
		ModuleAuthVO banner = null;
		for (ModuleAuthVO auth : authList) {
			if ("司机管理".equals(auth.getName())) {
				driver = auth;
			} else if ("经销商管理".equals(auth.getName())) {
				agency = auth;
			} else if ("货单管理".equals(auth.getName())) {
				order = auth;
			}else if("轮播图管理".equals(auth.getName())){
				banner = auth;
			} 
			else {
				OutputStringAction out = new OutputStringAction();
				out.jsonResultError("存在未知模块：" + auth.getName() + ", 请检查数据库数据.");
				return null;
			}
		}
		if (driver == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到司机模块的权限信息");
			return null;
		}
		if (agency == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到经销商模块的权限信息");
			return null;
		}
		if (order == null) {
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到货单模块的权限信息");
			return null;
		}
		if(banner ==null){
			OutputStringAction out = new OutputStringAction();
			out.jsonResultError("未找到轮播图模块的权限信息");
			return null;
		}
		ActionContext.getContext().put("driver", driver);
		ActionContext.getContext().put("agency", agency);
		ActionContext.getContext().put("order", order);
		ActionContext.getContext().put("banner", banner);
		ActionContext.getContext().put("isAdd", false);
		ActionContext.getContext().put("editUser", user);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}

	/**
	 * bc_User_userPage 个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String userPage() throws Exception {
		userId = getUser().getId();
		if (getUser().getRoleId() != RoleEnums.SUPER_ADMIN.getCode()) {
			ActionContext.getContext().put("disable", true);
		}
		ActionContext.getContext().put("isSelf", true);
		return editUserPage();
	}

	/**
	 * bc_User_checkAddAuth 检验是否有添加账号的权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkAddAuth() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getRoleId() == RoleEnums.SUPER_ADMIN.getCode()) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}

	/**
	 * bc_User_checkEditAuth 检查是否有编辑(删除)用户的权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkEditAuth() throws Exception {
		OutputStringAction out = new OutputStringAction();
		UserDao dao = new UserDao();
		User editUser = dao.findById(userId);
		if (editUser == null) {
			out.jsonResultError("找不到用户信息");
			return null;
		}
		User user = getUser();
		if (user.getRoleId() < editUser.getRoleId()
				|| Constants.ADMIN_AUTH.equals(user.getAuth())) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}

	/**
	 * bc_User_addUser 添加用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUser() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User currentUser = getUser();
		if (currentUser.getRoleId() != RoleEnums.SUPER_ADMIN.getCode()) {
			out.jsonResultError(Constants.NO_AUTH);
			return null;
		}
		if (userForm == null) {
			out.jsonResultError("参数异常");
			return null;
		}
		logger.debug(JsonUtils.formJson(userForm).toString());
		CheckFormResult cfr = DJCheckForm.check(userForm);
		if (!cfr.isOk()) {
			out.jsonResultError(cfr.getError());
			return null;
		}
		UserDao dao = new UserDao();
		if (dao.findByAccount(userForm.getAccount()) != null) {
			out.jsonResultError("登录名已存在");
			return null;
		}
		User user = new User(userForm);
		if (user.save()) {
			out.jsonResultSuccess("添加成功");
		} else {
			out.jsonResultError("保存数据失败");
		}
		return null;
	}

	/**
	 * bc_User_editUser 编辑用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUser() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if (userForm == null) {
			out.jsonResultError("参数异常");
			return null;
		}
//		logger.debug(JsonUtils.formJson(userForm).toString());
		CheckFormResult cfr = DJCheckForm.check(userForm);
		if (!cfr.isOk()) {
			out.jsonResultError(cfr.getError());
			return null;
		}
		UserDao dao = new UserDao();
		User user = dao.findById(userForm.getId());
		if (user == null) {
			out.jsonResultError("找不到用户信息");
			return null;
		}
		User currentUser = getUser();
		if (currentUser.getId() != user.getId()
				&& currentUser.getRoleId() >= user.getRoleId()
				&& !Constants.ADMIN_AUTH.equals(currentUser.getAuth())) {
			out.jsonResultError(Constants.NO_AUTH);
			return null;
		}
		if (currentUser.getId() == user.getId()
				&& user.getRoleId() != RoleEnums.SUPER_ADMIN.getCode()) {
			user.setSimpleFormData(userForm);
		} else {
			if (userForm.getRoleId() == null) {
				out.jsonResultError("角色不能为空");
				return null;
			}
			user.setFormData(userForm);
		}
		
		User checkAccount = dao.findByAccount(user.getAccount());
		if(checkAccount!=null && checkAccount.getId() != user.getId()){
			out.jsonResultError("登录名已存在");
			return null;
		}

		if (user.update()) {
			out.jsonResultSuccess("更新成功");
		} else {
			out.jsonResultError("更新失败");
		}
		return null;
	}

	/**
	 * bc_User_deleteUser 删除用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteUser() throws Exception {
		UserDao dao = new UserDao();
		User user = dao.findById(userId);
		OutputStringAction out = new OutputStringAction();
		if (user != null) {
			User currentUser = getUser();
			if (currentUser.getRoleId() >= user.getRoleId()
					&& !Constants.ADMIN_AUTH.equals(currentUser.getAuth())) {
				out.jsonResultError(Constants.NO_AUTH);
				return null;
			}
			if (!user.delete()) {
				out.jsonResultError("删除失败");
				return null;
			}
		}
		out.jsonResultSuccess("删除成功");
		return null;
	}

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

}
