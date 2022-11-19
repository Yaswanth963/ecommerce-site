package com.ecommerce.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.constants.KafkaTopics;

@Service
public class AdminListener extends UserListener {
	@KafkaListener(topics = KafkaTopics.KAFKA_TOPIC)
	public void listen(String data) {
		super.listen(data);
	}
}
