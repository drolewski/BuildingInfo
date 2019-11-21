package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Building;
import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.database.BuildingDb;
import com.put.buildinginfo.database.BuildingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BuildingServiceImpl implements  BuildingService{

    private final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

    private final BuildingRepo buildingRepo;

    private final FloorService floorService;

    @Autowired
    public BuildingServiceImpl(BuildingRepo buildingRepo, FloorService floorService) {
        this.buildingRepo = buildingRepo;
        this.floorService = floorService;
    }

    public ArrayList<Building> getAllBuildings(){
        logger.debug("Return all buildings");
        ArrayList<BuildingDb> buildingDbs = buildingRepo.findAll();
        ArrayList<Building> buildings = new ArrayList<>();
        for(BuildingDb buildingDb : buildingDbs){
            Building building = refactorBuildingDbToBuilding(buildingDb.getBuildingId());
            buildings.add(building);
        }
        return buildings;
    }


    public Building getBuildingById(int id){
        logger.debug("Return one building: " + id);
        return refactorBuildingDbToBuilding(buildingRepo.findById(id).getBuildingId());
    }

    public Building saveNewBuilding(Building building){
        logger.debug("Save new building in db: " + building);
        ArrayList<Integer> floorDbs = new ArrayList<>();
        for(Level o : building.getImmoveables()){
            Integer id = floorService.saveNewFloor(o).getId();
            floorDbs.add(id);
        }
        int id = 1;
        BuildingDb buildingDbId = buildingRepo.findFirstByOrderByBuildingIdDesc();
        if(buildingDbId != null){
            id = buildingDbId.getBuildingId() + 1;
        }
        BuildingDb buildingDb = new BuildingDb(id, building.getName(), floorDbs);
        buildingRepo.save(buildingDb);
        return refactorBuildingDbToBuilding(buildingDb.getBuildingId());
    }

    @Override
    public Building refactorBuildingDbToBuilding(int id){
        logger.debug("Change database entity type to business logic class: " + id);
        BuildingDb buildingDb = buildingRepo.findById(id);
        ArrayList<Level> levels = new ArrayList<>();
        boolean nullable = false;
        for(Integer i : buildingDb.getFloors()){
            Level level = floorService.refactorFloorDbToLevel(i);
            if(level !=null){
                logger.debug("Level is null. We have to update list of Levels.");
                levels.add(level);
            }else{
                nullable = true;
            }
        }
        Building building = new Building(buildingDb.getBuildingId(), buildingDb.getName(), levels);
        if(nullable){
            updateBuilding(building);
        }
        return building;
    }

    @Override
    public float calculateSurface(int id) {
        logger.debug("Calculate complete surface of building: " + id);
        if(buildingRepo.findById(id)!= null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateSurface();
        }
        logger.info("Cannot calculate surface: "+ id);
        return -1f;
    }

    @Override
    public float calculateCubature(int id) {
        logger.debug("Calculate complete cubature of building: " + id);
        if(buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return building.calculateCubature();
        }
        logger.info("Cannot calculate cubature: "+ id);
        return -1f;
    }

    @Override
    public float calculateHeating(int id) {
        logger.debug("Calculate complete heating of building: " + id);
        if(buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return (building.calculateHeating()/building.calculateCubature());
        }
        logger.info("Cannot calculate heating: "+ id);
        return -1f;
    }

    @Override
    public float calculateLighting(int id) {
        logger.debug("Calculate complete lighting of building: " + id);
        if (buildingRepo.findById(id) != null) {
            Building building = refactorBuildingDbToBuilding(id);
            return (building.calculateLighting()/building.calculateSurface());
        }
        logger.info("Cannot calculate lighting: "+ id);
        return -1f;
    }

    @Override
    public void deleteById(int id) {
        logger.debug("Delete building: " + id);
        BuildingDb buildingDb = buildingRepo.findById(id);
        if(buildingDb != null){
            for(Integer i : buildingDb.getFloors()){
                floorService.deleteById(i);
            }
            buildingRepo.deleteByBuildingId(id);
        }
    }

    @Override
    public Building updateBuilding(Building building) {
        logger.debug("Update building info stored in db: " + building);
        BuildingDb  buildingDb = buildingRepo.findById(building.getId());
        if(buildingDb != null){
            buildingDb.setName(building.getName());
            ArrayList<Level> updatedFloors = floorService.updateFloors(building.getImmoveables());
            ArrayList<Integer> levelsId = new ArrayList<>();
            for(Level level : updatedFloors){
                levelsId.add(level.getId());
            }
            buildingDb.setFloors(levelsId);
            buildingRepo.save(buildingDb);
            return refactorBuildingDbToBuilding(buildingDb.getBuildingId());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        logger.debug("Delete all buildings");
        buildingRepo.deleteAll();
    }
}
