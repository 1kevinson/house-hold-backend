package com.project.household.api.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.project.household.api.Controller.AppointmentController;
import com.project.household.api.Entity.Appointment;

@Component
public class AppointmentModelAssembler implements RepresentationModelAssembler<Appointment, EntityModel<Appointment>> {

	@Override
	public EntityModel<Appointment> toModel(Appointment appointment) {
		return EntityModel.of(appointment, //
				linkTo(methodOn(AppointmentController.class).getOneAppointment(appointment.getId())).withSelfRel(),
				linkTo(methodOn(AppointmentController.class).getAllAppointments()).withRel("appointments"));
	}

}
