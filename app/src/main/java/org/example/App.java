package org.example;

/**
 * Player class represents a Tic-Tac-Toe player with a name and symbol.
 */
class Player {
    private final String name;
    private final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

/**
 * App class contains core game logic for Tic-Tac-Toe.
 */
public class App {
    private static final int BOARD_SIZE = 3;
    private char[][] board;
    private Player currentPlayer;
    private Player playerX;
    private Player playerO;


    public App(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        resetBoard();
    }

    public void resetBoard() {
        int cellNumber = 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = (char) ('0' + cellNumber);
                cellNumber++;
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isMoveValid(int move) {
        if (move < 1 || move > 9) {
            return false;
        }
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
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != playerX.getSymbol() && board[i][j] != playerO.getSymbol()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}