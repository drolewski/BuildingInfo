package com.put.buildinginfo;

public abstract class Imoveables {
    protected int id;
    protected  String name;

    public abstract float calculateCubature();
    public abstract float calculateSurface();
    public abstract float calculateLighting();
    public abstract float calculateHeating();

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
