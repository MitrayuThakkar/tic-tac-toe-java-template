// ComputerPlayer.java
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(char symbol) {
        super("Computer", symbol);
    }

    public int chooseMove(char[][] board, char opponentSymbol) {
        if (isBoardEmpty(board)) return chooseRandomCorner();
        if (isSecondMove(board) && board[1][1] != getSymbol() && board[1][1] != opponentSymbol) return 5;

        Integer winMove = findWinningMove(board, getSymbol());
        if (winMove != null) return winMove;

        Integer blockMove = findWinningMove(board, opponentSymbol);
        if (blockMove != null) return blockMove;

        return chooseRandomAvailable(board);
    }

    private boolean isBoardEmpty(char[][] board) {
        for (char[] row : board)
            for (char c : row)
                if (c == 'X' || c == 'O') return false;
        return true;
    }

    private boolean isSecondMove(char[][] board) {
        int count = 0;
        for (char[] row : board)
            for (char c : row)
                if (c == 'X' || c == 'O') count++;
        return count == 1;
    }

    private Integer findWinningMove(char[][] board, char symbol) {
        for (int i = 1; i <= 9; i++) {
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            if (board[row][col] != 'X' && board[row][col] != 'O') {
                char temp = board[row][col];
                board[row][col] = symbol;
                boolean win = checkWin(board, symbol);
                board[row][col] = temp;
                if (win) return i;
            }
        }
        return null;
    }

    private boolean checkWin(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol))
                return true;

        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private int chooseRandomCorner() {
        int[] corners = {1, 3, 7, 9};
        return corners[new Random().nextInt(corners.length)];
    }

    private int chooseRandomAvailable(char[][] board) {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            if (board[row][col] != 'X' && board[row][col] != 'O') {
                available.add(i);
            }
        }
        return available.get(new Random().nextInt(available.size()));
    }
}
