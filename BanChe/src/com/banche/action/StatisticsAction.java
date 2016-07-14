package com.banche.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.enums.StatististicsTimeEnums;
import com.banche.form.SearchForm;
import com.banche.form.StatisticsForm;
import com.banche.service.FeedbackService;
import com.banche.service.StatisticsService;
import com.banche.vo.FeedbackVO;
import com.banche.vo.StatisticsVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.web.DJAction;
import com.opensymphony.xwork2.ActionContext;

public class StatisticsAction extends DJActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StatisticsForm subform;
	
	static Logger logger = LogManager.getLogger(StatisticsAction.class.getName());
	StatisticsService statisticsService = new StatisticsService();
	
	private FeedbackService feedbackService = new FeedbackService();
	
	private SearchForm searchForm;
	
	/**
	 * bc_Statistics_orderCount
	 * @return
	 * @throws Exception
	 */
	public String orderCount()throws Exception{
		StatisticsVO data = statisticsService.getOrderCount();
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("user", getUser());
		return DJAction.SHOW;
	}
	
	public String orderNew() throws Exception{
		if(subform==null){
			subform = new StatisticsForm();
			subform.setTimeType(StatististicsTimeEnums.MONTH.getCode());
		}
		StatisticsVO data = statisticsService.getOrderNew(subform);
		ActionContext.getContext().put("data", data);
		ActionContext.getContext().put("subform", subform);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Statistics_user
	 * @return
	 * @throws Exception
	 */
	public String user() throws Exception {
		
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Statistics_feedback
	 * app的反馈意见
	 * @return
	 * @throws Exception
	 */
	public String feedback() throws Exception {
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		DJPage<FeedbackVO> pageVO = feedbackService.findAll(searchForm.getPage(), Constants.PAGE_SIZE);
		ActionContext.getContext().put("pageVO", pageVO);
		return DJAction.SHOW;
	}

	public StatisticsForm getSubform() {
		return subform;
	}

	public void setSubform(StatisticsForm subform) {
		this.subform = subform;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}


}
