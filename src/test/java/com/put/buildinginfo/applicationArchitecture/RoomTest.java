package com.put.buildinginfo.applicationArchitecture;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoomTest {

    Room room;
    @Before
    public void setUp(){
        room = new Room(1, "test", 25.6f, 100.3f, 150f, 300f);
    }

    @Test
    public void calculateCubatureTest(){
        assertEquals(room.calculateCubature(), room.getCubature());
    }

    @Test
    public void calculateSurfaceTest(){
        assertEquals(room.calculateSurface(), room.getSurface());
    }

    @Test
    public void calculateHeatingTest(){
        assertEquals(room.calculateHeating(), room.getHeating());
    }

    @Test
    public void calculateLightingTest(){
        assertEquals(room.calculateLighting(), room.getLighting());
    }

}