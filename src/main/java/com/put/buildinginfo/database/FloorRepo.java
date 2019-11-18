package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FloorRepo extends MongoRepository<FloorDb, Integer> {

    FloorDb findFirstByOrderByFloorIdDesc();
    FloorDb findById(int id);
    ArrayList<FloorDb> findAll();
}
