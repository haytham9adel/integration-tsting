package com.stcs.kb.service.impl;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.stcs.kb.config.Constants;
import com.stcs.kb.service.BillingServiceApiService;
import com.stcs.kb.service.RabbitManagmentApiService;
import com.stcs.kb.util.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
			System.out.println("error in calling billing service >" + e.getMessage());
			return false ;
		}
		
		return swaggerResponse.isSuccessful();
	}
}
