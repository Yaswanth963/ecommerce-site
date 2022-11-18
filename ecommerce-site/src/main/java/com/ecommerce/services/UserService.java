package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.models.User;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.reqresModel.UserLoginRequest;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void addUser(User user) {
		userRepository.save(user);
	}

	public Optional<User> fetchUser(String userId) {
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId)).orElse(null);
		return user;
	}

	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	public boolean validLogin(UserLoginRequest loginRequest) {
		User user = userRepository.findByLoginIdAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
		return user != null;
	}

	public boolean validUser(User user) {
		List<User> userList = fetchAllUsers();
		return !userList.stream()
				.anyMatch(usr -> usr.getLoginId().equals(user.getLoginId()) || usr.getEmail().equals(user.getEmail()));
	}

	public User fetchUserLogin(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
}
