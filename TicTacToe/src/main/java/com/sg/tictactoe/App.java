/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author junho
 */
public class App {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        //The game board Design.
        char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

        //Prints out the Game Board.
        printGameBoard(gameBoard);

        while (true) {
            //Scans for the user to input between 1~9 for the location of their placement.
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter your position 1~9");
            int playerPos = scn.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken, enter in another position.");
                playerPos = scn.nextInt();
            }

            placeGamePieces(gameBoard, playerPos, "Player");

            String result = checkWinner();

            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placeGamePieces(gameBoard, cpuPos, "CPU");

            printGameBoard(gameBoard);

            result = checkWinner();

            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeGamePieces(char[][] gameBoard, int pos, String user) {
        //Set the default value of piece to ' '
        char piece = ' ';

        //if it's User's turn, the piece is 'X'
        //if it's CPU's turn, the picece is 'O'
        if (user.equals("Player")) {
            piece = 'X';
            playerPositions.add(pos);

        } else if (user.equals("CPU")) {
            piece = 'O';
            cpuPositions.add(pos);
        }

        //Switches the empty spaces within position 1~9 with 'X'
        switch (pos) {
            case 1:
                gameBoard[0][0] = piece;
                break;
            case 2:
                gameBoard[0][2] = piece;
                break;
            case 3:
                gameBoard[0][4] = piece;
                break;
            case 4:
                gameBoard[2][0] = piece;
                break;
            case 5:
                gameBoard[2][2] = piece;
                break;
            case 6:
                gameBoard[2][4] = piece;
                break;
            case 7:
                gameBoard[4][0] = piece;
                break;
            case 8:
                gameBoard[4][2] = piece;
                break;
            case 9:
                gameBoard[4][4] = piece;
                break;
            default:
                break;
        }

    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List rightDiag = Arrays.asList(1, 5, 9);
        List leftDiag = Arrays.asList(3, 5, 7);

        List<List> winConditions = new ArrayList<List>();
        winConditions.add(topRow);
        winConditions.add(middleRow);
        winConditions.add(bottomRow);
        winConditions.add(leftColumn);
        winConditions.add(middleColumn);
        winConditions.add(rightColumn);
        winConditions.add(rightDiag);
        winConditions.add(leftDiag);

        for (List l : winConditions) {
            if (playerPositions.containsAll(l)) {
                return "Congrats you won!";
            } else if (cpuPositions.contains(l)) {
                return "CPU Won and you Lost!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT No Winner!";
            }

        }

        return "";
    }

}
