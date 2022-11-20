package com.ecommerce.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.constants.KafkaTopics;
import com.ecommerce.mapper.DataMapper;
import com.ecommerce.reqresModel.KafkaMessage;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         Messenger class that sends data to kafka topics
 *
 */
@Component
public class KafkaMessenger {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	DataMapper dMap;

	public void sendMessage(String productName, long remainStock) {
		String message = remainStock == 0 ? "OUT OF STOCK" : "HURRY UP TO PURCHASE";
		kafkaTemplate.send(KafkaTopics.KAFKA_TOPIC, dMap.toJson(new KafkaMessage(productName, message)));
		LOG.info(String.format("{'ProductName':%s,'message':%s}", productName, message));
	}

	private static final Logger LOG = LoggerFactory.getLogger(KafkaMessenger.class);

}
