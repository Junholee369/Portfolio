/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junho
 */
public class GuessTheNumberDaoInMemo implements GuessTheNumberDao {

    private List<Game> gameList = new ArrayList<>();
    private List<Round> roundList = new ArrayList<>();

    public GuessTheNumberDaoInMemo() {

        Game validGame = new Game();
        validGame.setAnswer(1234);
        validGame.setGameId(1);
        validGame.setEndGame(false);

        gameList.add(validGame);

        Game validFinishedGame = new Game();
        validFinishedGame.setAnswer(1234);
        validFinishedGame.setGameId(2);
        validFinishedGame.setEndGame(true);

        gameList.add(validFinishedGame);

        Game validGameNoRound = new Game();
        validGameNoRound.setAnswer(4321);
        validGameNoRound.setGameId(3);
        validGameNoRound.setEndGame(false);

        gameList.add(validGameNoRound);
        
        Round validRound = new Round();
        validRound.setRoundId(1);
        validRound.setGameId(1);
        validRound.setRoundTime(null);
        validRound.setUserGuess(1243);
        validRound.setPartialGuess(2);
        validRound.setExactGuess(2);

        roundList.add(validRound);

        Round validFinishedRound = new Round();
        validFinishedRound.setRoundId(1);
        validFinishedRound.setGameId(2);
        validFinishedRound.setRoundTime(null);
        validFinishedRound.setUserGuess(1234);
        validFinishedRound.setPartialGuess(0);
        validFinishedRound.setExactGuess(4);

        roundList.add(validFinishedRound);
    }

    @Override
    public List<Game> getAllGame() throws InvalidGameException {
        return gameList;
    }

    @Override
    public Game getGameById(int gameId) throws InvalidGameException {
        Game toReturn = null;

        List<Game> allGame = gameList;

        for (Game toCheck : allGame) {
            if (toCheck.getGameId() == gameId) {
                toReturn = toCheck;
                break;
            }
        }

        if (toReturn == null) {
            throw new InvalidGameException("The game Id you have entered does not match any games we have.");
        }

        return toReturn;
    }

    @Override
    public List<Round> getRoundByGameId(int id) throws InvalidGameException {
        
        getGameById(id);
        
       List<Round> toReturn = new ArrayList();

        List<Round> allRounds = roundList;

        for (Round toCheck : allRounds) {
            if (toCheck.getGameId() == id) {
                toReturn.add(toCheck);
//                 toReturn = toCheck;
            }
        }
        
      
        return toReturn;
    }

    @Override
    public Game createGame(Game toAddG) throws InvalidGameException {
        List<Game> allGames = gameList;

        int newGameId = 0;

        for (Game toCheck : allGames) {
            if (toCheck.getGameId() > newGameId) {
                newGameId = toCheck.getGameId();
            }
        }

        newGameId++;
        toAddG.setGameId(newGameId);

        allGames.add(toAddG);

        return toAddG;

    }

    @Override
    public void deleteAllGames() throws InvalidGameException {
        gameList.clear();
        roundList.clear();

    }

    @Override
    public Round createRound(Round toAddR) throws InvalidRoundException {
        List<Round> allRounds = roundList;
        int newRoundId = -1;

        for (Round toCheck : allRounds) {
            if (toCheck.getRoundId() > newRoundId) {
                newRoundId = toCheck.getRoundId();
            }
        }
        newRoundId++;
        toAddR.setRoundId(newRoundId);

        allRounds.add(toAddR);

        return toAddR;
    }

    @Override
    public void setEndGame(int id) throws InvalidGameException {

        Game toReturn = null;

        List<Game> allGame = gameList;

        for (Game toCheck : allGame) {
            if (toCheck.getGameId() == id) {
                toReturn = toCheck;
                toReturn.setEndGame(true);
                break;
            }
        }
    }

}
