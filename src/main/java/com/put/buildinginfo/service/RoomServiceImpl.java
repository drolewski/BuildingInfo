package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.RoomDb;
import com.put.buildinginfo.database.RoomRepo;
import com.put.buildinginfo.exception.ImmovableNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomServiceImpl implements RoomService {
    private final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final RoomRepo roomRepo;

    @Autowired
    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public ArrayList<Room> getAllRooms() {
        logger.debug("Get all rooms");
        ArrayList<RoomDb> roomDbs = roomRepo.findAll();
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomDb r : roomDbs){
            rooms.add(refactorRoomDbToRoom(r.getRoomId()));
        }
        return rooms;
    }

    @Override
    public Room getRoomById(int id) {
        logger.debug("Get one room: " + id);
        RoomDb roomDb = roomRepo.findByRoomId(id);
        if(roomDb != null){
            return refactorRoomDbToRoom(roomDb.getRoomId());
        }
        logger.debug("Room does not exist: " + id);
        return null;
    }

    @Override
    public Room saveNewRoom(Room room) {
        //possible to refactor - auto generation of id in frontend
        logger.debug("Add new room: " + room);
        int id = 1;
        RoomDb roomDbId = roomRepo.findFirstByOrderByRoomIdDesc();
        if(roomDbId != null){
            id = roomDbId.getRoomId() + 1;
        }
        RoomDb roomDb = new RoomDb(id , room.getName(), room.getSurface(),
                                    room.getCubature(), room.getHeating(), room.getLighting());
        roomRepo.save(roomDb);
        return refactorRoomDbToRoom(roomDb.getRoomId());
    }

    @Override
    public Room refactorRoomDbToRoom(int id) {
        logger.debug("Refactor Roomdb object to Room: " + id);
        RoomDb roomDb = roomRepo.findByRoomId(id);
        if(roomDb != null) {
            Room room = new Room(roomDb.getRoomId(), roomDb.getName(),
                    roomDb.getSurface(), roomDb.getCubature(),
                    roomDb.getHeating(), roomDb.getLightning());
            return room;
        }
        logger.debug("Room to refactor does not exists: " + id);
        return null;
    }

    @Override
    public void deleteById(int id) {
        logger.debug("Delete one room: " + id);
        if(roomRepo.findByRoomId(id) != null){
            roomRepo.deleteByRoomId(id);
            return;
        }
        throw new ImmovableNotFound("Room Not Found: " + id );
    }

    @Override
    public float getSurface(int id) {
        logger.debug("Calculate surface of room: " + id);
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getSurface();
        }
        return -1f;
    }

    @Override
    public float getCubature(int id) {
        logger.debug("Calculate cubature of room: " + id);
        if(roomRepo.findByRoomId(id) != null){
            return roomRepo.findByRoomId(id).getCubature();
        }
        return -1f;
    }

    @Override
    public float getHeating(int id) {
        logger.debug("Calculate heating of room: " + id);
        if(roomRepo.findByRoomId(id) != null){
            return (roomRepo.findByRoomId(id).getHeating()/roomRepo.findByRoomId(id).getCubature());
        }
        return -1f;
    }

    @Override
    public float getLighting(int id) {
        logger.debug("Calculate lighting of room: " + id);
        if(roomRepo.findByRoomId(id) != null){
            return (roomRepo.findByRoomId(id).getLightning()/roomRepo.findByRoomId(id).getSurface());
        }
        return -1f;
    }

    @Override
    public Room updateRoom(Room room) {
        logger.debug("Update room: "+ room);
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
        return saveNewRoom(room);
    }

    @Override
    public ArrayList<Room> updateRooms(ArrayList<Room> rooms) {
        logger.debug("Update list of rooms: " + rooms);
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
        logger.debug("Delete all rooms from db.");
        roomRepo.deleteAll();
    }
}
