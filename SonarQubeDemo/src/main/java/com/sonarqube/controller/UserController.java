package com.sonarqube.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sonarqube.model.User;
import com.sonarqube.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<User> register(@RequestBody User user) {
		try {

			User registeredUser = userService.registerUser(user);
			return ResponseEntity.ok(registeredUser);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> userList = userService.getAllUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		try {
			User existingUser = userService.getById(id);
			if (existingUser == null) {
				return ResponseEntity.notFound().build();
			}
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<User> getById(@PathVariable int id) {
		try {
			User user = userService.getById(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}   
}
