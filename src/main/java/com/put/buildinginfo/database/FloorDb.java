package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The type Floor data base.
 */
@Document
public class FloorDb {

    @Id
    private int floorId;

    private String name;

    private List<Integer> rooms;


    /**
     * Instantiates a new Floor data base.
     *
     * @param floorId the floor identifier
     * @param rooms   the floor rooms list
     */
    public FloorDb(int floorId, List<Integer> rooms) {
        this.floorId = floorId;
        this.rooms = rooms;
    }

    /**
     * Instantiates a new Floor data base.
     *
     * @param floorId the floor identifier
     * @param name    the floor name
     * @param rooms   the floor rooms list
     */
    public FloorDb(int floorId, String name, List<Integer> rooms) {
        this.floorId = floorId;
        this.rooms = rooms;
        this.name = name;
    }

    /**
     * Instantiates a new Floor data base.
     */
    public FloorDb() {
    }

    /**
     * Gets the floor identifier.
     *
     * @return the floor identifier
     */
    public int getFloorId() {
        return floorId;
    }

    /**
     * Sets the floor identifier.
     *
     * @param floorId the floor identifier
     */
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    /**
     * Gets the floor rooms.
     *
     * @return the the floor rooms
     */
    public List<Integer> getRooms() {
        return rooms;
    }

    /**
     * Sets the floor rooms.
     *
     * @param rooms the floor rooms
     */
    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    /**
     * Gets the floor name.
     *
     * @return the floor name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the floor name.
     *
     * @param name the floor name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Equals method compare parameter object with itself.
     *
     * @param o object
     * @return the boolean value, true - means that comparing objects are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloorDb floorDb = (FloorDb) o;

        if (floorId != floorDb.floorId) return false;
        if (name != null ? !name.equals(floorDb.name) : floorDb.name != null) return false;
        return rooms != null ? rooms.equals(floorDb.rooms) : floorDb.rooms == null;
    }

    /**
     * Hash code.
     *
     * @return the int hash Code
     */
    @Override
    public int hashCode() {
        int result = floorId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }
}
