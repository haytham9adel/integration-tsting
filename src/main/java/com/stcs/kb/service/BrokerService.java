package com.stcs.kb.service;

import com.stcs.kb.model.Response;

public interface BrokerService {
   
	Response sendCatalogUpdate(String msg ) throws Exception ;
}
