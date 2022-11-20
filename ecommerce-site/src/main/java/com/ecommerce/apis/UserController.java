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
import com.ecommerce.reqresModel.UserLoginRequest;
import com.ecommerce.reqresModel.UserResponse;
import com.ecommerce.services.UserService;
/**
 * 
 * @author yaswanth.perumalla
 * 
 * These are User api's
 *
 */
@RestController
@RequestMapping("/api" + EcommerceConstants.APP_VERSION + "/shopping")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> signup(@RequestBody User user) {
		if (userService.validUser(user)) {
			userService.addUser(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable String userId) {
		Optional<User> user = userService.fetchUser(userId);
		if (user.isEmpty())
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(user);
	}

	//IMPORTANT: Store the passwords in encrypted form 
	@PostMapping("/login")
	public ResponseEntity<UserResponse> userLogin(@RequestBody UserLoginRequest loginRequest) {
		if (userService.validLogin(loginRequest))
			return ResponseEntity.ok(new UserResponse(true, "User logged in successfuly"));
		return ResponseEntity.ok((new UserResponse(false, "Invalid username or password")));
	}

	@GetMapping("{customername}/forgot")
	public ResponseEntity<String> forgotPassword(@RequestParam String loginId) {
		User user = userService.fetchUserLogin(loginId);
		if (user == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok("Your password has been sent to your mail...");
	}

}
