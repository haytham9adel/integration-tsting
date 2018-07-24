package com.stcs.kb.testing.testEnviroment;

import java.util.List;

public interface Component {

	String getName() ;
	
	boolean start();

	boolean stop();

	boolean isComponentUpAndRunning();

	List<Component> getDepndantComponents();
}