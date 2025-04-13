package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class GameLogger {
    public static void saveLogToFile(Player playerX, Player playerO, int ties) {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Final Game Statistics:\n");
            writer.write("Player X Wins: " + playerX.getWins() + "\n");
            writer.write("Player O Wins: " + playerO.getWins() + "\n");
            writer.write("Ties: " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Error saving game log: " + e.getMessage());
        }
    }
}
