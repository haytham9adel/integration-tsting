package com.stcs.kb.service.impl;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.killbill.billing.client.KillBillClient;
import org.killbill.billing.client.KillBillClientException;
import org.killbill.billing.client.KillBillHttpClient;
import org.killbill.billing.client.RequestOptions;
import org.killbill.billing.client.model.Account;
import org.killbill.billing.client.model.Invoice;
import org.killbill.billing.client.model.InvoiceDryRun;
import org.killbill.billing.client.model.Invoices;
import org.killbill.billing.invoice.api.DryRunType;

import com.ning.http.client.Response;
import com.stcs.kb.config.Constants;

public class BillingServiceKBImpl {
	
	KillBillHttpClient killBillHttpClient ;
    KillBillClient killBillClient ;

	static RequestOptions  inputOptions = new RequestOptions.RequestOptionsBuilder()
																.withCreatedBy("admin")
																.withReason("testing")
																.withComment("we need to do testing")
																.build();	
	
	
	public BillingServiceKBImpl( String tenantKey , String tenantSecret  ) {
		 killBillHttpClient = new KillBillHttpClient ( Constants.KB_URL, Constants.KB_USERNAME , Constants.KB_PASSWORD , tenantKey ,tenantSecret
				 ,  null,
                 null,
                 6000000,
                 6000000,
                 6000000,
                 false,
                 null );
		 killBillClient   = new KillBillClient(killBillHttpClient);
	}
	
	public DateTime getEffectiveDate() throws KillBillClientException {
		List<DateTime> catalogs = 	killBillClient.getCatalogVersions(inputOptions) ;
		catalogs.forEach(System.out::println);
		return catalogs.get(0) ;
	}
	
	public double nextInvoice (String AccountId) throws RuntimeException {
		final InvoiceDryRun dryRunArg = new InvoiceDryRun(DryRunType.TARGET_DATE
				                       , null, null, null, null, null, null, null, null, null, null, null);
		
		Invoice inv = null;
		try {
			inv = killBillClient.createDryRunInvoice(UUID.fromString(AccountId), LocalDate.now().plusMonths(1) , dryRunArg,inputOptions );
		} catch (KillBillClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		return (inv!= null)?  inv.getBalance().doubleValue():0 ;	
	}
	 
	public Account getAccount(String  id) {
		Account acc ; 
		try {
			acc= killBillClient.getAccount(UUID.fromString(id), inputOptions) ;
		} catch (KillBillClientException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return acc;
	}
	public double lastInvoice  (String AccountId) throws KillBillClientException  {
	     Invoices invs =  killBillClient.getInvoicesForAccount( UUID.fromString(AccountId) ,inputOptions ) ;
	     return invs.get( invs.size()-1 ).getAmount().doubleValue() ;
	}

	public void postTime(String requestedDate   ) throws Exception {
	    Response response = killBillHttpClient
	    		.doPost("/1.0/kb/test/clock?requestedDate=" + requestedDate + "&timeoutSec=5", String.class, inputOptions);

	    if (response.getStatusCode() != 200){
	        throw new Exception("Couldn't alter time on remote server");
	    }
	}
	
	
	public void cleanCache() {
	   try {
		killBillHttpClient.doDelete( "/1.0/kb/admin/cache", inputOptions);
	} catch (KillBillClientException e) {
		e.printStackTrace();
		throw new RuntimeException();
	}

	}
	public  void updateSubscription(String subId , String planName) throws Exception {
	    Response response = killBillHttpClient
	    		 .doPut("/1.0/kb/subscriptions/" + subId  , "{\"planName\":\""+planName+"\" }", Response.class, inputOptions);

	    if (response.getStatusCode() != 200){
	        throw new Exception("Couldn't UPDATE catalog");
	    }
	}
	
	
}
