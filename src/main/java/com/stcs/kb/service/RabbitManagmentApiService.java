package com.stcs.kb.service;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RabbitManagmentApiService {

	@GET("queues/%2f/{queueName}")
    public Call<JsonElement> getQueues( @Path("queueName") String queueName );
	
}
