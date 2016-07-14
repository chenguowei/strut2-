package com.dijing.server;

import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.dijing.server.dao.DatabaseAccessModule;

/**
 * 业务控制器（选择器）
 * 根据网络命令，校验身份，选择处理模块，返回处理结果
 * @author winter
 * @date   2014-12-13下午2:10:31
 */
public class BusinessController {

	static Logger logger = LogManager.getLogger(BusinessController.class.getName());
	
	public String process(JSONObject json, Socket socket){
		
		JSONObject feedback = new JSONObject();
		Communication comm = new Communication();
		
		//TODO 身份验证
				
		//TODO 选择处理模块
		DJResult<JSONObject> result = null;
		try{
			JSONObject data = json.getJSONObject(ServerJSONKey.DATA);
			DatabaseAccessModule dam = new DatabaseAccessModule();
			switch(json.getInt(ServerJSONKey.CMD)){
			case ServerCommand.DB_QUERY:
//				DatabaseAccessModule query = new DatabaseAccessModule();
				result = dam.query(data);
				break;
			case ServerCommand.DB_QUERY_REGION:
				result = dam.queryRegion(data);
				break;
			case ServerCommand.DB_QUERY_BY_SQL:
				result = dam.querySql(data);
				break;
			case ServerCommand.DB_UPDATE_ALL_BY_CONDITION:
//				DatabaseAccessModule updateAllByCondition = new DatabaseAccessModule();
				result = dam.updateAllByCondition(data);
				break;
			case ServerCommand.DB_UPDATE_BY_CONDITION:
//				DatabaseAccessModule updateByCondition = new DatabaseAccessModule();
				result = dam.updateByCondition(data);
				break;
			case ServerCommand.DB_DELETE_BY_CONDITION:
//				DatabaseAccessModule delete = new DatabaseAccessModule();
				result = dam.deleteByCondition(data);
				break;
			case ServerCommand.DB_INSERT:
//				DatabaseAccessModule insert = new DatabaseAccessModule();
				result = dam.insert(data);
				break;
			}		
		} catch (Exception e) {
			logger.error("", e);			
		}
		
		//TODO 反馈处理结果
		if(result==null){
			logger.error("result==null");
			feedback.put(ServerJSONKey.STATUS, Constants.ERROR_JSON);
			comm.send(socket, feedback);
			return "";
		}
		if(result.getStatus() != StatusConstant.SUCCESS){
			logger.error(""+result.getStatus());
			feedback.put(ServerJSONKey.STATUS, Constants.ERROR_JSON);
			comm.send(socket, feedback);
			return "";
		}
		
		feedback.put(ServerJSONKey.STATUS, Constants.SUCCESS_JSON);
		feedback.put(ServerJSONKey.DATA, result.getData());
		comm.send(socket, feedback);
		
		return "";
	}
}
