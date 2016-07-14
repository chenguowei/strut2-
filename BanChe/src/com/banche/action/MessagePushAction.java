package com.banche.action;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.banche.ActiveUserListener;
import com.banche.Constants;
import com.banche.FreeVist;
import com.dijing.ssh.utils.OutputStringAction;

public class MessagePushAction {
	
	private String message;//消息 

	/**
	 * bc_MessagePush_push
	 * @return
	 * @throws Exception 
	 */
	@FreeVist
	public String push() throws Exception{//添加
//		System.out.println(message);
		JSONObject json = new JSONObject();
		
		if(message == null){
			message = "消息为空";
		}
		Map<String, HttpSession> sessionMap = ActiveUserListener.getSessionMap();
//		System.out.println("目前有"+sessionMap.size()+"个浏览器");
		
		for(Map.Entry<String, HttpSession> entry : sessionMap.entrySet()){
//			System.out.println("Map.value:"+entry.getValue().getAttribute(Constants.NEW_INFO));
			Object obj = entry.getValue().getAttribute(Constants.NEW_INFO);
			if(obj == null){
				BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
				queue.put(message);
				entry.getValue().setAttribute(Constants.NEW_INFO, queue);
			}else{
				BlockingQueue<String> queue = (BlockingQueue<String>)obj;
				if(queue.size() < 10){
					queue.put(message);
				}else{
					queue.remove();
					queue.put(message);
				}
			}
		}
		json.put("status", 1);
		json.put("info", "成功");
		
		OutputStringAction out=new OutputStringAction();
		out.println(json.toString());
		
		return null;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
