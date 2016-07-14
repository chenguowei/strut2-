package com.dijing.server;

import com.banche.InitializationImp;

public class InitializationFactory {

	public static Initialization getInstance(){
		
		return new InitializationImp();
	}
}
