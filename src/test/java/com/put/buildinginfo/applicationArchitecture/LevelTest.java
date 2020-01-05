package com.put.buildinginfo.applicationArchitecture;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LevelTest {

    Level level;
    ArrayList<Room> rooms;

    @Before
    public void setUp(){
        Room room1 = new Room(1, "test1", 25.6f, 100.3f, 150f, 300f);
        Room room2 = new Room(2, "test2", 25.6f, 100.3f, 150f, 300f);
        rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        level = new Level(1, "levelTest", rooms);
    }

    @Test
    public void calculateSurfaceTest(){
        float sum = 0f;
        for(Room r: rooms){
            sum += r.calculateSurface();
        }
        assertEquals(level.calculateSurface(), sum);
    }

    @Test
    public void calculateCubatureTest(){
        float sum = 0f;
        for(Room r: rooms){
            sum += r.calculateCubature();
        }
        assertEquals(level.calculateCubature(), sum);
    }

    @Test
    public void calculateHeatingTest(){
        float sum = 0f;
        for(Room r: rooms){
            sum += r.calculateHeating();
        }
        assertEquals(level.calculateHeating(), sum);
    }

    @Test
    public void calculateLightingTest(){
        float sum = 0f;
        for(Room r: rooms){
            sum += r.calculateLighting();
        }
        assertEquals(level.calculateLighting(), sum);
    }

    @Test
    public void calculateSurfaceNoExistTest(){
        Level empty = new Level(2, "null", null);
        assertEquals(empty.calculateSurface(), 0f);
    }

    @Test
    public void calculateCubatureNoExistTest(){
        Level empty = new Level(2, "null", null);
        assertEquals(empty.calculateCubature(), 0f);
    }

    @Test
    public void calculateHeatingNoExistTest(){
        Level empty = new Level(2, "null", null);
        assertEquals(empty.calculateHeating(), 0f);
    }

    @Test
    public void calculateLightingNoExistTest(){
        Level empty = new Level(2, "null", null);
        assertEquals(empty.calculateLighting(), 0f);
    }
}