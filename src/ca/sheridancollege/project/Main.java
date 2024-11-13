/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Go Fish!");
        System.out.println("-------------------");

        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> playerNames = new ArrayList<>();
            
            for (int i = 1; i <= 4; i++) {
                System.out.print("Enter name for Player " + i + ": ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    name = "Player " + i;
                }
                playerNames.add(name);
            }

            GoFishGame game = new GoFishGame(playerNames);
            game.play();
            
            System.out.println("\nThank you for playing Go Fish!");
        }
    }
}
