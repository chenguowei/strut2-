package com.banche.action;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.CheckAuth;
import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.dao.AccountDao;
import com.banche.dao.DriverDao;
import com.banche.enums.StatusEnums;
import com.banche.form.DriverForm;
import com.banche.form.SearchForm;
import com.banche.model.Account;
import com.banche.model.Driver;
import com.banche.model.User;
import com.banche.service.DriverService;
import com.banche.service.HintService;
import com.banche.service.PushService;
import com.banche.vo.DriverVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.form.CheckFormResult;
import com.dijing.server.form.DJCheckForm;
import com.dijing.server.web.DJAction;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.JsonUtils;
import com.dijing.server.web.utils.MD5;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午9:10:39
 */
public class DriverAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(DriverAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DriverService driverService = new DriverService();

	private HintService hintService = new HintService();
	
	private PushService pushService = new PushService();

	private DriverForm driverForm;

	private SearchForm searchForm;

	private int page = 1;

	private long driverId;

	/**
	 * 允许查看 bc_Driver_viewInfo
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewInfo() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Driver_checkEdit 是否具有编辑权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkEdit() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getAvailableUrl().contains("bc_Driver_editDriver")) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}

	/**
	 * bc_Driver_checkAdd 是否具有添加权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkAdd() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getAvailableUrl().contains("bc_Driver_addDriver")) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}

	/**
	 * bc_Driver_driverList
	 * 
	 * @return
	 * @throws Exception
	 */
	public String driverList() throws Exception {
		logger.debug("driverList");
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		User user = getUser();
		DJPage<DriverVO> vo = driverService.findDriverList(searchForm, user);
		ActionContext.getContext().put("pageVO", vo);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", user);
		return DJAction.SHOW;
	}

	/**
	 * bc_Driver_uncheckedList
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uncheckedList() throws Exception {
		logger.debug("uncheckedList");
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		DJPage<DriverVO> vo = driverService.findUnpassedDriverList(searchForm);
		ActionContext.getContext().put("pageVO", vo);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}

	/**
	 * bc_Driver_blackList
	 * 
	 * @return
	 * @throws Exception
	 */
	public String blackList() throws Exception {
		logger.debug("blackList");
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		User user = getUser();
		DJPage<DriverVO> vo = driverService.findBlackList(searchForm, user);
		ActionContext.getContext().put("pageVO", vo);
		
		
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", user);
		return DJAction.SHOW;
	}

	/**
	 * bc_Driver_generateId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateId() throws Exception {
		OutputStringAction out = new OutputStringAction();
		String uuid = UUID.randomUUID().toString().substring(0, 8);
		out.jsonResultSuccess(uuid);
		return null;
	}

	/**
	 * bc_Driver_addDriver
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String addDriver() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if (driverForm == null) {
			out.jsonResultError("参数异常");
			return null;
		}
		CheckFormResult cfr = DJCheckForm.check(driverForm);
		if (!cfr.isOk()) {
			out.jsonResultError(cfr.getError());
			return null;
		}
		Driver driver = new Driver(driverForm);
		DriverDao dao = new DriverDao();
		if(dao.findByTel(driver.getTel())!=null){
			out.jsonResultError("手机号已存在");
			return null;
		}
		int i;
		long driverId = 0;
		for(i=0;i<50;i++){
			driverId = driverService.generateId();
			if(dao.findById(driverId)==null){
				break;
			}
		}
		if(i>0){
			logger.debug("generate times: " + i);
		}
		driver.setId(driverId);
		driver.setStatus(StatusEnums.NEW.getCode());
		driver.setRegisterTime(System.currentTimeMillis() / 1000);
//		if (driver.getIdentityFront() != null) {
//			driver.setIdentityFront(driver.getIdentityFront().replaceFirst(
//					Constants.UPLOAD + "/", ""));
//		}
//		if (driver.getIdentityBack() != null) {
//			driver.setIdentityBack(driver.getIdentityBack().replaceFirst(
//					Constants.UPLOAD + "/", ""));
//		}
//		if (driver.getDrivingLicense() != null) {
//			driver.setDrivingLicense(driver.getDrivingLicense().replaceFirst(
//					Constants.UPLOAD + "/", ""));
//		}
//		if (driver.getVehicleLicense() != null) {
//			driver.setVehicleLicense(driver.getVehicleLicense().replaceFirst(
//					Constants.UPLOAD + "/", ""));
//		}
		// 在account表中创建记录
		Account account = new Account();
		account.setAccountname(driver.getTel());
		account.setPassword(MD5.encryption(driver.getTel()));
		account.setCreatetime(new Date());
		if(account.save()){	
			driver.setAccountid(account.getId());
			if (driver.save()) {			
				out.jsonResultSuccess("success");
			} else {
				account.delete();
				out.jsonResultError("添加失败");
				logger.error("创建driver信息失败");
			}
		}else{
			out.jsonResultError("添加失败");
			logger.error("创建account信息失败");
		}
		
		

		return null;
	}

	/**
	 * bc_Driver_editDriver
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String editDriver() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if (driverForm == null) {
			out.jsonResultError("参数异常");
			return null;
		}
		CheckFormResult cfr = DJCheckForm.check(driverForm);
		if (!cfr.isOk()) {
			out.jsonResultError(cfr.getError());
			return null;
		}
		DriverDao dao = new DriverDao();
		Driver driver = dao.findById(driverForm.getId());
		driver.setFormData(driverForm);
		
		
		if (driver.update()) {
			DriverVO vo = new DriverVO(driver);
			
			out.jsonResultSuccess("success",
					JsonUtils.formJsonArray(vo.parseEntity()));
		} else {
			out.jsonResultError("编辑失败");
		}
		return null;
	}

	/**
	 * 审核通过 bc_Driver_checkPass
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String checkPass() throws Exception {
		logger.debug("checkPass");
		logger.debug("driverId: " + driverId);
		OutputStringAction out = new OutputStringAction();
		DriverDao dao = new DriverDao();
		Driver driver = dao.findById(driverId);
		if (driver == null) {
			out.jsonResultError("没有该司机信息");
			return null;
		} else {
			if (driver.getStatus() != StatusEnums.PASS.getCode()) {
				driver.setStatus(StatusEnums.PASS.getCode());
				if (!driver.update()) {
					out.jsonResultError("更新状态失败");
					return null;
				}
			}
		}
		out.jsonResultSuccess("成功");
		return null;
	}

	/**
	 * 删除司机信息 bc_Driver_deleteDriver
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String deleteDriver() throws Exception {
		OutputStringAction out = new OutputStringAction();
		DriverDao dao = new DriverDao();
		Driver driver = dao.findById(driverId);
		if (driver != null) {
			if (!driver.delete()) {
				out.jsonResultError("删除失败");
				return null;
			}
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.findById(driver.getAccountid());
			if(account!=null){
				if(!account.delete()){
					logger.error("删除账号失败: " + driver.getAccountid());
				}
			}
		}
		out.jsonResultSuccess("成功");
		return null;
	}

	/**
	 * bc_Driver_resetPassword 重置密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String resetPassword() throws Exception {
		OutputStringAction out = new OutputStringAction();
		logger.debug("resetPassword driverId: " + driverId);

		DriverDao dao = new DriverDao();
		Driver driver = dao.findById(driverId);
		if (driver == null) {
			out.jsonResultError("找不到司机信息");
			return null;
		}
//		AccountDao accountDao = new AccountDao();
//		Account account = accountDao.findById(driver.getAccountid());
//		if (account == null) {
//			out.jsonResultError("账号不到司机的账号信息");
//			return null;
//		}
//		String uuid = UUID.randomUUID().toString().substring(0, 8);
//		logger.debug("new password: " + uuid);
//		account.setPassword(MD5.encryption(uuid));
//		if (!account.update()) {
//			out.jsonResultError("重置失败");
//			return null;
//		}
		// 
		if(pushService.resetPassword(driver.getTel())){
			out.jsonResultSuccess("重置成功");
		}else{
			out.jsonResultError(pushService.getErrorMsg());
		}
		
		return null;
	}

	public DriverForm getDriverForm() {
		return driverForm;
	}

	public void setDriverForm(DriverForm driverForm) {
		this.driverForm = driverForm;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

}
