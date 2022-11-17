package com.ecommerce.kafkaconfiguration;

import org.apache.kafka.clients.admin.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaProducer extends KafkaProducerConfig {

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return super.producerFactory();
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return super.kafkaTemplate();
	}

	@Bean("adminClient")
	public Admin kafkaAdmin() {
		return super.kafkaAdmin();
	}

}
