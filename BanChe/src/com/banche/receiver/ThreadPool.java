package com.banche.receiver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * socket连接线程池
 * @author winter
 * @date   2015-11-17上午9:08:22
 */
public class ThreadPool implements RejectedExecutionHandler {

	static Logger logger = LogManager.getLogger(ThreadPool.class.getName());
	
	/**
	 * 
	 */
	public static ExecutorService pool = Executors.newFixedThreadPool(2);
	
	/**
	 * 线程池的队列长度。
	 */
	public final static int queueLength = 300;
	public static BlockingQueue<Runnable> queue = 
			new ArrayBlockingQueue<Runnable>(queueLength); 
	/**
	 * 连接的线程池。
	 */
	public static ThreadPoolExecutor executor = 
			new ThreadPoolExecutor(queueLength, queueLength, 1, TimeUnit.MINUTES, queue, 
					new ThreadPool());//new ThreadPoolExecutor.CallerRunsPolicy()
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.error("rejectedExecution");
	} 
}
