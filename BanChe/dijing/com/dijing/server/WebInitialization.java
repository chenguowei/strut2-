package com.dijing.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebInitialization implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("---------- destroy -----------");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("---------- init -----------");
//		WorkFlowConfig wfc = new WorkFlowConfig();
//		wfc.init();
		
//		ImportConfiguration ic = new ImportConfiguration();
		try {
			Initialization init = InitializationFactory.getInstance();
			if(init!=null){
				String path = arg0.getServletContext().getRealPath("/") + File.separator + "WEB-INF" +
						File.separator + "classes";
				init.doInitialiseForWeb(path);
			}
//			String path = arg0.getServletContext().getRealPath("/") + File.separator + "WEB-INF" +
//							File.separator + "classes";
//			ic.doImport(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
