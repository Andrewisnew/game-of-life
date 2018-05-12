package com.epam;

public class GameEngineImpl1 implements GameEngine {

    boolean[][] currentState;
    int raws;
    int columns;

    @Override
    public boolean[][] compute(boolean[][] initialState, int numberIterations) {

        raws = initialState.length;
        columns = initialState[0].length;
        currentState = new boolean[raws][columns];
        calculate(initialState, numberIterations);

        return currentState;
    }

    private void calculate(boolean[][] initialState, int numberIterations) {

        for (int i = 0; i < numberIterations; i++) {
            currentState = new boolean[raws][columns];
            for (int j = 0; j < raws; j++) {///строка
                for (int k = 0; k < columns; k++) {//столбец
                    currentState[j][k] = isAlive(initialState, j, k);
                }
            }
            initialState = currentState;
        }
    }

    private boolean isAlive(boolean[][] initialState, int j, int k) {

        int numberOfNeighbours = 0;
        int m;
        int n;
        m = j == 0 ? raws - 1 : j - 1;
        for (int top = -1; top < 2; ++top) {//верхние соседи
            if(k + top == -1) {
                n = columns - 1;
            }else if(k + top == columns){
                n = 0;
            } else {
                n = k + top;
            }
            if (initialState[m][n]) {
                numberOfNeighbours++;
            }
        }

        m = j == raws - 1 ? 0 : j + 1;
        for (int top = -1; top < 2; ++top) {//нижние соседи
            if(k + top == -1) {
                n = columns - 1;
            }else if(k + top == columns){
                n = 0;
            } else {
                n = k + top;
            }
            if (initialState[m][n]) {
                numberOfNeighbours++;
            }
        }
        n = k == 0 ? columns - 1 : k - 1;
        if (initialState[j][n]) {
            numberOfNeighbours++;
        }
        n = k == columns - 1 ? 0 : k + 1;

        if (initialState[j][n]) {
            numberOfNeighbours++;
        }

        if (numberOfNeighbours > 3 || numberOfNeighbours < 2) {
            return false;
        }
        if (numberOfNeighbours == 3) {
            return true;
        }
        return initialState[j][k];
    }

}
