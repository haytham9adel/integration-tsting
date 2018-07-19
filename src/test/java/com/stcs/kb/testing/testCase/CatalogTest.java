package com.stcs.kb.testing.testCase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stcs.kb.config.Constants;
import com.stcs.kb.model.Response;
import com.stcs.kb.service.impl.BillingServiceKBImpl;
import com.stcs.kb.service.impl.BrockerServiceRabbitImpl;
import com.stcs.kb.testing.Utils;

import junit.framework.TestCase;


public class CatalogTest extends IntegrationTestTemplate {
	
	 BrockerServiceRabbitImpl rabbitMQClient;
	 BillingServiceKBImpl cartwheelKB  ; 
	 
	@Before
	public void setUp() throws IOException {
		rabbitMQClient = new BrockerServiceRabbitImpl();
	    cartwheelKB    = new BillingServiceKBImpl(Constants.KB_CARTWHEEL_KEY, Constants.KB_CARTWHEEL_SECRET) ;
	}
	
	@Test
	public void testUploadCatalog() throws Exception {
		
			boolean isStart = false; 
			  try {
				 isStart = startEnviroment() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
			 //todo  
			if(isStart)  {
				System.out.println("successed to start the envioment" ); 
	
//		     File   catalogJsonFile = new File(getClass().getClassLoader().getResource("/catalog.json").getFile());
//			 String catalogJsonString = Utils.readFile(catalogJsonFile.getCanonicalPath(), Charset.forName("utf8")) ;
//			 String dateStrInJson =  Utils.getDateFromCatalogJson(catalogJsonString, "effective_date") ; //TODO REMOVE
//			 System.out.println(" STAGE 0 : input [0] : " + catalogJsonString +" with date : " + dateStrInJson);
//
//			 //start 
//			 Response responseFromRabbit =  rabbitMQClient.sendCatalogUpdate(catalogJsonString) ;
//			 
//			 //stage 1
//			 System.out.println(" STAGE 1 : sending to rabbit MQ");
//			 System.out.println(" RESULT >> " + responseFromRabbit.getMsg());
//			 assertTrue(responseFromRabbit.isOk());
//			 
//			 //stage 2
//			 System.out.println(" STAGE 2 : updating plugins tables");
//			 
//			 
//			 //stage 3
//			 System.out.println(" STAGE 3 : check the KB API");
//			 DateTime timestamp =  cartwheelKB.getEffectiveDate() ;
//			 System.out.println(" RESULT >> latest updated date " + timestamp +" while in json :"+dateStrInJson);
//			 
			}else {
				System.out.println("failed to start the envioment"); 
			}
			
			shutdownEnviroment();


	}
	
	
}
