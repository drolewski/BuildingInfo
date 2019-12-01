package com.put.buildinginfo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * The type Room data base.
 */
@Document
public class RoomDb {

    @Id
    private int roomId;

    private String name;

    private float surface;

    private float cubature;

    private float heating;

    private float lightning;

    /**
     * Instantiates a new Room data base.
     */
    public RoomDb() {
    }

    /**
     * Instantiates a new Room data base.
     *
     * @param roomId    the room identifier
     * @param name      the room name
     * @param surface   the room surface
     * @param cubature  the room cubature
     * @param heating   the room heating
     * @param lightning the room lightning
     */
    public RoomDb(int roomId, String name, float surface, float cubature, float heating, float lightning) {
        this.roomId = roomId;
        this.name = name;
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lightning = lightning;
    }

    /**
     * Instantiates a new Room data base.
     *
     * @param roomId    the room identifier
     * @param surface   the room surface
     * @param cubature  the room cubature
     * @param heating   the room heating
     * @param lightning the room lightning
     */
    public RoomDb(int roomId, float surface, float cubature, float heating, float lightning) {
        this.roomId = roomId;
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lightning = lightning;
    }

    /**
     * Gets room identifier.
     *
     * @return the room identifier
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Sets room identifier.
     *
     * @param roomId the room identifier
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets the room name.
     *
     * @return the room name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the room name.
     *
     * @param name the room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the room surface.
     *
     * @return the room surface
     */
    public float getSurface() {
        return surface;
    }

    /**
     * Sets the room surface.
     *
     * @param surface the room surface
     */
    public void setSurface(float surface) {
        this.surface = surface;
    }

    /**
     * Gets the room cubature.
     *
     * @return the room cubature
     */
    public float getCubature() {
        return cubature;
    }

    /**
     * Sets the room cubature.
     *
     * @param cubature the room cubature
     */
    public void setCubature(float cubature) {
        this.cubature = cubature;
    }

    /**
     * Gets the room heating.
     *
     * @return the room heating
     */
    public float getHeating() {
        return heating;
    }

    /**
     * Sets the room heating.
     *
     * @param heating the room heating
     */
    public void setHeating(float heating) {
        this.heating = heating;
    }

    /**
     * Gets the room lightning.
     *
     * @return the room lightning
     */
    public float getLightning() {
        return lightning;
    }

    /**
     * Sets the room lightning.
     *
     * @param lightning the room lightning
     */
    public void setLightning(float lightning) {
        this.lightning = lightning;
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

        RoomDb roomDb = (RoomDb) o;

        if (roomId != roomDb.roomId) return false;
        if (Float.compare(roomDb.surface, surface) != 0) return false;
        if (Float.compare(roomDb.cubature, cubature) != 0) return false;
        if (Float.compare(roomDb.heating, heating) != 0) return false;
        if (Float.compare(roomDb.lightning, lightning) != 0) return false;
        return name != null ? name.equals(roomDb.name) : roomDb.name == null;
    }

    /**
     * Hash code.
     *
     * @return the int hash Code
     */
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
