package com.project.household.api.Repository.House;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.House;

@Repository
@Transactional(readOnly = true)
public class HouseRepositoryImpl implements HouseRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<House> fetchOwnerHouses(Integer userId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM houses WHERE user_id = ?", House.class);
		query.setParameter(1, userId);

		return query.getResultList();
	}

}
