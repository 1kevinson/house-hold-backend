package com.project.household.api.Repository.Request;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.Request;

@Repository
@Transactional(readOnly = true)
//Custom implementation always end with "Impl"
public class RequestRepositoryImpl implements RequestRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> fetchUserRequests(Integer userId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM requests  WHERE user_id = ?", Request.class);
		query.setParameter(1, userId);

		return query.getResultList();
	}

}
