package com.banche.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.CheckAuth;
import com.banche.DJActionSupport;
import com.banche.dao.EarnestDifferentDao;
import com.banche.dao.EarnestRateDao;
import com.banche.dao.OrderDao;
import com.banche.enums.OrderStatus;
import com.banche.form.SearchForm;
import com.banche.model.EarnestDifferent;
import com.banche.model.EarnestRate;
import com.banche.model.Order;
import com.banche.model.User;
import com.banche.service.AgencyService;
import com.banche.service.HintService;
import com.banche.service.OrderService;
import com.banche.service.PushService;
import com.banche.vo.ModuleAuthVO;
import com.banche.vo.OrderAndDriverVO;
import com.banche.vo.OrderVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityInfo;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.DJAction;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.JsonUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月11日下午9:07:46
 */
public class OrderAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(OrderAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderService();

	private HintService hintService = new HintService();
	
	private PushService pushService = new PushService();
	
	private AgencyService agencyService = new AgencyService();

	private SearchForm searchForm;
	/**
	 * 订单id
	 */
	private long orderId;
	/**
	 * 定价
	 */
	private String price;
	
	/**
	 * 司机端价格
	 */
	private String driverShowPrice;
	
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 定金比例	
	 */
	private String rate;
	
	/**
	 * 差价金额	
	 */
	private String different;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		return null;
	}
	
	/**
	 * 允许查看
	 * bc_Order_viewInfo
	 * @return
	 * @throws Exception
	 */
	public String viewInfo() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}
	
	/**
	 * bc_Order_orderModule
	 * @return
	 * @throws Exception
	 */
	public String orderModule() throws Exception {
		User user = getUser();
		ModuleAuthVO order = orderService.getOrderPage(user);
		
		if(order==null){
//			OutputStringAction out = new OutputStringAction();
//			out.jsonResultError("找不到货单模块信息");
			return DJAction.SHOW;
		}
		if(order.getSonModuleList().size()==0){
			ActionContext.getContext().put("hint", hintService.orderHint());
			ActionContext.getContext().put("user", user);
			ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
			return DJAction.SHOW;
		}
		logger.debug("first order page: " + order.getSonModuleList().get(0).getUrl());
		ActionContext.getContext().put(DJAction.RE_PATH, order.getSonModuleList().get(0).getUrl());
		return DJAction.RE_PATH;
	}
	
	/**
	 * bc_Order_viewPricing
	 * 待拟价列表-查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewPricing() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_pricingList 待拟价
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String pricingList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		
		DJPage<OrderVO> pageVO = orderService.findByStatus(
				OrderStatus.PRICING.getCode(), searchForm);      //根据状态去查询订单
		User user = getUser();
		
		EarnestRateDao erDao = new EarnestRateDao();
		EarnestRate er = erDao.findCurrentRate();
		if(er==null){
			er = new EarnestRate();
			er.setRate(30);
		}
		boolean modifyRate = user.getAvailableUrl().contains("bc_Order_editRate");
		
		ActionContext.getContext().put("rate", er.getRate());
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		ActionContext.getContext().put("modifyRate", modifyRate);
		ActionContext.getContext().put("cancelable", true);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewEarnest
	 * 待付定金 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewEarnest() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_earnestList 待付定金
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String earnestList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		DJPage<OrderVO> pageVO = orderService.findByStatus(
				OrderStatus.EARNEST.getCode(), searchForm);
		User user = getUser();
		boolean modifyRate = user.getAvailableUrl().contains("bc_Order_editRate");
		logger.debug("modifyRate: " + modifyRate);
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		ActionContext.getContext().put("modifyRate", modifyRate);
		ActionContext.getContext().put("cancelable", true);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewAccepting
	 * 等待接单 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewAccepting() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_waitAcceptList 等待接单
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String waitAcceptList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> list = new ArrayList<>();
		list.add(OrderStatus.ACCEPTING);
		list.add(OrderStatus.ACCEPTED_BUT_NOTICE);
		DJPage<OrderVO> pageVO = orderService.findByStatus(list, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		ActionContext.getContext().put("cancelable", true);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewStart
	 * 待发车 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewStart() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_waitStartList 待发车
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String waitStartList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.START);
		status.add(OrderStatus.DZC);
		status.add(OrderStatus.YZC);
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		ActionContext.getContext().put("cancelable", true);
		return DJAction.SHOW;
	}

	/**
	 * bc_Order_viewOnTheWay
	 * 运输中 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewOnTheWay() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}
	
	/**
	 * bc_Order_onTheWay 运输中
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String onTheWay() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.ON_THE_WAY);
		status.add(OrderStatus.YFC);
		
		status.add(OrderStatus.RECEIVING);
		status.add(OrderStatus.RECEIVING_AND_NOTICED);
		status.add(OrderStatus.LAST_PAY);
		status.add(OrderStatus.LAST_PAY_ADN_NOTICED);
		
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		return DJAction.SHOW;
	}

	/**
	 * bc_Order_receivingList 待确认收货(去掉)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String receivingList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.RECEIVING);
		status.add(OrderStatus.RECEIVING_AND_NOTICED);
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewLastPay
	 * 待确认尾款 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewLastPay() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_lastPayList 待确认尾款(去掉)
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String lastPayList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.LAST_PAY);
		status.add(OrderStatus.LAST_PAY_ADN_NOTICED);
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewComplete
	 * 已完成 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewComplete() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_completeList 已完成
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String completeList() throws Exception {
		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.COMPLETE);
		status.add(OrderStatus.COMPLETE_AND_COMMENTED);
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		return DJAction.SHOW;
	}
	
	/**
	 * bc_Order_viewFailure
	 * 失败订单 - 查看 权限
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String viewFailure() throws Exception {
		OutputStringAction out = new OutputStringAction();
		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_failureList 失败的订单
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String failureList() throws Exception {

		if (searchForm == null) {
			searchForm = new SearchForm();
		}
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.FAILURE);
		status.add(OrderStatus.HDCX);
		status.add(OrderStatus.ZCSB);
		status.add(OrderStatus.YSYW);
		status.add(OrderStatus.YSBTG);
		DJPage<OrderAndDriverVO> pageVO = orderService
				.findOrderAndDriverByStatus(status, searchForm);
		User user = getUser();
		ActionContext.getContext().put("pageVO", pageVO);
		ActionContext.getContext().put("hint", hintService.orderHint());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("order", orderService.getOrderPageVO(user));
		return DJAction.SHOW;
	}

	/**
	 * bc_Order_sendPrice 发送定价
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String sendPrice() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(StringUtils.isEmpty(price)){
			out.jsonResultError("未填写价格");
			return null;
		}
		if(!StringUtils.isNumeric(price)){
			out.jsonResultError("请输入数字");
			return null;
		}
		if(StringUtils.isEmpty(driverShowPrice)){
			out.jsonResultError("未填写司机端价格");
			return null;
		}
		if(!StringUtils.isNumeric(driverShowPrice)){
			out.jsonResultError("请输入数字到司机端价格");
			return null;
		}
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		
		order.setDriverShowPrice(Double.parseDouble(driverShowPrice));
		
		order.setTotalFee(Double.parseDouble(price));
		
		if(order.getTotalFee() < 0){
			out.jsonResultError("总费用不能为负");
			return null;
		}
		if(order.getDriverShowPrice() <= 0){
			out.jsonResultError("司机端价格必须大于0");
			return null;
		}
		
		double earnest = order.getTotalFee() * Double.parseDouble(rate) / 100;
		order.setEarnest(earnest);
		order.setStatus(OrderStatus.EARNEST.getCode());
		if (!order.update()) {
			out.jsonResultError("设置价格失败");
			return null;
		}
		
		// 通知app
		logger.debug("拟价通知app");
		try{
			if(!pushService.pushMessage(order, "货单已定价，价格为：" + price + "元")){
				out.jsonResultError(pushService.getErrorMsg());
				return null;
			}
		} catch (Exception e) {
			logger.error("", e);
			out.jsonResultError("推送接口格式异常");
			return null;
		}		

		out.jsonResultSuccess("成功");
		return null;
	}
	
	/**
	 * 修改定金的默认比例
	 * bc_Order_editRate
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String editRate() throws Exception {
		OutputStringAction out = new OutputStringAction();
		if(StringUtils.isEmpty(rate)){
			out.jsonResultError("未填写定金比例");
			return null;
		}
		EarnestRate er = new EarnestRate();
		er.setRate(Double.parseDouble(rate));
		er.setTime(System.currentTimeMillis());
		er.setUserId(getUser().getId());
		if(er.save()){
			out.jsonResultSuccess("");
		}else{
			out.jsonResultError("保存失败");
		}
		return null;
	}
	
	/**
	 * 修改差价金额的默认值
	 * bc_Order_editDifferent
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String editDifferent() throws Exception {
//		System.out.println(different);
		OutputStringAction out = new OutputStringAction();
		if(StringUtils.isEmpty(different)){
			out.jsonResultError("未填写差价金额");
			return null;
		}
		EarnestDifferent ed = new EarnestDifferent();
		ed.setDifferent(Integer.parseInt(different));
		ed.setTime(System.currentTimeMillis());
		ed.setUserId(getUser().getId());
		if(ed.save()){
			out.jsonResultSuccess("");
		}else{
			out.jsonResultError("保存失败");
		}
		return null;
	}
	
	/**
	 * 当前默认定金比例
	 * bc_Order_currentRate
	 * @return
	 * @throws Exception
	 */
	public String currentRate() throws Exception {
		OutputStringAction out = new OutputStringAction();
		EarnestRateDao erDao = new EarnestRateDao();
		EarnestRate er = erDao.findCurrentRate();
		if(er==null){
			er = new EarnestRate();
			er.setRate(30);
		}
		out.jsonResultSuccess(er.getRate()+"");
		return null;
	}
	
	/**
	 * 当前差价金额
	 * bc_Order_currentDifferent
	 * @return
	 * @throws Exception
	 */
	public String currentDifferent() throws Exception {
		OutputStringAction out = new OutputStringAction();
		EarnestDifferentDao edDao = new EarnestDifferentDao();
		EarnestDifferent ed = edDao.findCurrentDifferent();
		if(ed==null){
			ed = new EarnestDifferent();
			ed.setDifferent(200);
		}
		out.jsonResultSuccess(ed.getDifferent()+"");
		return null;
	}

	/**
	 * 添加备注 bc_Order_editNote
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editNote() throws Exception {
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		order.setNote(note);
		if (!order.update()) {
			out.jsonResultError("添加备注失败");
			return null;
		}

		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_changePrice 修改订单价格
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String changePrice() throws Exception {

		OutputStringAction out = new OutputStringAction();
		if(StringUtils.isEmpty(price)){
			out.jsonResultError("未填写价格");
			return null;
		}
		if(!StringUtils.isNumeric(price)){
			out.jsonResultError("请输入数字");
			return null;
		}
		if(StringUtils.isEmpty(driverShowPrice)){
			out.jsonResultError("未填写司机端价格");
			return null;
		}
		if(!StringUtils.isNumeric(driverShowPrice)){
			out.jsonResultError("请输入数字到司机端价格");
			return null;
		}
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		if (order.getStatus() != OrderStatus.EARNEST.getCode()) {
			out.jsonResultError("订单价格已不能修改");
			return null;
		}
		if (order.getOriginalPrice() == 0) {
			order.setOriginalPrice(order.getTotalFee());
		}
		
		order.setDriverShowPrice(Double.parseDouble(driverShowPrice));
		
		order.setTotalFee(Double.parseDouble(price));
		
		if(order.getTotalFee() < 0){
			out.jsonResultError("总费用不能为负");
			return null;
		}
		if(order.getDriverShowPrice() <= 0){
			out.jsonResultError("司机端价格必须大于0");
			return null;
		}
		
		double earnest = order.getTotalFee() * Double.parseDouble(rate) / 100;
		order.setEarnest(earnest);
		if (!order.update()) {
			out.jsonResultError("设置价格失败");
			return null;
		}
		

		List<EntityInfo> list = EntityUtils.parseEntity(order);
		
		list.add(new EntityInfo("rate", rate));
		list.add(new EntityInfo("different", order.getTotalFee() - order.getDriverShowPrice()));
		
		// 通知app
		logger.debug("修改价格通知app");
		if(!pushService.pushMessage(order, "货单价格已修改，最新价格为：" + price + "元")){
			out.jsonResultSuccess(pushService.getErrorMsg(), JsonUtils.formJsonArray(list));
			return null;
		}

		out.jsonResultSuccess("成功", JsonUtils.formJsonArray(list));
		return null;
	}

	/**
	 * bc_Order_noticeAccepted 通知app已接单
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String noticeAccepted() throws Exception {

		logger.debug("noticeAccepted");
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		if (order.getStatus() != OrderStatus.ACCEPTED_BUT_NOTICE.getCode()) {
			out.jsonResultError("订单状态异常");
			return null;
		}

		order.setStatus(OrderStatus.START.getCode());
		if (!order.update()) {
			out.jsonResultError("更新状态失败");
			return null;
		}
		
		// 通知app
		logger.debug("已接单，通知app");
		if(!pushService.pushMessage(order, "已有司机接受订单")){
			out.jsonResultError(pushService.getErrorMsg());
			return null;
		}

		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_noticeReceiving 通知收货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String noticeReceiving() throws Exception {

		logger.debug("noticeReceiving");
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		if (order.getStatus() != OrderStatus.RECEIVING.getCode()) {
			out.jsonResultError("订单状态异常");
			return null;
		}

		order.setStatus(OrderStatus.RECEIVING_AND_NOTICED.getCode());
		if (!order.update()) {
			out.jsonResultError("更新状态失败");
			return null;
		}
		// TODO 通知app 已接单
		logger.debug("通知app 收货");

		out.jsonResultSuccess("");
		return null;
	}

	/**
	 * bc_Order_noticeLastPay 通知app付尾款
	 * 
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String noticeLastPay() throws Exception {
		logger.debug("noticeLastPay");
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		if (order.getStatus() != OrderStatus.LAST_PAY.getCode()) {
			out.jsonResultError("订单状态异常");
			return null;
		}

		order.setStatus(OrderStatus.LAST_PAY_ADN_NOTICED.getCode());
		if (!order.update()) {
			out.jsonResultError("更新状态失败");
			return null;
		}
		// 通知app 已接单
		logger.debug("通知app 付尾款");
		if(!pushService.pushMessage(order, "您的订单【" + order.getSn() + "】尾款未支付。请及时支付。")){
			out.jsonResultError(pushService.getErrorMsg());
			return null;
		}

		out.jsonResultSuccess("");
		return null;
	}
	
	/**
	 * 取消订单
	 * bc_Order_cancelOrder
	 * @return
	 * @throws Exception
	 */
	public String cancelOrder() throws Exception {
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		if(StringUtils.isEmpty(note)){
			out.jsonResultError("请填写撤销订单原因~");
			return null;
		}
		order.setStatus(OrderStatus.HDCX.getCode());
		order.setNote(note);
		if (!order.update()) {
			out.jsonResultError("更新状态失败");
			return null;
		}
		out.jsonResultSuccess("成功");
		return null;
	}

	/**
	 * bc_Order_release 发布订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String release() throws Exception {
		OutputStringAction out = new OutputStringAction();
		OrderDao dao = new OrderDao();
		Order order = dao.findById(orderId);
		if (order == null) {
			out.jsonResultError("找不到该订单信息");
			return null;
		}
		order.setStatus(OrderStatus.ACCEPTING.getCode());
		if (!order.update()) {
			out.jsonResultError("更新状态失败");
			return null;
		}

		out.jsonResultSuccess("");
		return null;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDriverShowPrice() {
		return driverShowPrice;
	}

	public void setDriverShowPrice(String driverShowPrice) {
		this.driverShowPrice = driverShowPrice;
	}

	public String getDifferent() {
		return different;
	}

	public void setDifferent(String different) {
		this.different = different;
	}

	
}
