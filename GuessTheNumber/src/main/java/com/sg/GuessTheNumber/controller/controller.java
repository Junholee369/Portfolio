/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.controller;

import com.sg.GuessTheNumber.dao.InvalidGameException;
import com.sg.GuessTheNumber.dao.InvalidRoundException;
import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;

import com.sg.GuessTheNumber.service.GuessTheNumberService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junho
 */
@RestController
@RequestMapping("/api")
public class controller {

    @Autowired
    GuessTheNumberService service;

    @PostMapping("/begin")
    public int startGame() throws InvalidGameException {

        return service.createGame();
    }

    @PostMapping("/guess")
    public Round playRound(Integer gameId, Integer guess) throws InvalidGameException, InvalidGameException, InvalidRoundException {

        return service.createRound(gameId, guess);
    }

    @GetMapping("/game")
    public List<Game> allGame() {
        List<Game> toReturn = null;
        try {
            return service.getAllGame();
        } catch (InvalidGameException ex) {
        }
        return toReturn;
    }

    @GetMapping("/game/{gameId}")
    @CrossOrigin
    public Game getGame(@PathVariable Integer gameId) {
        Game toReturn = null;
        try {
            toReturn = service.getGameById(gameId);
        } catch (InvalidGameException ex) {
        }
        return toReturn;
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getRound(@PathVariable Integer gameId) {

        List<Round> toReturn = null;
        try {
            return service.getRoundByGameId(gameId);
        } catch (InvalidGameException ex) {
        }
        return toReturn;
    }
}
