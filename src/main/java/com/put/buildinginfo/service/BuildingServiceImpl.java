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
//TODO Implementacja Delete w dwie strony
//TODO implementacja dociagania danych z bazy
//TODO zwracanie spójnych obiektów LEVEL/BUILDING/ROOM
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
    public ArrayList<BuildingDb> getAllBuildings(){
        return buildingRepo.findAll();
    }

    //returns one building by id
    public BuildingDb getBuildingById(int id){
        return buildingRepo.findById(id);
    }

    public BuildingDb saveNewBuilding(Building building){
        logger.info("Save new building in db");
        ArrayList<Integer> floorDbs = new ArrayList<>();
        for(Level o : building.getImmoveables()){
            Integer id = floorService.saveNewFloor(o).getFloorId();
            floorDbs.add(id);
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
        for(Integer i : buildingDb.getFloors()){
            Level level = floorService.refactorFloorDbToLevel(i);
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
            for(Integer i : buildingDb.getFloors()){
                floorService.deleteById(i);
            }
            buildingRepo.deleteById(id);
        }
    }

    @Override
    public BuildingDb updateBuilding(Building building) {
        BuildingDb  buildingDb = buildingRepo.findById(building.getId());
        if(buildingDb != null){
            buildingDb.setName(building.getName());
            ArrayList<FloorDb> updatedFloors = floorService.updateFloors(building.getImmoveables());
            ArrayList<Integer> levelsId = new ArrayList<>();
            for(FloorDb floorDb : updatedFloors){
                levelsId.add(floorDb.getFloorId());
            }
            buildingDb.setFloors(levelsId);
            buildingRepo.save(buildingDb);
        }
        return buildingDb;
    }
}
