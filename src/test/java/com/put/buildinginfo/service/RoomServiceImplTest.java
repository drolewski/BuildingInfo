package com.put.buildinginfo.service;

import com.put.buildinginfo.applicationArchitecture.Room;
import com.put.buildinginfo.database.RoomDb;
import com.put.buildinginfo.database.RoomRepo;
import com.put.buildinginfo.exception.ImmovableNotFound;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomServiceImplTest {

    RoomServiceImpl roomService;
    RoomRepo roomRepo;

    @Before
    public void setUp(){
        roomRepo = mock(RoomRepo.class);
        roomService = new RoomServiceImpl(roomRepo);
    }

    @Test
    public void getAllRoomsTest(){
        ArrayList<RoomDb> rooms = new ArrayList<>();
        rooms.add(new RoomDb(1, "test", 25.6f, 100.3f, 150f, 300f));
        when(roomRepo.findAll()).thenReturn(rooms);
        when(roomRepo.findByRoomId(rooms.get(0).getRoomId())).thenReturn(rooms.get(0));

        ArrayList<Room> result = new ArrayList<>();
        result.add(new Room(1, "test", 25.6f, 100.3f, 150f, 300f));
        assertEquals(result.get(0).getId(), roomService.getAllRooms().get(0).getId());
        assertEquals(result.get(0).getName(), roomService.getAllRooms().get(0).getName());
    }


    @Test
    public void getRoomByIdTest(){
        when(roomRepo.findByRoomId(1)).thenReturn(new RoomDb(1, "test", 25.6f, 100.3f, 150f, 300f));
        Room result = new Room(1, "test", 25.6f, 100.3f, 150f, 300f);
        assertEquals(result.getName(), roomService.getRoomById(1).getName());
    }

    @Test
    public void saveRoomTest(){
        when(roomRepo.findFirstByOrderByRoomIdDesc()).thenReturn(new RoomDb(10, "test", 25.6f, 100.3f, 150f, 300f));
        Room res = new Room(1, "test", 25.6f, 100.3f, 150f, 300f);

        when(roomRepo.findByRoomId(11)).thenReturn(new RoomDb(11, "test", 25.6f, 100.3f, 150f, 300f));
        assertEquals(res.getName(), roomService.saveNewRoom(res).getName());
    }

    @Test
    public void refactorRoomTest(){
        when(roomRepo.findByRoomId(11)).thenReturn(new RoomDb(11, "test", 25.6f, 100.3f, 150f, 300f));
        Room r = new Room(11, "test", 25.6f, 100.3f, 150f, 300f);
        assertEquals(roomService.refactorRoomDbToRoom(11).getName(), r.getName());
    }

    @Test
    public void refactorRoomNULLTest(){
        when(roomRepo.findByRoomId(11)).thenReturn(null);
        assertNull(roomService.refactorRoomDbToRoom(11));
    }

    @Test(expected = ImmovableNotFound.class)
    public void deleteByIdTest(){
        when(roomRepo.findByRoomId(1)).thenReturn(null);
        roomService.deleteById(1);
    }

    @Test
    public void updateRoomTest(){
        when(roomRepo.findByRoomId(11)).thenReturn(new RoomDb(11, "test", 25.6f, 100.3f, 150f, 300f));
        Room update = new Room(11, "test", 125.6f, 100.3f, 150f, 300f);
        assertEquals(roomService.updateRoom(update).getSurface(), update.getSurface());
    }

    @Test
    public void propertiesOfNonExistingRoomTest(){
        when(roomRepo.findByRoomId(11)).thenReturn(null);
        assertEquals(roomService.getSurface(11),-1f);
        assertEquals(roomService.getCubature(11),-1f);
        assertEquals(roomService.getHeating(11),-1f);
        assertEquals(roomService.getLighting(11),-1f);
    }

    @Test
    public void propertiesOfExitingRoomTest(){
        when(roomRepo.findByRoomId(11)).thenReturn(new RoomDb(11, "test", 25.6f, 100.3f, 150f, 300f));
        assertEquals(roomService.getSurface(11),25.6f);
        assertEquals(roomService.getCubature(11),100.3f);
        assertEquals(roomService.getHeating(11),150f/100.3f);
        assertEquals(roomService.getLighting(11),300f/25.6f);
    }
}