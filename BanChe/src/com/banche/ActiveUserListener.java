package com.banche;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ActiveUserListener implements HttpSessionListener {

	static Logger logger = LogManager.getLogger(ActiveUserListener.class
			.getName());
	
	private static Map<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		if(session.getAttribute(Constants.USER_INFO) != null){
			String sessionId = session.getId();
			logger.debug("create session: " + sessionId);
			sessionMap.put(sessionId, session);
		}		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		String sessionId = arg0.getSession().getId();
		sessionMap.remove(sessionId);
		logger.debug("destroy session: " + sessionId);
	}

	public static Map<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	public static void setSessionMap(Map<String, HttpSession> sessionMap) {
		ActiveUserListener.sessionMap = sessionMap;
	}

}
