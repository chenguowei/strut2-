package com.banche.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.banche.Constants;
import com.banche.dao.AddressBookDao;
import com.banche.dao.AutomoldDao;
import com.banche.dao.DriverDao;
import com.banche.dao.OrderDao;
import com.banche.dao.OrderautoDao;
import com.banche.enums.OrderStatus;
import com.banche.form.SearchForm;
import com.banche.model.Addressbook;
import com.banche.model.Automold;
import com.banche.model.Driver;
import com.banche.model.Oauto;
import com.banche.model.Order;
import com.banche.model.OrderState;
import com.banche.model.Orderauto;
import com.banche.model.User;
import com.banche.vo.ModuleAuthVO;
import com.banche.vo.OrderAndDriverVO;
import com.banche.vo.OrderPageVO;
import com.banche.vo.OrderVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.DJNumberUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午9:09:45
 */
public class OrderService {

	private OrderDao dao = new OrderDao();
	
	public ModuleAuthVO getOrderPage(User user){
		ModuleAuthVO order = null;
		for (ModuleAuthVO auth : user.getAuthList()) {
			if ("货单管理".equals(auth.getName())) {
				order = auth;
				break;
			}
		}
		return order;
	}
	
	public List<OrderPageVO> getOrderPageVO(User user){
		List<OrderPageVO> volist = new ArrayList<>();
		ModuleAuthVO order = getOrderPage(user);
		for(ModuleAuthVO son : order.getSonModuleList()){
			OrderPageVO vo = new OrderPageVO();
			volist.add(vo);
			vo.setName(son.getName());
			vo.setUrl(son.getUrl());
			List<OrderStatus> status = new ArrayList<>();
			if("待拟价".equals(son.getName())){
				status.add(OrderStatus.PRICING);				
//			}else if("待付定金".equals(son.getName())){
//				status.add(OrderStatus.EARNEST);				
//			}else if("等待发车".equals(son.getName())){
//				status.add(OrderStatus.START);				
//			}else if("待确认尾款".equals(son.getName())){
//				status.add(OrderStatus.LAST_PAY);
//				status.add(OrderStatus.LAST_PAY_ADN_NOTICED);			
			}else{
				continue;
			}
			vo.setHint(countByStatus(status));			
		}
		return volist;
	}

