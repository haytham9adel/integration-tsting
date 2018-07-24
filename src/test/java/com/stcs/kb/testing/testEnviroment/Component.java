package com.stcs.kb.testing.testEnviroment;

import java.util.List;


/**
 * 
 * An interface defines the methods should be found in any component to be used in integration testing
 * 
 * @author Haytham.Adel
 */

public interface Component {

	String getName() ;
	
	boolean start();

	boolean stop();

	boolean isUpAndRunning();
	
	Component addDepndantComponent(Component depndant);

	List<Component> getDepndantComponents();
}