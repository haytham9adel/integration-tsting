package com.stcs.kb.testing.testEnviroment.impl;

import static org.awaitility.Awaitility.await;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.killbill.billing.client.KillBillClientException;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.testing.testEnviroment.Component;
import com.stcs.kb.testing.testEnviroment.Enviroment;

public class EnviromentImpl implements Enviroment {
	
	private String enviromentNetwork = "mynetwork";

	private List<Component> testEnviroment;

	public EnviromentImpl() {
		testEnviroment = new ArrayList<Component>();
		initTheEnviroment() ;
	}
	
	public void initTheEnviroment() {
		Component billingService =new BillingServiceComponent(enviromentNetwork); 
		Component killBill = new KillBillComponent(enviromentNetwork);
		
		killBill.addDepndantComponent(new KillBillDBComponent(enviromentNetwork))
				.addDepndantComponent(new RabbitComponent(enviromentNetwork));
		
		billingService.addDepndantComponent(killBill);
		
		testEnviroment.add(billingService);
	}
	
	@Override
	public boolean startEnviroment() throws Exception {

		testEnviroment.parallelStream().forEach(comp -> {
			StartComponentAndItsDependants(comp);
		});

		for (Component comp : testEnviroment) {
			if (!comp.isUpAndRunning())
				return false;
		}
		return true;
	}

	private void StartComponentAndItsDependants(Component comp) {
		System.out.println("try to start : " + comp.getName());
		try {
			
			comp.getDepndantComponents().parallelStream().forEach(dedpendant -> StartComponentAndItsDependants(dedpendant));
			comp.start();

			await().atMost(1, TimeUnit.MINUTES).with().pollInterval( 10  , TimeUnit.SECONDS)
					.until(comp::isUpAndRunning);

			System.out.println(comp.getName() + " has started at : " + new Date());

		} catch (Exception e) {
			System.out.println(comp.getName() + " cant be  started as " + e.getMessage());
		}

	}


	@Override
	public void shutdownEnviroment() {
		System.out.println("shutting down enviroment");
		
		testEnviroment.parallelStream()
		.forEach(comp -> {
			closeComponentAndItsDependants(comp);
		});
	}
	
	public void closeComponentAndItsDependants(Component comp) {
		System.out.println("try to shutdown : " + comp.getName());
		
		comp.stop();
		comp.getDepndantComponents().parallelStream()
		.forEach(dependant -> {
			closeComponentAndItsDependants(dependant);
		});
	}

	public static void main(String... args) throws KillBillClientException {
		// DBI dbi = new DBI("jdbc:postgresql://localhost:5432/killbill_plugins_db",
		// "killbill" , "killbill" ) ;
		// Handle handle = dbi.open() ;

		KBBillingImpl cartwheelKB = new KBBillingImpl(Constants.KB_CARTWHEEL_KEY,
				Constants.KB_CARTWHEEL_SECRET, 1000);
		cartwheelKB.cleanCache();
		System.out.println(cartwheelKB.getEffectiveDate());
	}
}
