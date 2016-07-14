package com.banche;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.dijing.server.Constants;
import com.dijing.server.Initialization;
import com.dijing.server.dao.Mysql;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2015年11月24日下午5:57:42
 */
public class InitializationImp implements Initialization {

	@Override
	public void doInitialization() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInitialiseForWeb(String path) throws Exception {
		System.out.println("path: "+path);
		Properties p = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(path+File.separator+Constants.DATABASE_FILE));
		p.load(in);
		Mysql mysql = Mysql.getInstance();
		mysql.setUser(p.getProperty("dbUser"));
		mysql.setPassword(p.getProperty("dbPassword"));
		mysql.setDriver(p.getProperty("dbDriver"));
		mysql.setURL(p.getProperty("dbURL"));
		mysql.setCode(p.getProperty("dbCode"));
		Constants.DATABASE_NAME = p.getProperty("dbName");
		System.out.println("driver: "+p.getProperty("dbDriver"));
		System.out.println("URL: "+p.getProperty("dbURL"));
		
		in.close();
	}

}
