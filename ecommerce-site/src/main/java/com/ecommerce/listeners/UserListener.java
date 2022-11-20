package com.ecommerce.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.mapper.DataMapper;
import com.ecommerce.reqresModel.KafkaMessage;
import com.ecommerce.services.ProductService;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         This is a parent class that listens to all it's child class messages
 *         and update the product status from database
 *
 */
public abstract class UserListener {

	@Autowired
	ProductService productService;

	@Autowired
	DataMapper dMap;

	public void listen(String data) {
		KafkaMessage message = dMap.fromJson(KafkaMessage.class, data);
		String productName = message.getProductName();
		String productStatus = message.getStatus();
		productService.updatetatus(productName, productStatus);
		LOG.info(String.format("Product %s status updated to %s", productName, productStatus));
	}

	private static final Logger LOG = LoggerFactory.getLogger(UserListener.class);

}
