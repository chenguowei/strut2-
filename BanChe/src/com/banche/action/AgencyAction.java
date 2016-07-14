package com.banche.action;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.CheckAuth;
import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.dao.AccountDao;
import com.banche.dao.AgencyDao;
import com.banche.form.AgencyForm;
import com.banche.form.SearchForm;
import com.banche.model.Account;
import com.banche.model.Agency;
import com.banche.model.User;
import com.banche.service.AgencyService;
import com.banche.service.HintService;
import com.banche.service.PushService;
import com.banche.vo.AgencyVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.form.CheckFormResult;
import com.dijing.server.form.DJCheckForm;
import com.dijing.server.web.DJAction;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.JsonUtils;
import com.dijing.server.web.utils.MD5;
import com.dijing.server.web.utils.TimeUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午1:55:03
 */
public class AgencyAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(AgencyAction.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HintService hintService = new HintService();
	
	private AgencyService agencyService = new AgencyService();	 
	
	private PushService pushService = new PushService();
	
	private AgencyForm agencyForm;
	
	private SearchForm searchForm;
	
	private int page = 1;
	
	private long agencyId;
	
	/**
	 * 允许查看
	 * bc_Agency_viewInfo
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
	 * bc_Agency_checkAdd
	 * 是否具有添加权限
	 * @return
	 * @throws Exception
	 */
	public String checkAdd() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if(user.getAvailableUrl().contains("bc_Agency_addAgency")){
			out.jsonResultSuccess("");
		}else{
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}
	
	/**
	 * bc_Agency_checkEdit
	 * 是否具有编辑权限
	 * @return
	 * @throws Exception
	 */
	public String checkEdit() throws Exception {
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if(user.getAvailableUrl().contains("bc_Agency_editAgency")){
			out.jsonResultSuccess("");
		}else{
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}
	
	/**
	 * bc_Agency_agencyList
	 * @return
	 * @throws Exception
	 */
	public String agencyList() throws Exception {
		logger.debug("agencyList");
		if(searchForm==null){
			searchForm = new SearchForm();
		}
		DJPage<AgencyVO> pageVO = agencyService.findList(searchForm, getUser());
		
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Agency_blackList
	 * @return
	 * @throws Exception
	 */
	public String blackList() throws Exception {
		
		if(searchForm==null){
			searchForm = new SearchForm();
		}
		User user = getUser();
		DJPage<AgencyVO> pageVO = agencyService.findBlackList(searchForm, user);
		
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.getHint());
		ActionContext.getContext().put("user", user);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Agency_addAgency
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String addAgency() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(agencyForm==null){
			out.jsonResultError("参数异常");
			return null;
		}
		CheckFormResult cfr = DJCheckForm.check(agencyForm);
		if(!cfr.isOk()){
			out.jsonResultError(cfr.getError());
			return null;
		}
		Agency agency = new Agency(agencyForm);
		AgencyDao dao = new AgencyDao();
		if(dao.findByTel(agency.getContactTel())!=null){
			out.jsonResultError("手机号已存在");
			return null;
		}
		int i;
		long agencyId = 0;
		for(i=0;i<50;i++){
			agencyId = agencyService.generateId();
			if(dao.findById(agencyId)==null){
				break;
			}
		}
		agency.setId(agencyId);
		
		//在account表中创建记录
		Account account = new Account();
		account.setAccountname(agency.getContactTel());
		account.setPassword(MD5.encryption(agency.getContactTel()));
		account.setCreatetime(new Date());
		if(account.save()){
			agency.setAccountid(account.getId());
			if(agency.save()){
				out.jsonResultSuccess("success");
			}else{
				account.delete();
				out.jsonResultError("添加失败");
			}
		}else{
			out.jsonResultError("添加失败");
			logger.error("创建account信息失败");
		}
		
		return null;
	}
	
	/**
	 * bc_Agency_editAgency
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String editAgency() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(agencyForm==null){
			out.jsonResultError("参数异常");
			return null;
		}
		CheckFormResult cfr = DJCheckForm.check(agencyForm);
		if(!cfr.isOk()){
			out.jsonResultError(cfr.getError());
			return null;
		}
		AgencyDao dao = new AgencyDao();
		Agency agency = dao.findById(agencyForm.getId());
		agency.setFormData(agencyForm);
		if(agency.update()){
			AgencyVO vo = new AgencyVO();
			EntityUtils.entityCopy(agency, vo);
			vo.setRegisterVO(TimeUtils.getTimeFromLong2(vo.getRegisterTime()));
			out.jsonResultSuccess("success", JsonUtils.formJsonArray(vo.parseEntity()));
		}else{
			out.jsonResultError("添加失败");
		}
		return null;
	}
	
	/**
	 * bc_Agency_deleteAgency
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String deleteAgency() throws Exception {
		OutputStringAction out = new OutputStringAction();
		AgencyDao dao = new AgencyDao();
		Agency agency = dao.findById(agencyId);
		if(agency!=null){
			if(!agency.delete()){
				out.jsonResultError("删除失败");
				return null;
			}
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.findById(agency.getAccountid());
			if(account!=null){
				account.delete();
			}
		}
		out.jsonResultSuccess("删除成功");
		return null;
	}
	
	/**
	 * bc_Agency_resetPassword
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String resetPassword() throws Exception {
		OutputStringAction out = new OutputStringAction();
		
		logger.debug("agencyId: "+agencyId);
		AgencyDao dao = new AgencyDao();
		Agency agency = dao.findById(agencyId);
		if(agency==null){
			out.jsonResultError("无法找到经销商信息");
			return null;
		}
		
//		AccountDao accountDao = new AccountDao();
//		Account account = accountDao.findById(agency.getAccountid());
//		if(account==null){
//			out.jsonResultError("账号不到经销商的账号信息");
//			return null;
//		}
//		String uuid = UUID.randomUUID().toString().substring(0, 8);
//		logger.debug("new password: " + uuid);
//		account.setPassword(MD5.encryption(uuid));
//		if(!account.update()){
//			out.jsonResultError("重置失败");
//			return null;
//		}
		
		if(pushService.resetPassword(agency.getContactTel())){
			out.jsonResultSuccess("重置成功");
		}else{
			out.jsonResultError(pushService.getErrorMsg());
		}
		return null;
	}

	public AgencyForm getAgencyForm() {
		return agencyForm;
	}

	public void setAgencyForm(AgencyForm agencyForm) {
		this.agencyForm = agencyForm;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

}
