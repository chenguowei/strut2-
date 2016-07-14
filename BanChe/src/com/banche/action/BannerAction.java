package com.banche.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.CheckAuth;
import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.dao.BannerDao;
import com.banche.enums.BannerType;
import com.banche.form.BannerForm;
import com.banche.form.SearchForm;
import com.banche.model.Banner;
import com.banche.model.User;
import com.banche.service.BannerService;
import com.banche.vo.BannerVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.form.CheckFormResult;
import com.dijing.server.form.DJCheckForm;
import com.dijing.server.web.DJAction;
import com.dijing.server.web.action.OutputStringAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author winter
 * @date 2016年3月30日下午1:31:07
 */
public class BannerAction extends DJActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = LogManager.getLogger(BannerAction.class.getName());
	
	private SearchForm searchForm;
	
	private BannerService bannerService = new BannerService();
	
	private BannerForm bannerForm;
	
	private long bannerId;
	
	private int bannerType;
	
	/**
	 * 获取banner列表
	 * bc_Banner_bannerList
	 * @return
	 * @throws Exception
	 */
	public String bannerList() throws Exception {
		if(searchForm==null){
			searchForm = new SearchForm();
		}
		DJPage<BannerVO> pageVO = bannerService.findAgencyBySearch(searchForm);
		
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("user", getUser());
//		ActionContext.getContext().put("bannerType", BannerType.AGENCY.getCode());
		bannerType = BannerType.AGENCY.getCode();
		return DJAction.SHOW;
	}
	
	/**
	 * 司机轮播图
	 * bc_Banner_driverBannerList
	 * @return
	 * @throws Exception
	 */
	public String driverBannerList() throws Exception {
		if(searchForm==null){
			searchForm = new SearchForm();
		}
		DJPage<BannerVO> pageVO = bannerService.findDriverBySearch(searchForm);
		
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("user", getUser());
		bannerType = BannerType.DRIVER.getCode();
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Banner_addPage
	 * 添加banner的页面
	 * @return
	 * @throws Exception
	 */
	public String addPage() throws Exception {
		logger.debug("bannerType: " + bannerType);
		String url = "";
		if(bannerType==BannerType.AGENCY.getCode()){
			url = "bc_Banner_bannerList";
		}else{
			url = "bc_Banner_driverBannerList";
		}
		ActionContext.getContext().put("typeName", BannerType.getByCode(bannerType).getName());
		ActionContext.getContext().put("url", url);
		return DJAction.SHOW;
	}
	
	/**
	 * 添加banner轮播图
	 * bc_Banner_add
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String add() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(bannerForm==null){
			out.jsonResultError("参数异常");
			return null;
		}
		CheckFormResult cfr = DJCheckForm.check(bannerForm);
		if(!cfr.isOk()){
			out.jsonResultError(cfr.getError());
			return null;
		}
		Banner banner = new Banner(bannerForm);
		if(banner.save()){
			out.jsonResultSuccess("添加成功");
		}else{
			out.jsonResultError("添加失败");
		}
		return null;
	}
	
	/**
	 * bc_Banner_edit
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String edit() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(bannerForm==null){
			out.jsonResultError("参数异常");
			return null;
		}
		BannerDao dao = new BannerDao();
		Banner banner = dao.findById(bannerForm.getId());
		if(banner==null){
			out.jsonResultError("找不到轮播图");
			return null;
		}
		banner.setFormData(bannerForm);
		if(banner.update()){
			out.jsonResultSuccess("成功");
		}else{
			out.jsonResultError("编辑失败");
		}
		return null;
	}
	
	/**
	 * bc_Banner_delete
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String delete() throws Exception {
		OutputStringAction out = new OutputStringAction();
		BannerDao dao = new BannerDao();
		Banner banner = dao.findById(bannerId);
		if(banner!=null){
			if(!banner.delete()){
				out.jsonResultError("删除失败");
				return null;
			}
		}
		out.jsonResultSuccess("成功");
		return null;
	}
	
	/**
	 * bc_Banner_checkAdd
	 * 检查是否有添加权限
	 * @return
	 * @throws Exception
	 */
	public String checkAdd() throws Exception{
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getAvailableUrl().contains("bc_Banner_add")) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}
	
	/**
	 * bc_Banner_checkEdit
	 * 检查是否有编辑权限
	 * @return
	 * @throws Exception
	 */
	public String checkEdit() throws Exception{
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getAvailableUrl().contains("bc_Banner_edit")) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}
	
	/**
	 * bc_Banner_checkDelete
	 * 检查是否有添加权限
	 * @return
	 * @throws Exception
	 */
	public String checkDelete() throws Exception{
		OutputStringAction out = new OutputStringAction();
		User user = getUser();
		if (user.getAvailableUrl().contains("bc_Banner_delete")) {
			out.jsonResultSuccess("");
		} else {
			out.jsonResultError(Constants.NO_AUTH);
		}
		return null;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}



	public BannerForm getBannerForm() {
		return bannerForm;
	}



	public void setBannerForm(BannerForm bannerForm) {
		this.bannerForm = bannerForm;
	}

	public long getBannerId() {
		return bannerId;
	}

	public void setBannerId(long bannerId) {
		this.bannerId = bannerId;
	}

	public int getBannerType() {
		return bannerType;
	}

	public void setBannerType(int bannerType) {
		this.bannerType = bannerType;
	}
	
}
