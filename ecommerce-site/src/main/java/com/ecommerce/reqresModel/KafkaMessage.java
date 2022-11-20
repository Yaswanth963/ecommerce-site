package com.ecommerce.reqresModel;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         Kafka Message format
 *
 */
public class KafkaMessage {

	public KafkaMessage(String productName, String status) {
		this.productName = productName;
		this.status = status;
	}

	private String productName;
	private String status;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
