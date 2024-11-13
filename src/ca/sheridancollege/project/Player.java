/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * Name: Arshdeep Singh
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCard(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public boolean hasCard(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }
}