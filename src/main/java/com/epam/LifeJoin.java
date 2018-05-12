package com.epam;

import java.util.concurrent.RecursiveAction;

public class LifeJoin extends RecursiveAction {

    boolean[][] initialState;

    LifeJoin(boolean[][] initialState) {
        this.initialState = initialState;
    }

    @Override
    protected void compute() {

    }
}