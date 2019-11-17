package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends MongoRepository<BuildingDb, Integer> {

    BuildingDb findFirstByOrderByBuildingIdDesc();
}
