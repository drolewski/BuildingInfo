package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

public class Level extends Composite<Room> {

    public Level(int id, String name, ArrayList<Room> rooms) {
        super(id, name, rooms);
    }

    @Override
    public float calculateSurface() {
        float surface = 0f;
        for(Room room: this.getImmoveables()){
            surface += room.getSurface();
        }
        return surface;
    }

    @Override
    public float calculateCubature() {
        float cubature = 0f;
        for(Room room: this.getImmoveables()){
            cubature += room.getCubature();
        }
        return cubature;
    }

    @Override
    public float calculateLighting() {
        float lighting = 0f;
        for(Room room : this.getImmoveables()){
            lighting += room.getLighting();
        }
        return lighting;
    }

    @Override
    public float calculateHeating() {
        float heating = 0f;
        for (Room room: this.getImmoveables()){
            heating+=room.getHeating();
        }
        return heating;
    }
}
