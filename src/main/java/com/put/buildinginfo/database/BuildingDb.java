package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * The type Building data base.
 */
@Document
public class BuildingDb {

    @Id
    private int buildingId;

    private String name;

    private List<Integer> floors;

    /**
     * Instantiates a new Building data base.
     *
     * @param buildingId the building identifier
     * @param floors     the building floors
     */
    public BuildingDb(int buildingId, List<Integer> floors) {
        this.buildingId = buildingId;
        this.floors = floors;
    }

    /**
     * Instantiates a new Building data base.
     *
     * @param buildingId the building identifier
     * @param name       the building name
     * @param floors     the building floors
     */
    public BuildingDb(int buildingId, String name, List<Integer> floors) {
        this.buildingId = buildingId;
        this.name = name;
        this.floors = floors;
    }

    /**
     * Instantiates a new Building data base.
     */
    public BuildingDb() {
    }

    /**
     * Gets the building identifier.
     *
     * @return the building identifier.
     */
    public int getBuildingId() {
        return buildingId;
    }


    /**
     * Sets the building identifier.
     *
     * @param buildingId the building identifier
     */
    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * Gets the building name.
     *
     * @return the building name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the building name.
     *
     * @param name the building name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the building floors.
     *
     * @return the building floors
     */
    public List<Integer> getFloors() {
        return floors;
    }


    /**
     * Sets the building floors.
     *
     * @param floors the building floors
     */
    public void setFloors(List<Integer> floors) {
        this.floors = floors;
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

        BuildingDb that = (BuildingDb) o;

        if (buildingId != that.buildingId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return floors != null ? floors.equals(that.floors) : that.floors == null;
    }

    /**
     * Hash code.
     *
     * @return the int hash Code
     */
    @Override
    public int hashCode() {
        int result = buildingId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (floors != null ? floors.hashCode() : 0);
        return result;
    }
}
