package com.put.buildinginfo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("list")
public class Controller {

    Controller() {

    }

//    @GetMapping("/room")
//    List<Room> getAllRooms() {
//
//    }

//    @GetMapping("/room/{id}")
//    Room getOneRoom(@PathVariable Long id) {
//
//    }

//    @GetMapping("/level")
//    List<Level> getAllLevels() {
//
//    }

//    @GetMapping("/level/{id}")
//    Level getOneLevel(@PathVariable Long id) {
//
//    }

//    @GetMapping("/building")
//    List<Buildings> getAllBuildings() {
//
//    }

//    @GetMapping("/building/{id}")
//    Level getOneBuilding(@PathVariable Long id) {
//
//    }

//    @PostMapping("/room")
//    Room newRoom(@RequestBody Room newRoom) {
//
//    }

//    @PostMapping("/level")
//    Level newLevel(@RequestBody Level newLevel) {
//
//    }

//    @PostMapping("/building")
//    Building newBuilding(@RequestBody Building newBuilding) {
//
//    }

//    @PutMapping("/room/{id}")
//    Room replaceRoom(@RequestBody Room newRoom, @PathVariable Long id) {
//
//    }

//    @PutMapping("/level/{id}")
//    Level replaceLevel(@RequestBody Level newLevel, @PathVariable Long id) {
//
//    }

//    @PutMapping("/building/{id}")
//    Building replaceBuilding(@RequestBody Building newBuilding, @PathVariable Long id) {
//
//    }

    @DeleteMapping("/room/{id}")
    void deleteRoom(@PathVariable Long id) {

    }

    @DeleteMapping("/level/{id}")
    void deleteLevel(@PathVariable Long id) {

    }

    @DeleteMapping("/building/{id}")
    void deleteBuilding(@PathVariable Long id) {

    }

}
