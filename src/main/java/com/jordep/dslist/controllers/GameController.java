package com.jordep.dslist.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jordep.dslist.dto.GameDTO;
import com.jordep.dslist.dto.GameMinDTO;
import com.jordep.dslist.sevices.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class GameController {

    private final GameService gameService;

    @GetMapping(value = "/game/all")
    public List<GameMinDTO> findAll() {
        List<GameMinDTO> result = gameService.findAll();
        return result;
    }
    
    @GetMapping(value = "/game")
    public GameDTO findById(@RequestParam("id") Long listId) {
        GameDTO result = gameService.findById(listId);
        return result;
    }

    @GetMapping(value = "/game/list")
    public List<GameMinDTO> findByList(@RequestParam("id") Long listId) {
        return gameService.findByList(listId);
    }
    
}
