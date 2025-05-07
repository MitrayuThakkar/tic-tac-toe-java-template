//GameLoggerTest.java
package org.example;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GameLoggerTest {

    @Test
    void testLogFileCreation() {
        Player playerX = new Player("X", 'X');
        Player playerO = new Player("O", 'O');
        GameLogger.saveLogToFile(playerX, playerO, 2);
        File file = new File("game.txt");
        assertTrue(file.exists());
    }
}

