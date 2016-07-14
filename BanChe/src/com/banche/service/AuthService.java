package com.banche.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.banche.Constants;
import com.banche.dao.AuthModuleDao;
import com.banche.dao.UserDao;
import com.banche.enums.ModuleType;
import com.banche.model.AuthModule;
import com.banche.model.User;
import com.banche.vo.FuncAuthVO;
import com.banche.vo.ModuleAuthVO;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日上午11:07:52
 */
public class AuthService {

	static Logger logger = LogManager.getLogger(AuthService.class.getName());
	
	private AuthModuleDao dao = new AuthModuleDao();
	
	/**
	 * 获取所有模块信息（按分类排列）
	 * @return
	 */
	public List<ModuleAuthVO> getAllModuleInfo(){
		return formVO(dao.findAll());
	}
	
	/**
	 * 按分类获取模块列表，并标记指定用户对模块是否具有访问权限
	 * @param user
	 * @return
	 */
	public List<ModuleAuthVO> getUserModule(User user){
		List<Integer> authIds = new ArrayList<>();
		boolean isAdmin = false;
		if(!StringUtils.isEmpty(user.getAuth())){
			if(Constants.ADMIN_AUTH.equals(user.getAuth())){
				isAdmin = true;
			}else{
				JSONArray arr = new JSONArray(user.getAuth());
				for(int i=0;i<arr.length();i++){
					authIds.add(arr.getInt(i));
				}
			}			
		}
		List<AuthModule> list = dao.findAll();
		for(AuthModule am : list){
			if(authIds.contains(am.getId()) || isAdmin){
				am.setHas(true);
			}
		}
		return formVO(list);
	}
	
	/**
	 * 按模块归类
	 * @param list
	 * @return
	 */
	public List<ModuleAuthVO> formVO(List<AuthModule> list){
		List<ModuleAuthVO> volist = new ArrayList<>();
		for(AuthModule am : list){
			if(am.getParent()==0){
				if(am.getType()==ModuleType.FUNC.getCode()){
					logger.error("根模块类型不能为‘功能点（按钮）’");
				}else{
					Object rootModule = formModuleAuthVO(list, am);
					if(rootModule instanceof ModuleAuthVO){
						volist.add((ModuleAuthVO) rootModule);
					}else if(rootModule instanceof FuncAuthVO){
						FuncAuthVO fa = (FuncAuthVO) rootModule;
						volist.add(new ModuleAuthVO(fa));
					}else{
						logger.error("unknown type: "+rootModule);
					}
				}
			}
		}
		return volist;
	}
	
	public FuncAuthVO formModuleAuthVO(List<AuthModule> list, AuthModule am){
		FuncAuthVO vo = null;
		if(ModuleType.FUNC.getCode()==am.getType()){
			vo = new FuncAuthVO(am);
			
		}else if(ModuleType.PAGE.getCode()==am.getType()){
			ModuleAuthVO mvo = new ModuleAuthVO(am);
			
			for(AuthModule a : list){
				if(a.getParent()==am.getId()){
					FuncAuthVO temp = formModuleAuthVO(list, a);
					if(temp instanceof ModuleAuthVO){
						mvo.getSonModuleList().add((ModuleAuthVO) temp);
					}else{
						mvo.getFuncList().add(temp);
					}
				}
			}
			vo = mvo;
		}else{
			logger.error("unknown module type: "+am.getType());
			vo = new FuncAuthVO(am);
		}
		return vo;
	}
	
	/**
	 * 解析用户的可用url并保存
	 * @param userinfo
	 */
	public void setAvailableUrl(User userinfo){
		
		UserDao userDao = new UserDao();
		User user = userDao.findById(userinfo.getId());
		userinfo.setAuth(user.getAuth());
		
		if (StringUtils.isEmpty(userinfo.getAuth())) {
			userinfo.setAvailableUrl(new ArrayList<>());
			userinfo.setAvailablePage(new ArrayList<>());
			userinfo.setAuthList(new ArrayList<>());
		} else {
			if (Constants.ADMIN_AUTH.equals(userinfo.getAuth())) {
				AuthModuleDao dao = new AuthModuleDao();
				List<AuthModule> list = dao.findAll();
				List<String> availableUrl = list.stream().map(m -> m.getUrl())
						.distinct().collect(Collectors.toList());
				userinfo.setAvailableUrl(availableUrl);
				List<String> availablePage = list.stream()
						.filter(m -> m.getType() == ModuleType.PAGE
								.getCode()).map(m -> m.getUrl()).distinct()
						.collect(Collectors.toList());
				userinfo.setAvailablePage(availablePage);
				userinfo.setAuthList(formVO(list));
			} else {
				JSONArray arr = new JSONArray(userinfo.getAuth());
				if (arr.length() == 0) {
					userinfo.setAvailableUrl(new ArrayList<>());
					userinfo.setAvailablePage(new ArrayList<>());
					userinfo.setAuthList(new ArrayList<>());
				} else {
					List<Integer> authIds = new ArrayList<>();
					for (int i = 0; i < arr.length(); i++) {
						authIds.add(arr.getInt(i));
					}
					//根节点(与数据库)
					List<AuthModule> root = dao.findByParent(0);
					for(AuthModule am : root){
						authIds.add(am.getId());
					}
					AuthModuleDao dao = new AuthModuleDao();
					List<AuthModule> list = dao.findByIds(authIds);
					List<String> availableUrl = list.stream()
							.map(m -> m.getUrl()).distinct()
							.collect(Collectors.toList());
					userinfo.setAvailableUrl(availableUrl);
					List<String> availablePage = list.stream()
							.filter(m -> m.getType() == ModuleType.PAGE
									.getCode()).map(m -> m.getUrl()).distinct()
							.collect(Collectors.toList());
					userinfo.setAvailablePage(availablePage);
					userinfo.setAuthList(formVO(list));
				}
			}
		}
	}
}
