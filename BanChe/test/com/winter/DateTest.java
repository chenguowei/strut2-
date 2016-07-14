package com.winter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.impl.cookie.DateUtils;
import org.json.JSONException;

import com.banche.dao.AccountDao;
import com.banche.model.Account;
import com.dijing.server.web.utils.JsonUtils;

public class DateTest {

	public static void main(String[] args) throws JSONException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub

		Date d = new Date(System.currentTimeMillis());
		System.out.println(DateUtils.formatDate(d, "yyyy-MM-dd HH:mm:ss"));
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sd.format(d));
		
		String type = d.getClass().getTypeName();
		System.out.println(type);
		
	}
}
