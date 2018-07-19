package com.stcs.kb.service.impl;

import java.util.Iterator;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import com.stcs.kb.config.Constants;
import com.stcs.kb.dao.Account;
import com.stcs.kb.service.AccountService;

public class DBServiceKillBillImpl {

	DBI dbi ;
	AccountService accountService ;
	
    public DBServiceKillBillImpl(){
 	     dbi = new DBI(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
  	     accountService = dbi.onDemand(AccountService.class);
     }
    
    public List<Account> getBluevaultAccounts() {
		return accountService.getBluevaultAccounts();   	
    }

    public void updateToBluevaultTeanat(List<Account> blusvultAccounts, long bluevaultTenantId ) { 
    	blusvultAccounts.forEach(acc-> acc.setTenantId(bluevaultTenantId) );
    	accountService.updateAccountDataToBluevaultTeanat(blusvultAccounts);
    }
    
    
}
