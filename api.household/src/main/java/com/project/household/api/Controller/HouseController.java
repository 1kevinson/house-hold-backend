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

import com.project.household.api.Assembler.HouseModelAssembler;
import com.project.household.api.Entity.House;
import com.project.household.api.Exception.NotFound.HouseNotFoundException;
import com.project.household.api.Repository.House.HouseRepository;

@RestController
@RequestMapping("/api")
public class HouseController {

	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private HouseModelAssembler houseModelAssembler;

	// Get all houses
	@GetMapping("/houses")
	public CollectionModel<EntityModel<House>> getAllHouses() {
		List<EntityModel<House>> houses = houseRepository.findAll().stream() //
				.map(houseModelAssembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(houses, linkTo(methodOn(HouseController.class).getAllHouses()).withSelfRel());
	}

	// Get one house
	@GetMapping("/houses/{id}")
	public EntityModel<House> getOneHouse(@PathVariable Integer id) {
		House house = houseRepository.findById(id).orElseThrow(() -> new HouseNotFoundException(id));
		return houseModelAssembler.toModel(house);
	}

	// Add a new house
	@PostMapping("/houses")
	public ResponseEntity<?> addHouse(@RequestBody House newHouse) {
		EntityModel<House> entityModel = houseModelAssembler.toModel(houseRepository.save(newHouse));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a house
	@PutMapping("/houses/{id}")
	public ResponseEntity<?> replaceHouse(@RequestBody House newHouse, @PathVariable Integer id) {
		House updateHouse = houseRepository.findById(id).map(house -> {
			return houseRepository.save(house);
		}).orElseGet(() -> {
			newHouse.setId(id);
			return houseRepository.save(newHouse);
		});

		EntityModel<House> entityModel = houseModelAssembler.toModel(updateHouse);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one house
	@DeleteMapping("/houses/{id}")
	public ResponseEntity<?> deleteHouse(@PathVariable Integer id) {
		houseRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
