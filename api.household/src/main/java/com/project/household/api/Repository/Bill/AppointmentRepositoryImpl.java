package com.project.household.api.Repository.Bill;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.Appointment;

@Repository
@Transactional(readOnly = true)
public class AppointmentRepositoryImpl implements AppointmentRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> fetchUserAppointments(Integer userId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM appointments WHERE user_id = ?",
				Appointment.class);
		query.setParameter(1, userId);

		return query.getResultList();
	}

}
