package com.banche.service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.banche.dao.AgencyDao;
import com.banche.model.Agency;
import com.banche.model.Order;
import com.dijing.server.http.HttpTool;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月15日上午10:28:45
 */
public class PushService {

	static Logger logger = LogManager.getLogger(PushService.class.getName());

	private String errorMsg;

	public boolean pushMessage(Order order, String content) throws Exception {
		String url = "http://101.201.198.46:8082/index.php/api/propel/pushMessage";

		// AgencyDao agencyDao = new AgencyDao();
		// Agency agency = agencyDao.findById(order.getAgencyId());
		// if(agency==null){
		// logger.error("can't find agency by id: " + order.getAgencyId());
		// return false;
		// }
		// JSONArray arr = new JSONArray();
		// arr.put(agency.getContactTel());
		// JSONObject json = new JSONObject();
		// json.put("tellist", arr);
		// json.put("Content", content);
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", content);
		map.put("uid", order.getAgencyId() + "");

		JSONObject result = HttpTool.httpPostWithJSON(url, map);
		if (result == null) {
			return false;
		}
		logger.debug("rev: " + URLDecoder.decode(result.toString(), "UTF8"));
		// if("failed".equals(result.getString("result"))){
		// logger.error(URLDecoder.decode(result.getString("reason"), "UTF8"));
		// return false;
		// }
		if (result.getInt("code") != 0) {
			this.errorMsg = result.optString("data", "推送失败");
			return false;
		}
		return true;
	}

	/**
	 * 发短信
	 * 
	 * @param phone
	 *            手机号
	 * @param content
	 *            发送内容
	 * @return
	 */
	public boolean pushSMS(String phone, String content) throws Exception {
//		content = new String(content.getBytes("UTF-8"), "GB2312");
		String url = String
				.format("http://qd.qxt666.cn/interface/tomsg.jsp?user=kakaqiche&pwd=kakaqiche123&phone=%s&msgcont=%s",
						phone, content);
		String back = HttpTool.httpGet(url);
		System.out.println("back: " + back);
		if(back == null || !"0".equals(back) || "".equals(back)){
			return false;
		}

		return true;
	}
	
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean resetPassword(String phone) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sign", "wYX6oJGiHSpcXmV9");
		map.put("mobile", phone);
		final String url = "http://101.201.198.46:8082/index.php/user/sendSMSForBack";
		JSONObject result = HttpTool.httpPostWithJSON(url, map);
		if (result == null) {
			return false;
		}
		logger.debug("rev: " + URLDecoder.decode(result.toString(), "UTF8"));
		if (result.getInt("code") != 0) {
			this.errorMsg = result.optString("data", "推送失败");
			return false;
		}
		return true;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
