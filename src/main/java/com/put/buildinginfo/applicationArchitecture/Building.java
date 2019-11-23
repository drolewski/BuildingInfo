package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

public class Building extends Composite<Level> {

    public Building(int id, String name, ArrayList<Level> immoveables) {
        super(id, name, immoveables);
    }
}
