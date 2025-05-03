package com.jordep.dslist.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jordep.dslist.dto.GameListDTO;
import com.jordep.dslist.dto.ReplacementDTO;
import com.jordep.dslist.sevices.GameListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class GameListController {

    private final GameListService gameListService;

    @GetMapping(value = "/list/all")
    public List<GameListDTO> findAll() {
        List<GameListDTO> result = gameListService.findAll();
        return result;
    }

    @PostMapping(value = "/list/replacement")
    public void move(@RequestParam("id") Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }

}

