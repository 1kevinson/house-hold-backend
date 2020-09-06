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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.household.api.Assembler.AppointmentModelAssembler;
import com.project.household.api.Entity.Appointment;
import com.project.household.api.Enumeration.AppointmentStatus;
import com.project.household.api.Exception.NotFound.AppointmentNotFoundException;
import com.project.household.api.Repository.Appointment.AppointmentRepository;
import com.project.household.api.Repository.User.UserRepository;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppointmentModelAssembler appointmentModelAssembler;

	// Get all appointments
	@GetMapping("/appointments")
	public CollectionModel<EntityModel<Appointment>> getAllAppointments() {
		List<EntityModel<Appointment>> appointments = appointmentRepository.findAll().stream() //
				.map(appointmentModelAssembler::toModel) //
				.collect(Collectors.toList());
		// CollectionModel<> is another Spring HATEOAS container aimed at encapsulating
		// collections. It, too, also lets you include links.
		return CollectionModel.of(appointments,
				linkTo(methodOn(AppointmentController.class).getAllAppointments()).withSelfRel());
	}

	// Get one Appointment
	@GetMapping("/appointments/{id}")
	public EntityModel<Appointment> getOneAppointment(@PathVariable Integer id) {
		Appointment Appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException(id));
		return appointmentModelAssembler.toModel(Appointment);
	}

	// Get all user appointments
	@GetMapping("/appointments/users/{id}")
	public CollectionModel<EntityModel<Appointment>> getUserAppointments(@PathVariable Integer id) {
		List<EntityModel<Appointment>> appointments = appointmentRepository.fetchUserAppointments(id).stream() //
				.map(appointmentModelAssembler::toModel) //
				.collect(Collectors.toList());
		return CollectionModel.of(appointments,
				linkTo(methodOn(AppointmentController.class).getUserAppointments(id)).withSelfRel());
	}

	// Add a new Appointment
	@PostMapping("/appointments/add/{user_id}")
	public ResponseEntity<?> addAppointment(@RequestBody Appointment newAppointment, @PathVariable Integer user_id) {
		// get the optional user or return null
		newAppointment.setUser(userRepository.findById(user_id).get());
		newAppointment.setDate(new Date());
		newAppointment.setStatus(AppointmentStatus.PENDING.getEnumString());
		EntityModel<Appointment> entityModel = appointmentModelAssembler
				.toModel(appointmentRepository.save(newAppointment));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Update a Appointment
	@PutMapping("/appointments/{id}")
	public ResponseEntity<?> replaceAppointment(@RequestBody Appointment newAppointment, @PathVariable Integer id) {
		Appointment updateAppointment = appointmentRepository.findById(id).map(Appointment -> {
			Appointment.setStatus(newAppointment.getStatus());
			Appointment.setNotes(newAppointment.getNotes());
			return appointmentRepository.save(Appointment);
		}).orElseGet(() -> {
			newAppointment.setId(id);
			return appointmentRepository.save(newAppointment);
		});

		EntityModel<Appointment> entityModel = appointmentModelAssembler.toModel(updateAppointment);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// Delete one Appointment
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable Integer id) {
		appointmentRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
