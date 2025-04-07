package org.example;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        Player playerX = new Player("Player 1", 'X');
        Player playerO = new Player("Player 2", 'O');
        Player currentFirstPlayer = playerX;

        App game = new App(playerX, playerO, currentFirstPlayer);

        System.out.println("Welcome to Tic-Tac-Toe!");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean playAgain = true;

            while (playAgain) {
                game.resetBoard();
                boolean gameEnded = false;

                while (!gameEnded) {
                    game.printBoard();
                    System.out.print("What is your move? ");
                    int move;
                    try {
                        move = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        continue;
                    }

                    if (!game.isMoveValid(move)) {
                        System.out.println("Invalid move! Try again.");
                        continue;
                    }

                    game.makeMove(move);

                    if (game.checkWin()) {
                        game.printBoard();
                        System.out.println("Player " + game.getCurrentPlayer().getSymbol() + " wins!");
                        game.incrementWin(game.getCurrentPlayer());

                        currentFirstPlayer = (game.getCurrentPlayer() == playerX) ? playerO : playerX;
                        game.setFirstPlayer(currentFirstPlayer);
                        game.printLog();
                        gameEnded = true;
                    } else if (game.isBoardFull()) {
                        game.printBoard();
                        System.out.println("It's a tie!");
                        game.incrementTie();
                        game.printLog();
                        gameEnded = true;
                    } else {
                        game.switchPlayer();
                    }
                }

                System.out.print("Would you like to play again (yes/no)? ");
                String response = scanner.nextLine().trim().toLowerCase();
                playAgain = response.equals("yes");

                if (playAgain) {
                    System.out.println("\nGreat! This time " + currentFirstPlayer.getSymbol() + " will go first!\n");
                }
            }
        }

        System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
        game.saveLogToFile();
    }
}
