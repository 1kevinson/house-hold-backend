package com.project.household.api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Entity.User;
import com.project.household.api.Repositiory.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserRepository userRepository = null;

	@PostMapping(path = "/add")
	public String addNewUser(@RequestParam String name, @RequestParam String email) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}
