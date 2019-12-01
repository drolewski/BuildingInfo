package com.put.buildinginfo.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * The interface Room repo.
 */
@Repository
public interface RoomRepo extends MongoRepository<RoomDb, Integer> {

    /**
     * Find first by order by room id desc room db.
     *
     * @return the room data base
     */
    RoomDb findFirstByOrderByRoomIdDesc();

    /**
     * Find by room id room data base.
     *
     * @param id the identifier
     * @return the room data base
     */
    RoomDb findByRoomId(int id);

    /**
     * Find all array list.
     *
     * @return the array list
     */
    ArrayList<RoomDb> findAll();

    /**
     * Delete by room identifier.
     *
     * @param id the room identifier
     */
    void deleteByRoomId(int id);
}
