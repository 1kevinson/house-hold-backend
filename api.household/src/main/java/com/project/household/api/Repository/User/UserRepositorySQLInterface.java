package com.project.household.api.Repository.User;

import java.util.List;

import com.project.household.api.Entity.User;

public interface UserRepositorySQLInterface {
	List<User> findUserByEmail(String email);
}
