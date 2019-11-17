package com.put.buildinginfo.applicationArchitecture;

public class Room extends Immovable {
    private float surface;
    private float cubature;
    private float heating;
    private float lighting;

    public Room(int id, String name, float surface, float cubature, float heating, float lighting) {
        super(id, name);
        this.surface = surface;
        this.cubature = cubature;
        this.heating = heating;
        this.lighting = lighting;
    }

    public Room() {
    }

    public float getSurface(){
        return this.surface;
    }
    public void setSurface(float surface){
        this.surface=surface;
    }
    public float getCubature(){
        return this.cubature;
    }
    public void setCubature(float cubature){
        this.cubature=cubature;
    }
    public float getLighting(){
        return this.lighting;
    }
    public void setLighting(float lighting){
        this.lighting=lighting;
    }
    public float getHeating(){
        return this.heating;
    }
    public void setHeating(float heating){
        this.heating=heating;
    }
    public float calculateSurface(){
        return this.surface;
    }
    public float calculateCubature(){
        return this.cubature;
    }
    public float calculateLighting(){
        return this.lighting;
    }
    public float calculateHeating(){
        return this.heating;
    }

}
