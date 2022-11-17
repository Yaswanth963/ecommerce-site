package com.ecommerce.kafkaconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumer extends KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return super.consumerFactory();
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		return super.kafkaListenerContainerFactory();
	}

}