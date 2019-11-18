package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Building;

import java.util.ArrayList;

public interface BuildingService {

    ArrayList<Building> getAllBuildings();
    Building getBuildingById(int id);
    Building saveNewBuilding(Building building);
    Building refactorBuildingDbToBuilding(int id);
    float calculateSurface(int id);
    float calculateCubature(int id);
    float calculateHeating(int id);
    float calculateLighting(int id);
    void deleteById(int id);
    Building updateBuilding(Building building);
}
