package com.LifeGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private Model model;

    @BeforeEach
    void setUp() {
        this.model = new Model();
    }

    @Test
    @DisplayName("Test : Set Map Size")
    void setMap() {

        //given
        int t1 = 3;
        int t2 = 5;
        int t3 = 10;

        //when
        int[][] arr = this.model.setMapSize(t1);
        int[][] arr2 = this.model.setMapSize(t2);
        int[][] arr3 = this.model.setMapSize(t3);

        int[][] ans = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] ans2 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int[][] ans3 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, arr);
        assertArrayEquals(ans2, arr2);
        assertArrayEquals(ans3, arr3);
    }

    @Test
    @DisplayName("Test : Dot update")
    void dot() {
        //given
        int[][] map = this.model.setMapSize(3);
        int[][] map2 = this.model.setMapSize(5);
        int[][] map3 = this.model.setMapSize(4);

        //when
        int[][] arr = this.model.toggle(0, 0, map);
        int[][] arr2 = this.model.toggle(4, 2, map2);
        int[][] arr3 = this.model.toggle(1, 3, map3);

        int[][] ans = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] ans2 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        int[][] ans3 = {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, arr);
        assertArrayEquals(ans2, arr2);
        assertArrayEquals(ans3, arr3);
    }

    @Test
    @DisplayName("Test : Next state")
    void update() {
        //given
        int[][] arr = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        int[][] arr2 = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        int[][] arr3 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};

        int[][] ans = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] ans2 = {{0, 0, 0, 0}, {0, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}};
        int[][] ans3 = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};

        //when
        arr = this.model.nextState(arr);
        arr2 = this.model.nextState(arr2);
        arr3 = this.model.nextState(arr3);

        //then
        assertArrayEquals(ans, arr);
        assertArrayEquals(ans2, arr2);
        assertArrayEquals(ans3, arr3);
    }
}