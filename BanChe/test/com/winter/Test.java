package com.winter;

import org.apache.commons.lang.StringUtils;

import com.banche.service.PushService;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String str = "1";
		System.out.println(StringUtils.isEmpty(str));
		
		long id = 1000000 + System.currentTimeMillis() % 100000;
		System.out.println("id: " + id);
		
		PushService ps = new PushService();
//		ps.pushSMS("08615240666681", String.format("【板车物流】%s为您的临时密码。如非本人操作，请忽略。", "123456"));
		ps.resetPassword("15240666681");
	}

}
