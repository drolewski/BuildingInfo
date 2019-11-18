package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RoomRepo extends MongoRepository<RoomDb, Integer> {

    RoomDb findFirstByOrderByRoomIdDesc();
    RoomDb findByRoomId(int id);
    ArrayList<RoomDb> findAll();
}
