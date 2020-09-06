package com.project.household.api.Repository.Request;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>, RequestRepositorySQLInterface {

}
