package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.FloorDb;
import com.put.buildinginfo.database.FloorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FloorServiceImpl implements  FloorService{

    private final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    private final FloorRepo floorRepo;

    private final RoomService roomService;

    @Autowired
    public FloorServiceImpl(FloorRepo floorRepo, RoomService roomService) {
        this.floorRepo = floorRepo;
        this.roomService = roomService;
    }

    @Override
    public ArrayList<Level> getAllFloors() {
        logger.debug("Get all floors");
        ArrayList<FloorDb> floordbs = floorRepo.findAll();
        ArrayList<Level> levels = new ArrayList<>();
        for(FloorDb floorDb : floordbs){
            Level level = refactorFloorDbToLevel(floorDb.getFloorId());
            levels.add(level);
        }
        return levels;
    }

    @Override
    public Level getFloorById(int id) {
        logger.debug("Get one floor: " + id);
        return refactorFloorDbToLevel(floorRepo.findById(id).getFloorId());
    }

    @Override
    public Level saveNewFloor(Level level) {
        logger.debug("Save new level in db: " + level);
        ArrayList<Integer> roomdbs = new ArrayList<>();
        for(Room r : level.getImmoveables()){
            Room roomTmp = roomService.saveNewRoom(r);
            roomdbs.add(roomTmp.getId());
        }
        //possible to refactor - auto generation of id in frontend
        int id = 1;
        FloorDb floorDbId = floorRepo.findFirstByOrderByFloorIdDesc();
        if(floorDbId != null){
            id = floorDbId.getFloorId() + 1;
        }
        FloorDb floorDb = new FloorDb(id , level.getName(), roomdbs);
        floorRepo.save(floorDb);
        Level levelRef = refactorFloorDbToLevel(floorDb.getFloorId());
        logger.debug("Added level: " + levelRef);
        return levelRef;
    }

    @Override
    public Level refactorFloorDbToLevel(int id) {
        logger.debug("Refactor FloorDb to Level: " + id);
        FloorDb floorDb = floorRepo.findById(id);
        boolean nullable = false;
        if(floorDb != null) {
            ArrayList<Room> rooms = new ArrayList<>();
            for (Integer i : floorDb.getRooms()) {
                Room room = roomService.refactorRoomDbToRoom(i);
                if(room != null){
                    logger.info("Room is null. We have to update list of Rooms.");
                    rooms.add(room);
                }else{
                    nullable = true;
                }
            }
            Level refLevel = new Level(floorDb.getFloorId(), floorDb.getName(), rooms);
            if(nullable){
                updateFloor(refLevel);
            }
            return refLevel;
        }
        return null;
    }

    @Override
    public float calculateSurface(int id) {
        logger.debug("Calculate Surface of the level: " + id);
        if(floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateSurface();
        }
        return -1f;
    }

    @Override
    public float calculateCubature(int id) {
        logger.debug("Calculate cubature of the level: " + id);
        if(floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateCubature();
        }
        return -1f;
    }

    @Override
    public float calculateHeating(int id) {
        logger.debug("Calculate heating of the level: " + id);
        if (floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return (level.calculateHeating()/level.calculateHeating());
        }
        return -1f;
    }

    @Override
    public float calculateLighting(int id) {
        logger.debug("Calculate lighting of the level: " + id);
        if (floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return (level.calculateLighting()/level.calculateSurface());
        }
        return -1f;
    }

    @Override
    public void deleteById(int id) {
        logger.debug("Delete floor from db: " + id);
        FloorDb floorDb = floorRepo.findById(id);
        if(floorDb != null){
            for(Integer i : floorDb.getRooms()){
                roomService.deleteById(i);
            }
            floorRepo.deleteByFloorId(id);
        }
    }

    @Override
    public Level updateFloor(Level level) {
        logger.debug("Update level in database: " + level);
        FloorDb floorDb = floorRepo.findById(level.getId());
        if(floorDb != null){
            floorDb.setName(level.getName());
            ArrayList<Room> updatedRooms = roomService.updateRooms(level.getImmoveables());
            ArrayList<Integer> roomsId = new ArrayList<>();
            for(Room roomTemp : updatedRooms){
                roomsId.add(roomTemp.getId());
            }
            floorDb.setRooms(roomsId);
            floorRepo.save(floorDb);
            return refactorFloorDbToLevel(floorDb.getFloorId());
        }
        return null;
    }

    @Override
    public ArrayList<Level> updateFloors(ArrayList<Level> levels) {
        logger.debug("Update many levels: " + levels);
        ArrayList<Level> floorDbs = new ArrayList<>();
        for(Level level : levels){
            Level levelUpdated = updateFloor(level);
            if(levelUpdated != null){
                floorDbs.add(levelUpdated);
            }
        }
        return floorDbs;
    }

    @Override
    public void deleteAll() {
        logger.debug("Delete all floors from db.");
        floorRepo.deleteAll();
    }
}
