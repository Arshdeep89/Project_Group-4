/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class GoFishGame extends Game {
    private Deck deck;
    private Scanner scanner;

    public GoFishGame(ArrayList<String> playerNames) {
        super("Go Fish");
        deck = new Deck();
        scanner = new Scanner(System.in);

        // It initializes players with the provided names
        for (String name : playerNames) {
            getPlayers().add(new Player(name));
        }
    }

    @Override
    public void play() {
        System.out.println("\n=== Starting Go Fish Game! ===\n");

        // Shuufles the initial cards to every player
        for (Player player : getPlayers()) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }

        // Main game loop
        while (!isGameOver()) {
            for (Player currentPlayer : getPlayers()) {
                if (isGameOver()) break;

                System.out.println("\n---------------------------------");
                System.out.println(currentPlayer.getName() + "'s turn (Score: " + currentPlayer.getScore() + ")");
                System.out.println("---------------------------------");
                takeTurn(currentPlayer);
                checkForBooks(currentPlayer);
            }
        }

        declareWinner();
    }

    private void takeTurn(Player currentPlayer) {
        System.out.println("Your hand: " + currentPlayer.getHand());
        System.out.print("Ask for a rank: ");
        String rank = scanner.nextLine();
    
        Player targetPlayer = selectOpponent(currentPlayer);
        System.out.println(currentPlayer.getName() + " asks " + targetPlayer.getName() + " for " + rank);
    
        // Checks if the targeted player has cards of the specified rank
        if (targetPlayer.hasCard(rank)) {
            ArrayList<Card> cardsToTransfer = new ArrayList<>();
    
            // Collects all cards of the specified rank from the targeted player
            for (Card card : new ArrayList<>(targetPlayer.getHand())) {
                if (card.getRank().equals(rank)) {
                    cardsToTransfer.add(card);
                    targetPlayer.getHand().remove(card);
                }
            }
    
            // Transfers all the cards of the specified rank to the current player
            currentPlayer.getHand().addAll(cardsToTransfer);
            System.out.println(targetPlayer.getName() + " has " + cardsToTransfer.size() + " " + rank + "(s)! "
                    + currentPlayer.getName() + " receives all of them and gets another turn.");
            
            // Gives the current player another turn after receiving cards
            
            takeTurn(currentPlayer);
        } else {
            // Go Fish! No cards of the specified rank found in target player's hand
            System.out.println("Go Fish!");
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                currentPlayer.addCard(drawnCard);
                System.out.println(currentPlayer.getName() + " draws " + drawnCard);
                if (drawnCard.getRank().equals(rank)) {
                    System.out.println(currentPlayer.getName() + " draws the card they wanted and gets another turn!");
                    takeTurn(currentPlayer);
                }
            }
        }
    }
    

    private Player selectOpponent(Player currentPlayer) {
        ArrayList<Player> opponents = new ArrayList<>(getPlayers());
        opponents.remove(currentPlayer);

        System.out.println("\nChoose an opponent to ask:");
        for (int i = 0; i < opponents.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + opponents.get(i).getName());
        }

        int choice;
        while (true) {
            System.out.print("Enter the number of the player you want to ask: ");
            try {
                choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice >= 0 && choice < opponents.size()) {
                    return opponents.get(choice);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void checkForBooks(Player player) {
        for (Card card : new ArrayList<>(player.getHand())) {
            int count = 0;
            for (Card c : player.getHand()) {
                if (c.getRank().equals(card.getRank())) {
                    count++;
                }
            }
            if (count == 4) {
                player.incrementScore();
                for (int i = 0; i < 4; i++) {
                    player.removeCard(card.getRank());
                }
                System.out.println(player.getName() + " completed a book of " + card.getRank() + "s!");
            }
        }
    }

    private boolean isGameOver() {
        return deck.isEmpty() && getPlayers().stream().allMatch(Player::hasEmptyHand);
    }

    @Override
    public void declareWinner() {

        int highestScore = 0;
        ArrayList<Player> winners = new ArrayList<>();

        for (Player player : getPlayers()) {
            if (player.getScore() > highestScore) {
                highestScore = player.getScore();
                winners.clear();
                winners.add(player);
            } else if (player.getScore() == highestScore){
                winners.add(player);
            }
        }

        System.out.println("\n=== Game Over! ===");
        for (Player player : getPlayers()) {
            System.out.println("  " + player.getName() + " - Score: " + player.getScore() + " books");
        }
        
        System.out.print("\nWinner(s): ");
        
        for (Player winner : winners) {
            System.out.print(winner.getName() + " ");
        }
        
        System.out.println("with " + highestScore + " books!\n");
    
    }
}
