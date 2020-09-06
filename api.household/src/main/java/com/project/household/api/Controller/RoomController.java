package com.project.household.api.Controller;

//Always imports those two for generated HATEAOS links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Assembler.UserModelAssembler;
import com.project.household.api.Entity.User;
import com.project.household.api.Exception.NotFound.UserNotFoundException;
import com.project.household.api.Repository.User.UserRepository;

@RestController
@RequestMapping("/api")
public class RoomController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserModelAssembler userModelAssembler;

	// Get all users
	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getAllUsers() {
		List<EntityModel<User>> users = userRepository.findAll().stream() //
				.map(userModelAssembler::toModel) //
				.collect(Collectors.toList());
		// CollectionModel<> is another Spring HATEOAS container aimed at encapsulating
		// collections. It, too, also lets you include links.
		return CollectionModel.of(users, linkTo(methodOn(RoomController.class).getAllUsers()).withSelfRel());
	}

	// Get one user
	@GetMapping("/users/{id}")
	public EntityModel<User> getOneUser(@PathVariable Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return userModelAssembler.toModel(user);
	}

	// Add a new user
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User newUser) {
		EntityModel<User> entityModel = userModelAssembler.toModel(userRepository.save(newUser));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a user
	@PutMapping("/users/{id}")
	public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Integer id) {
		User updateUser = userRepository.findById(id).map(user -> {
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return userRepository.save(newUser);
		});

		EntityModel<User> entityModel = userModelAssembler.toModel(updateUser);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
