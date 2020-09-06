package com.project.household.api.Repository.Request;

import java.util.List;

import com.project.household.api.Entity.Request;

public interface RequestRepositorySQLInterface {
	List<Request> fetchUserRequests (Integer userId);
}
