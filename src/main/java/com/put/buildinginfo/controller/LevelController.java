package com.put.buildinginfo.controller;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.exception.ImmovableNotFound;
import com.put.buildinginfo.service.FloorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("level")
public class LevelController {
    private static final String LEVEL_NOT_FOUND = "Level not found: ";
    private final FloorServiceImpl floorService;

    @Autowired
    public LevelController(FloorServiceImpl floorService) {
        this.floorService = floorService;
    }

    @GetMapping("")
    public ArrayList<Level> getAllFloors() {
        return floorService.getAllFloors();
    }

    @GetMapping("/{id}")
    public Level getFloorById(@PathVariable int id) {
        Level levelTmp = floorService.getFloorById(id);
        if(levelTmp != null) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float calculateSurface(@PathVariable int id) {
        float levelTop = floorService.calculateSurface(id);
        if(levelTop != -1f) {
            return levelTop;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float calculateCubature(@PathVariable int id) {
        float levelTmp = floorService.calculateCubature(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float calculateLighting(@PathVariable int id) {
        float levelTmp =  floorService.calculateLighting(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float calculateHeating(@PathVariable int id) {
        float levelTmp = floorService.calculateHeating(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }


}
