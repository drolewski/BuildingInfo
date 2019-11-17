package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Building;
import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.database.BuildingDb;
import com.put.buildinginfo.database.BuildingRepo;
import com.put.buildinginfo.database.FloorDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements  BuildingService{

    final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

    private final BuildingRepo buildingRepo;

    private final FloorService floorService;

    @Autowired
    public BuildingServiceImpl(BuildingRepo buildingRepo, FloorService floorService) {
        this.buildingRepo = buildingRepo;
        this.floorService = floorService;
    }

    //returns all informations about buildings stored in db
    public List<BuildingDb> getAllBuildings(){
        return buildingRepo.findAll();
    }

    //returns one building by id
    public BuildingDb getBuildingById(int id){
        return buildingRepo.findById(id);
    }

    public BuildingDb saveNewBuilding(Building building){
        logger.info("Save new building in db");
        ArrayList<FloorDb> floorDbs = new ArrayList<>();
        for(Level o : building.getImmoveables()){
            FloorDb floor = floorService.saveNewFloor(o);
            floorDbs.add(floor);
        }
        BuildingDb buildingDb = new BuildingDb((buildingRepo.findFirstByOrderByBuildingIdDesc().getBuildingId() + 1),
                                                building.getName(), floorDbs);
        buildingRepo.save(buildingDb);
        return buildingDb;
    }

    @Override
    public Building refactorBuildingDbToBuilding(int id){
        BuildingDb buildingDb = buildingRepo.findById(id);
        ArrayList<Level> levels = new ArrayList<>();
        for(FloorDb floorDb : buildingDb.getFloors()){
            Level level = floorService.refactorFloorDbToLevel(floorDb.getFloorId());
            levels.add(level);
        }
        Building building = new Building(buildingDb.getBuildingId(), buildingDb.getName(), levels);
        return building;
    }

    @Override
    public float calculateSurface(int id) {
        if(buildingRepo.findById(id)!= null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateSurface();
        }
        return -1f;
    }

    @Override
    public float calculateCubature(int id) {
        if(buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateCubature();
        }
        return -1f;
    }

    @Override
    public float calculateHeating(int id) {
        if(buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateHeating();
        }
        return -1f;
    }

    @Override
    public float calculateLighting(int id) {
        if (buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateLighting();
        }
        return -1f;
    }

    @Override
    public void deleteById(int id) {
        BuildingDb buildingDb = buildingRepo.findById(id);
        if(buildingDb != null){
            for(FloorDb floorDb : buildingDb.getFloors()){
                floorService.deleteById(floorDb.getFloorId());
            }
            buildingRepo.deleteById(id);
        }
    }
}
