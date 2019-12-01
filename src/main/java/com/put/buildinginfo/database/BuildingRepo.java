package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * The interface Building repo.
 */
@Repository
public interface BuildingRepo extends MongoRepository<BuildingDb, Integer> {

    /**
     * Find first by order by building id desc building db.
     *
     * @return the building data base
     */
    BuildingDb findFirstByOrderByBuildingIdDesc();

    /**
     * Find by id building data base.
     *
     * @param id the identifier
     * @return the building data base
     */
    BuildingDb findById(int id);

    /**
     * Find all array list.
     *
     * @return the building array list
     */
    ArrayList<BuildingDb> findAll();

    /**
     * Delete by building id.
     *
     * @param id the id
     */
    void deleteByBuildingId(int id);
}
