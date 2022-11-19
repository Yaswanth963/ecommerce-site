package com.ecommerce.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.constants.KafkaTopics;

@Component
public class KafkaMessenger {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String productName, long remainStock) {
		// Kafka Message format: {productName:"",message:""} (OUT OF STOCK(0),HURRY UP
		// TO PURCHASE)
		kafkaTemplate.send(KafkaTopics.KAFKA_TOPIC, "key", "value");
	}
}
