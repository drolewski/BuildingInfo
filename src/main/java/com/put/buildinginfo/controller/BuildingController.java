package com.put.buildinginfo.controller;

import com.put.buildinginfo.applicationArchitecture.Building;
import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.exception.ImmovableNotFound;
import com.put.buildinginfo.service.BuildingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("building")
public class BuildingController {
    private final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    private static final String BUILDING_NOT_FOUND = "Building not found: ";
    private final BuildingServiceImpl buildingService;

    @Autowired
    public BuildingController(BuildingServiceImpl buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("")
    public ArrayList<Building> getAllBuildings() {
        logger.info("[GET] - /building");
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable int id) {
        logger.info("[GET] - /building/{id}");
        Building buildingTmp = buildingService.getBuildingById(id);
        if(buildingTmp != null) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float calculateSurface(@PathVariable int id) {
        logger.info("[GET] - /building/surface/{id}");
        float buildingTmp = buildingService.calculateSurface(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float calculateCubature(@PathVariable int id) {
        logger.info("[GET] - /building/cubature/{id}");
        float buildingTmp = buildingService.calculateCubature(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float calculateLighting(@PathVariable int id) {
        logger.info("[GET] - /building/lighting/{id}");
        float buildingTmp =  buildingService.calculateLighting(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float calculateHeating(@PathVariable int id) {
        logger.info("[GET] - /building/heating/{id}");
        float buildingTmp = buildingService.calculateHeating(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @PostMapping("")
    public Building saveNewBuilding(@RequestBody Building building) {
        logger.info("[POST] - /building");
        return buildingService.saveNewBuilding(building);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)  {
        logger.info("[DELETE] - /building/{id}");
        buildingService.deleteById(id);
    }

    @PutMapping("")
    public Building updateBuilding(@RequestBody Building building) {
        logger.info("[PUT] - /building");
        return buildingService.updateBuilding(building);
    }

    @PutMapping("/{id}/addLevel")
    public Building addNewLevel(@PathVariable int id,  @RequestBody Level level){
        logger.info("[PUT] - /"+id+"/addLevel");
        Building building = buildingService.addLevel(id, level);
        if(building != null){
            return building;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

}
