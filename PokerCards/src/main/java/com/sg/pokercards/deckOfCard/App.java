/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokercards.deckOfCard;

/**
 *
 * @author junho
 */
public class Cards {

    public static void main(String[] args) {

        String[] suit = {"Spade", "Diamond", "Clover", "Heart"};
        String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] deck = new String[52];

        for (int i = 0; i < deck.length; i++) {
            deck[i] = rank[i % 13] + suit[i / 13];
            System.out.println(deck[i]);
        }
        
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);

            String temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }

        for (String shuffle : deck) {
            System.out.println(shuffle);
        }

    }
}
