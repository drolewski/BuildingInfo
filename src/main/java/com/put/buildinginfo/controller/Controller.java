package com.put.buildinginfo.controller;

import com.put.buildinginfo.service.BuildingServiceImpl;
import com.put.buildinginfo.service.FloorServiceImpl;
import com.put.buildinginfo.service.RoomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final RoomServiceImpl roomService;
    private final FloorServiceImpl floorService;
    private final BuildingServiceImpl buildingService;

    @Autowired
    private Controller(RoomServiceImpl roomService, FloorServiceImpl floorService, BuildingServiceImpl buildingService){
        this.roomService = roomService;
        this.floorService = floorService;
        this.buildingService = buildingService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        logger.info("Clear MongoDB collection.");
        roomService.deleteAll();
        floorService.deleteAll();
        buildingService.deleteAll();
    }
}
