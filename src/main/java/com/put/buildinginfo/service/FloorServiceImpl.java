package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Level;
import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.FloorDb;
import com.put.buildinginfo.database.FloorRepo;
import com.put.buildinginfo.database.RoomDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<FloorDb> getAllFloors() {
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
        FloorDb floorDb = new FloorDb(level.getId(), level.getName(), roomdbs);
        floorRepo.save(floorDb);
        return floorDb;
    }
}
