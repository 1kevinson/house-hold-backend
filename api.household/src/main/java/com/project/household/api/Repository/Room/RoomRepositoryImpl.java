package com.project.household.api.Repository.Room;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.Room;

@Repository
@Transactional(readOnly = true)
public class RoomRepositoryImpl implements RoomRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> fetchHouseRooms(Integer userId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM rooms WHERE house_id = ?", Room.class);
		query.setParameter(1, userId);

		return query.getResultList();
	}

}
