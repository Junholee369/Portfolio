
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.util.List;

/**
 *
 * @author junho
 */
public interface GuessTheNumberDao {

    List<Game> getAllGame() throws InvalidGameException;

    Game getGameById(int id) throws InvalidGameException, InvalidGameException;

    List<Round> getRoundByGameId(int id) throws InvalidGameException;

    Game createGame(Game toAddG) throws InvalidGameException;

    Round createRound(Round toAddR) throws InvalidGameException, InvalidRoundException;

    public void setEndGame(int id) throws InvalidGameException;

    public void deleteAllGames() throws InvalidGameException;
}
