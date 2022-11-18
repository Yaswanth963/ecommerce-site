package com.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByLoginId(String customername);

	User findByLoginIdAndPassword(String userName, String password);

}
