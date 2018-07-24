package com.stcs.kb.testing.testEnviroment.impl;

import com.stcs.kb.client.RabbitConnection;
import com.stcs.kb.testing.testEnviroment.AbstractDockerComponent;

public class RabbitComponent extends AbstractDockerComponent  {
	
    // docker run -p 15672:15672 -p  5672:5672 rabbit-integration1

	private String url = "localhost";
	private String username  = "guest" ;
	private String password = "guest";
	private String vhost = "/";
	
	
	public RabbitComponent(String netwrokName) {
		super(netwrokName);
		
		this. dockerImage = "rabbit-integration1"; 
		this. name = "brokernode" ;
		this. port = 5672 ;
		this. managmentPort = 15672 ;	
	}
	
	

	@Override
	public boolean isUpAndRunning() {
		try { 
		   RabbitConnection rabbitConnection = new RabbitConnection(url, username, password, vhost) ;
		   boolean ok =  rabbitConnection.getConnection().isOpen() ;
		   if(ok) rabbitConnection.getConnection().close();
		   return ok ;
		}catch (Exception e) {
			System.out.println(name + "  dont running yet : " + e.getMessage() );
			return false ;
		}		
	}

}
