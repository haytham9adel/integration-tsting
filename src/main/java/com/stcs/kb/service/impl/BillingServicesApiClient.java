package com.stcs.kb.service.impl;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.stcs.kb.config.Constants;
import com.stcs.kb.service.BillingServiceApiService;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Log4j2
public class BillingServicesApiClient {
	
	public static BillingServiceApiService getService() {
		OkHttpClient client = new OkHttpClient.Builder()
				.build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BILLING_SERVICE_URL)
				.addConverterFactory(GsonConverterFactory.create()).client(client)
				.build();

		return retrofit.create(BillingServiceApiService.class);
	}

	public static boolean isConnected()  {
		Response<JsonElement> swaggerResponse = null ;
		try {
			swaggerResponse = getService().getswagger().execute();
		} catch (IOException e) {
			log.info("error in calling billing service {}" , e.getMessage());
			return false ;
		}
		
		return swaggerResponse.isSuccessful();
	}
}
