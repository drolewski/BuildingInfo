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
        RoomDb roomDb = new RoomDb((roomRepo.findFirstByOrderByRoomIdDesc().getRoomId() + 1), room.getName(), room.getSurface(),
                                    room.getCubature(), room.getHeating(), room.getLighting());
        roomRepo.save(roomDb);
        return roomDb;
    }

    @Override
    public Room refactorRoomDbToRoom(int id) {
        RoomDb roomDb = roomRepo.findByRoomId(id);
        Room room = new Room(roomDb.getRoomId(),roomDb.getName(),
                            roomDb.getSurface(), roomDb.getCubature(),
                            roomDb.getHeating(), roomDb.getLightning());
        return room;
    }

    @Override
    public void deleteById(int id) {
        if(roomRepo.findByRoomId(id) != null){
            roomRepo.deleteById(id);
        }
    }

    @Override
    public float getSurface(int id) {
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getSurface();
        }
        return -1f;
    }

    @Override
    public float getCubature(int id) {
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getCubature();
        }
        return -1f;
    }

    @Override
    public float getHeating(int id) {
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getHeating();
        }
        return -1f;
    }

    @Override
    public float getLighing(int id) {
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getLightning();
        }
        return -1f;
    }
}
