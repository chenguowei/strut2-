package com.banche.service;

import java.util.ArrayList;
import java.util.List;

import com.banche.enums.OrderStatus;
import com.banche.vo.HintVO;
import com.banche.vo.OrderHintVO;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日下午3:51:40
 */
public class HintService {

	private DriverService driverService = new DriverService();
	
	private OrderService orderService = new OrderService();
	
	public HintVO getHint(){
		HintVO hint = new HintVO();
		hint.setUnpassed(driverService.getUnpassedNumber());
		List<OrderStatus> status = new ArrayList<>();
		status.add(OrderStatus.PRICING);
		hint.setPricing(orderService.countByStatus(status));
		return hint;
	}
	
	public OrderHintVO orderHint(){
		OrderHintVO hint = new OrderHintVO();
//		List<OrderStatus> status = new ArrayList<>();
//		status.add(OrderStatus.EARNEST);
//		hint.setEarnest(orderService.countByStatus(status));
//		
//		status.clear();
//		status.add(OrderStatus.LAST_PAY);
//		status.add(OrderStatus.LAST_PAY_ADN_NOTICED);
//		hint.setLastPay(orderService.countByStatus(status));
//		
//		status.clear();
//		status.add(OrderStatus.PRICING);
//		hint.setPricing(orderService.countByStatus(status));
//		
//		status.clear();
//		status.add(OrderStatus.RECEIVING);
//		status.add(OrderStatus.RECEIVING_AND_NOTICED);
//		hint.setReceiving(orderService.countByStatus(status));
//		
//		status.clear();
//		status.add(OrderStatus.START);
//		hint.setStart(orderService.countByStatus(status));
		return hint;
	}
}
