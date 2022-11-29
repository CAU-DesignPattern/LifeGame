package com.LifeGame.model;

import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    @DisplayName("toggle : 1 -> 0 test")
    void toggle2() {
        //given
        this.model.setMapSize(4);
        this.model.toggle(1,3);

        //when
        this.model.toggle(1, 3);
        int[][] ans = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

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

    @Test
    @DisplayName("Test : 통합 테스트")
    void entire(){
        this.model.setMapSize(3);
        this.model.toggle(0,0);
        this.model.toggle(0,1);
        this.model.toggle(0,2);
        this.model.toggle(1,0);
        this.model.toggle(2,2);
        int[][] arr = this.model.getMap();
        int[][] ans = {{1,1,1},{1,0,0},{0,0,1}};
        assertArrayEquals(ans, arr);

        this.model.nextState(arr);
        int[][] ans2 = {{1,1,0},{1,0,1},{0,0,0}};
        int[][] arr2 = this.model.getMap();
        assertArrayEquals(ans2, arr2);

        this.model.toggle(2,2);
        int[][] arr3 = this.model.getMap();
        this.model.nextState(arr3);
        int[][] ans3 = {{1,1,0},{1,0,1},{0,1,0}};
        assertArrayEquals(ans3, arr3);
    }

    @Test
    @DisplayName("[mapChanged] mapChanged 호출 시 LifePanel에 update가 잘 호출는지 테스트")
    void mapChangedTest() {

        //given
        LifePanel lifePanel = Mockito.mock(LifePanel.class);

        //when
        this.model.addObserver(lifePanel);
        this.model.mapChanged();

        //then
        verify(lifePanel).update(any(), any());
    }
}