package org.example;
import java.util.Scanner;

public class App {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int BOARD_SIZE = 3;

    private char[][] board;
    private char currentPlayer;
    private boolean gameRunning;

    public App() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        resetBoard();
        currentPlayer = PLAYER_X;
        gameRunning = true;
    }

    /**
     * Reset the board to the initial state: '1'..'9'.
     */
    public void resetBoard() {
        int cellNumber = 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = (char) ('0' + cellNumber);
                cellNumber++;
            }
        }
    }

    /**
     * Access the board array (used by AppTest).
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Display the board in a human-friendly format.
     */
    private void displayBoard() {
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < BOARD_SIZE - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    /**
     * Check if a chosen cell (1-9) is valid (not already taken).
     */
    public boolean isMoveValid(int move) {
        if (move < 1 || move > 9) {
            return false;
        }
        int row = (move - 1) / BOARD_SIZE; // 0..2
        int col = (move - 1) % BOARD_SIZE; // 0..2
        return board[row][col] != PLAYER_X && board[row][col] != PLAYER_O;
    }

    /**
     * Place the current player's mark (X or O) in the chosen cell.
     */
    public void makeMove(int move) {
        int row = (move - 1) / BOARD_SIZE;
        int col = (move - 1) % BOARD_SIZE;
        board[row][col] = currentPlayer;
    }

    /**
     * Check if the current player has a winning line.
     */
    public boolean checkWin() {
        // Rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Diagonals
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    /**
     * Check if every cell on the board is occupied (draw condition).
     */
    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != PLAYER_X && board[i][j] != PLAYER_O) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Switch from X to O, or O to X.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    /**
     * Main game loop, played via console input.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");

        while (gameRunning) {
            displayBoard();
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");

            // Validate input is an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                scanner.nextLine(); // discard invalid token
                continue;
            }

            int move = scanner.nextInt();
            // Validate the move
            if (!isMoveValid(move)) {
                System.out.println("That is not a valid move! Try again.");
                continue;
            }

            // Make the move
            makeMove(move);

            // Check win or draw
            if (checkWin()) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameRunning = false;
            } else if (isBoardFull()) {
                displayBoard();
                System.out.println("It's a draw!");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }

        // Ask to play again
        scanner.nextLine(); // flush leftover newline
        System.out.print("Would you like to play again (yes/no)? ");
        String response = scanner.nextLine().trim().toLowerCase();
        while (!response.equals("yes") && !response.equals("no")) {
            System.out.println("That is not a valid entry!");
            System.out.print("Would you like to play again (yes/no)? ");
            response = scanner.nextLine().trim().toLowerCase();
        }

        if (response.equals("yes")) {
            resetBoard();
            currentPlayer = PLAYER_X;
            gameRunning = true;
            startGame();
        } else {
            System.out.println("Goodbye!");
            scanner.close();
        }
    }

    /**
     * Main entry point.
     */
    public static void main(String[] args) {
        App game = new App();
        game.startGame();
    }
}
