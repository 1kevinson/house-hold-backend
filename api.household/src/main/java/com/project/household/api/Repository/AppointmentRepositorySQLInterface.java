package com.project.household.api.Repository;

import java.util.List;

import com.project.household.api.Entity.Appointment;

public interface AppointmentRepositorySQLInterface {
	List<Appointment> fetchUserAppointments(Integer userId);
}
