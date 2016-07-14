package com.banche.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.banche.Constants;
import com.banche.dao.DriverBlackListDao;
import com.banche.dao.DriverDao;
import com.banche.dao.OrderDao;
import com.banche.enums.StatusEnums;
import com.banche.form.SearchForm;
import com.banche.model.Driver;
import com.banche.model.DriverBlackList;
import com.banche.model.User;
import com.banche.vo.DriverVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月20日下午5:48:44
 */
public class DriverService {

	private DriverDao dao = new DriverDao();
	
	public long generateId(){
		return Constants.DRIVER_BASE + System.currentTimeMillis() % 100000;
	}

	public DJPage<DriverVO> findDriverList(SearchForm searchForm, User user) {
		DriverBlackListDao blackDao = new DriverBlackListDao();
		List<DriverBlackList> blackList = blackDao.findByOwner(user.getId());
		List<Long> driverIds = blackList.stream().map(m -> m.getTarget())
				.collect(Collectors.toList());
		DJPage<Driver> page = dao.findByNameAndIdsNotIn(searchForm.getSearch(), driverIds,
				searchForm.getPage(), Constants.PAGE_SIZE);

		return formDriverVO(page);
	}

	public DJPage<DriverVO> findUnpassedDriverList(SearchForm searchForm) {
		DJPage<Driver> page = dao.findUnpassedByName(searchForm.getSearch(),
				searchForm.getPage(), Constants.PAGE_SIZE);
		return formDriverVO(page);
	}

	public int getUnpassedNumber() {
		return dao.countUnpassed();
	}

	public DJPage<DriverVO> findBlackList(SearchForm searchForm, User user) {
		DriverBlackListDao blackDao = new DriverBlackListDao();
		List<DriverBlackList> blackList = blackDao.findByOwner(user.getId());
		List<Long> driverIds = blackList.stream().map(m -> m.getTarget())
				.collect(Collectors.toList());
		if(driverIds.size()==0){
			return new DJPage<DriverVO>(new ArrayList<DriverVO>(), 0, 1, 0);
		}
		DJPage<Driver> page = dao.findByNameAndIds(searchForm.getSearch(), driverIds,
				searchForm.getPage(), Constants.PAGE_SIZE);
		
		List<DriverVO> list = new ArrayList<>();
		for (Driver d : page.getData()) {
			DriverVO vo = new DriverVO(d, "封禁");
			list.add(vo);
		}

		return new DJPage<DriverVO>(page, list);
	}
	
	private DJPage<DriverVO> formDriverVO(DJPage<Driver> page){
		List<DriverVO> list = new ArrayList<>();
		
		for (Driver d : page.getData()) {
			DriverVO vo = new DriverVO(d);			
			list.add(vo);
		}

		return new DJPage<DriverVO>(page, list);
	}
}
