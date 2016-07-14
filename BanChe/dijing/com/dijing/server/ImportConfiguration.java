package com.dijing.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.dijing.server.dao.Mysql;
import com.dijing.server.web.utils.email.EmailConstants;

public class ImportConfiguration {

	public void doImport(String path) throws IOException{
		
//		InputStream in = 
//				new BufferedInputStream(new FileInputStream(path+File.separator+"configuration.properties"));
		
		Properties p = new Properties();
//		p.load(in);
//		Constants.ACCESSORY_PATH = p.getProperty("accessoryPath");
//		Constants.TEMPFILE_PATH = p.getProperty("newUserInfo");
//		String logPath = p.getProperty("logPath");
//		in.close();
//		
//		File file = new File(logPath);
//		if(!file.exists()){
//			file.mkdirs();
//		}
		
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
		
		/**
		 * 导入email信息
		 */
		in = new BufferedInputStream(new FileInputStream(path+File.separator+"mail.properties"));
		p.load(in);
		EmailConstants.MailServerHost = p.getProperty("MailServerHost");
		EmailConstants.MailServerPort = Integer.parseInt(p.getProperty("MailServerPort"));
		EmailConstants.UserName = p.getProperty("UserName");
		EmailConstants.Password = p.getProperty("Password");
		System.out.println("MailServerHost: "+EmailConstants.MailServerHost);
		System.out.println("MailServerPort: "+EmailConstants.MailServerPort);
		System.out.println("UserName: "+EmailConstants.UserName);
		System.out.println("Password: "+EmailConstants.Password);
		in.close();
		
		
		
//		/**
//		 * 写入测试数据
//		 */
//		OfficeBuilding ob = new OfficeBuilding();
//		ob.setId(1);
//		ob.setAddress("高新区总部路3号");
//		ob.setIntroduction("大学生创业基地2年免费入住");
//		ob.setName("大学生创业基地");
//		ob.setPic("test1.jpg");
//		TestData.officeBuilding = new ArrayList<OfficeBuilding>();
//		TestData.officeBuilding.add(ob);
//		
//		Merchants m = new Merchants();
//		m.setId(1);
//		m.setIntroduction("国家级创业基地");
//		m.setName("大学生创业基地");
//		m.setPic("3dfe7daf-2859-4b63-b193-e94dfc8df6a9.jpg");
//		m.setTel("13788888888");
//		TestData.merchants = new ArrayList<Merchants>();
//		TestData.merchants.add(m);
//		m = new Merchants();
//		m.setId(2);
//		m.setIntroduction("概念型写字楼");
//		m.setName("未来科技");
//		m.setPic("test2.png");
//		m.setTel("13988888888");
//		TestData.merchants.add(m);
//		
//		Dishes dishes = new Dishes();
//		dishes.setId(1);
//		dishes.setMerchantsId(1);
//		dishes.setIntroduction("测试测试");
//		dishes.setName("菜心");
//		dishes.setPic("dishes1.png");
//		dishes.setPrice("3.5");
//		dishes.setType("素菜");
//		TestData.dishes = new ArrayList<Dishes>();
//		TestData.dishes.add(dishes);
//		dishes = new Dishes();
//		dishes.setId(2);
//		dishes.setMerchantsId(1);
//		dishes.setIntroduction("测试测试");
//		dishes.setName("扣肉");
//		dishes.setPic("dishes2.jpg");
//		dishes.setPrice("10");
//		dishes.setType("荤菜");
//		TestData.dishes.add(dishes);
//		dishes = new Dishes();
//		dishes.setId(3);
//		dishes.setMerchantsId(1);
//		dishes.setIntroduction("测试测试");
//		dishes.setName("炒面");
//		dishes.setPic("dishes3.jpg");
//		dishes.setPrice("7");
//		dishes.setType("主食");
//		TestData.dishes.add(dishes);
//		
//		Order order = new Order();
//		order.setAddress("9栋517");
//		order.setContact("地精");
//		order.setDeliverTimeE("2015-05-05 12:00:00");
//		order.setDeliverTimeS("2015-05-05 11:30:00");
//		order.setDishes("[{\"id\":1,\"number\":2},{\"id\":2,\"number\":1}]");
//		order.setId(1);
//		order.setMerchantsId(1);
//		order.setOfficeBuilding(1);
//		order.setTime(""+System.currentTimeMillis());
//		order.setTel("13599999999");
//		order.setUserId(1);
//		TestData.order = new ArrayList<Order>();
//		TestData.order.add(order);
//		
//		User user = new User();
//		user.setAccount("dijing");
//		user.setEmail("w.winter323@163.com");
//		user.setFloor("5楼517");
//		user.setId(1);
//		user.setName("地精科技");
//		user.setOfficeBuildingId(1);
//		user.setTel("15240666681");
//		TestData.user = new ArrayList<User>();
//		TestData.user.add(user);
//		
//		Evaluation evaluation = new Evaluation();
//		evaluation.setComment("xxxxx菜很好啊。");
//		evaluation.setId(1);
//		evaluation.setLevel(5);
//		evaluation.setMerchantsId(1);
//		evaluation.setTime(""+System.currentTimeMillis());
//		evaluation.setUserId(1);
//		TestData.evaluation = new ArrayList<Evaluation>();
//		TestData.evaluation.add(evaluation);
//		evaluation = new Evaluation();
//		evaluation.setComment("送餐很快。");
//		evaluation.setId(2);
//		evaluation.setLevel(5);
//		evaluation.setMerchantsId(1);
//		evaluation.setTime(""+System.currentTimeMillis());
//		evaluation.setUserId(1);
//		TestData.evaluation.add(evaluation);
	}
}
