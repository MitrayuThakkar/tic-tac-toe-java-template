//ComputerPlayerTest.java
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ComputerPlayerTest {

    @Test
    void testConstructor() {
        ComputerPlayer cpu = new ComputerPlayer('O');
        assertEquals("Computer", cpu.getName());
        assertEquals('O', cpu.getSymbol());
    }

    @Test
    void testChooseMoveWithinBounds() {
        ComputerPlayer cpu = new ComputerPlayer('O');
        char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        };
        int move = cpu.chooseMove(board, 'X');
        assertTrue(move >= 1 && move <= 9);
    }
}

