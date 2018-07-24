package com.stcs.kb.service;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BillingServiceApiService {
	
	@GET("/swagger.json")
    public Call<JsonElement> getswagger( );
	
}
