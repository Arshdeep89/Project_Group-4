/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * Author: Arshdeep Singh
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. 
 * Must be general enough to be instantiated for any Cardgame. 
 * Students wishing to add to the code should remember to add themselves as a modifier.
 *
 */
public class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
