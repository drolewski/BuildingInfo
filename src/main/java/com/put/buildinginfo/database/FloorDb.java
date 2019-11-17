package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class FloorDb {

    @Id
    private int floorId;

    private String name;

    private List<RoomDb> rooms;


    public FloorDb(int floorId, List<RoomDb> rooms) {
        this.floorId = floorId;
        this.rooms = rooms;
    }

    public FloorDb(int floorId, String name, List<RoomDb> rooms) {
        this.floorId = floorId;
        this.rooms = rooms;
        this.name = name;
    }

    public FloorDb() {
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public List<RoomDb> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDb> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloorDb floorDb = (FloorDb) o;

        if (floorId != floorDb.floorId) return false;
        if (rooms != null ? !rooms.equals(floorDb.rooms) : floorDb.rooms != null) return false;
        return name != null ? name.equals(floorDb.name) : floorDb.name == null;
    }

    @Override
    public int hashCode() {
        int result = floorId;
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
