package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

/**
 * The type Level.
 */
public class Level extends Composite<Room> {

    /**
     * Instantiates a new Level.
     *
     * @param id    the level identifier
     * @param name  the level name
     * @param rooms the rooms on the level
     */
    public Level(int id, String name, ArrayList<Room> rooms) {
        super(id, name, rooms);
    }
}
