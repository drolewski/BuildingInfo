package com.put.buildinginfo.applicationArchitecture;


/**
 * The type Room.
 */
public class Room extends Immovable {
    private float surface;
    private float cubature;
    private float heating;
    private float lighting;

    /**
     * Instantiates a new Room.
     *
     * @param id       the room identifier
     * @param name     the room name
     * @param surface  the room surface
     * @param cubature the room cubature
     * @param heating  the room heating
     * @param lighting the room lighting
     */
    public Room(int id, String name, float surface, float cubature, float heating, float lighting) {
        super(id, name);
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lighting = lighting;
    }

    /**
     * Instantiates a new Room.
     */
    public Room() {
    }

    /**
     * Get the room surface.
     *
     * @return the the room surface. The value will always be non-negative.
     */
    public float getSurface(){
        return this.surface;
    }

    /**
     * Set the room surface.
     *
     * @param surface the room surface
     */
    public void setSurface(float surface){
        this.surface=surface;
    }

    /**
     * Get the room cubature.
     *
     * @return the room cubature . The value will always be non-negative.
     */
    public float getCubature(){
        return this.cubature;
    }

    /**
     * Set the room cubature.
     *
     * @param cubature the room cubature
     */
    public void setCubature(float cubature){
        this.cubature=cubature;
    }

    /**
     * Get the room lighting.
     *
     * @return the room lighting. The value will always be non-negative.
     */
    public float getLighting(){
        return this.lighting;
    }

    /**
     * Set the room lighting.
     *
     * @param lighting the room lighting
     */
    public void setLighting(float lighting){
        this.lighting=lighting;
    }

    /**
     * Get the room heating.
     *
     * @return the room heating. The value will always be non-negative.
     */
    public float getHeating(){
        return this.heating;
    }

    /**
     * Set the room heating.
     *
     * @param heating the room heating
     */
    public void setHeating(float heating){
        this.heating=heating;
    }

    /**
     * Calculate the room surface.
     *
     * @return the room surface float. The value will always be non-negative.
     */
    public float calculateSurface(){
        return this.surface;
    }

    /**
     * Calculate the room cubature.
     *
     * @return the room cubature float. The value will always be non-negative.
     */
    public float calculateCubature(){
        return this.cubature;
    }

    /**
     * Calculate the room lighting.
     *
     * @return the lighting. The value will always be non-negative.
     */
    public float calculateLighting(){
        return this.lighting;
    }

    /**
     * Calculate the room heating.
     *
     * @return the heating. The value will always be non-negative.
     */
    public float calculateHeating(){
        return this.heating;
    }

}
