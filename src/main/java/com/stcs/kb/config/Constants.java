package com.stcs.kb.config;

public interface Constants {

	//killbill	
	String KB_URL = "http://127.0.0.1:8080" ;
	String KB_USERNAME = "admin" ;
	String KB_PASSWORD =  "password" ;
	String KB_CARTWHEEL_KEY = "cartwheel";
	String KB_CARTWHEEL_SECRET ="secret";
	String KB_BLUVAULT_KEY = "testMoveAcc";
	String KB_BLUVAULT_SECRET = "testMoveAcc";
	
	//database
	String DB_URL ="jdbc:postgresql://127.0.0.1:5432/killbill_db" ;
	String DB_USERNAME = "killbill";
	String DB_PASSWORD = "killbill";

	// rabbit 
	String RABBIT_URL = "localhost" ;
	String RABBIT_USERNAME = "guest" ;
	String RABBIT_PASSWORD = "guest" ;
	String RABBIT_HOST = "/" ;
	String STC_BILL_EXCHANGE = "cartwheel" ;

	String CATALOG_UPLOAD_ROUTING_KEY = "cartwheel.catalog.updated" ;
	String CATALOG_UPLOAD_QUEUE = "billing_catalog_updated_queue";
	String RABBIT_MANAGMENT_API = "http://localhost:15672/api/";
	
	
}
