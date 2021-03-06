package com.project.household.api.Exception.NotFound;

@SuppressWarnings("serial")
public class AppointmentNotFoundException extends RuntimeException {
	public AppointmentNotFoundException(Integer id) {
		super("Could not find Appointment with Id : " + id);
	}
}
