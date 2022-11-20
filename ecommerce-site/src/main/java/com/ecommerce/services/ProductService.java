package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.models.Product;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.util.KafkaMessenger;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         Service for product api's
 *
 */
@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	KafkaMessenger kafkaMessenger;

	public List<Product> fetchProdutcts() {
		List<Product> products = productRepository.findAll();
		String prds = JSONArray.toJSONString(products);
		LOG.info(prds);
		return products;
	}

	public Optional<Product> fetchProduct(String productId) {
		LOG.info("Fetching Product with id: " + productId);
		return productRepository.findById(productId);
	}

	public Product fetchProductWithName(String productName) {
		LOG.info("Fetching Product: " + productName);
		return productRepository.findByProductName(productName);
	}

	public void addProduct(Product product) {
		LOG.info("Product " + product.getProductName() + " added to the store");
		productRepository.save(product);
	}

	public void delete(String productId) {
		LOG.info("Product with id: " + productId + " deleted");
		productRepository.deleteById(productId);
	}

	public void placeOrder(String productName, long itemCount) {
		Product product = fetchProductWithName(productName);
		long remainStock = product.getProductStock() - itemCount;
		product.setProductStock(remainStock);
		productRepository.save(product);
		kafkaMessenger.sendMessage(productName, remainStock);
		LOG.info("Order Placed: { Product: " + productName + ", Item count: " + itemCount + " }");
		LOG.info("Remaining stock: " + remainStock);
	}

	public void updatetatus(String productName, String status) {
		Product product = fetchProductWithName(productName);
		product.setStatus(status);
		productRepository.save(product);
	}

	public boolean validProduct(Product product) {
		return !fetchProdutcts().stream().anyMatch(prd -> prd.getProductName().equals(product.getProductName()));
	}

	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

}
