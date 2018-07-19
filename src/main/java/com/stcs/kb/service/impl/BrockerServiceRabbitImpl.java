package com.stcs.kb.service.impl;

import com.stcs.kb.client.RabbitConnection;
import com.stcs.kb.config.Constants;
import com.stcs.kb.model.Response;
import com.stcs.kb.service.BrokerService;
import com.stcs.kb.util.ConcuencyUtil;


public class BrockerServiceRabbitImpl implements BrokerService {

	RabbitConnection rabbitConnection ;
	String stcsBillTopic ;
	String stcsCatalogUploadRoutingKey ;
	String stcsCatalogUploadQueue;
	
    public BrockerServiceRabbitImpl() {
    	stcsBillTopic = Constants.STC_BILL_EXCHANGE ;
    	stcsCatalogUploadRoutingKey = Constants.CATALOG_UPLOAD_ROUTING_KEY ;
    	stcsCatalogUploadQueue = Constants.CATALOG_UPLOAD_QUEUE ;
   }
	
	public Response send(String message , String topic  ,  String routingKey , String queue  ) {
		
		 boolean status = false ;
		 String msg ="" ;
		 RabbitConnection	rabbitConnection = new RabbitConnection
				 (Constants.RABBIT_URL, Constants.RABBIT_USERNAME, Constants.RABBIT_PASSWORD, Constants.RABBIT_HOST) ;

		 try {
		        long[] counterBeforeSend = RabbitManagmentClient.getQueueData(queue);
		    	rabbitConnection.send(message , topic , routingKey);
		    	rabbitConnection.getConnection().close();
		    	ConcuencyUtil.pause(10);    
                long[] counterAfterSend = RabbitManagmentClient.getQueueData(queue);

		        if(  counterAfterSend[0] > counterBeforeSend[0] && counterAfterSend[1] == counterBeforeSend[1] ) {
		        	status = true ;
		        	msg ="OK: MESSAGE HAS BEEN DELLIVERED" ; 
		        } else if (counterAfterSend[0] > counterBeforeSend[0] && counterAfterSend[1] > counterBeforeSend[1] ) {
		        	msg ="ERROR : MESSAGE HAS BEEN QUEUED" ; 
		        } else {
		        	msg ="ERROR : MESSAGE DIDNT BEEN ROUTED TO QUEUE" ;
		        }		        
			} catch (Exception e) {
				e.printStackTrace();
				msg = "ERROR : cant send to messaging SERVER/queue >>   " + e.getMessage() ;
			}
		 
		 return new Response(status, msg) ;
	}

	public Response sendCatalogUpdate(String msg)  {
		return send(msg, stcsBillTopic , stcsCatalogUploadRoutingKey , stcsCatalogUploadQueue ) ;
	}
	
	
}
