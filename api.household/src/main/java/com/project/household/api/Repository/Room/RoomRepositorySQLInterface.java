package com.project.household.api.Repository.Room;

import java.util.List;

import com.project.household.api.Entity.Room;

public interface RoomRepositorySQLInterface {
	List<Room> fetchHouseRooms(Integer userId);
}
