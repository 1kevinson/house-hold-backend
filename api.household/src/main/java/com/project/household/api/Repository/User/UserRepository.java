package com.project.household.api.Repository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositorySQLInterface {

}
