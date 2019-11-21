package com.put.buildinginfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("room")
public class RoomController {
    public static final String ROOM_NOT_FOUND = "Room not found: ";
    private  final RoomServcie roomServcie;

    @Autowired
    public RoomController(RoomServcie roomServcie) {
        this.roomServcie = roomServcie;
    }

    @GetMapping("")
    public ArrayList<Room> getAllRooms() {
        return roomServcie.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable int id) {
        Room roomTmp = roomServcie.getRoomById(id);
        if(roomTmp != null) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/surface/{id}")
    public float getSurface(@PathVariable int id) {
        float roomTmp = roomServcie.getSurface(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/cubature/{id}")
    public float getCubature(@PathVariable int id) {
        float roomTmp = roomServcie.getCubature(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/lighting/{id}")
    public float getLighting(@PathVariable int id) {
        float roomTmp =  roomServcie.getLighting(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @GetMapping("/heating/{id}")
    public float getHeating(@PathVariable int id) {
        float roomTmp = roomServcie.getHeating(id);
        if(roomTmp != -1f) {
            return roomTmp;
        }
        throw new ImmovableNotFound(ROOM_NOT_FOUND + id);
    }

    @PostMapping("")
    public Room saveNewRoom(@RequestBody Room room) {
        return roomService.saveNewRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        return roomServcie.deleteById(id);
    }

    @PutMapping("")
    public Room updateRoom(@RequestBody Room room) {
        return roomServcie.updateRoom(room);
    }
}
