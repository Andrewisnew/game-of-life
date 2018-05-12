package com.epam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameEngineBasisConfigurationsTest {
    GameEngine gameEngine=new GameEngineImpl1();

    @Test
    void standardAliveSectorTest2() {
        boolean[][] initialState = {
            {false, false, false, false, false},
            {false, true,  false, false, false},
            {false, false, false, true, false},
            {false, false, true,  false, false},
            {false, false, false, false, false}
        };
        int numberIterations = 1;



        boolean[][] expectedState = {
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, true,  false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
    }


    @Test
    void standardAliveSectorTest3() {
        boolean[][] initialState = {
                {false, false, false, false, false},
                {false, true,  false, false, false},
                {false, false, true, true, false},
                {false, true, true,  false, false},
                {false, false, false, false, false}
        };
        int numberIterations = 1;



        boolean[][] expectedState = {
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, false,  true, false},
                {false, true, true, true, false},
                {false, false, false, false, false}
        };
        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
    }




    @Test
    void standardAliveSectorTest4() {
        boolean[][] initialState = {
                {false, false, false, false, false},
                {false, true,  false, false, false},
                {false, false, false, true, false},
                {false, false, true,  false, false},
                {false, false, false, false, false}
        };
        int numberIterations = 2;



        boolean[][] expectedState = {
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false,  false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
    }


//    @Test
//    void standardAliveSectorTest5() {
//        boolean[][] initialState = {
//                {false, false, false, false, false},
//                {false, false, true, false, false},
//                {false, false, false, true, false},
//                {false, true, true, true, false},
//                {false, false, false, false, false}
//        };
//        int numberIterations = 6;
//
//
//
//
//        boolean[][] expectedState = {
//                {false, false, false, true, true},
//                {false, false, false, false, false},
//                {false, false, false, false, false},
//                {false, false, false, false, true},
//                {false, false, true, false, true}
//        };
//        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
//    }
@Test
void standardAliveSectorTest5() {
    boolean[][] initialState = {
            {false, false, false, true, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, true, false, true},
            {false, false, false, true, true}
    };
    int numberIterations = 1;




    boolean[][] expectedState = {
            {false, false, false, true, true},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, true},
            {false, false, true, false, true}
    };
    Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
}

    @Test
    void standardAliveSectorTest6() {
        boolean[][] initialState = {
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, true, true, true, false},
                {false, false, false, false, false}
        };
        int numberIterations = 2;




        boolean[][] expectedState = {
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, true, false},
                {false, true, false, true, false},
                {false, false, true, true, false}
        };
        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
    }

    @Test
    void standardAliveSectorTest7() {
        boolean[][] initialState = {
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, false, true, false},
                {false, true, true, true, false},
                {false, false, false, false, false}
        };
        int numberIterations = 6;




        boolean[][] expectedState = {
                {false, false, false, true, true},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, true},
                {false, false, true, false, true}
        };
        Assertions.assertArrayEquals(expectedState,gameEngine.compute(initialState,numberIterations));
    }



}