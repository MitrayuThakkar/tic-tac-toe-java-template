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
    void testInitialBoardSetup() {
        char[][] expected = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        };
        assertArrayEquals(expected, game.getBoard(), "Board should start labeled 1 through 9");
    }

    @Test
    void testIsMoveValid() {
        assertTrue(game.isMoveValid(1), "Move 1 should be valid");
        assertFalse(game.isMoveValid(0), "Move 0 is out of range");
        assertFalse(game.isMoveValid(10), "Move 10 is out of range");
    }

    @Test
    void testMakeMove() {
        game.makeMove(1);
        assertFalse(game.isMoveValid(1), "Move should not be valid after being made");
    }

    @Test
    void testSwitchPlayer() {
        Player initial = game.getCurrentPlayer();
        game.switchPlayer();
        assertNotEquals(initial, game.getCurrentPlayer(), "Player should switch after turn");
    }

    @Test
    void testWinConditionRow() {
        game.makeMove(1); // X
        game.switchPlayer();
        game.makeMove(4); // O
        game.switchPlayer();
        game.makeMove(2); // X
        game.switchPlayer();
        game.makeMove(5); // O
        game.switchPlayer();
        game.makeMove(3); // X

        assertTrue(game.checkWin(), "Player X should win by completing the top row");
    }

    @Test
    void testTieCondition() {
        int[] moves = {1, 2, 3, 5, 4, 6, 8, 7, 9};
        
        // Alternate moves explicitly for each turn.
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
            if (i < moves.length - 1) { // no need to switch after the last move
                game.switchPlayer();
            }
        }
        
        assertTrue(game.isBoardFull(), "Board should be full");
        assertFalse(game.checkWin(), "There should be no winner");
    }
}
