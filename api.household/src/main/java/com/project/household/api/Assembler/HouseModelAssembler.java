package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.HouseController;
import com.project.household.api.Entity.House;

@Component
public class HouseModelAssembler implements RepresentationModelAssembler<House, EntityModel<House>> {

	@Override
	public EntityModel<House> toModel(House house) {
		return EntityModel.of(house, //
				linkTo(methodOn(HouseController.class).getOneHouse(house.getId())).withSelfRel(),
				linkTo(methodOn(HouseController.class).getAllHouses()).withRel("houses"));
	}

}
