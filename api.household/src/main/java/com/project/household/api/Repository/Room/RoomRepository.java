package com.project.household.api.Repository.Room;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.household.api.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>, RoomRepositorySQLInterface {

}
