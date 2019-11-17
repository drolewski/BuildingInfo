package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends MongoRepository<RoomDb, Integer> {

    RoomDb findFirstByOrderByRoomIdDesc();
}
