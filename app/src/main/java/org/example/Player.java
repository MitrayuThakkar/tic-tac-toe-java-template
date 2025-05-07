package org.example;

public class Player {
    private final String name;
    private final char symbol;
    private int wins = 0;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }
}
