package com.stcs.kb.migration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.killbill.billing.client.KillBillClient;
import org.killbill.billing.client.KillBillHttpClient;
import org.killbill.billing.client.RequestOptions;

import junit.framework.TestCase;


//TODO Haytham will move it to another project
public class PriceChangeTest extends TestCase {

	  KillBillHttpClient killBillHttpClient ;
	  KillBillClient killBillClient ;
	  String admin   = "admin";
	  String reason  = "testing";
	  String comment = "PriceChangeTest";
	  RequestOptions  inputOptions ;
	  ClassLoader classLoader = getClass().getClassLoader();
	  
	  
	  UUID accountId =  UUID.fromString("62904ef2-3cb4-43d5-a228-7cb9d163242a")   ;
	  String subscriptionId =  "1c26e441-40c0-4eb4-9bab-0ddacd661ab7";
	  String sub2 ="e7c959ca-fc00-401a-ae0c-d64b0dddf20c" ;
	  String sub3 ="4672c2ef-1c8b-486f-a4a3-83643aab75ba" ;
	  String usageSub = "8acc63de-a6cb-4daf-808b-e034c3f40aea" ;
	  
	  String planName = "ipvpn-colocation-530-plan-1-monthly-p-ic0ujjjm0in6mexsuscumzlzhx2rzmhd-p" ;
	  String planName2 = "vdc-ipvpn-555-ipvpn-monthly-p-ic0ujjjm0in6mexsuscumzlzhx2rzmhd-p" ; 
	  String planName3 = "dia-colocation-768-plan-1-monthly-p-ic0ujjjm0in6mexsuscumzlzhx2rzmhd-p" ; 
	  String planName4 = "virtual-data-center-52_plan_customer-15615-price_list-13_p";
		  
	@Before
	public void setUp() throws IOException {
		
	    FileInputStream inputStream = new FileInputStream(classLoader.getResource("test.properties").getPath());
	    Properties prop = new Properties();
	    prop.load(inputStream);
	    
		killBillHttpClient = new KillBillHttpClient(prop.getProperty("kb-server"),prop.getProperty("kb-username"),
				                  prop.getProperty("kb-password"), prop.getProperty("kb-api-key"), prop.getProperty("kb-api-secret")
				                ,  null,
	                              null,
	                              6000000,
	                              6000000,
	                              6000000,
	                              false,
	                              null );
 		killBillClient  = new KillBillClient(killBillHttpClient);
		inputOptions = new RequestOptions.RequestOptionsBuilder()
				.withCreatedBy(admin)
				.withReason(reason)
				.withComment(comment)
				.build();
	}
	
	@Test
	public void testConnected() throws Exception {
			    	
		 FileInputStream inputStream = new FileInputStream(classLoader.getResource("catalog.xml").getPath());
		     
	     killBillClient.uploadXMLCatalog(inputStream, inputOptions);
	     
	   //  Utils.waitAsyncRemoter();
	     
	 //    Utils.updateSubscription(usageSub, planName4 ,killBillHttpClient, inputOptions);

	  //   Utils.updateSubscription(subscriptionId, planName ,killBillHttpClient, inputOptions);
	  //   Utils.updateSubscription(sub2, planName2 ,killBillHttpClient, inputOptions);
	  //   Utils.updateSubscription(sub3, planName3 ,killBillHttpClient, inputOptions);

//	     Utils.postTime("2018-10-30", killBillHttpClient, inputOptions);
//	     
//	     Invoices invs =  killBillClient.getInvoicesForAccount(accountId ,inputOptions ) ;
//	     
//	      System.out.println("last invoice :" + invs.get( invs.size()-1 ).getAmount().longValue() );
//	     
//	     assertTrue("new invoice with new price ",  invs.get(invs.size()-1).getAmount().longValue() == 1800 ) ;
	     System.out.println("last invoice");
	}

}
