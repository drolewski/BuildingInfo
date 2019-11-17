package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.RoomDb;

import java.util.List;

public interface RoomService {

    List<RoomDb> getAllRooms();
    RoomDb getRoomById(int id);
    RoomDb saveNewRoom(Room room);
}
