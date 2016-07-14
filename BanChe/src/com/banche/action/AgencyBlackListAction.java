package com.banche.action;

import com.banche.CheckAuth;
import com.banche.DJActionSupport;
import com.banche.dao.AgencyBlackListDao;
import com.banche.model.AgencyBlackList;
import com.banche.model.User;
import com.dijing.server.web.action.OutputStringAction;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年1月25日下午5:24:24
 */
public class AgencyBlackListAction extends DJActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long agencyId;

	/**
	 * bc_AgencyBlackList_add
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String add() throws Exception {
		User user = getUser();
		AgencyBlackListDao dao = new AgencyBlackListDao();
		AgencyBlackList abl = dao.findByOwnerAndTarget(user.getId(), agencyId);
		OutputStringAction out = new OutputStringAction();
		if(abl==null){
			abl = new AgencyBlackList();
			abl.setOwner(user.getId());
			abl.setTarget(agencyId);
			if(abl.save()){
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
	 * bc_AgencyBlackList_remove
	 * @return
	 * @throws Exception
	 */
	@CheckAuth
	public String remove() throws Exception {
		User user = getUser();
		AgencyBlackListDao dao = new AgencyBlackListDao();
		AgencyBlackList abl = dao.findByOwnerAndTarget(user.getId(), agencyId);
		OutputStringAction out = new OutputStringAction();
		if(abl!=null){
			if(abl.delete()){
				out.jsonResultSuccess("success");
			}else{
				out.jsonResultError("删除失败");
			}
		}else{
			out.jsonResultSuccess("success");
		}
		return null;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

}
