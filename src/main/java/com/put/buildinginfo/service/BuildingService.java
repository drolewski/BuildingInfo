package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Building;
import com.put.buildinginfo.database.BuildingDb;

import java.util.List;

public interface BuildingService {

    List<BuildingDb> getAllBuildings();
    BuildingDb getBuildingById(int id);
    BuildingDb saveNewBuilding(Building building);
    Building refactorBuildingDbToBuilding(int id);
    float calculateSurface(int id);
    float calculateCubature(int id);
    float calculateHeating(int id);
    float calculateLighting(int id);
    void deleteById(int id);
}