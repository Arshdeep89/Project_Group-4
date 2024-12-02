/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author LENOVO
 */

/**
 * Factory for creating Card objects.
 * Design Pattern: Factory Pattern
 */
public class CardFactory {
    public static Card createCard(String rank, String suit) {
        return new Card(rank, suit);
    }
}
