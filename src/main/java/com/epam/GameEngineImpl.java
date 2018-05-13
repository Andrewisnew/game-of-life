package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameEngineImpl implements GameEngine {

    public void setCurrentState(int i, int j, boolean value) {
        currentState[i][j] = value;
    }

    boolean[][] currentState;
    byte[][] currState;
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

    @Override
    public byte[][] compute(byte[][] initialState, int numberIterations) {
        raws = initialState.length;
        columns = initialState[0].length;
        currState = new byte[raws][columns];
        try {
            calculate(initialState, numberIterations);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return currState;
    }

    private void calculate(boolean[][] initialState, int numberIterations)
        throws InterruptedException {

        for (int i = 0; i < numberIterations; ++i) {
            currentState = new boolean[raws][columns];
            boolean[][] finalInitialState = initialState;
            Thread up = new Thread(() -> calc(finalInitialState, 0, raws / 4));
            Thread middle = new Thread(() -> calc(finalInitialState, raws / 4 + 1, raws / 2));
            Thread middle2 = new Thread(() -> calc(finalInitialState, raws / 2 + 1, 3 * raws / 4));
            Thread down = new Thread(() -> calc(finalInitialState, 3 * raws / 4 + 1, raws - 1));
            up.start();
            middle.start();
            middle2.start();
            down.start();

            up.join();
            middle.join();
            middle2.join();
            down.join();
            initialState = currentState;
        }
    }

    private void calculate(byte[][] initialState, int numberIterations)
        throws InterruptedException {

        for (int i = 0; i < numberIterations; ++i) {
            currentState = new boolean[raws][columns];
            byte[][] finalInitialState = initialState;
            Thread up = new Thread(() -> calc(finalInitialState, 0, raws / 4));
            Thread middle = new Thread(() -> calc(finalInitialState, raws / 4 + 1, raws / 2));
            Thread middle2 = new Thread(() -> calc(finalInitialState, raws / 2 + 1, 3 * raws / 4));
            Thread down = new Thread(() -> calc(finalInitialState, 3 * raws / 4 + 1, raws - 1));
            up.start();
            middle.start();
            middle2.start();
            down.start();

            up.join();
            middle.join();
            middle2.join();
            down.join();
            initialState = currState;
        }
    }

    private void calc(boolean[][] initialState, int rowNumber, int lastRowNumber) {
        for (int j = rowNumber; j <= lastRowNumber; ++j) {///строка
            for (int k = 0; k < columns; ++k) {//столбец
                currentState[j][k] = isAlive(initialState, j, k);
            }
        }
    }

    private boolean isAlive(boolean[][] initialState, int j, int k) {

        int numberOfNeighbours = 0;
        int m;
        int n;
        int i = raws - 1;
        int n1 = columns - 1;
        if (j == 0) {
            m = i;
        } else {
            m = j - 1;
        }
        for (int top = -1; top < 2; ++top) {//верхние соседи
            int n2 = k + top;
            if (n2 == -1) {
                n = n1;
            } else if (n2 == columns) {
                n = 0;
            } else {
                n = n2;
            }
            if (initialState[m][n]) {
                ++numberOfNeighbours;
            }
        }

        if (j == i) {
            m = 0;
        } else {
            m = j + 1;
        }
        for (int top = -1; top < 2; ++top) {//нижние соседи
            int i1 = k + top;
            if (i1 == -1) {
                n = n1;
            } else if (i1 == columns) {
                n = 0;
            } else {
                n = i1;
            }
            if (initialState[m][n]) {
                ++numberOfNeighbours;
            }
        }
        if (k == 0) {
            n = n1;
        } else {
            n = k - 1;
        }
        if (initialState[j][n]) {
            ++numberOfNeighbours;
        }
        if (k == n1) {
            n = 0;
        } else {
            n = k + 1;
        }

        if (initialState[j][n]) {
            ++numberOfNeighbours;
        }

        if (numberOfNeighbours > 3 || numberOfNeighbours < 2) {
            return false;
        } else if (numberOfNeighbours == 3) {
            return true;
        }
        return initialState[j][k];
    }

    private void calc(byte[][] initialState, int rowNumber, int lastRowNumber) {
        for (int j = rowNumber; j <= lastRowNumber; ++j) {///строка
            for (int k = 0; k < columns; ++k) {//столбец
                currState[j][k] = isAlive(initialState, j, k);
            }
        }
    }

    private byte isAlive(byte[][] initialState, int j, int k) {
        int[] numberOfNeighbours = {0,0,0,0};

        int m;
        int n;
        int i = raws - 1;
        int n1 = columns - 1;
        if (j == 0) {
            m = i;
        } else {
            m = j - 1;
        }
        for (int top = -1; top < 2; ++top) {//верхние соседи
            int n2 = k + top;
            if (n2 == -1) {
                n = n1;
            } else if (n2 == columns) {
                n = 0;
            } else {
                n = n2;
            }
            if (initialState[m][n] != 0) {
                ++numberOfNeighbours[initialState[m][n] - 1];
            }
        }

        if (j == i) {
            m = 0;
        } else {
            m = j + 1;
        }
        for (int top = -1; top < 2; ++top) {//нижние соседи
            int i1 = k + top;
            if (i1 == -1) {
                n = n1;
            } else if (i1 == columns) {
                n = 0;
            } else {
                n = i1;
            }
            if (initialState[m][n] != 0) {
                ++numberOfNeighbours[initialState[m][n] - 1];
            }
        }
        if (k == 0) {
            n = n1;
        } else {
            n = k - 1;
        }
        if (initialState[j][n] != 0) {
            ++numberOfNeighbours[initialState[j][n] - 1];
        }
        if (k == n1) {
            n = 0;
        } else {
            n = k + 1;
        }

        if (initialState[j][n] != 0) {
            ++numberOfNeighbours[initialState[j][n] - 1];
        }
        return result(numberOfNeighbours, initialState[j][k]);
    }

    private byte result(int[] numberOfNeighbours, byte cur) {
        for(int p1 = 0; p1 < 4; p1++) {
            int p2 = (p1 + 1) % 4;
            int p3 = (p1 + 2) % 4;
            int p4 = (p1 + 3) % 4;
            if (numberOfNeighbours[p4] == 0 && numberOfNeighbours[p2] == 0
                && numberOfNeighbours[p3] == 0){
                if (numberOfNeighbours[p1] > 3 || numberOfNeighbours[p1] < 2) {
                    return 0;
                } else if (numberOfNeighbours[p1] == 3) {
                    return (byte) (p1 + 1);
                }
                return cur;
            }
        }
        boolean dominante = true;
        int max = Integer.MIN_VALUE;
        int maxP = -1;
        for(int i = 0; i < 4; i++){
            if(numberOfNeighbours[i] > max) {
                max = numberOfNeighbours[i];
                maxP = i;
                dominante = true;
            } else if(numberOfNeighbours[i] == max){
                dominante = false;
            }
        }
        if(dominante){
            return (byte) (maxP + 1);
        }
        ArrayList<Integer> dominants = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if(numberOfNeighbours[i] == max) {
                dominants.add(i);
            }
        }
        return (byte) (dominants.get((int) (Math.random() * dominants.size())) + 1);
    }


}
