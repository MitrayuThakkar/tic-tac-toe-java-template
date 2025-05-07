//GameRunnerTest.java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {

    @Test
    void testMainMethodRunsWithoutCrash() {
        assertDoesNotThrow(() -> GameRunner.main(new String[]{}));
    }
}


