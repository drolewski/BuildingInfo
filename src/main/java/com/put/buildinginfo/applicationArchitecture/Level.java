package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

public class Level extends Composite<Room> {

    public Level(int id, String name, ArrayList<Room> rooms) {
        super(id, name, rooms);
    }
}
