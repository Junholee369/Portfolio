/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.model;

import java.util.List;

/**
 *
 * @author junho
 */
public class Game {

    private int gameId;
    private int answer;
    private boolean endGame;

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
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    /**
     * @return the endGame
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * @param endGame the endGame to set
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
    
}
