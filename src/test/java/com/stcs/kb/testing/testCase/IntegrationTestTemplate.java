package com.stcs.kb.testing.testCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import org.killbill.billing.client.KillBillClientException;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.testing.testEnviroment.Component;
import com.stcs.kb.testing.testEnviroment.Enviroment;
import com.stcs.kb.testing.testEnviroment.impl.BillingServiceComponent;
import com.stcs.kb.testing.testEnviroment.impl.EnviromentImpl;
import com.stcs.kb.testing.testEnviroment.impl.KillBillComponent;
import com.stcs.kb.testing.testEnviroment.impl.KillBillDBComponent;
import com.stcs.kb.testing.testEnviroment.impl.RabbitComponent;

import junit.framework.TestCase;

public class IntegrationTestTemplate extends TestCase {

	protected Enviroment killbillEnviroment  ;

	public IntegrationTestTemplate() {
		killbillEnviroment = new EnviromentImpl() ;
	}
	
}
