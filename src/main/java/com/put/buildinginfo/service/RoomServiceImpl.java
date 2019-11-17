package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.RoomDb;
import com.put.buildinginfo.database.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public List<RoomDb> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public RoomDb getRoomById(int id) {
        return roomRepo.findByRoomId(id);
    }

    @Override
    public RoomDb saveNewRoom(Room room) {
        RoomDb roomDb = new RoomDb(room.getId(), room.getName(), room.getSurface(),
                                    room.getCubature(), room.getHeating(), room.getLighting());
        roomRepo.save(roomDb);
        return roomDb;
    }
}
