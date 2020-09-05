package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.UserController;
import com.project.household.api.Entity.User;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel(User user) {
		return EntityModel.of(user, //
				linkTo(methodOn(UserController.class).getOneUser(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
	}
}
