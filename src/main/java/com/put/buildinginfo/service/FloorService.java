package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;

import java.util.ArrayList;

public interface FloorService {

    ArrayList<Level> getAllFloors();
    Level getFloorById(int id);
    Level saveNewFloor(Level level);
    Level refactorFloorDbToLevel(int id);
    float calculateSurface(int id);
    float calculateCubature(int id);
    float calculateHeating(int id);
    float calculateLighting(int id);
    void deleteById(int id);
    Level updateFloor(Level level);
    ArrayList<Level> updateFloors(ArrayList<Level> levels);
    void deleteAll();
    Level addRoom(int id, Room room);
}
