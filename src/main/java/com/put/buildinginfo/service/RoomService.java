package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;

import java.util.ArrayList;

public interface RoomService {

    ArrayList<Room> getAllRooms();
    Room getRoomById(int id);
    Room saveNewRoom(Room room);
    Room refactorRoomDbToRoom(int id);
    void deleteById(int id);
    float getSurface(int id);
    float getCubature(int id);
    float getHeating(int id);
    float getLighting(int id);
    Room updateRoom(Room room);
    ArrayList<Room> updateRooms(ArrayList<Room> rooms);
    void deleteAll();
}
