package com.project.household.api.Repository.House;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.House;

public interface HouseRepository extends JpaRepository<House, Integer>, HouseRepositorySQLInterface {

}
