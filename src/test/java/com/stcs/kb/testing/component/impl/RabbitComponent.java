package com.stcs.kb.testing.component.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

import com.stcs.kb.client.RabbitConnection;
import com.stcs.kb.testing.component.AbstractComponent;
import com.stcs.kb.testing.component.AbstractDockerComponent;

public class RabbitComponent extends AbstractDockerComponent  {
	
    // docker run -p 15672:15672 -p  5672:5672 rabbit-integration1

	private String url = "localhost";
	private String username  = "guest" ;
	private String password = "guest";
	private String vhost = "/";
	
	public RabbitComponent() {
		this. dockerImage = "rabbit-integration1"; 
		this. name = "rabbit mq node" ;
		this. port = 5672 ;
		this. managmentPort = 15672 ;	
	}
	
	

	@Override
	public boolean isComponentUpAndRunning() {
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
