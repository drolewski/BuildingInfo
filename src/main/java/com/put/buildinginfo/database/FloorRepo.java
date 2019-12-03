package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * The interface Floor repo.
 */
@Repository
public interface FloorRepo extends MongoRepository<FloorDb, Integer> {

    /**
     * Find first by order by floor id desc floor data base.
     *
     * @return the floor data base
     */
    FloorDb findFirstByOrderByFloorIdDesc();

    /**
     * Find by id floor data base.
     *
     * @param id the identifier
     * @return the floor data base
     */
    FloorDb findById(int id);

    /**
     * Find all array list.
     *
     * @return the array list
     */
    ArrayList<FloorDb> findAll();

    /**
     * Delete by floor identifier.
     *
     * @param id the floor identifier
     */
    void deleteByFloorId(int id);
}
