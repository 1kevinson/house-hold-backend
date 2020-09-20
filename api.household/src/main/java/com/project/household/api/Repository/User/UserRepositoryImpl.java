package com.project.household.api.Repository.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.User;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<User> findUserByEmail(String email) {
		Query query = entityManager.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
		query.setParameter(1, email);

		return query.getResultList();

	}

}
