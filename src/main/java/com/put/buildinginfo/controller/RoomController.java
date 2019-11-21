package com.put.buildinginfo.controller;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.exception.ImmovableNotFound;
import com.put.buildinginfo.service.RoomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("room")
public class RoomController {
    private final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private static final String ROOM_NOT_FOUND = "Room not found: ";
    private  final RoomServiceImpl roomService;

    @Autowired
    public RoomController(RoomServiceImpl roomServcie) {
        this.roomService = roomServcie;
    }

    @GetMapping("")
    public ArrayList<Room> getAllRooms() {
        logger.info("[GET] - /room");
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable int id) {
        logger.info("[GET] - /room/{id}");
        Room roomTmp = roomService.getRoomById(id);
        if(roomTmp != null) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float getSurface(@PathVariable int id) {
        logger.info("[GET] - /room/surface/{id}");
        float roomTmp = roomService.getSurface(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float getCubature(@PathVariable int id) {
        logger.info("[GET] - /room/cubature/{id}");
        float roomTmp = roomService.getCubature(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float getLighting(@PathVariable int id) {
        logger.info("[GET] - /room/lighting/{id}");
        float roomTmp =  roomService.getLighting(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float getHeating(@PathVariable int id) {
        logger.info("[GET] - /room/heating/{id}");
        float roomTmp = roomService.getHeating(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @PostMapping("")
    public Room saveNewRoom(@RequestBody Room room) {
        logger.info("[POST] - /room");
        return roomService.saveNewRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)  {
        logger.info("[DELETE] - /room/{id}");
        roomService.deleteById(id);
    }

    @PutMapping("")
    public Room updateRoom(@RequestBody Room room) {
        logger.info("[PUT] - /room");
        return roomService.updateRoom(room);
    }
}
