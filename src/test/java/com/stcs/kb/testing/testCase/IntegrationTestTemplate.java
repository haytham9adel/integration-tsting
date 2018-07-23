package com.stcs.kb.testing.testCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import org.killbill.billing.client.KillBillClientException;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.BillingServiceKBImpl;
import com.stcs.kb.testing.component.Component;
import com.stcs.kb.testing.component.impl.BillingServiceComponent;
import com.stcs.kb.testing.component.impl.KillBillComponent;
import com.stcs.kb.testing.component.impl.KillBillDBComponent;
import com.stcs.kb.testing.component.impl.RabbitComponent;

import junit.framework.TestCase;

public class IntegrationTestTemplate extends TestCase {

	String enviromentNetwork = "mynetwork";

	List<Component> testEnviroment;

	public IntegrationTestTemplate() {

		Component billingService =new BillingServiceComponent(); 
		Component killBill = new KillBillComponent();
		killBill.getDepndantComponent().add(new KillBillDBComponent());
		killBill.getDepndantComponent().add(new RabbitComponent());
		billingService.getDepndantComponent().add(killBill);
		testEnviroment = new ArrayList<Component>();
		testEnviroment.add(billingService);
	}

	public boolean startEnviroment() throws Exception {

		testEnviroment.parallelStream().forEach(comp -> {
			StartComponentAndItsDependants(comp);
		});

		for (Component comp : testEnviroment) {
			if (!comp.isComponentUpAndRunning())
				return false;
		}
		return true;
	}

	private void StartComponentAndItsDependants(Component comp) {
		System.out.println("try to start : " + comp.getName());
		try {
			
			comp.getDepndantComponent().parallelStream().forEach(dedpendant -> StartComponentAndItsDependants(dedpendant));
			comp.start();

			await().atMost(1, TimeUnit.MINUTES).with().pollInterval(3, TimeUnit.SECONDS)
					.until(comp::isComponentUpAndRunning);

			System.out.println(comp.getName() + " has started at : " + new Date());

		} catch (Exception e) {
			System.out.println(comp.getName() + " cant be  started as " + e.getMessage());
		}

	}

	public void shutdownEnviroment() {
		System.out.println("shutting down enviroment");

		testEnviroment.parallelStream().filter(comp -> comp.isComponentUpAndRunning()).forEach(comp -> {
			System.out.println("try to shutdown : " + comp.getName());
			comp.stop();
		});
	}

	public static void main(String... args) throws KillBillClientException {
		// DBI dbi = new DBI("jdbc:postgresql://localhost:5432/killbill_plugins_db",
		// "killbill" , "killbill" ) ;
		// Handle handle = dbi.open() ;

		BillingServiceKBImpl cartwheelKB = new BillingServiceKBImpl(Constants.KB_CARTWHEEL_KEY,
				Constants.KB_CARTWHEEL_SECRET, 1000);
		cartwheelKB.cleanCache();
		System.out.println(cartwheelKB.getEffectiveDate());
	}

}
