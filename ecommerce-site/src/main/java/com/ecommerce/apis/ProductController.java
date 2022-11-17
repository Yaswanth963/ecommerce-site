package com.ecommerce.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.constants.EcommerceConstants;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.ProductRepository;

@RestController
@RequestMapping("/api" + EcommerceConstants.APP_VERSION + "/shopping")
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getProducts() {
		var products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/products/search/{productName}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable String productName) {
		var products = productRepository.findByproductName(productName);
		return ResponseEntity.ok(products);
	}

	@PostMapping("/{productName}/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		productRepository.save(product);
		return ResponseEntity.ok(product);
	}

	@PutMapping("/{productName}/update/{productId}")
	public ResponseEntity<Optional<Product>> updateProduct(@RequestBody Product productData,
			@PathVariable String productId) {
		Optional<Product> product = productRepository.findById(productId);
		productData.setId(productId);
		productRepository.save(productData);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{productName}/delete/productId")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
		productRepository.deleteById(productId);
		return ResponseEntity.ok("Product with product Id: " + productId + " deleted");
	}

}
