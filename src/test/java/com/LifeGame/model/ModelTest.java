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
    @DisplayName("SetMapSize : 3*3 test")
    void setmap1() {
        //given
        this.model.setMapSize(3);

        //when
        int[][] ans = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("SetMapSize : 5*5 test")
    void setmap2() {
        //given
        this.model.setMapSize(5);

        //when
        int[][] ans = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("toggle : 3*3 test")
    void toggle1() {
        //given
        this.model.setMapSize(3);

        //when
        this.model.toggle(0, 0);
        int[][] ans = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("toggle : 4*4 test")
    void toggle2() {
        //given
        this.model.setMapSize(4);

        //when
        this.model.toggle(1, 3);
        int[][] ans = {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("toggle : 5*5 test")
    void toggle3() {
        //given
        this.model.setMapSize(5);

        //when
        this.model.toggle(4, 2);
        int[][] ans = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }
    @Test
    @DisplayName("nextState : 3*3 test")
    void next1() {
        //given
        int[][] arr = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        int[][] ans = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};

        //when
        this.model.nextState(arr);

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("nextState : 4*4 test")
    void next2() {
        //given
        int[][] arr = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        int[][] ans = {{0, 0, 0, 0}, {0, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}};

        //when
        this.model.nextState(arr);

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("nextState : 5*5 test")
    void next3() {
        //given
        int[][] arr = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};
        int[][] ans = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};

        //when
        this.model.nextState(arr);

        //then
        assertArrayEquals(ans, this.model.getMap());
    }
}