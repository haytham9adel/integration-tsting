package com.stcs.kb.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitConnection {

	private String url;

	private String username;

	private String password;

	private String vhost;

	private Connection connection;

	
	public RabbitConnection(String url, String username, String password, String vhost) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
		this.vhost = vhost;
	}

	public Connection getConnection() throws IOException, TimeoutException {

		if (connection == null || !connection.isOpen()) {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername(username);
			factory.setPassword(password);
			factory.setVirtualHost(vhost);
			factory.setHost(url);
			factory.setRequestedHeartbeat(30);
			factory.setConnectionTimeout(30000);

			connection = factory.newConnection();
		}
		return connection;
	}

	public boolean send(String message, String topic, String routingKey)
			throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {

		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().contentType("application/json")
				.deliveryMode(2).priority(1).build();

		Channel channel = getConnection().createChannel();
		channel.basicPublish(topic, routingKey, true, properties, message.getBytes());
		channel.close();
		return true;
	}
}
