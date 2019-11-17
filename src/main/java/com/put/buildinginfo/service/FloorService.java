package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.database.FloorDb;

import java.util.List;

public interface FloorService {

    List<FloorDb> getAllFloors();
    FloorDb getFloorById(int id);
    FloorDb saveNewFloor(Level level);
}
