package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class RoomDb {

    @Id
    private int roomId;

    private String name;

    private float surface;

    private float cubature;

    private float heating;

    private float lightning;

    public RoomDb() {
    }

    public RoomDb(int roomId, String name, float surface, float cubature, float heating, float lightning) {
        this.roomId = roomId;
        this.name = name;
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lightning = lightning;
    }

    public RoomDb(int roomId, float surface, float cubature, float heating, float lightning) {
        this.roomId = roomId;
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lightning = lightning;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public float getCubature() {
        return cubature;
    }

    public void setCubature(float cubature) {
        this.cubature = cubature;
    }

    public float getHeating() {
        return heating;
    }

    public void setHeating(float heating) {
        this.heating = heating;
    }

    public float getLightning() {
        return lightning;
    }

    public void setLightning(float lightning) {
        this.lightning = lightning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomDb roomDb = (RoomDb) o;

        if (roomId != roomDb.roomId) return false;
        if (Float.compare(roomDb.surface, surface) != 0) return false;
        if (Float.compare(roomDb.cubature, cubature) != 0) return false;
        if (Float.compare(roomDb.heating, heating) != 0) return false;
        if (Float.compare(roomDb.lightning, lightning) != 0) return false;
        return name != null ? name.equals(roomDb.name) : roomDb.name == null;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surface != +0.0f ? Float.floatToIntBits(surface) : 0);
        result = 31 * result + (cubature != +0.0f ? Float.floatToIntBits(cubature) : 0);
        result = 31 * result + (heating != +0.0f ? Float.floatToIntBits(heating) : 0);
        result = 31 * result + (lightning != +0.0f ? Float.floatToIntBits(lightning) : 0);
        return result;
    }
}
