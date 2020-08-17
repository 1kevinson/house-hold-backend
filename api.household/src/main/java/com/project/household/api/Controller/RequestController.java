package com.project.household.api.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//Always imports those two for generated HATEAOS links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
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

import com.project.household.api.Configuration.RequestModelAssembler;
import com.project.household.api.Entity.Request;
import com.project.household.api.Enumeration.RequestStatus;
import com.project.household.api.Exception.RequestNotFoundException;
import com.project.household.api.Repositiory.RequestRepository;

@RestController
@RequestMapping("/api")
public class RequestController {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private RequestModelAssembler requestModelAssembler;

	// Get all requests
	@GetMapping("/requests")
	public CollectionModel<EntityModel<Request>> getAllRequests() {
		List<EntityModel<Request>> requests = requestRepository.findAll().stream() //
				.map(requestModelAssembler::toModel) //
				.collect(Collectors.toList());
		// CollectionModel<> is another Spring HATEOAS container aimed at encapsulating
		// collections. It, too, also lets you include links.
		return CollectionModel.of(requests, linkTo(methodOn(RequestController.class).getAllRequests()).withSelfRel());
	}

	// Get one request
	@GetMapping("/requests/{id}")
	public EntityModel<Request> getOneRequest(@PathVariable Integer id) {
		Request request = requestRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
		return requestModelAssembler.toModel(request);
	}

	// Get all user requests
	@GetMapping("/requests/users/{id}")
	public CollectionModel<EntityModel<Request>> getUserRequests(@PathVariable Integer id) {
		List<EntityModel<Request>>  requests = requestRepository.fetchUserRequests(id).stream() //
				.map(requestModelAssembler::toModel) //
				.collect(Collectors.toList());
		
		return CollectionModel.of(requests, linkTo(methodOn(RequestController.class).getUserRequests(id)).withSelfRel());
	}

	// Add a new request
	@PostMapping("/requests")
	public ResponseEntity<?> addRequest(@RequestBody Request newRequest) {
		newRequest.setDate(new Date());
		newRequest.setStatus(RequestStatus.SENDED.getEnumString());
		EntityModel<Request> entityModel = requestModelAssembler.toModel(requestRepository.save(newRequest));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a request
	@PutMapping("/requests/{id}")
	public ResponseEntity<?> replaceRequest(@RequestBody Request newRequest, @PathVariable Integer id) {
		Request updateRequest = requestRepository.findById(id).map(request -> {
			request.setStatus(newRequest.getStatus());
			request.setContent(newRequest.getContent());
			return requestRepository.save(request);
		}).orElseGet(() -> {
			newRequest.setId(id);
			return requestRepository.save(newRequest);
		});

		EntityModel<Request> entityModel = requestModelAssembler.toModel(updateRequest);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one request
	@DeleteMapping("/requests/{id}")
	public ResponseEntity<?> deleteRequest(@PathVariable Integer id) {
		requestRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
