package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.FloorDb;
import com.put.buildinginfo.database.FloorRepo;
import com.put.buildinginfo.database.RoomDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FloorServiceImpl implements  FloorService{

    private final FloorRepo floorRepo;

    private final RoomService roomService;

    @Autowired
    public FloorServiceImpl(FloorRepo floorRepo, RoomService roomService) {
        this.floorRepo = floorRepo;
        this.roomService = roomService;
    }

    @Override
    public ArrayList<FloorDb> getAllFloors() {
        return floorRepo.findAll();
    }

    @Override
    public FloorDb getFloorById(int id) {
        return floorRepo.findById(id);
    }

    @Override
    public FloorDb saveNewFloor(Level level) {
        ArrayList<RoomDb> roomdbs = new ArrayList<>();
        for(Room r : level.getImmoveables()){
            RoomDb roomTmp = roomService.saveNewRoom(r);
            roomdbs.add(roomTmp);
        }
        //possible to refactor - auto generation of id in frontend
        FloorDb floorDb = new FloorDb((floorRepo.findFirstByOrderByFloorIdDesc().getFloorId() + 1), level.getName(), roomdbs);
        floorRepo.save(floorDb);
        return floorDb;
    }

    @Override
    public Level refactorFloorDbToLevel(int id) {
        FloorDb floorDb = floorRepo.findById(id);
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomDb roomdb : floorDb.getRooms()){
            Room room = roomService.refactorRoomDbToRoom(roomdb.getRoomId());
            rooms.add(room);
        }
        Level refLevel = new Level(floorDb.getFloorId(), floorDb.getName(), rooms);
        return refLevel;
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
            for(RoomDb roomDb : floorDb.getRooms()){
                roomService.deleteById(roomDb.getRoomId());
            }
            floorRepo.deleteById(id);
        }
    }

    @Override
    public FloorDb updateFloor(Level level) {
        FloorDb floorDb = floorRepo.findById(level.getId());
        if(floorDb != null){
            floorDb.setName(level.getName());
            floorDb.setRooms(roomService.updateRooms(level.getImmoveables()));
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
}
