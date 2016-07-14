package com.dijing.server.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * 消息队列
 * 并提供向该队列发消息和读取消息的方法
 * @author winter
 * @date   2015-3-6上午9:10:56
 */
public class MessageBox {

	static Logger logger = LogManager.getLogger(MessageBox.class.getName());
	
	private int userid;
	
	private final int queueMaxSize = 20;
	/**
	 * 消息队列。临时保存发送给指定用户的消息。
	 */
	private Queue<Message> msgQueue = 
			new ArrayBlockingQueue<Message>(queueMaxSize);
	
	public MessageBox(int userid){
		setUserid(userid);
	}
	
	public Queue<Message> getMsgQueue() {
		return msgQueue;
	}
	public void setMsgQueue(Queue<Message> msgQueue) {
		this.msgQueue = msgQueue;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * 向当前消息箱中发送消息
	 * @param msg
	 * @return
	 */
	public synchronized void send(Message msg){
		if(this.getMsgQueue().size()==this.queueMaxSize){
			logger.error("this.getMsgQueue().size()==this.queueMaxSize  "+userid);
			this.getMsgQueue().poll();
		}
//		this.getMsgQueue().add(msg);
		if(!this.getMsgQueue().offer(msg)){
			logger.error("队列满！  "+userid);
		}else{
			logger.debug("add msg: "+msg.toJSONString());
		}
	}
	
	/**
	 * 在当前消息箱中提取未读信息
	 * @return
	 */
	public synchronized List<Message> extract(){
		List<Message> list = new ArrayList<Message>();
		Message msg;
		while((msg=this.getMsgQueue().poll()) != null){
			list.add(msg);
		}
		return list;
	}
}
