package com.stcs.kb.testing.testCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.killbill.billing.client.KillBillClientException;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.stcs.kb.config.Constants;
import com.stcs.kb.service.impl.BillingServiceKBImpl;
import com.stcs.kb.testing.component.AbstractComponent;
import com.stcs.kb.testing.component.Component;
import com.stcs.kb.testing.component.impl.KillBillDBComponent;
import com.stcs.kb.testing.component.impl.KillBillServer;
import com.stcs.kb.testing.component.impl.RabbitComponent;

import junit.framework.TestCase;

public class IntegrationTestTemplate extends TestCase {
	
	String enviromentNetwork = "mynetwork" ;
	
	List<Component> testEnviroment ; 
	
	public IntegrationTestTemplate() {
		Component rabbit = new RabbitComponent() ;
		
		Component db     = new KillBillDBComponent() ;
		db.getDepndantComponent().add(new KillBillServer()) ; 
		
		testEnviroment  =new ArrayList<Component>(); 
		testEnviroment.add(rabbit) ;
		testEnviroment.add(db) ;
	}
	
	public boolean startEnviroment() throws Exception {
		
		testEnviroment.parallelStream().forEach(comp -> {
           tryStartcomponent(comp);
		});

		for(Component comp : testEnviroment ) {  
			if (!comp.isComponentUpAndRunning()) 
				return false;
		}
	   return true;
	}
	
	private void tryStartcomponent(Component comp) {
		 System.out.println("try to start : " + comp.getName() ); 
         comp.start() ;
         try {
         	
         	Awaitility .await()
         	  .atMost( 1 , TimeUnit.MINUTES)
         	.with()
         	  .pollInterval(3 , TimeUnit.SECONDS)
         	  .until(comp::isComponentUpAndRunning);
         	
				System.out.println( comp.getName() + " has started at : " + new Date());
				
				comp.getDepndantComponent().parallelStream().forEach(dedpendant-> tryStartcomponent(dedpendant) );
          } catch (Exception e) {
				System.out.println( comp.getName() + " cant be  started as " + e.getMessage());
			}
		
	}

	public void shutdownEnviroment() {
		System.out.println("shutting down enviroment");

		testEnviroment
		   .parallelStream()
		   .filter(comp-> comp.isComponentUpAndRunning())
		   .forEach(comp-> { 
	            System.out.println("try to shutdown : " + comp.getName() ); 
 			    comp.stop() ;
		   });
	}
	
	public static void main(String...args) throws KillBillClientException {
//		DBI	 dbi = new DBI("jdbc:postgresql://localhost:5432/killbill_plugins_db", "killbill" , "killbill" ) ;
//		Handle handle = dbi.open() ;
		
		  BillingServiceKBImpl cartwheelKB    = 
					 new BillingServiceKBImpl(Constants.KB_CARTWHEEL_KEY, Constants.KB_CARTWHEEL_SECRET , 1000) ;
		  cartwheelKB.cleanCache();
		  System.out.println( cartwheelKB.getEffectiveDate() ); 
	}
	
	
}
