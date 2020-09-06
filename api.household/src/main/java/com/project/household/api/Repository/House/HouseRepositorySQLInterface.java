package com.project.household.api.Repository.House;

import java.util.List;

import com.project.household.api.Entity.House;

public interface HouseRepositorySQLInterface {
	List<House> fetchOwnerHouses(Integer userId);
}
