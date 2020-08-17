package com.project.household.api.Repositiory;

import java.util.List;

import com.project.household.api.Entity.Request;

public interface RequestRepositoryCustom {
	List<Request> fetchUserRequests (Integer userId);
}
