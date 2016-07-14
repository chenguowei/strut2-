package com.banche.service;

import java.util.ArrayList;
import java.util.List;

import com.banche.Constants;
import com.banche.dao.BannerDao;
import com.banche.enums.BannerStatus;
import com.banche.enums.BannerType;
import com.banche.form.SearchForm;
import com.banche.model.Banner;
import com.banche.vo.BannerVO;
import com.dijing.server.dao.DJPage;
import com.dijing.server.entity.EntityUtils;
import com.dijing.server.web.utils.TimeUtils;

/**
 * 
 * @author winter
 * @date 2016年3月30日下午1:19:18
 */
public class BannerService {

	private BannerDao dao = new BannerDao();
	
	public DJPage<BannerVO> findAgencyBySearch(SearchForm searchForm){
		return findBySearch(searchForm, BannerType.AGENCY);
	}
	
	public DJPage<BannerVO> findDriverBySearch(SearchForm searchForm){
		return findBySearch(searchForm, BannerType.DRIVER);
	}
	
	public DJPage<BannerVO> findBySearch(SearchForm searchForm, BannerType type){
		DJPage<Banner> banners = dao.findBySearch(searchForm.getSearch(), type.getCode(),
				searchForm.getPage(), Constants.PAGE_SIZE);
		List<BannerVO> list = new ArrayList<>();
		for(Banner b : banners.getData()){
			BannerVO vo = new BannerVO();
			try {
				EntityUtils.entityCopy(b, vo);
				vo.setTimeVO(TimeUtils.getTimeFromLong(b.getTime()));
				vo.setStatusVO(BannerStatus.getNameByCode(b.getStatus()));
				list.add(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DJPage<BannerVO> pageVO = new DJPage<BannerVO>(list,
				banners.getTotalPage(), banners.getCurrentPage(),
				banners.getTotalNumber());
		return pageVO;
	}
}
