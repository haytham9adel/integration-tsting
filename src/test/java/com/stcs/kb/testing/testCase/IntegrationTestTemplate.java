package com.stcs.kb.testing.testCase;

import com.stcs.kb.testing.testEnviroment.Enviroment;
import com.stcs.kb.testing.testEnviroment.impl.EnviromentImpl;

import junit.framework.TestCase;

public class IntegrationTestTemplate extends TestCase {

	protected Enviroment killbillEnviroment  ;

	public IntegrationTestTemplate() {
		killbillEnviroment = new EnviromentImpl() ;
	}
	
}
