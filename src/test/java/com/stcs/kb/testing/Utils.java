package com.stcs.kb.testing;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.killbill.billing.client.model.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode ;
public class Utils {

	
	
	public static String getDateFromCatalogJson(String json , String nodeName ) throws JsonProcessingException, IOException {
		  ObjectMapper mapper = new ObjectMapper();
		  JsonNode actualObj = mapper.readTree(json);
		  return  actualObj.get(nodeName).asText() ;
	}
	
	
	public static String readFile(String path, Charset encoding)  throws IOException {
		   
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
    }
	
	public static Subscription  createSubscripForUpgrade( UUID subscriptionId , UUID accountId , String plan ) {
	    	Subscription sub1 = new Subscription();
	    	sub1.setSubscriptionId(subscriptionId) ;
			sub1.setAccountId(accountId);
			sub1.setPlanName(plan);
			return sub1 ;
	}
	 
}
