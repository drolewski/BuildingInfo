package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.RoomDb;
import com.put.buildinginfo.database.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public ArrayList<Room> getAllRooms() {
        ArrayList<RoomDb> roomDbs = roomRepo.findAll();
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomDb r : roomDbs){
            rooms.add(refactorRoomDbToRoom(r.getRoomId()));
        }
        return rooms;
    }

    @Override
    public Room getRoomById(int id) {
        RoomDb roomDb = roomRepo.findByRoomId(id);
        if(roomDb != null){
            return refactorRoomDbToRoom(roomDb.getRoomId());
        }
        return null;
    }

    @Override
    public Room saveNewRoom(Room room) {
        //possible to refactor - auto generation of id in frontend
        int id = 1;
        RoomDb roomDbId = roomRepo.findFirstByOrderByRoomIdDesc();
        if(roomDbId != null){
            id = roomDbId.getRoomId() + 1;
        }
        RoomDb roomDb = new RoomDb(id , room.getName(), room.getSurface(),
                                    room.getCubature(), room.getHeating(), room.getLighting());
        roomRepo.save(roomDb);
        Room roomRef = refactorRoomDbToRoom(roomDb.getRoomId());
        return roomRef;
    }

    @Override
    public Room refactorRoomDbToRoom(int id) {
        RoomDb roomDb = roomRepo.findByRoomId(id);
        if(roomDb != null) {
            Room room = new Room(roomDb.getRoomId(), roomDb.getName(),
                    roomDb.getSurface(), roomDb.getCubature(),
                    roomDb.getHeating(), roomDb.getLightning());
            return room;
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if(roomRepo.findByRoomId(id) != null){
            roomRepo.deleteByRoomId(id);
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
            return (roomRepo.findByRoomId(id).getHeating()/roomRepo.findByRoomId(id).getCubature());
        }
        return -1f;
    }

    @Override
    public float getLighting(int id) {
        if(roomRepo.findByRoomId(id) != null){
            return (roomRepo.findByRoomId(id).getLightning()/roomRepo.findByRoomId(id).getSurface());
        }
        return -1f;
    }

    @Override
    public Room updateRoom(Room room) {
        RoomDb roomDb = roomRepo.findByRoomId(room.getId());
        if(roomDb != null){
            roomDb.setName(room.getName());
            roomDb.setSurface(room.getSurface());
            roomDb.setCubature(room.getCubature());
            roomDb.setHeating(room.getHeating());
            roomDb.setLightning(room.getLighting());
            roomRepo.save(roomDb);
            return refactorRoomDbToRoom(roomDb.getRoomId());
        }
        return null;
    }

    @Override
    public ArrayList<Room> updateRooms(ArrayList<Room> rooms) {
        ArrayList<Room> roomDbs = new ArrayList<>();
        for(Room room : rooms) {
            Room roomUpdated = updateRoom(room);
            if (room != null) {
                roomDbs.add(roomUpdated);
            }
        }
        return roomDbs;
    }

    @Override
    public void deleteAll() {
        roomRepo.deleteAll();
    }
}
