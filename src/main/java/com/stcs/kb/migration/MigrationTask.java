package com.stcs.kb.migration;

import java.math.BigDecimal;
import java.util.List;
	
import com.stcs.kb.config.Constants;
import com.stcs.kb.dao.Account;
import com.stcs.kb.service.impl.BillingServiceKBImpl;
import com.stcs.kb.service.impl.DBServiceKillBillImpl;
import com.stcs.kb.util.ConcuencyUtil;

public class MigrationTask {
        
	    static long bluevaultTenantId = 34 ;          
	    public static void main(String[] args) throws InterruptedException  {
	    	DBServiceKillBillImpl db = new DBServiceKillBillImpl() ;
	    	BillingServiceKBImpl cartwheelKB = new BillingServiceKBImpl(Constants.KB_CARTWHEEL_KEY, Constants.KB_CARTWHEEL_SECRET) ;
	    	
	    	 List<Account> blusvultAccounts = db.getBluevaultAccounts() ;
	    	 System.out.println("blusvultAccounts size > " + blusvultAccounts.size() );
	    	 blusvultAccounts.forEach(acc->{
	    		 double nxtInvoice = cartwheelKB.nextInvoice(acc.getId()) ;
	    		 acc.setNextInvoice(nxtInvoice);
	    		 System.out.println("cartwhell account > " + acc.getAccountId() + " nxt invoice > " + nxtInvoice );
	    	 });
	    	 
	    	 System.out.println("STEP 2 : WE WILL UPDATE DB");
	    	 db.updateToBluevaultTeanat(blusvultAccounts , bluevaultTenantId); 
	  
	    	 System.out.println("STEP 3 : WE WILL WAIT & CLEAN cartwheel & bluevault CACHE ");
	    	 ConcuencyUtil.pause(60);
         	 cartwheelKB.cleanCache(); 
	    	 BillingServiceKBImpl bluevaultKB = new BillingServiceKBImpl(Constants.KB_BLUVAULT_KEY, Constants.KB_BLUVAULT_SECRET) ;
	    	 bluevaultKB.cleanCache();
	    	 
	    	 System.out.println("STEP 4 : WE WILL TEST BY COPMARE OLD NEXT INVOICE AND NEW NEXT INVOICE");
		     blusvultAccounts.forEach(acc->{
	    		 System.out.println("bluevault account > " + acc.getAccountId() 
	    		                     + " before invoice :" + acc.getNextInvoice()
	    				             + " new invoice : " +  bluevaultKB.nextInvoice(acc.getId()));
		     });	    	 
	    }	
}
