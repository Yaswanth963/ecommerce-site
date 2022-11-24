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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.constants.EcommerceConstants;
import com.ecommerce.models.Product;
import com.ecommerce.services.ProductService;
/**
 * 
 * 
 * 
 * These are product api's 
 *
 */
@RestController
@RequestMapping("/api" + EcommerceConstants.APP_VERSION + "/shopping")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getProducts() {
		var products = productService.fetchProdutcts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/products/search/{productName}")
	public ResponseEntity<Product> getProductsByName(@PathVariable String productName) {
		Product product = productService.fetchProductWithName(productName);
		if (product == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(product);
	}

	@PostMapping("/{productName}/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if (productService.validProduct(product)) {
			productService.addProduct(product);
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/{productName}/update/{productId}")
	public ResponseEntity<Optional<Product>> updateProduct(@RequestBody Product productData,
			@PathVariable String productId) {
		Optional<Product> product = productService.fetchProduct(productId);
		if (product.isEmpty())
			return ResponseEntity.badRequest().build();
		productData.setId(productId);
		productService.addProduct(productData);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{productName}/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
		Optional<Product> product = productService.fetchProduct(productId);
		if (product.isEmpty())
			return ResponseEntity.badRequest().build();
		productService.delete(productId);
		return ResponseEntity.ok("Product with product Id: " + productId + " deleted");
	}

	@GetMapping("/product/getStock/{productName}")
	public ResponseEntity<Long> getProductStock(@PathVariable String productName) {
		Product product = productService.fetchProductWithName(productName);
		if (product == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(product.getProductStock());
	}
	
	@PostMapping("placeOrder/{productName}")
	public ResponseEntity<String> placeOrder(@PathVariable String productName,@RequestParam long itemCount) {
		productService.placeOrder(productName,itemCount);
		return ResponseEntity.ok("Order Placed");
	}

}
