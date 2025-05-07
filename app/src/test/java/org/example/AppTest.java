//AppTest.java
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private Player playerX;
    private Player playerO;
    private App game;

    @BeforeEach
    void setUp() {
        playerX = new Player("Player 1", 'X');
        playerO = new Player("Player 2", 'O');
        game = new App(playerX, playerO, playerX);
    }

    @Test
    void testBoardReset() {
        game.resetBoard();
        char[][] board = game.getBoard();
        assertEquals('1', board[0][0]);
        assertEquals('5', board[1][1]);
        assertEquals('9', board[2][2]);
    }

    @Test
    void testMoveValidity() {
        assertTrue(game.isMoveValid(1));
        game.makeMove(1);
        assertFalse(game.isMoveValid(1));
        assertFalse(game.isMoveValid(0));
        assertFalse(game.isMoveValid(10));
    }

    @Test
    void testWinDetection() {
        game.makeMove(1);
        game.switchPlayer();
        game.makeMove(4);
        game.switchPlayer();
        game.makeMove(2);
        game.switchPlayer();
        game.makeMove(5);
        game.switchPlayer();
        game.makeMove(3); // Player X wins
        assertTrue(game.checkWin());
    }

    @Test
    void testTieDetection() {
        int[] moves = {1, 2, 3, 5, 4, 6, 8, 7, 9};
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
            if (i < moves.length - 1) game.switchPlayer();
        }
        assertTrue(game.isBoardFull());
        assertFalse(game.checkWin());
    }

    @Test
    void testSwitchPlayer() {
        Player current = game.getCurrentPlayer();
        game.switchPlayer();
        assertNotEquals(current, game.getCurrentPlayer());
    }
}
