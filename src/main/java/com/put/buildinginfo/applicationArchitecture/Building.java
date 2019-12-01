package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

/**
 * The type Building.
 */
public class Building extends Composite<Level> {

    /**
     * Instantiates a new Building.
     *
     * @param id          the building identifier
     * @param name        the building name
     * @param immoveables the levels in the building
     */
    public Building(int id, String name, ArrayList<Level> immoveables) {
        super(id, name, immoveables);
    }
}