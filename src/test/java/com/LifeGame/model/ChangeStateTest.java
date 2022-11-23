package com.LifeGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeStateTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("set Map Size test")
    void setMap(){
        //given
        int t1 = 3;
        int t2 = 5;
        int t3 = 10;

        //when
        int [][] arr = ChangeState.setMap(t1);
        int [][] arr2 = ChangeState.setMap(t2);
        int [][] arr3 = ChangeState.setMap(t3);

        int[][] ans = {{0,0,0},{0,0,0},{0,0,0}};
        int[][] ans2 = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        int[][] ans3 = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};

        //then
        assertArrayEquals(ans, arr);
        assertArrayEquals(ans2, arr2);
        assertArrayEquals(ans3, arr3);
    }

    @Test
    @DisplayName("update test")
    void update() {
        //given
        int[][] arr = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        int[][] arr2 = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        int[][] arr3 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};

        int[][] ans = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] ans2 = {{0, 0, 0, 0}, {0, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}};
        int[][] ans3 = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};

        //when
        arr = ChangeState.update(arr);
        arr2 = ChangeState.update(arr2);
        arr3 = ChangeState.update(arr3);

        //then
        assertArrayEquals(ans, arr);
        assertArrayEquals(ans2, arr2);
        assertArrayEquals(ans3, arr3);
    }
}