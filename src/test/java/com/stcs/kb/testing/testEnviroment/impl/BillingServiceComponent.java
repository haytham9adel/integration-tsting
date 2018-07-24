package com.stcs.kb.testing.testEnviroment.impl;

import com.stcs.kb.service.impl.BillingServicesApiClient;
import com.stcs.kb.testing.testEnviroment.AbstractDockerComponent;

public class BillingServiceComponent extends AbstractDockerComponent{

	public BillingServiceComponent(String netwrokName) {
		super(netwrokName);
		// TODO Auto-generated constructor stub
		name= "billingsrvnode" ;
		port = 8070 ;
		dockerImage = "bill_srv" ;
	}

	@Override
	public boolean isUpAndRunning() {
		return BillingServicesApiClient.isConnected();
	}
	
	
}
