package com.stcs.kb.testing.component.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import com.stcs.kb.testing.component.AbstractComponent;
import com.stcs.kb.testing.component.AbstractDockerComponent;

public class KillBillDBComponent extends AbstractDockerComponent {

	private String machine = "localhost";
    // docker run -p 15672:15672 -p  5672:5672 rabbit-integration1
	private String dbNameKB  = "killbill" ;
	private String usernameKB  = "killbill" ;
	private String passwordKB = "killbill";

	private String dbNamePlugins    = "killbill_plugins" ;
	private String usernamePlugins  = "killbill" ;
	private String passwordPlugins  = "killbill";
	
	private String dbNameBilling    = "billing_service_db" ;
	private String usernameBilling  = "billing_service" ;
	private String passwordBilling  = "billing_service";
	
	public KillBillDBComponent() {
		this. dockerImage = "bill_it_db"; 
		this.  port = 5432 ;
		this.  name= " KB , BILLING DataBase Node " ;
		
	}


	@Override
	public  boolean isComponentUpAndRunning()   {
		try { 
			DBI	 dbi = new DBI("jdbc:postgresql://localhost:"+port+"/"+dbNameKB, usernameKB , passwordKB ) ;
			Handle handle = dbi.open() ;
			return handle != null  ;
		}catch (Exception e) {
			 System.out.println(name + "  dont running yet : " + e.getMessage() );
			return false ;
		}	
	}

}
