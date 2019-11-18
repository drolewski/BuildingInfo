package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.database.FloorDb;

import java.util.ArrayList;

public interface FloorService {

    ArrayList<Level> getAllFloors();
    Level getFloorById(int id);
    FloorDb saveNewFloor(Level level);
    Level refactorFloorDbToLevel(int id);
    float calculateSurface(int id);
    float calculateCubature(int id);
    float calculateHeating(int id);
    float calculateLighting(int id);
    void deleteById(int id);
    FloorDb updateFloor(Level level);
    ArrayList<FloorDb> updateFloors(ArrayList<Level> levels);
}
