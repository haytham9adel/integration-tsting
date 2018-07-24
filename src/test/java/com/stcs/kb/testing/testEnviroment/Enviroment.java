package com.stcs.kb.testing.testEnviroment;

public interface Enviroment {

	void initTheEnviroment()  ;
	
	boolean startEnviroment() throws Exception;

	void shutdownEnviroment();

	
}