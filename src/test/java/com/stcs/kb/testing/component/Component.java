package com.stcs.kb.testing.component;

import java.util.List;

public interface Component {

	 String getName() ;
	
	boolean start();

	boolean stop();

	boolean isComponentUpAndRunning();

	List<Component> getDepndantComponent();
}