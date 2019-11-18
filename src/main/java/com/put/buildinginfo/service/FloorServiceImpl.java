package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.FloorDb;
import com.put.buildinginfo.database.FloorRepo;
import com.put.buildinginfo.database.RoomDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FloorServiceImpl implements  FloorService{

    final Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    private final FloorRepo floorRepo;

    private final RoomService roomService;

    @Autowired
    public FloorServiceImpl(FloorRepo floorRepo, RoomService roomService) {
        this.floorRepo = floorRepo;
        this.roomService = roomService;
    }

    @Override
    public ArrayList<Level> getAllFloors() {
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
        return refactorFloorDbToLevel(floorRepo.findById(id).getFloorId());
    }

    @Override
    public FloorDb saveNewFloor(Level level) {
        ArrayList<Integer> roomdbs = new ArrayList<>();
        for(Room r : level.getImmoveables()){
            RoomDb roomTmp = roomService.saveNewRoom(r);
            roomdbs.add(roomTmp.getRoomId());
        }
        //possible to refactor - auto generation of id in frontend
        int id = 1;
        FloorDb floorDbId = floorRepo.findFirstByOrderByFloorIdDesc();
        if(floorDbId != null){
            id = floorDbId.getFloorId() + 1;
        }
        FloorDb floorDb = new FloorDb(id , level.getName(), roomdbs);
        floorRepo.save(floorDb);
        return floorDb;
    }

    @Override
    public Level refactorFloorDbToLevel(int id) {
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
        if(floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateSurface();
        }
        return -1f;
    }

    @Override
    public float calculateCubature(int id) {
        if(floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateCubature();
        }
        return -1f;
    }

    @Override
    public float calculateHeating(int id) {
        if (floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateHeating();
        }
        return -1f;
    }

    @Override
    public float calculateLighting(int id) {
        if (floorRepo.findById(id) != null) {
            Level level = refactorFloorDbToLevel(id);
            return level.calculateLighting();
        }
        return -1f;
    }

    @Override
    public void deleteById(int id) {
        FloorDb floorDb = floorRepo.findById(id);
        if(floorDb != null){
            for(Integer i : floorDb.getRooms()){
                roomService.deleteById(i);
            }
            floorRepo.deleteByFloorId(id);
        }
    }

    @Override
    public FloorDb updateFloor(Level level) {
        FloorDb floorDb = floorRepo.findById(level.getId());
        if(floorDb != null){
            floorDb.setName(level.getName());
            ArrayList<RoomDb> updatedRooms = roomService.updateRooms(level.getImmoveables());
            ArrayList<Integer> roomsId = new ArrayList<>();
            for(RoomDb roomDb : updatedRooms){
                roomsId.add(roomDb.getRoomId());
            }
            floorDb.setRooms(roomsId);
            floorRepo.save(floorDb);
        }
        return floorDb;
    }

    @Override
    public ArrayList<FloorDb> updateFloors(ArrayList<Level> levels) {
        ArrayList<FloorDb> floorDbs = new ArrayList<>();
        for(Level level : levels){
            FloorDb floorDb = updateFloor(level);
            if(floorDb != null){
                floorDbs.add(floorDb);
            }
        }
        return floorDbs;
    }

    @Override
    public void deleteAll() {
        floorRepo.deleteAll();
    }
}
