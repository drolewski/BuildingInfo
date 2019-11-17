package com.put.buildinginfo.applicationArchitecture;

public abstract class Immovable {
    private int id;
    private  String name;

    public abstract float calculateCubature();
    public abstract float calculateSurface();
    public abstract float calculateLighting();
    public abstract float calculateHeating();

    public Immovable() {
    }

    public Immovable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
