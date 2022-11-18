package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.models.Product;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.util.KafkaMessenger;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	KafkaMessenger kafkaMessenger;

	public List<Product> fetchProdutcts() {
		return productRepository.findAll();
	}

	public Optional<Product> fetchProduct(String productId) {
		return productRepository.findById(productId);
	}

	public Product fetchProductWithName(String productName) {
		return productRepository.findByProductName(productName);
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public boolean validProduct(Product product) {
		return !fetchProdutcts().stream().anyMatch(prd -> prd.getProductName().equals(product.getProductName()));
	}

	public void delete(String productId) {
		productRepository.deleteById(productId);
	}

	public void placeOrder(String productName, long itemCount) {
		Product product = fetchProductWithName(productName);
		long remainStock = product.getProductStock() - itemCount;
		product.setProductStock(remainStock);
		kafkaMessenger.sendMessage(productName, remainStock);
	}

}
