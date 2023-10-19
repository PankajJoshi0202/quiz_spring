package com.example.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.entity.UserInfo;
import com.example.quiz.service.UserService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/signUp")
	public ResponseEntity<UserInfo> signUp(@RequestBody UserInfo userinfo) {
		System.out.println("In Signup");
		// Assuming the operation was successful, return a success message
		UserInfo successMessage = userService.signUp(userinfo);

		// You can also return a specific HTTP status code (e.g., 200 OK)
		return ResponseEntity.status(HttpStatus.OK).body(successMessage);

	}

	@GetMapping("/signIn")
	public ResponseEntity<UserInfo> signIn(@RequestParam("userName") String userName,
			@RequestParam("password") String pass) {
		System.out.println("Hello");

		// Assuming the operation was successful, return a success message
		UserInfo successMessage = userService.signIn(userName, pass);

		// You can also return a specific HTTP status code (e.g., 200 OK)
		return ResponseEntity.status(HttpStatus.OK).body(successMessage);

	}

	@GetMapping("/getIdByUsername")
	public ResponseEntity<String> getIdByUsername(@RequestParam("userName") String userName) {
		return userService.getIdByUsername(userName);
	}

}
