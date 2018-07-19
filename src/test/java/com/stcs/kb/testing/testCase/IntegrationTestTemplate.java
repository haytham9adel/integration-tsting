package com.stcs.kb.testing.testCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;

import com.stcs.kb.testing.component.AbstractComponent;
import com.stcs.kb.testing.component.Component;
import com.stcs.kb.testing.component.impl.KillBillDBComponent;
import com.stcs.kb.testing.component.impl.RabbitComponent;

import junit.framework.TestCase;

public class IntegrationTestTemplate extends TestCase {
	List<AbstractComponent> testEnviroment ; 
	
	public IntegrationTestTemplate() {
		AbstractComponent rabbit = new RabbitComponent() ;
		AbstractComponent db     = new KillBillDBComponent() ;
		
		testEnviroment  =new ArrayList<AbstractComponent>(); 
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
	
	
	
       	
}
