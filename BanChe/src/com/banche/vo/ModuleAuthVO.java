package com.banche.vo;

import java.util.ArrayList;
import java.util.List;

import com.banche.model.AuthModule;

/**
 * 模块权限信息
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月29日上午11:24:48
 */
public class ModuleAuthVO extends FuncAuthVO {

	/**
	 * 模块下的功能点
	 */
	private List<FuncAuthVO> funcList;
	/**
	 * 模块下的子模块
	 */
	private List<ModuleAuthVO> sonModuleList;
	
	private String url;
	
	public ModuleAuthVO(){}
	
	public ModuleAuthVO(FuncAuthVO vo){
		setId(vo.getId());
		setName(vo.getName());
		super.setHas(vo.isHas());
		this.funcList = new ArrayList<>();
		this.sonModuleList = new ArrayList<>();
	}
	
	public ModuleAuthVO(AuthModule am){
		super(am);
		this.url = am.getUrl();
		this.funcList = new ArrayList<>();
		this.sonModuleList = new ArrayList<>();
	}

	public List<FuncAuthVO> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<FuncAuthVO> funcList) {
		this.funcList = funcList;
	}

	public List<ModuleAuthVO> getSonModuleList() {
		return sonModuleList;
	}

	public void setSonModuleList(List<ModuleAuthVO> sonModuleList) {
		this.sonModuleList = sonModuleList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
