package com.epam;

public class GameEngineImpl1 implements GameEngine {

    public void setCurrentState(int i, int j, boolean value) {
        currentState[i][j] = value;
    }

    boolean[][] currentState;
    int raws;
    int columns;


    @Override
    public boolean[][] compute(boolean[][] initialState, int numberIterations) {

        raws = initialState.length;
        columns = initialState[0].length;
        currentState = new boolean[raws][columns];
        try {
            calculate(initialState, numberIterations);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return currentState;
    }

    private void calculate(boolean[][] initialState, int numberIterations) throws InterruptedException {

        for (int i = 0; i < numberIterations; i++) {
            currentState = new boolean[raws][columns];
            boolean[][] finalInitialState = initialState;
            Thread up = new Thread(() -> calc(finalInitialState, 0, raws/3));
            Thread middle = new Thread(() -> calc(finalInitialState, raws/3 + 1, 2*raws/3));
            Thread down = new Thread(() -> calc(finalInitialState, 2*raws/3 + 1, raws - 1));
            up.start();
            middle.start();
            down.start();

            up.join();
            middle.join();
            down.join();
            initialState = currentState;
        }
    }


    private void calc(boolean[][] initialState, int rowNumber, int lastRowNumber){
        for (int j = rowNumber; j <= lastRowNumber; j++) {///строка
                for (int k = 0; k < columns; k++) {//столбец
                    currentState[j][k] = isAlive(initialState, j, k);
                }
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
