package com.banche.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.banche.Constants;
import com.banche.dao.AgencyBlackListDao;
import com.banche.dao.AgencyDao;
import com.banche.form.SearchForm;
import com.banche.model.Agency;
import com.banche.model.AgencyBlackList;
import com.banche.model.User;
import com.banche.vo.AgencyVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午3:20:37
 */
public class AgencyService {

	private AgencyDao dao = new AgencyDao();
	
	public long generateId(){
		return Constants.AGENT_BASE + System.currentTimeMillis() % 100000;
	}

	public DJPage<AgencyVO> findList(SearchForm searchForm, User user) {
		AgencyBlackListDao blackDao = new AgencyBlackListDao();
		List<AgencyBlackList> blackList = blackDao.findByOwner(user.getId());
		List<Long> agencyIds = blackList.stream().map(m -> m.getTarget())
				.collect(Collectors.toList());
		DJPage<Agency> page = dao.findByNameAndIdsNotIn(searchForm.getSearch(),
				agencyIds, searchForm.getPage(), Constants.PAGE_SIZE);
		List<AgencyVO> volist = new ArrayList<>();
		for (Agency a : page.getData()) {
			AgencyVO vo = new AgencyVO(a);
			vo.setStatusVO("正常");
			volist.add(vo);
		}
		return new DJPage<AgencyVO>(page, volist);
	}

	public DJPage<AgencyVO> findBlackList(SearchForm searchForm, User user) {
		AgencyBlackListDao blackDao = new AgencyBlackListDao();
		List<AgencyBlackList> blackList = blackDao.findByOwner(user.getId());
		List<Long> agencyIds = blackList.stream().map(m -> m.getTarget())
				.collect(Collectors.toList());
		if (agencyIds.size() == 0) {
			return new DJPage<AgencyVO>(new ArrayList<>(), 0, 1, 0);
		}
		DJPage<Agency> page = dao.findByNameAndIdsIn(searchForm.getSearch(),
				agencyIds, searchForm.getPage(), Constants.PAGE_SIZE);
		List<AgencyVO> list = new ArrayList<>();
		for(Agency a : page.getData()){
			AgencyVO vo = new AgencyVO();
			try {
				EntityUtils.entityCopy(a, vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setStatusVO("封禁");
			vo.setRegisterVO(TimeUtils.getTimeFromLong2(a.getRegisterTime()));
			list.add(vo);
		}
		return new DJPage<AgencyVO>(page, list);
	}
}
