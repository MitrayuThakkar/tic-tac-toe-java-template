package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class App {
    private static final int BOARD_SIZE = 3;
    private char[][] board;
    private Player currentPlayer;
    private Player playerX;
    private Player playerO;

    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;

    public App(Player playerX, Player playerO, Player firstPlayer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = firstPlayer;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        resetBoard();
    }

    public void resetBoard() {
        int cellNumber = 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = (char) ('0' + cellNumber++);
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isMoveValid(int move) {
        if (move < 1 || move > 9) return false;
        int row = (move - 1) / BOARD_SIZE;
        int col = (move - 1) % BOARD_SIZE;
        return board[row][col] != playerX.getSymbol() && board[row][col] != playerO.getSymbol();
    }

    public void makeMove(int move) {
        int row = (move - 1) / BOARD_SIZE;
        int col = (move - 1) % BOARD_SIZE;
        board[row][col] = currentPlayer.getSymbol();
    }

    public boolean checkWin() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if ((board[i][0] == currentPlayer.getSymbol() && board[i][1] == currentPlayer.getSymbol() && board[i][2] == currentPlayer.getSymbol()) ||
                (board[0][i] == currentPlayer.getSymbol() && board[1][i] == currentPlayer.getSymbol() && board[2][i] == currentPlayer.getSymbol())) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer.getSymbol() && board[1][1] == currentPlayer.getSymbol() && board[2][2] == currentPlayer.getSymbol()) ||
               (board[0][2] == currentPlayer.getSymbol() && board[1][1] == currentPlayer.getSymbol() && board[2][0] == currentPlayer.getSymbol());
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c != playerX.getSymbol() && c != playerO.getSymbol()) return false;
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(" ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            if (i < 2) {
                System.out.println("\n-----------");
            }
        }
        System.out.println();
    }

    public void printLog() {
        System.out.println("\nPlayer X Wins   " + xWins);
        System.out.println("Player O Wins   " + oWins);
        System.out.println("Ties            " + ties + "\n");
    }

    public void saveLogToFile() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Final Game Statistics:\n");
            writer.write("Player X Wins: " + xWins + "\n");
            writer.write("Player O Wins: " + oWins + "\n");
            writer.write("Ties: " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Error saving game log: " + e.getMessage());
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setFirstPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public void incrementWin(Player player) {
        if (player == playerX) xWins++;
        else if (player == playerO) oWins++;
    }

    public void incrementTie() {
        ties++;
    }
}
