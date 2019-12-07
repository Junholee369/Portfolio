/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summativesums.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author junho
 */
//paper beats rock
//rock beats scissors
//scissors beat paper
public class RockPaperScissors {

    public static void main(String[] args) {

        //The program first asks the user how many rounds he/she wants to play.
        Scanner scn = new Scanner(System.in);
        //boolean = true, let's me loop when the user uses Yes, yes
        boolean startAgain = true;
        while (startAgain) {

            //asking the user how many games they would like to play
            System.out.println("Shally we play a game?");
            
            System.out.println("How many rounds would you like to play? You can play from 1 to 10 games.");

            int rounds = scn.nextInt();
            //tells the user to pick between 1 - 10, nothing less nothing more
            while (rounds < 1 || rounds > 10) {
                System.out.println("Please choose between 1 - 10.");

                rounds = scn.nextInt();

            }
            gameOfRPS(rounds);

            System.out.println("Would you like to play again? Yes/No");

            scn.nextLine();

            String answer = scn.nextLine();

            //asking the user if they want to play again
            switch (answer) {
                // let's me loop when user uses Yes, yes
                case "Yes":
                case "yes":
                    startAgain = true;
                    break;
                //ends when user types No,no
                case "No":
                case "no":
                    startAgain = false;
                    System.out.println("Thank you come again");
                    break;

                //asks the user to pick again because it's asking either yes or no 
                default:
                    System.out.println("invalid choice!!");
                    break;

            }
        }

    }

    //rock = 1
    //paper = 2
    //scissor = 3
//game of rock paper scissor in method
    public static void gameOfRPS(int rounds) {
        Scanner scn = new Scanner(System.in);
        Random rnd = new Random();

        int finishedRPS = 0;

        int tie = 0;
        int win = 0;
        int lose = 0;

        while (finishedRPS < rounds) {
            int r = 1;
            int p = 2;
            int s = 3;
            
            int userInput = 0;
            //sets that the random number is between 1-3 so it's nextInt((max-min)+1)+min
            int randomRPS = rnd.nextInt(3) + 1;

            //should ask to play rock paper scissors and give me a random value.
            while (userInput > 3 || userInput < 1) {
                System.out.println("Let's play Rock Paper Scissors.1 = rock, 2 = paper, 3 = scissors");
                userInput = scn.nextInt(); //should be able to put your choice
            }
            //rocks
            if (randomRPS == r) {
                if (userInput == r) {
                    System.out.println("Rock and Rock is a same thing, It's a Tie -__-");
                    tie++;
                } else if (userInput == p) {
                    System.out.println("Rock loses against Paper, You WIN!! OWO");
                    win++;
                } else if (userInput == s) {
                    System.out.println("Rock beats Scissors You Lose. UWU");
                    lose++;
                }
            } //paper
            else if (randomRPS == p) {

                if (userInput == r) {
                    System.out.println("Paper wraps rock, You Lose. UWU");
                    lose++;
                } else if (userInput == p) {
                    System.out.println("Paper and Paper is the same, It's a Tie. -__- ");
                    tie++;
                } else if (userInput == s) {
                    System.out.println("Paper get cut by Scissor, You WIN!! OWO");
                    win++;
                }
            } //scissors
            else if (randomRPS == s) {

                if (userInput == r) {
                    System.out.println("Scissors can't cut rock, You WIN! OWO");
                    win++;
                } else if (userInput == p) {
                    System.out.println("Scissors Sta.. I mean cut Paper, You Lose. UWU");
                    lose++;
                } else if (userInput == s) {
                    System.out.println("Scissors and Scissors just dulls eachother, It's a Tie. -__-");
                    tie++;
                }
            }
            
            //increases the win, lose, and tie depending on whether or not the user won against the computer
            finishedRPS++;

        }
        System.out.println("You won " + win + " times");
        System.out.println("You lost " + lose + " times");
        System.out.println("You Tied " + tie + " times");
        System.out.println(" ");
        System.out.println("You played " + rounds + " rounds");

    }

}
