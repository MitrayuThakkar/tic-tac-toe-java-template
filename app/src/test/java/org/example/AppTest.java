package org.example;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void testInitialBoardSetup() {
        Player playerX = new Player("Player 1", 'X');
        Player playerO = new Player("Player 2", 'O');
        App game = new App(playerX, playerO);
        char[][] expected = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
        };
        assertArrayEquals(expected, game.getBoard(), "Board should start labeled '1' through '9'");
    }

    @Test
    void testIsMoveValid() {
        Player playerX = new Player("Player 1", 'X');
        Player playerO = new Player("Player 2", 'O');
        App game = new App(playerX, playerO);
        assertTrue(game.isMoveValid(1), "Move 1 should be valid on empty board");
        assertFalse(game.isMoveValid(0), "Move 0 is out of range");
        assertFalse(game.isMoveValid(10), "Move 10 is out of range");
    }

    @Test
    void testMakeMoveAndCheck() {
        Player playerX = new Player("Player 1", 'X');
        Player playerO = new Player("Player 2", 'O');
        App game = new App(playerX, playerO);
        game.makeMove(1);
        assertFalse(game.isMoveValid(1), "Cell 1 should now be occupied");
    }
}
