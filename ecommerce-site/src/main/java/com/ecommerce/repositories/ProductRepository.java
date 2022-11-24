package com.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.models.Product;

/**
 * 
 * Product repository
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Product findByProductName(String productName);

}
