package com.stcs.kb.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.stcs.kb.config.Constants;
import com.stcs.kb.service.RabbitManagmentApiService;
import com.stcs.kb.util.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RabbitManagmentClient {

	public static RabbitManagmentApiService getService() {
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(new BasicAuthInterceptor(Constants.RABBIT_USERNAME, Constants.RABBIT_PASSWORD))
				.build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.RABBIT_MANAGMENT_API)
				.addConverterFactory(GsonConverterFactory.create()).client(client)
				.build();

		return retrofit.create(RabbitManagmentApiService.class);
	}

	public static long[] getQueueData(String queue) throws Exception {
		long[] data = new long[2];

		Response<JsonElement> queusResponse = getService().getQueues(queue).execute();

		JsonObject json = queusResponse.body().getAsJsonObject();
		data[0] = json.getAsJsonObject("message_stats") == null ? 0
				: json.getAsJsonObject("message_stats").get("publish").getAsLong();

		data[1] = json.get("messages_ram").getAsLong();
		return data;
	}

}