	public DJPage<OrderVO> findByStatus(int status, SearchForm searchForm) {
		DJPage<Order> page = dao.findByStatus(status,
				searchForm.getSearch(), searchForm.getPage(),
				Constants.PAGE_SIZE);
		List<OrderVO> list = new ArrayList<>();
//		List<Long> addressIds = new ArrayList<>();
		//获得车辆详情与车辆类型
		List<Orderauto> autoList = new OrderautoDao().findByOrderList(page.getData());//车辆详情列表
		List<Automold> moldList = new AutomoldDao().findByautoList(autoList);//车辆类型列表
		
		/**修改*/
//		Map<Long,Orderauto>  autoMap = new HashMap<Long, Orderauto>();
//		Map<Long, Automold> moldMap = new HashMap<Long, Automold>();
//		for(Orderauto oa:autoList){
//			 autoMap.put(oa.getOrderid(), oa);
//		}
//		for(Automold mold:moldList){
//			moldMap.put(mold.getId(), mold);
//		}
		List<Oauto> autolist = new ArrayList<Oauto>();
		List<OrderState> moldlist = new ArrayList<OrderState>();
		for(Orderauto oa:autoList){
			autolist.add(new Oauto(oa.getOrderid(), oa));
		}
		for(Automold mold:moldList){
			moldlist.add(new OrderState(mold.getId(), mold));
		}
		/**修改end*/
		
		for (Order o : page.getData()) {
			OrderVO vo = new OrderVO();
			try {
				EntityUtils.entityCopy(o, vo);
//				if(!StringUtils.isEmpty(vo.getSendAddr())){
//					vo.setSendAddrId(Long.parseLong(vo.getSendAddr()));
//					addressIds.add(vo.getSendAddrId());					
//				}
//				if(!StringUtils.isEmpty(vo.getReceiveAddr())){
//					vo.setRevAddrId(Long.parseLong(vo.getReceiveAddr()));
//					addressIds.add(vo.getRevAddrId());
//				}
				
				vo.setDifferent(vo.getTotalFee() - vo.getDriverShowPrice());
				if(vo.getTotalFee()!=0){
					vo.setRate(DJNumberUtils.formatDouble1(vo.getEarnest() / vo.getTotalFee() * 100));
				}				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setStatusVO(OrderStatus.getNameByCode(vo.getStatus()));
			vo.setCreateTime(TimeUtils.getTimeFromLongCn(o.getCreatetime()*1000));	
			
			/**修改*/
//			Orderauto oa = autoMap.get(o.getId())!=null?autoMap.get(o.getId()):new Orderauto();//获得车辆详情
//			Automold mold = moldMap.get(oa.getMoldid())!=null?moldMap.get(oa.getMoldid()):new Automold();//获取车辆款式
			StringBuffer info = new StringBuffer();
			for(Oauto oauto: autolist) {//按车辆详情Map循环
				if(oauto.getO().getOrderid() == o.getId()){//车辆详情的订单id等于货单id
					Orderauto oa = oauto.getO();//获取该车辆详情信息
					for(OrderState os : moldlist){
						if(os.getA().getId() == oa.getMoldid()){
							String str = new String();
							str = oa.getAutoname()+" "+//品牌
									os.getA().getMoldname()+" x "+//类型
									oa.getAutonum()+"；";//数量
							info.append(str);
						}
					}
					}
			}
//            System.out.println(info.toString());
			vo.setMold(info.toString());
//			vo.setMold(mold.getMoldname());
//			vo.setAutoname(oa.getAutoname());
//			vo.setAutonum(oa.getAutonum());
			/**修改end*/
			
			list.add(vo);
		}
//		AddressBookDao abd = new AddressBookDao();
//		Map<Long, Addressbook> map = abd.findByIds(addressIds);
//		for(OrderVO vo : list){
//			Addressbook send = map.get(vo.getSendAddrId());
//			if(send!=null){
//				vo.setSendAddr(send.getDetail());
//			}
//			Addressbook rev = map.get(vo.getRevAddrId());
//			if(rev!=null){
//				vo.setReceiveAddr(rev.getDetail());
//			}
//		}
		return new DJPage<OrderVO>(page, list);
	}

	public DJPage<OrderVO> findByStatus(List<OrderStatus> status,
			SearchForm searchForm) {
		List<Integer> statusList = status.stream().map(m -> m.getCode())
				.collect(Collectors.toList());
		DJPage<Order> page = dao.findByStatus(statusList,
				searchForm.getSearch(), searchForm.getPage(),
				Constants.PAGE_SIZE);
		List<OrderVO> list = new ArrayList<>();
		List<Long> addressIds = new ArrayList<>();
		for (Order o : page.getData()) {
			OrderVO vo = new OrderVO();
			try {
				EntityUtils.entityCopy(o, vo);
				vo.setDifferent(vo.getTotalFee() - vo.getDriverShowPrice());
				if(vo.getTotalFee()!=0){
					vo.setRate(DJNumberUtils.formatDouble1(vo.getEarnest() / vo.getTotalFee() * 100));
				}
//				if(!StringUtils.isEmpty(vo.getSendAddr())){
//					vo.setSendAddrId(Long.parseLong(vo.getSendAddr()));
//					addressIds.add(vo.getSendAddrId());					
//				}
//				if(!StringUtils.isEmpty(vo.getReceiveAddr())){
//					vo.setRevAddrId(Long.parseLong(vo.getReceiveAddr()));
//					addressIds.add(vo.getRevAddrId());
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setStatusVO(OrderStatus.getNameByCode(vo.getStatus()));
			vo.setCreateTime(TimeUtils.getTimeFromLongCn(o.getCreatetime()*1000));
			list.add(vo);
		}
		AddressBookDao abd = new AddressBookDao();
		Map<Long, Addressbook> map = abd.findByIds(addressIds);
		for(OrderVO vo : list){
			Addressbook send = map.get(vo.getSendAddrId());
			if(send!=null){
				vo.setSendAddr(send.getDetail());
			}
			Addressbook rev = map.get(vo.getRevAddrId());
			if(rev!=null){
				vo.setReceiveAddr(rev.getDetail());
			}
		}
		return new DJPage<OrderVO>(page, list);
	}

	public DJPage<OrderAndDriverVO> findOrderAndDriverByStatus(
			List<OrderStatus> status, SearchForm searchForm) {
		List<Integer> statusList = status.stream().map(m -> m.getCode())
				.collect(Collectors.toList());
		DJPage<Order> page = dao.findByStatus(statusList,
				searchForm.getSearch(), searchForm.getPage(),
				Constants.PAGE_SIZE);
		List<Long> driverIds = page.getData().stream().map(m -> m.getDriver())
				.collect(Collectors.toList());
		DriverDao driverDao = new DriverDao();
		List<Driver> driverList = driverDao.findByIds(driverIds);
		List<OrderAndDriverVO> list = new ArrayList<>();
//		AddressBookDao abd = new AddressBookDao();
//		List<Long> addressIds = new ArrayList<>();
		
		//获得车辆详情与车辆类型
				List<Orderauto> autoList = new OrderautoDao().findByOrderList(page.getData());//车辆详情列表
				List<Automold> moldList = new AutomoldDao().findByautoList(autoList);//车辆类型列表
				/**修改*/
//				Map<Long,Orderauto> autoMap = new HashMap<Long, Orderauto>();
//				Map<Long, Automold> moldMap = new HashMap<Long, Automold>();
//				for(Orderauto oa:autoList){
//					autoMap.put(oa.getOrderid(), oa);
//				}
//				for(Automold mold:moldList){
//					moldMap.put(mold.getId(), mold);
//				}
				List<Oauto> autolist = new ArrayList<Oauto>();
				List<OrderState> moldlist = new ArrayList<OrderState>();
				for(Orderauto oa:autoList){
					autolist.add(new Oauto(oa.getOrderid(), oa));
				}
				for(Automold mold:moldList){
					moldlist.add(new OrderState(mold.getId(), mold));
				}
				/**修改end*/
		for (Order o : page.getData()) {
			OrderAndDriverVO vo = new OrderAndDriverVO();
			try {
				EntityUtils.entityCopy(o, vo);
				vo.setDifferent(vo.getTotalFee() - vo.getDriverShowPrice());
				if(vo.getTotalFee()!=0){
					vo.setRate(DJNumberUtils.formatDouble1(vo.getEarnest() / vo.getTotalFee() * 100));
				}
//				if(!StringUtils.isEmpty(vo.getSendAddr())){
//					vo.setSendAddrId(Long.parseLong(vo.getSendAddr()));
//					addressIds.add(vo.getSendAddrId());					
//				}
//				if(!StringUtils.isEmpty(vo.getReceiveAddr())){
//					vo.setRevAddrId(Long.parseLong(vo.getReceiveAddr()));
//					addressIds.add(vo.getRevAddrId());
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setStatusVO(OrderStatus.getNameByCode(vo.getStatus()));
			vo.setCreateTime(TimeUtils.getTimeFromLongCn(o.getCreatetime()*1000));;
			for(Driver d : driverList){
				if(d.getId()==o.getDriver()){
//					vo.setName(d.getName());
//					vo.setTel(d.getTel());
//					vo.setCarNumber(d.getCarNumber());
					try {
						EntityUtils.entityCopy(d, vo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//vo.setStatusVO(StatusEnums.getNameByCode(d.getStatus()));
					vo.setTime(TimeUtils.getTimeFromLong2(d.getRegisterTime()));
					vo.setOrderNumber(dao.countOrderByDriver(d.getId()));
					
					vo.setDrivingLicense(Constants.UPLOAD + "/" + d.getDrivingLicense());
					vo.setIdentityBack(Constants.UPLOAD + "/" + d.getIdentityBack());
					vo.setIdentityFront(Constants.UPLOAD + "/" + d.getIdentityFront());
					vo.setVehicleLicense(Constants.UPLOAD + "/" + d.getVehicleLicense());
					
					vo.setDrivingLicenseBack(Constants.UPLOAD + "/" + d.getDrivingLicenseBack());
					vo.setVehicleLicenseBack(Constants.UPLOAD + "/" + d.getVehicleLicenseBack());
					
					//TODO 
					vo.setCarTypeVO("");
					break;
				}
			}
			
			/**修改*/
//			Orderauto oa = autoMap.get(o.getId())!=null?autoMap.get(o.getId()):new Orderauto();//获得车辆详情
//			Automold mold = moldMap.get(oa.getMoldid())!=null?moldMap.get(oa.getMoldid()):new Automold();//获取车辆款式
//			vo.setMold(mold.getMoldname());
//			vo.setAutoname(oa.getAutoname());
//			vo.setAutonum(oa.getAutonum());
			StringBuffer info = new StringBuffer();
			for(Oauto oauto: autolist) {//按车辆详情Map循环
				if(oauto.getO().getOrderid() == o.getId()){//车辆详情的订单id等于货单id
					Orderauto oa = oauto.getO();//获取该车辆详情信息
					for(OrderState os : moldlist){
						if(os.getA().getId() == oa.getMoldid()){
							String str = new String();
							str = oa.getAutoname()+" "+//品牌
									os.getA().getMoldname()+" x "+//类型
									oa.getAutonum()+"；";//数量
							info.append(str);
						}
					}
					}
			}
//            System.out.println(info.toString());
			vo.setMold(info.toString());
			/**修改end*/
			
			list.add(vo);
		}
//		Map<Long, Addressbook> map = abd.findByIds(addressIds);
//		for(OrderAndDriverVO vo : list){
//			Addressbook send = map.get(vo.getSendAddrId());
//			if(send!=null){
//				vo.setSendAddr(send.getDetail());
//			}
//			Addressbook rev = map.get(vo.getRevAddrId());
//			if(rev!=null){
//				vo.setReceiveAddr(rev.getDetail());
//			}
//		}
		return new DJPage<OrderAndDriverVO>(page, list);
	}
	
	public int countByStatus(List<OrderStatus> status){
		List<Integer> statusList = status.stream().map(m -> m.getCode())
				.collect(Collectors.toList());
		return dao.countByStatus(statusList);
	}
}
