package com.example.waterprovider.controller;

import com.example.waterprovider.DTO.Api;
import com.example.waterprovider.model.History;
import com.example.waterprovider.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class HistoryController {

    private final HistoryService historyService;

    Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @GetMapping("histories")
    public ResponseEntity<List<History>> getHistory(){
        logger.info("Request in get history ");
        return ResponseEntity.status(200).body(historyService.getHistory());
    }


}
