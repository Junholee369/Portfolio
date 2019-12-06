/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

import com.sg.GuessTheNumber.dao.GuessTheNumberDao;
import com.sg.GuessTheNumber.dao.GuessTheNumberDaoInMemo;

import com.sg.GuessTheNumber.dao.InvalidGameException;
import com.sg.GuessTheNumber.dao.InvalidRoundException;
import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author junho
 */
public class GuessTheNumberServiceImplTest {

    public GuessTheNumberServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void GetAllGameGoldenTest() {

        try {
            GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
            GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

            List<Game> getAllGame = service.getAllGame();
            assertEquals(3, getAllGame.size());

            Game getGame = service.getGameById(1);
            assertEquals(1, getGame.getGameId());
            assertEquals(0, getGame.getAnswer());
            assertEquals(false, getGame.isEndGame());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void getGameByIdGoldenTest() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
        try {

            Game getGame = service.getGameById(1);
            assertEquals(1, getGame.getGameId());
            assertEquals(0, getGame.getAnswer());
            assertEquals(false, getGame.isEndGame());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void getAnswerfinishedGame() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
        try {

            Game getGame = service.getGameById(2);
            assertEquals(2, getGame.getGameId());
            assertEquals(1234, getGame.getAnswer());
            assertEquals(true, getGame.isEndGame());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testGetInvalidId() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
        try {

            Game getGame = service.getGameById(-1);
            assertEquals(0, getGame.getGameId());
            fail();
        } catch (InvalidGameException ex) {

        }
    }

    /**
     * Test of createGame method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void createGameGoldenPath() {

        try {
            GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
            GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

            int gameId = service.createGame();

            Game valGame = dao.getGameById(gameId);

            assertEquals(gameId, valGame.getGameId());
            assertEquals(false, valGame.isEndGame());
        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void createRoundGoldenPath() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

        try {

            Round valGame = service.createRound(1, 1243);

            assertEquals(2, valGame.getRoundId());
            assertEquals(1, valGame.getGameId());
            assertEquals(1243, valGame.getUserGuess());
            assertEquals(2, valGame.getPartialGuess());
            assertEquals(2, valGame.getExactGuess());

        } catch (InvalidGameException | InvalidRoundException ex) {
            fail();
        }
    }

    @Test
    public void testCreateRoundOnInvalidGame()  {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

        try {

            Round valGame = service.createRound(-1, 1243);
            
            fail();
        } catch (InvalidRoundException ex) {
            fail();
        } catch (InvalidGameException ex) {
            
     
        
        }
       
    }

    @Test
    public void createCorrectGuessRoundGoldenPath() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

        try {

            Round valRound = service.createRound(1, 1234);

            assertEquals(2, valRound.getRoundId());
            assertEquals(1, valRound.getGameId());
            assertEquals(1234, valRound.getUserGuess());
            assertEquals(0, valRound.getPartialGuess());
            assertEquals(4, valRound.getExactGuess());

            Game valGame = service.getGameById(1);
            assertEquals(true, valGame.isEndGame());
            
            
        } catch (InvalidGameException |  InvalidRoundException ex) {
            fail();
        }
    }

    @Test
    public void testCreateRoundDuplicateGuess() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

        try {

            Round valGame = service.createRound(1, 1244);

            fail();
        } catch (InvalidRoundException ex) {

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testCreateRoundMoreThanFourDigits() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);

        try {

            Round valGame = service.createRound(1, 12436);

            fail();

        } catch (InvalidRoundException ex) {
            
        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void getRoundByGameIdGoldenPath() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
        try {
            Game getGame = service.getGameById(1);

            List<Round> allRound = service.getRoundByGameId(getGame.getGameId());

            assertEquals(1, allRound.size());

            assertEquals(1243, allRound.get(0).getUserGuess());
            assertEquals(2, allRound.get(0).getPartialGuess());
            assertEquals(2, allRound.get(0).getExactGuess());

        } catch (InvalidGameException ex) {
            fail();
        }
    }

    @Test
    public void testGetInvalidGameId() {

        GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
        GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
        try {
            Game getGame = service.getGameById(-1);
            
        fail();
        } catch (InvalidGameException ex) {
            
        }
    }

    @Test
    public void testGetEmptyRoundByGameId() {
        
            GuessTheNumberDao dao = new GuessTheNumberDaoInMemo();
            GuessTheNumberService service = new GuessTheNumberServiceImpl(dao);
          try {  
            Game getGame = service.getGameById(3);
            
 
            List<Round> allRound = service.getRoundByGameId(getGame.getGameId());
            
            assertEquals(0 ,allRound.size());
            
            
            
        } catch (InvalidGameException ex) {
            fail();
        }      
    }
}
