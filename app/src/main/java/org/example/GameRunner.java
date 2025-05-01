// GameRunner.java
package org.example;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!\n");
        System.out.println("What kind of game would you like to play?\n");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human\n");
        System.out.print("What is your selection: ");

        int mode;
        Player playerX, playerO;
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                mode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Defaulting to Human vs Human.");
                mode = 1;
            }

            switch (mode) {
                case 2:
                    playerX = new Player("Player 1", 'X');
                    playerO = new ComputerPlayer('O');
                    break;
                case 3:
                    playerX = new ComputerPlayer('X');
                    playerO = new Player("Player 2", 'O');
                    break;
                default:
                    playerX = new Player("Player 1", 'X');
                    playerO = new Player("Player 2", 'O');
            }

            Player currentFirstPlayer = playerX;
            App game = new App(playerX, playerO, currentFirstPlayer);

            boolean playAgain = true;
            while (playAgain) {
                game.resetBoard();
                boolean gameEnded = false;

                while (!gameEnded) {
                    game.printBoard();

                    Player current = game.getCurrentPlayer();
                    int move;
                    if (current instanceof ComputerPlayer) {
                        System.out.println("Computer is thinking...");
                        move = ((ComputerPlayer) current).chooseMove(game.getBoard(),
                                current == playerX ? playerO.getSymbol() : playerX.getSymbol());
                        System.out.println("Computer chooses: " + move);
                    } else {
                        System.out.print("What is your move: ");
                        try {
                            move = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Try again.");
                            continue;
                        }
                    }

                    if (!game.isMoveValid(move)) {
                        System.out.println("Invalid move! Try again.");
                        continue;
                    }

                    game.makeMove(move);

                    if (game.checkWin()) {
                        game.printBoard();
                        System.out.println("Player " + current.getSymbol() + " wins!");
                        current.addWin();

                        currentFirstPlayer = (current == playerX) ? playerO : playerX;
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
                playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");

                if (playAgain) {
                    System.out.println("\nGreat! This time " + currentFirstPlayer.getSymbol() + " will go first!\n");
                }
            }

            System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
            GameLogger.saveLogToFile(playerX, playerO, game.getTies());
        }
    }
}
