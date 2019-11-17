package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepo extends MongoRepository<FloorDb, Integer> {

    FloorDb findFirstByOrderByFloorIdDesc();
    FloorDb findById(int id);
}
