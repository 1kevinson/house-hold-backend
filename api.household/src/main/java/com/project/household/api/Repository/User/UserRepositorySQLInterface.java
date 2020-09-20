package com.project.household.api.Repository.User;

import com.project.household.api.Entity.User;

public interface UserRepositorySQLInterface {
	User findUserByEmail(String email);
}
