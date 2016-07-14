package com.banche.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.banche.CheckAuth;
import com.banche.DJActionSupport;
import com.banche.dao.DriverBlackListDao;
import com.banche.model.DriverBlackList;
import com.banche.model.User;
import com.dijing.server.web.action.OutputStringAction;


/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月21日下午1:54:00
 */
public class DriverBlackListAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(DriverBlackListAction.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long driverId;
	
	/**
	 * bc_DriverBlackList_add
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String add() throws Exception {
		User user = getUser();
		DriverBlackListDao dao = new DriverBlackListDao();
		DriverBlackList dbl = dao.findByOwnerAndTarget(user.getId(), driverId);
		OutputStringAction out = new OutputStringAction();
		if(dbl==null){
			dbl = new DriverBlackList();
			dbl.setOwner(user.getId());
			dbl.setTarget(driverId);
			if(dbl.save()){
				out.jsonResultSuccess("success");
			}else{
				out.jsonResultError("添加失败");
			}
		}else{
			out.jsonResultSuccess("success");
		}
		return null;
	}
	
	/**
	 * bc_DriverBlackList_remove
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String remove() throws Exception {
		logger.debug("remove driverId: "+driverId);
		User user = getUser();
		DriverBlackListDao dao = new DriverBlackListDao();
		DriverBlackList dbl = dao.findByOwnerAndTarget(user.getId(), driverId);
		OutputStringAction out = new OutputStringAction();
		if(dbl!=null){
			if(dbl.delete()){
				out.jsonResultSuccess("success");
			}else{
				out.jsonResultError("移除失败");
			}
		}else{
			out.jsonResultSuccess("success");
		}
		return null;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

}
