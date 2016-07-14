package com.banche.receiver;

import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.banche.enums.MessageEnums;
import com.banche.model.OrderMessage;
import com.dijing.server.Communication;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月2日下午2:02:47
 */
public class ReadData extends Thread {

	static Logger logger = LogManager.getLogger(ReadData.class.getName());
	
	private Socket socket = null;
	
	public ReadData(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run(){
		
		String str = null;
		Communication comm = new Communication();
		try {
			str = comm.readLine(socket);
			logger.info("from [" + socket.getInetAddress()
					+ "] Server received: [" + str + "]");

			JSONObject json = new JSONObject(str);
			//TODO 
			OrderMessage om = new OrderMessage();
			om.setOrderId(json.getLong("orderId"));
			om.setOrderStatus(json.getInt("status"));
			om.setStatus(MessageEnums.UN_READ.getCode());
			om.setTime(System.currentTimeMillis());
			om.setUserId(0);
			om.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
}
