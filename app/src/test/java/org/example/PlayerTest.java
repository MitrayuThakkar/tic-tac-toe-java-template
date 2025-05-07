//PlayerTest.java
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testPlayerCreationAndWinTracking() {
        Player player = new Player("Tester", 'X');
        assertEquals("Tester", player.getName());
        assertEquals('X', player.getSymbol());
        assertEquals(0, player.getWins());
        player.addWin();
        assertEquals(1, player.getWins());
    }
}
