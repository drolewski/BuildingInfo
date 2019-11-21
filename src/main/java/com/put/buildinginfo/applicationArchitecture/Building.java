package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

public class Building extends Composite<Level> {

    public Building(int id, String name, ArrayList<Level> immoveables) {
        super(id, name, immoveables);
    }

    @Override
    public float calculateSurface() {
        float surface = 0f;
        for(Level l: this.getImmoveables()){
            surface += l.calculateSurface();
        }
        return surface;
    }

    @Override
    public float calculateCubature() {
        float cubature = 0f;
        for(Level l: this.getImmoveables()){
            cubature += l.calculateCubature();
        }
        return cubature;
    }

    @Override
    public float calculateLighting() {
        float ligting = 0f;
        for(Level l: this.getImmoveables()){
            ligting += l.calculateLighting();
        }
        return ligting;
    }

    @Override
    public float calculateHeating() {
        float heating = 0f;
        for(Level l: this.getImmoveables()){
            heating += l.calculateHeating();
        }
        return heating;
    }
}
