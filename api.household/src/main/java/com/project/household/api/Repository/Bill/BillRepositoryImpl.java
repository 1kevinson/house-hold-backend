package com.project.household.api.Repository.Bill;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.household.api.Entity.Bill;

@Repository
@Transactional(readOnly = true)
public class BillRepositoryImpl implements BillRepositorySQLInterface {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> fetchTenantBills(Integer userId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM bills WHERE user_id = ?", Bill.class);
		query.setParameter(1, userId);

		return query.getResultList();
	}

}
