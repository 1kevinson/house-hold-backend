package com.project.household.api.Repository.Bill;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>, AppointmentRepositorySQLInterface {

}
