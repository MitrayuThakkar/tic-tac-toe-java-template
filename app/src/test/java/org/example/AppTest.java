package org.example;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void testInitialBoardSetup() {
        App game = new App();
        char[][] expected = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
        };
        assertArrayEquals(
            expected,
            game.getBoard(),
            "Board should start labeled '1' through '9'"
        );
    }

    @Test
    void testIsMoveValid() {
        App game = new App();
        assertTrue(game.isMoveValid(1), "Move 1 should be valid on empty board");
        assertFalse(game.isMoveValid(0), "Move 0 is out of range");
        assertFalse(game.isMoveValid(10), "Move 10 is out of range");
    }

    @Test
    void testMakeMoveAndCheck() {
        App game = new App();
        game.makeMove(1); // place X in cell 1
        assertFalse(game.isMoveValid(1), "Cell 1 should now be occupied");
    }

    @Test
    void testWinRow() {
        App game = new App();
        // Simulate: X -> 1, O -> 4, X -> 2, O -> 5, X -> 3
        game.makeMove(1);            // X in cell 1
        game.switchPlayer();         // O
        game.makeMove(4);            // O in cell 4
        game.switchPlayer();         // X
        game.makeMove(2);            // X in cell 2
        game.switchPlayer();         // O
        game.makeMove(5);            // O in cell 5
        game.switchPlayer();         // X
        game.makeMove(3);            // X in cell 3 -> row 1,2,3
        assertTrue(game.checkWin(), "X should win with row 1,2,3");
    }

    @Test
    void testFullBoardNoWinner() {
        App game = new App();
        // Fill board: X,O,X,O,X,O,X,O,X
        // 1(X),2(O),3(X),4(O),5(X),6(O),7(X),8(O),9(X)
        game.makeMove(1);
        game.switchPlayer();
        game.makeMove(2);
        game.switchPlayer();
        game.makeMove(3);
        game.switchPlayer();
        game.makeMove(4);
        game.switchPlayer();
        game.makeMove(5);
        game.switchPlayer();
        game.makeMove(6);
        game.switchPlayer();
        game.makeMove(7);
        game.switchPlayer();
        game.makeMove(8);
        game.switchPlayer();
        game.makeMove(9); // X

        assertTrue(game.isBoardFull(), "Board should be fully occupied");
        assertTrue(game.checkWin(), "No winning line should exist");
    }
}
