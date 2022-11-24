package com.ecommerce.models;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * 
 *         Product model class
 *
 */
@Document
public class Product {
	public Product() {
	}

	@Id
	private String id;
	private String productName;
	private String productDescription;
	private long price;
	private List<String> features;
	private String status;
	private long productStock;

	public long getProductStock() {
		return productStock;
	}

	public Product setProductStock(long productStock) {
		this.productStock = productStock;
		return this;
	}

	public String getId() {
		return id;
	}

	public Product setId(String id) {
		this.id = id;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public Product setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Product setProductDescription(String productDescription) {
		this.productDescription = productDescription;
		return this;
	}

	public long getPrice() {
		return price;
	}

	public Product setPrice(long price) {
		this.price = price;
		return this;
	}

	public List<String> getFeatures() {
		return features;
	}

	public Product setFeatures(List<String> features) {
		this.features = features;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Product setStatus(String status) {
		this.status = status;
		return this;
	}

}
