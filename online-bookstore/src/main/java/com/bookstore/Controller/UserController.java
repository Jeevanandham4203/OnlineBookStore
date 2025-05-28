package com.bookstore.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.Entity.User;
import com.bookstore.Service.UserService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userservice;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody @Valid User user) {
	    String message = userservice.registerUser(user);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", message);

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
	    User user = userservice.loginUser(email, password);
	    return ResponseEntity.ok(user); // Send full user data
	}
	
}
