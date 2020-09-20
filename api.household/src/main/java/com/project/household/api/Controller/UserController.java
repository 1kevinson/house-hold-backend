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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Assembler.UserModelAssembler;
import com.project.household.api.Configuration.JWT.JwtRequest;
import com.project.household.api.Configuration.JWT.JwtResponse;
import com.project.household.api.Configuration.JWT.JwtTokenUtil;
import com.project.household.api.Entity.User;
import com.project.household.api.Exception.NotFound.UserNotFoundException;
import com.project.household.api.Repository.User.UserRepository;
import com.project.household.api.Services.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserModelAssembler userModelAssembler;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	// Get all users
	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getAllUsers() {
		List<EntityModel<User>> users = userRepository.findAll().stream() //
				.map(userModelAssembler::toModel) //
				.collect(Collectors.toList());
		// CollectionModel<> is another Spring HATEOAS container aimed at encapsulating
		// collections. It, too, also lets you include links.
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
	}

	// Get one user
	@GetMapping("/users/{id}")
	public EntityModel<User> getOneUser(@PathVariable Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return userModelAssembler.toModel(user);
	}

	// Add a new user
	@PostMapping("/users/add")
	public ResponseEntity<?> addUser(@RequestBody User newUser) {
		EntityModel<User> entityModel = userModelAssembler.toModel(userRepository.save(newUser));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a user
	@PutMapping("/users/update/{id}")
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
	@DeleteMapping("/users/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
