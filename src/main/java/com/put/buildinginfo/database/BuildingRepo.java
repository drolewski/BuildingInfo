package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BuildingRepo extends MongoRepository<BuildingDb, Integer> {

    BuildingDb findFirstByOrderByBuildingIdDesc();
    BuildingDb findById(int id);
    ArrayList<BuildingDb> findAll();
}
