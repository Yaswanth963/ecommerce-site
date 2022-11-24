package com.ecommerce.kafkaconfiguration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * 
 * This abstract class needs to be extended and the
 *         following annotations needs to be added in the extending class.
 *
 */
public abstract class KafkaConsumerConfig {

	@Autowired
	Environment env;

	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapConfig());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
		if (isSecurityEnabled()) {
			props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol());
		}

		return new DefaultKafkaConsumerFactory<>(props);
	}

	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	/**
	 * This method can be overridden to change bootstrap-servers config
	 * 
	 * @return
	 */
	public String getBootstrapConfig() {
		return env.getProperty("kafka.bootstrap.servers");
	}

	/**
	 * This method can be overridden to change group-id config
	 * 
	 * @return
	 */
	public String getGroupId() {
		return env.getProperty("kafka.consumer.group-id");
	}

	/**
	 * This method can be overridden to change ssl protocol
	 * 
	 * @return
	 */
	private String securityProtocol() {
		String protocol = env.getProperty("kafka.ssl.protocol");
		return StringUtils.isNoneEmpty(protocol) ? protocol : null;
	}

	/**
	 * This method can be overridden to alter security
	 * 
	 * @return
	 */
	private boolean isSecurityEnabled() {
		return "true".equalsIgnoreCase(env.getProperty("kafka.ssl.enabled"));
	}
}
