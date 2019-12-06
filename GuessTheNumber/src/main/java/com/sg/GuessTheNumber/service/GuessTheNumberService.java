/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

import com.sg.GuessTheNumber.dao.InvalidGameException;
import com.sg.GuessTheNumber.dao.InvalidRoundException;
import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.util.List;

/**
 *
 * @author junho
 */
public interface GuessTheNumberService {

    public int createGame() throws InvalidGameException;

    public Game getGameById(int id) throws InvalidGameException;
    
    public List<Game> getAllGame() throws InvalidGameException;
    
    public Round createRound(int gameId, int guess) throws InvalidGameException, InvalidRoundException;
    
    public List<Round> getRoundByGameId(int id) throws InvalidGameException;

}
