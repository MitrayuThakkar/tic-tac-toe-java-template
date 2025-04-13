// App.java
package org.example;

public class App {
    private static final int BOARD_SIZE = 3;
    private char[][] board;
    private Player currentPlayer;
    private Player playerX;
    private Player playerO;
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
        System.out.println("\nPlayer X Wins   " + playerX.getWins());
        System.out.println("Player O Wins   " + playerO.getWins());
        System.out.println("Ties            " + ties + "\n");
    }

    public void incrementTie() {
        ties++;
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

    public int getTies() {
        return ties;
    }
}
