package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.RequestController;
import com.project.household.api.Entity.Request;

@Component
public class RequestModelAssembler implements RepresentationModelAssembler<Request, EntityModel<Request>> {

	@Override
	public EntityModel<Request> toModel(Request request) {
		return EntityModel.of(request, //
				linkTo(methodOn(RequestController.class).getOneRequest(request.getId())).withSelfRel(),
				linkTo(methodOn(RequestController.class).getAllRequests()).withRel("requests"));
	}

}
