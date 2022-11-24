package com.ecommerce.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecommerce.constants.KafkaTopics;

/**
 * 
 * 
 *         Admin Listener listens to a topic and send the message to it's
 *         parent's class
 *
 */
@Service
public class AdminListener extends UserListener {
	@KafkaListener(topics = KafkaTopics.KAFKA_TOPIC)
	public void listen(String data) {
		super.listen(data);
	}
}
