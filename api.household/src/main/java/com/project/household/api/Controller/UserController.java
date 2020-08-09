package com.project.household.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Entity.User;
import com.project.household.api.Exception.UserNotFoundExeption;
import com.project.household.api.Repositiory.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// Get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Get one user
	@GetMapping("/users/{id}")
	public User getOneUser(@PathVariable Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundExeption(id));
	}

	// Add a new user
	@PostMapping("/users")
	public User addUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	// Update a user
	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

		return userRepository.findById(id).map(user -> {
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return userRepository.save(newUser);
		});
	}

	// Delete one user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}

}
