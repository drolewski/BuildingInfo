package com.put.buildinginfo.applicationArchitecture;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingTest {

    Building building;
    ArrayList<Level> levels;

    @Before
    public void setUp(){
        Room room1 = new Room(1, "test1", 25.6f, 100.3f, 150f, 300f);
        Room room2 = new Room(2, "test2", 25.6f, 100.3f, 150f, 300f);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        Level level1 = new Level(1, "levelTest", rooms);
        Level level2 = new Level(1, "levelTest", rooms);

        levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        building = new Building(1, "buildingTest", levels);
    }

    @Test
    public void calculateSurfaceTest(){
        float sum = 0f;
        for(Level r: levels){
            sum += r.calculateSurface();
        }
        assertEquals(building.calculateSurface(), sum);
    }

    @Test
    public void calculateCubatureTest(){
        float sum = 0f;
        for(Level r: levels){
            sum += r.calculateCubature();
        }
        assertEquals(building.calculateCubature(), sum);
    }

    @Test
    public void calculateHeatingTest(){
        float sum = 0f;
        for(Level r: levels){
            sum += r.calculateHeating();
        }
        assertEquals(building.calculateHeating(), sum);
    }

    @Test
    public void calculateLightingTest(){
        float sum = 0f;
        for(Level r: levels){
            sum += r.calculateLighting();
        }
        assertEquals(building.calculateLighting(), sum);
    }

    @Test
    public void calculateSurfaceNoExistTest(){
        Building empty = new Building(2, "null", null);
        assertEquals(empty.calculateSurface(), 0f);
    }

    @Test
    public void calculateCubatureNoExistTest(){
        Building empty = new Building(2, "null", null);
        assertEquals(empty.calculateCubature(), 0f);
    }

    @Test
    public void calculateHeatingNoExistTest(){
        Building empty = new Building(2, "null", null);
        assertEquals(empty.calculateHeating(), 0f);
    }

    @Test
    public void calculateLightingNoExistTest(){
        Building empty = new Building(2, "null", null);
        assertEquals(empty.calculateLighting(), 0f);
    }

}