package com.epam;

public interface GameEngine {

    boolean[][] compute(boolean[][] initialState, int numberIterations);
    byte[][] compute(byte[][] initialState, int numberIterations);
}
