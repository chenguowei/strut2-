package com.banche.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.banche.dao.UserDao;
import com.banche.form.SearchForm;
import com.banche.model.User;
import com.banche.vo.UserVO;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日下午8:18:35
 */
public class UserService {

	private UserDao dao = new UserDao();
	
	
	public List<UserVO> getAll(){
		List<User> users = dao.findAll();
		return users.stream().map(m -> new UserVO(m)).collect(Collectors.toList());
	}
	
	public List<UserVO> getBySearch(SearchForm searchForm){
		List<User> users = null;
		if(StringUtils.isEmpty(searchForm.getSearch())){
			users = dao.findAll();
		}else{
			users = dao.findBySearch(searchForm.getSearch());
		}
		return users.stream().map(m -> new UserVO(m)).collect(Collectors.toList());
	}
}
