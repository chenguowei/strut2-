package com.dijing.server.msg;

import java.util.Map;

/**
 * 用户信息与用户消息哈希表
 * @author winter
 * @date   2015-3-6上午9:13:19
 */
public class UserMessageMap {

	/**
	 * key为用户id，value为该用户的消息箱（队列）
	 */
	public static Map<Integer, MessageBox> msg = 
			new java.util.concurrent.ConcurrentHashMap<Integer, MessageBox>();
}
