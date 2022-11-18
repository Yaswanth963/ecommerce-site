package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		LOG.info("User with id: " + user.getLoginId() + " added");
		userRepository.save(user);
	}

	public Optional<User> fetchUser(String userId) {
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId)).orElse(null);
		return user;
	}

	public List<User> fetchAllUsers() {
		List<User> users = userRepository.findAll();
		String usrs = JSONArray.toJSONString(users);
		LOG.info(usrs);
		return users;
	}

	public boolean validLogin(UserLoginRequest loginRequest) {
		User user = userRepository.findByLoginIdAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
		if (user != null)
			LOG.info("Valid User");
		else
			LOG.info("Invalid User");
		return user != null;
	}

	public User fetchUserLogin(String loginId) {
		LOG.info("Fetching user " + loginId);
		return userRepository.findByLoginId(loginId);
	}

	public boolean validUser(User user) {
		List<User> userList = fetchAllUsers();
		return !userList.stream()
				.anyMatch(usr -> usr.getLoginId().equals(user.getLoginId()) || usr.getEmail().equals(user.getEmail()));
	}

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

}
