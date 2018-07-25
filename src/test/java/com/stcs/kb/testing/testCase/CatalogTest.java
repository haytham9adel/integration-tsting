package com.stcs.kb.testing.testCase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import com.stcs.kb.config.Constants;
import com.stcs.kb.model.Response;
import com.stcs.kb.service.impl.KBBillingImpl;
import com.stcs.kb.service.impl.BrockerServiceRabbitImpl;
import com.stcs.kb.testing.Utils;
import com.stcs.kb.util.ConcuencyUtil;

import junit.framework.TestCase;


public class CatalogTest extends IntegrationTestTemplate {
	
	 BrockerServiceRabbitImpl rabbitMQClient;
	 KBBillingImpl cartwheelKB  ; 
	 
	@Before
	public void setUp() throws IOException {
		rabbitMQClient = new BrockerServiceRabbitImpl();
	    cartwheelKB    = new KBBillingImpl(Constants.KB_CARTWHEEL_KEY, Constants.KB_CARTWHEEL_SECRET) ;
	}
	
	@Test
	public void testUploadCatalog() throws Exception {
		
			boolean isStart = false; 
			  try {
				 isStart = killbillEnviroment.startEnviroment() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	     try {		  
			 //todo  
			if(isStart)  {
				System.out.println("successed to start the envioment" ); 
	
		     File   catalogJsonFile = new File(getClass().getClassLoader().getResource("catalog.json").getFile());
			 String catalogJsonString = Utils.readFile(catalogJsonFile.getCanonicalPath(), Charset.forName("utf8")) ;
			 
			 //start 
			 Response responseFromRabbit =  rabbitMQClient.sendCatalogUpdate(catalogJsonString) ;
			 
			 //stage 1
			 System.out.println(" STAGE 1 : sending to rabbit MQ");
			 System.out.println(" RESULT >> " + responseFromRabbit.getMsg());
			 assertTrue(responseFromRabbit.isOk());
			 
			 //stage 2
			 System.out.println(" STAGE 2 : updating plugins tables");
			 DBI dbi = new DBI(Constants.DB_PLUGIN_URL, Constants.DB_PLUGIN_USERNAME, Constants.DB_PLUGIN_PASSWORD);
			 Handle handle = dbi.open();
			 Query<Map<String, Object>> data = handle.createQuery("SELECT COUNT(*) AS CNT FROM cartwheel_plans") ;
			 System.out.println(" count of added plans >>"+ data.first().get("CNT"));
			 assertTrue(Integer.parseInt( data.first().get("CNT").toString() )  == 862 ); 
			 
			 //stage 3
			 System.out.println(" STAGE 3 : check the KB API");
			 ConcuencyUtil.pause(10);
			 cartwheelKB.cleanCache();
			 int uploadedPlans =  cartwheelKB.getCatalogPlansCount();
			 System.out.println(" RESULT >> latest Catalog Plans Count " + uploadedPlans );
			 
			 assertEquals(706, uploadedPlans);
			 
			}else {
				System.out.println("failed to start the envioment"); 
			}

	     }catch(Exception e) {
	       System.err.println("ERROR IN RUUNING TEST CASE : " + e.getMessage() );
	       throw e;
	     }finally {	
			killbillEnviroment.shutdownEnviroment();
	     }

	}
	
	
}
