package com.banche.action;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.banche.Constants;
import com.banche.DJActionSupport;
import com.banche.service.HintService;
import com.banche.vo.HintVO;
import com.dijing.server.web.action.OutputStringAction;
import com.dijing.server.web.utils.JsonUtils;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月3日下午4:54:28
 */
public class PollAction extends DJActionSupport {

	static Logger logger = LogManager.getLogger(PollAction.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HintService hintService = new HintService();
	
	
	public String poll() throws Exception {
		OutputStringAction out = new OutputStringAction();
		
		return null;
	}
	
	/**
	 * bc_Poll_refreshHint
	 * @return
	 * @throws Exception
	 */
	public String refreshHint() throws Exception {
		
//		logger.debug("refresh");
		/**添加*/
		HttpServletRequest request = ServletActionContext.getRequest();

		BlockingQueue<String> queue = (BlockingQueue<String>) request.getSession().getAttribute(Constants.NEW_INFO);
		/**添加end*/
		
		OutputStringAction out = new OutputStringAction(false);
		
		HintVO hint = hintService.getHint();
		
		/**添加*/
//		logger.debug(request.getSession().getAttributeNames()+":"+queue.size());
		if(queue != null && queue.size() != 0){
			hint.setInfo(1);//有新消息
//			System.out.println("有新消息");
			queue.remove();//删除queue第一个元素
		}
		/**添加end*/
		
		out.jsonResultSuccess("", JsonUtils.formJson(hint));
//		logger.debug("hint: " + JsonUtils.formJson(hint));
		return null;
	}

	public HintService getHintService() {
		return hintService;
	}

	public void setHintService(HintService hintService) {
		this.hintService = hintService;
	}

}
