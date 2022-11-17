package com.ecommerce.apis;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.constants.EcommerceConstants;
import com.ecommerce.models.User;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.reqresModel.UserLoginRequest;
import com.ecommerce.reqresModel.UserResponse;

@RestController
@RequestMapping("/api" + EcommerceConstants.APP_VERSION + "/shopping")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<User> signup(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable String userId) {
		Optional<User> user = Optional.ofNullable(userRepository.findById(userId)).orElse(null);
		if (user == null)
			return (ResponseEntity<Optional<User>>) ResponseEntity.badRequest();
		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> userLogin(@RequestBody UserLoginRequest loginRequest) {
		if (validUser(loginRequest))
			return ResponseEntity.ok(new UserResponse(true, "User logged in successfuly"));
		return ResponseEntity.ok((new UserResponse(false, "Invalid username or password")));
	}

	@GetMapping("{customername}/forgot")
	public ResponseEntity<String> forgotPassword(@RequestParam String password, @PathVariable String customername) {
		User user = userRepository.findByEmail(customername);
		return ResponseEntity.ok("Password has been changed successfully...");
	}

	private boolean validUser(UserLoginRequest loginRequest) {
		// To be implemented
		return true;
	}
}
