/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author junho
 */
public class Card {
    private Rank rank;
    private Suit suit;
    
    
    public static enum Suit{
        Diamond,
        Club,
        Heart,
        Spade
    };
    
    
    public static enum Rank {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
        
        
    };
}
