/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author junho
 */
public class Round {
   
    private int roundId;
    private int gameId;
    private LocalDateTime roundTime;
    private int userGuess;
    private int partialGuess;
    private int exactGuess;

        
    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the roundTime
     */
    public LocalDateTime getRoundTime() {
        return roundTime;
    }

    /**
     * @param roundTime the roundTime to set
     */
    public void setRoundTime(LocalDateTime roundTime) {
        this.roundTime = roundTime;
    }

    /**
     * @return the userGuess
     */
    public int getUserGuess() {
        return userGuess;
    }

    /**
     * @param userGuess the userGuess to set
     */
    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }

    /**
     * @return the partialGuess
     */
    public int getPartialGuess() {
        return partialGuess;
    }

    /**
     * @param partialGuess the partialGuess to set
     */
    public void setPartialGuess(int partialGuess) {
        this.partialGuess = partialGuess;
    }

    /**
     * @return the exactGuess
     */
    public int getExactGuess() {
        return exactGuess;
    }

    /**
     * @param exactGuess the exactGuess to set
     */
    public void setExactGuess(int exactGuess) {
        this.exactGuess = exactGuess;
    }
}
