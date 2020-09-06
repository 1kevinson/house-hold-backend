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

import com.project.household.api.Assembler.RoomModelAssembler;
import com.project.household.api.Entity.Room;
import com.project.household.api.Exception.NotFound.RoomNotFoundException;
import com.project.household.api.Repository.Room.RoomRepository;

@RestController
@RequestMapping("/api")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomModelAssembler roomModelAssembler;

	// Get all rooms
	@GetMapping("/rooms")
	public CollectionModel<EntityModel<Room>> getAllRooms() {
		List<EntityModel<Room>> rooms = roomRepository.findAll().stream() //
				.map(roomModelAssembler::toModel) //
				.collect(Collectors.toList());
		// CollectionModel<> is another Spring HATEOAS container aimed at encapsulating
		// collections. It, too, also lets you include links.
		return CollectionModel.of(rooms, linkTo(methodOn(RoomController.class).getAllRooms()).withSelfRel());
	}

	// Get one room
	@GetMapping("/rooms/{id}")
	public EntityModel<Room> getOneRoom(@PathVariable Integer id) {
		Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
		return roomModelAssembler.toModel(room);
	}

	// Add a new room
	@PostMapping("/rooms")
	public ResponseEntity<?> addRoom(@RequestBody Room newRoom) {
		EntityModel<Room> entityModel = roomModelAssembler.toModel(roomRepository.save(newRoom));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a room
	@PutMapping("/rooms/{id}")
	public ResponseEntity<?> replaceRoom(@RequestBody Room newRoom, @PathVariable Integer id) {
		Room updateRoom = roomRepository.findById(id).map(room -> {
			return roomRepository.save(room);
		}).orElseGet(() -> {
			newRoom.setId(id);
			return roomRepository.save(newRoom);
		});

		EntityModel<Room> entityModel = roomModelAssembler.toModel(updateRoom);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one room
	@DeleteMapping("/rooms/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
		roomRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
