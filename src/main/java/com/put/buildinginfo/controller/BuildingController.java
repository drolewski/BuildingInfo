package com.put.buildinginfo.controller;

import java.util.ArrayList;
import java.util.List;

import com.put.buildinginfo.applicationArchitecture.Building;
import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.exception.ImmovableNotFound;
import com.put.buildinginfo.service.BuildingServiceImpl;
import com.put.buildinginfo.service.FloorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("building")
public class BuildingController {

    private static final String BUILDING_NOT_FOUND = "Building not found: ";
    private final BuildingServiceImpl buildingService;

    @Autowired
    public BuildingController(BuildingServiceImpl buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("")
    public ArrayList<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable int id) {
        Building buildingTmp = buildingService.getBuildingById(id);
        if(buildingTmp != null) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float calculateSurface(@PathVariable int id) {
        float buildingTmp = buildingService.calculateSurface(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float calculateCubature(@PathVariable int id) {
        float buildingTmp = buildingService.calculateCubature(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float calculateLighting(@PathVariable int id) {
        float buildingTmp =  buildingService.calculateLighting(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float calculateHeating(@PathVariable int id) {
        float buildingTmp = buildingService.calculateHeating(id);
        if(buildingTmp != -1f) {
            return buildingTmp;
        }
        throw new ImmovableNotFound(BUILDING_NOT_FOUND + id);
    }

    @PostMapping("")
    public Building saveNewFloor(@RequestBody Building building) {
        return buildingService.saveNewBuilding(building);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)  {
        buildingService.deleteById(id);
    }

    @PutMapping("")
    public Building updateBuilding(@RequestBody Building building) {
        return buildingService.updateBuilding(building);
    }



}
