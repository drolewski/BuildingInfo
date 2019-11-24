package com.put.buildinginfo.controller;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.exception.ImmovableNotFound;
import com.put.buildinginfo.service.FloorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("level")
public class LevelController {
    private final Logger logger = LoggerFactory.getLogger(LevelController.class);

    private static final String LEVEL_NOT_FOUND = "Level not found: ";
    private final FloorServiceImpl floorService;

    @Autowired
    public LevelController(FloorServiceImpl floorService) { this.floorService = floorService; }

    @GetMapping("")
    public ArrayList<Level> getAllFloors() {
        logger.info("[GET] - /level");
        return floorService.getAllFloors();
    }

    @GetMapping("/{id}")
    public Level getFloorById(@PathVariable int id) {
        logger.info("[GET] - /level/{id}");
        Level levelTmp = floorService.getFloorById(id);
        if(levelTmp != null) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float calculateSurface(@PathVariable int id) {
        logger.info("[GET] - /level/surface/{id}");
        float levelTop = floorService.calculateSurface(id);
        if(levelTop != -1f) {
            return levelTop;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float calculateCubature(@PathVariable int id) {
        logger.info("[GET] - /level/cubature/{id}");
        float levelTmp = floorService.calculateCubature(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float calculateLighting(@PathVariable int id) {
        logger.info("[GET] - /level/lighting/{id}");
        float levelTmp =  floorService.calculateLighting(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float calculateHeating(@PathVariable int id) {
        logger.info("[GET] - /level/heating/{id}");
        float levelTmp = floorService.calculateHeating(id);
        if(levelTmp != -1f) {
            return levelTmp;
        }
        throw new ImmovableNotFound(LEVEL_NOT_FOUND + id);
    }

    @PostMapping("")
    public Level saveNewFloor(@RequestBody Level level) {
        logger.info("[POST] - /level");
        return floorService.saveNewFloor(level);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)  {
        logger.info("[DELETE] - /level/{id}");
        floorService.deleteById(id);
    }

    @PutMapping("")
    public Level updateFloor(@RequestBody Level level) {
        logger.info("[PUT] - /level");
        return floorService.updateFloor(level);
    }


    @PutMapping("/{id}/addRoom")
    public Level addRoomToFloor(@PathVariable int id, @RequestBody Room room){
        return floorService.addRoom(id, room);
    }

}
