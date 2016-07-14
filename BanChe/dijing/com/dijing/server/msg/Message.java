package com.dijing.server.msg;

import java.util.Map;

import org.json.JSONObject;

import com.dijing.server.ServerJSONKey;

public class Message {

	
	/**
	 * 消息发送者ID
	 */
	private int srcId = 0;
	
	/**
	 * 发送者用户名
	 */
	private String srcName = null;
	
	/**
	 * 消息
	 */
	private String msg = null;
	
	/**
	 * 以哈希表形式封装的消息
	 */
	private Map<String, Object> data;
	/**
	 * 服务器向客户端推送消息的命令
	 */
	private String cmd;
	
	public Message(String cmd, Map<String, Object> data){
		this.setCmd(cmd);
		this.setData(data);
	}
	
	public String toJSONString(){
		JSONObject json = new JSONObject();
		json.put(ServerJSONKey.CMD, cmd);
		json.put(ServerJSONKey.DATA, data);
//		if(Configuration.debug){
//			System.out.println("Message: "+json.toString());
//		}
		return json.toString();
	}
	
	public Message(String srcName, String msg){
		this.srcName = srcName;
		this.msg = msg;
	}
	
	public int getSrcId() {
		return srcId;
	}
	
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
