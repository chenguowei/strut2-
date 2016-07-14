package com.banche.service;

import java.util.ArrayList;
import java.util.List;

import com.banche.Constants;
import com.banche.dao.AgencyDao;
import com.banche.dao.DriverDao;
import com.banche.dao.FeedbackDao;
import com.banche.model.Agency;
import com.banche.model.Driver;
import com.banche.model.Feedback;
import com.banche.vo.FeedbackVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter
 * @date 2016年4月15日下午1:19:43
 */
public class FeedbackService {

	public DJPage<FeedbackVO> findAll(int page, int pageSize) {
		FeedbackDao dao = new FeedbackDao();
		DJPage<Feedback> result = dao.findAll(page, pageSize);
		List<Long> driverIds = new ArrayList<>();
		List<Long> agentIds = new ArrayList<>();
		for(Feedback fb : result.getData()){
			if(fb.getUid() > Constants.DRIVER_BASE){
				driverIds.add(fb.getUid());
			}else{
				agentIds.add(fb.getUid());
			}
		}
		DriverDao driverDao = new DriverDao();
		List<Driver> drivers = driverDao.findByIds(driverIds);
		AgencyDao agencyDao = new AgencyDao();
		List<Agency> agencys = agencyDao.findByIds(agentIds);
		List<FeedbackVO> list = new ArrayList<>();
		for (Feedback fb : result.getData()) {
			FeedbackVO vo = new FeedbackVO();
			try {
				EntityUtils.entityCopy(fb, vo);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			vo.setTimeVO(TimeUtils.getTimeFromLong(fb.getCreatetime().getTime()));
			if(fb.getUid() > Constants.DRIVER_BASE){
				Driver driver = drivers.stream().filter(m -> m.getId() == fb.getUid()).findFirst().orElse(null);
				if(driver!=null){
					vo.setUserName(driver.getName() + " (司机)");
				}
			}else{
				Agency agency = agencys.stream().filter(m -> m.getId() == fb.getUid()).findFirst().orElse(null);
				if(agency!=null){
					vo.setUserName(agency.getContact() + " (经销商)");
				}
			}
			list.add(vo);
		}
		return new DJPage<FeedbackVO>(list, result.getTotalPage(),
				result.getCurrentPage(), result.getTotalNumber());
	}
}
