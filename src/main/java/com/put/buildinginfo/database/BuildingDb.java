package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class BuildingDb {

    @Id
    private int buildingId;

    private String name;

    private List<Integer> floors;

    public BuildingDb(int buildingId, List<Integer> floors) {
        this.buildingId = buildingId;
        this.floors = floors;
    }

    public BuildingDb(int buildingId, String name, List<Integer> floors) {
        this.buildingId = buildingId;
        this.name = name;
        this.floors = floors;
    }

    public BuildingDb() {
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getFloors() {
        return floors;
    }

    public void setFloors(List<Integer> floors) {
        this.floors = floors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildingDb that = (BuildingDb) o;

        if (buildingId != that.buildingId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return floors != null ? floors.equals(that.floors) : that.floors == null;
    }

    @Override
    public int hashCode() {
        int result = buildingId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (floors != null ? floors.hashCode() : 0);
        return result;
    }
}
