package com.banche.receiver;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dijing.server.Communication;

/**
 * 
 * @author winter , 361482732@qq.com
 * @Date: 2016年2月2日下午1:48:06
 */
public class ReceiverListener implements ServletContextListener {

	static Logger logger = LogManager.getLogger(ReceiverListener.class.getName());
	
	private Thread listener;
	private boolean shutDown = false;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		shutDown = true;
		InetAddress addr;
		try {
			addr = InetAddress.getByName("127.0.0.1");
			Socket socket = new Socket(addr, com.banche.Constants.SERVER_PORT);			
			Communication comm = new Communication();
			comm.send(socket, "");
			socket.close();
			logger.info("shutdown socket");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		listener.interrupt();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		listener = new Thread(new Runnable(){

			@Override
			public void run() {
				ServerSocket s;
				try {
					s = new ServerSocket(com.banche.Constants.SERVER_PORT);
					s.setReuseAddress(true);
					logger.info("****** The Banche receiver has started!(v1.0) ******");
					logger.info(s);
					
					while(true){
						try{
							Socket socket = s.accept();
							if(shutDown){
								break;
							}
							// 读数据
//							WXReadData rd = new WXReadData(socket);
//							rd.start();
//							ThreadPool.executor.execute(rd);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					ThreadPool.pool.shutdown();
					ThreadPool.executor.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				logger.info("****** The Banche receiver has stop! ******");
			}});
		listener.start();
		
	}

}
