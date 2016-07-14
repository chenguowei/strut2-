package com.dijing.server;

public interface Initialization {

	public void doInitialization() throws Exception;
	
	public void doInitialiseForWeb(String path) throws Exception;
}
