package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.RoomController;
import com.project.household.api.Entity.Room;

@Component
public class RoomModelAssembler implements RepresentationModelAssembler<Room, EntityModel<Room>> {

	@Override
	public EntityModel<Room> toModel(Room room) {
		return EntityModel.of(room, //
				linkTo(methodOn(RoomController.class).getOneRoom(room.getId())).withSelfRel(),
				linkTo(methodOn(RoomController.class).getAllRooms()).withRel("rooms"));
	}

}
