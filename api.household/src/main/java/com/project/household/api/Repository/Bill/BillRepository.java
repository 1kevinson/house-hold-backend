package com.project.household.api.Repository.Bill;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>, BillRepositorySQLInterface {

}
