/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.TestApplicationConfiguration;
import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author junho
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GuessTheNumberDaoImplTest {

    @Autowired
    GuessTheNumberDao dao;

    public GuessTheNumberDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws InvalidGameException {
        dao.deleteAllGames();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllGame method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetAllGameGoldenPath() {

        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setEndGame(false);

            Game toAddg = new Game();
            toAddg.setAnswer(4321);
            toAddg.setEndGame(true);

            Game addedG = dao.createGame(toAddG);
            Game addedg = dao.createGame(toAddg);

            List<Game> allGame = dao.getAllGame();
            assertEquals(2, allGame.size());

            Game valGameOne = allGame.get(0);
            Game valGameTwo = allGame.get(1);

            assertEquals(addedG.getGameId(), valGameOne.getGameId());
            assertEquals(1234, valGameOne.getAnswer());
            assertEquals(false, valGameOne.isEndGame());

            assertEquals(addedg.getGameId(), valGameTwo.getGameId());
            assertEquals(4321, valGameTwo.getAnswer());
            assertEquals(true, valGameTwo.isEndGame());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    /**
     * Test of getGameById method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetGameByIdGoldenPath() {
        try {

            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setEndGame(false);

            Game added = dao.createGame(toAddG);

            Game getGame = dao.getGameById(added.getGameId());

            assertEquals(1234, getGame.getAnswer());
            assertEquals(false, getGame.isEndGame());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testGetNull() {
        try {

            Game toAddG = null;

            Game added = dao.createGame(toAddG);

            Game getGame = dao.getGameById(added.getGameId());

            fail("Expected to Throw a InvalidGameException but was not thrown");
        } catch (InvalidGameException ex) {
        }
    }

    /**
     * Test of getRoundById method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetRoundByIdGoldenPath() {
        try {
            Game toGetGame = new Game();
            toGetGame.setAnswer(1234);
            toGetGame.setGameId(1);
            toGetGame.setEndGame(false);

            Game addedG = dao.createGame(toGetGame);

            Round toGetRound = new Round();
            toGetRound.setGameId(addedG.getGameId());
            toGetRound.setRoundTime(LocalDateTime.now());
            toGetRound.setUserGuess(1243);
            toGetRound.setPartialGuess(2);
            toGetRound.setExactGuess(2);

            Round toGetRoundTwo = new Round();
            toGetRoundTwo.setGameId(addedG.getGameId());
            toGetRoundTwo.setRoundTime(LocalDateTime.now());
            toGetRoundTwo.setUserGuess(1234);
            toGetRoundTwo.setPartialGuess(0);
            toGetRoundTwo.setExactGuess(4);

            Round addedR = dao.createRound(toGetRound);
            Round addedr = dao.createRound(toGetRoundTwo);

            List<Round> allRounds = dao.getRoundByGameId(toGetGame.getGameId());
            assertEquals(2, allRounds.size());

            assertEquals(addedG.getGameId(), allRounds.get(0).getGameId());
            assertEquals(1243, allRounds.get(0).getUserGuess());
            assertEquals(2, allRounds.get(0).getPartialGuess());
            assertEquals(2, allRounds.get(0).getExactGuess());

            assertEquals(addedG.getGameId(), allRounds.get(1).getGameId());
            assertEquals(1234, allRounds.get(1).getUserGuess());
            assertEquals(0, allRounds.get(1).getPartialGuess());
            assertEquals(4, allRounds.get(1).getExactGuess());

        } catch (InvalidGameException | InvalidRoundException ex) {
            fail();
        }
    }

    @Test
    public void testGetRoundByIdInvalidGameId() {
        try {
            List<Round> allRounds = dao.getRoundByGameId(-1);

            fail();
        } catch (InvalidGameException ex) {

        }
    }

    /**
     * Test of createGame method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testCreateGameGoldenPath() {
        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setGameId(1);
            toAddG.setEndGame(false);

            Game added = dao.createGame(toAddG);

            Game toCheck = dao.getGameById(added.getGameId());
            assertEquals(1234, toCheck.getAnswer());
            assertEquals(false, toCheck.isEndGame());
        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testCreateNull() {
        try {
            Game toAddG = null;

            Game added = dao.createGame(toAddG);
            fail("Expected to Throw a InvalidGameException but was not thrown");
        } catch (InvalidGameException ex) {

        }
    }

    /**
     * Test of createRound method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testCreateRoundGoldenPath() {
        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setGameId(1);
            toAddG.setEndGame(false);

            Game addedG = dao.createGame(toAddG);

            Round toAddR = new Round();
            toAddR.setGameId(addedG.getGameId());
            toAddR.setRoundTime(LocalDateTime.now());
            toAddR.setUserGuess(1243);
            toAddR.setPartialGuess(2);
            toAddR.setExactGuess(2);

            Round toAddr = new Round();
            toAddr.setGameId(addedG.getGameId());
            toAddr.setRoundTime(LocalDateTime.now());
            toAddr.setUserGuess(1234);
            toAddr.setPartialGuess(0);
            toAddr.setExactGuess(4);

            Round addedR = dao.createRound(toAddR);
            Round addedr = dao.createRound(toAddr);

            List<Round> allRounds = dao.getRoundByGameId(toAddG.getGameId());
            assertEquals(2, allRounds.size());

            Round valRoundOne = allRounds.get(0);
            Round valRoundTwo = allRounds.get(1);

            assertEquals(addedR.getRoundId(), valRoundOne.getRoundId());
            assertEquals(addedR.getGameId(), valRoundOne.getGameId());

            assertEquals(addedR.getPartialGuess(), valRoundOne.getPartialGuess());
            assertEquals(addedR.getExactGuess(), valRoundOne.getExactGuess());

            assertEquals(addedr.getRoundId(), valRoundTwo.getRoundId());
            assertEquals(addedr.getGameId(), valRoundTwo.getGameId());

            assertEquals(addedr.getPartialGuess(), valRoundTwo.getPartialGuess());
            assertEquals(addedr.getExactGuess(), valRoundTwo.getExactGuess());
        } catch (InvalidGameException | InvalidRoundException ex) {
            fail();

        }
    }

    @Test
    public void testCreateRoundInvalidGameId() {

        try {
            Round toAddR = new Round();
            toAddR.setGameId(-1);
            toAddR.setRoundTime(LocalDateTime.now());
            toAddR.setUserGuess(1243);
            toAddR.setPartialGuess(2);
            toAddR.setExactGuess(2);

            Round addedR = dao.createRound(toAddR);

            fail();
        } catch (InvalidGameException ex) {

        } catch (InvalidRoundException ex) {

            fail();
        }
    }

    @Test
    public void testCreateRoundNullRoundTime() {
        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setGameId(1);
            toAddG.setEndGame(false);

            Game addedG = dao.createGame(toAddG);

            Round toAddR = new Round();
            toAddR.setGameId(addedG.getGameId());
            toAddR.setRoundTime(null);
            toAddR.setUserGuess(1243);
            toAddR.setPartialGuess(2);
            toAddR.setExactGuess(2);

            Round addedR = dao.createRound(toAddR);

            fail("Expected to Throw a InvalidGameException but was not thrown");
        } catch (InvalidRoundException ex) {

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testCreateRoundNullRound() {
        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setGameId(1);
            toAddG.setEndGame(false);

            Game addedG = dao.createGame(toAddG);

            Round toAddR = null;

            Round addedR = dao.createRound(toAddR);
            fail("Expected to Throw a InvalidGameException but was not thrown");
        } catch (InvalidRoundException ex) {

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testCreateRoundOnGameEnd() {
        try {
            Game toAddG = new Game();
            toAddG.setAnswer(1234);
            toAddG.setGameId(1);
            toAddG.setEndGame(true);

            Game addedG = dao.createGame(toAddG);

            Round toAddR = new Round();

            Round addedR = dao.createRound(toAddR);

            fail();
        } catch (InvalidGameException ex) {
            
        } catch (InvalidRoundException ex) {

            fail();
        }
    }

}
