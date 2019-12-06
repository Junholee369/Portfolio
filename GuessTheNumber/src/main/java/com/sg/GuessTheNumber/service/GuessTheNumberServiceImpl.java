/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

import com.sg.GuessTheNumber.dao.GuessTheNumberDao;
import com.sg.GuessTheNumber.dao.InvalidGameException;
import com.sg.GuessTheNumber.dao.InvalidRoundException;
import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author junho
 */
@Component
public class GuessTheNumberServiceImpl implements GuessTheNumberService {

    private GuessTheNumberDao dao;

    @Autowired
    public GuessTheNumberServiceImpl(GuessTheNumberDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Game> getAllGame() throws InvalidGameException {

        List<Game> toReturn = null;
        try {
            toReturn = dao.getAllGame();
            for (int i = 0; i < toReturn.size(); i++) {
                Game currentGame = toReturn.get(i);
                if (currentGame.isEndGame() == false) {

                    currentGame.setAnswer(0);
                }
            }

            return toReturn;

        } catch (InvalidGameException ex) {
            throw new InvalidGameException("Something is wrong with the dao database.");
        }
    }

    @Override
    public Game getGameById(int id) throws InvalidGameException {

        Game toReturn = dao.getGameById(id);

        if (toReturn.isEndGame() == false) {
            toReturn.setAnswer(0);
        }

        return toReturn;
    }

    @Override
    public List<Round> getRoundByGameId(int id) throws InvalidGameException {
        List<Round> toReturn = null;
        try {

            Game game = dao.getGameById(id);

            toReturn = dao.getRoundByGameId(id);

        } catch (InvalidGameException ex) {
            throw new InvalidGameException("something is wrong with the getRoundByGameId service dao");
        }
        return toReturn;
    }

    @Override
    public int createGame() throws InvalidGameException {
        Game addGame = new Game();
        try {

            addGame.setAnswer(generateTargetNew());
            addGame.setEndGame(false);

            dao.createGame(addGame);
        } catch (InvalidGameException ex) {
            throw new InvalidGameException("something is wrong with the createGame service dao");
        }
        return addGame.getGameId();
    }

    @Override
    public Round createRound(int gameId, int guess) throws InvalidGameException, InvalidRoundException {

        Round addRound = new Round();

        try {

            LocalDateTime now = LocalDateTime.now();

            Game chosenGame = dao.getGameById(gameId);
            boolean checkRepeat = hasDuplicate(guess);

            addRound.setUserGuess(guess);
            addRound.setGameId(gameId);
            addRound.setRoundTime(now);
            addRound.setExactGuess(computeExact(chosenGame.getAnswer(), guess));
            addRound.setPartialGuess(computePartialMatches(chosenGame.getAnswer(), guess));

            if (checkRepeat) {
                throw new InvalidRoundException("cannot have duplicate in your guess");
            }

            if (guess >= 10000) {
                throw new InvalidRoundException("Enter a number of at least 4 digits.");
            }

            dao.createRound(addRound);

            if (guess == chosenGame.getAnswer()) {
                chosenGame.setEndGame(true);
                dao.setEndGame(gameId);
            }

        } catch (InvalidRoundException ex) {
            throw new InvalidRoundException("something is wrong with the createRound service dao");
        }
        return addRound;
    }

    private int computeExact(int target, int guess) {
        int toReturn = 0;
        for (int i = 0; i < 4; i++) {

            int targetOnes = target % 10;
            int guessOnes = guess % 10;
            if (targetOnes == guessOnes) {
                toReturn++;
            }
            target /= 10;
            guess /= 10;
        }
        return toReturn;
    }

    private boolean hasDuplicate(int n) {
        boolean toReturn = false;
        boolean[] seenDigit = new boolean[]{false, false, false, false, false, false, false, false, false, false};

        for (int i = 0; i < 4; i++) {
            int onesPlace = n % 10;
            n /= 10;
            boolean seenBefore = seenDigit[onesPlace];
            if (seenBefore) {
                toReturn = true;
                break;
            } else {
                seenDigit[onesPlace] = true;
            }

        }
        return toReturn;
    }

    //go back and check
    Random rng = new Random();

    private int generateTargetNew() {
        List<Integer> avail = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        int toReturn = 0;
        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int digitIndex = rng.nextInt(avail.size());
            int digit = avail.get(digitIndex);
            toReturn += digit;
            avail.remove(digitIndex);
        }
        return toReturn;
    }

    private int computePartialMatches(int target, int guess) {
        int toReturn = 0;
        String t = target + "";
        String g = guess + "";
        if (target < 1000) {
            t = "0" + t;
        }
        if (guess < 1000) {
            g = "0 + g";
        }
        for (int i = 0; i < 4; i++) {
            char gc = g.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    char gt = t.charAt(j);
                    if (gc == gt) {
                        toReturn++;
                    }
                }
            }
        }
        return toReturn;
    }

}
