/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dao;

import com.sg.GuessTheNumber.model.Game;
import com.sg.GuessTheNumber.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author junho
 */
@Repository
public class GuessTheNumberDaoImpl implements GuessTheNumberDao {

    @Autowired
    private JdbcTemplate template;

    private String gameQuery = "Select *"
            + " From game g";

    private String roundQuery = "Select *"
            + " From round r";

    @Override
    public List<Game> getAllGame() throws InvalidGameException {

        String query = gameQuery;
        List<Game> toReturn = template.query(query, new GameMapper());

        return toReturn;
    }

    @Override
    public Game getGameById(int id) throws InvalidGameException {

        String query = gameQuery + " WHERE gameId = ?";
        Game toReturn = null;
        try {
            toReturn = template.queryForObject(query, new GameMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new InvalidGameException("Could not find game with id = " + id, ex);
        }

        return toReturn;
    }

    @Override
    public List<Round> getRoundByGameId(int id) throws InvalidGameException {
        
        getGameById(id);
        
        String query = roundQuery + " WHERE gameId = ?";

        List<Round> toReturn = template.query(query, new RoundMapper(), id);

        return toReturn;
    }

    @Override
    public Game createGame(Game toAddG) throws InvalidGameException {
        if (toAddG == null) {
            throw new InvalidGameException("the number cannot be null");
        }
        String insert = "INSERT INTO Game(Answer, EndGame) Values(?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

                toReturn.setInt(1, toAddG.getAnswer());
                toReturn.setBoolean(2, toAddG.isEndGame());

                return toReturn;
            }
        };

        template.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        toAddG.setGameId(generatedId);

        return toAddG;
    }

    @Override
    public Round createRound(Round toAddR) throws InvalidGameException, InvalidRoundException {

        if (toAddR == null) {
            throw new InvalidRoundException("The Round Object cannot be Null.");
        }

        Game toCheck = getGameById(toAddR.getGameId());

        if (toCheck == null) {
            throw new InvalidGameException("You cannot create Round in a non-existing game.");
        }

        if (toAddR.getRoundTime() == null) {
            throw new InvalidRoundException("You cannot create Round with a Null Time.");

        }

        if (toCheck.isEndGame() == true) {
            throw new InvalidGameException("You cannot guess when the game is done.");
        }

        String insert = "INSERT INTO Round(GameId, roundTime, userGuess, partialGuess, exactGuess) Values(?,?,?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                LocalDateTime now = toAddR.getRoundTime();
                Timestamp timeStamp = Timestamp.valueOf(now);

                toReturn.setInt(1, toAddR.getGameId());
                toReturn.setTimestamp(2, timeStamp);
                toReturn.setInt(3, toAddR.getUserGuess());
                toReturn.setInt(4, toAddR.getPartialGuess());
                toReturn.setInt(5, toAddR.getExactGuess());

                return toReturn;
            }
        };
        template.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        toAddR.setRoundId(generatedId);

        return toAddR;
    }

    @Override
    public void setEndGame(int id) throws InvalidGameException {
        String updateGame = "update guessinggame.game set EndGame = 1 where gameId = ?";

        template.update(updateGame, id);
    }

    @Override
    @Transactional
    public void deleteAllGames() throws InvalidGameException {
        String delAllRound = "delete from round";
        String delAllGame = "delete from Game";
        template.update(delAllRound);
        template.update(delAllGame);

    }

    private class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet results, int rowNum) throws SQLException {
            Game toAdd = new Game();

            toAdd.setGameId(results.getInt("GameId"));
            toAdd.setAnswer(results.getInt("Answer"));
            toAdd.setEndGame(results.getBoolean("EndGame"));

            return toAdd;
        }
    }

    private class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet results, int rowNum) throws SQLException {
            Round toAdd = new Round();

            toAdd.setRoundId(results.getInt("roundId"));
            toAdd.setGameId(results.getInt("GameId"));
            toAdd.setRoundTime(results.getTimestamp("roundTime").toLocalDateTime());
            toAdd.setUserGuess(results.getInt("userGuess"));
            toAdd.setPartialGuess(results.getInt("partialGuess"));
            toAdd.setExactGuess(results.getInt("exactGuess"));

            return toAdd;
        }
    }
}
