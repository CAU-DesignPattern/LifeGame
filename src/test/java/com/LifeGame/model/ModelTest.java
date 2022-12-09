package com.LifeGame.model;

import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ModelTest {

    @Spy
    private Model model;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[clearMap] clear map 호출시 mapChanged 호출되는지 test")
    void clearMapTest() {
        //given
        this.model.setMapSize(3);
        //when
        this.model.clearMap();
        //then
        verify(this.model).mapChanged();
    }

    @Test
    @DisplayName("[SetMapSize] 3*3 test")
    void setMapSize1Test() {
        //given
        this.model.setMapSize(3);

        //when
        int[][] ans = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[SetMapSize] 5*5 test")
    void setMapSize2Test() {
        //given
        this.model.setMapSize(5);

        //when
        int[][] ans = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[toggle] 3*3 test")
    void toggle1Test() {

        //given
        this.model.setMapSize(3);

        //when
        this.model.toggle(0, 0);
        int[][] ans = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[toggle] 1 -> 0 test")
    void toggle2Test() {
        //given
        this.model.setMapSize(4);
        this.model.toggle(1, 3);

        //when
        this.model.toggle(1, 3);
        int[][] ans = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[toggle] 5*5 test")
    void toggle3Test() {
        //given
        this.model.setMapSize(5);

        //when
        this.model.toggle(4, 2);
        int[][] ans = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[toggle] toggle 호출시 mapChanged 호출되는지 test")
    void toggleTest() {
        //given
        this.model.setMapSize(3);
        //when
        this.model.toggle(1, 1);
        //then
        verify(this.model).mapChanged();
    }

    @Test
    @DisplayName("[setMap] setmap 호출시 mapChanged 호출되는지 test")
    void setMapTest() {
        //given
        int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        //when
        this.model.setMap(arr);
        //then
        verify(this.model).mapChanged();
    }

    @Test
    @DisplayName("[draw] draw 기능 테스트")
    void draw1Test() {

        //given
        this.model.setMapSize(3);

        int[][] bluePrint = new int[][] {{0, 1}, {1, 0}};
        int x = 0;
        int y = 0;

        //when
        this.model.draw(bluePrint, x, y);
        //then
        assertArrayEquals(new int[][] {{0, 1, 0}, {1, 0, 0}, {0, 0, 0}}, this.model.getMap());
        verify(this.model).mapChanged();
    }

    @Test
    @DisplayName("[draw] 그림이 잘려야 할 때 draw 기능 테스트")
    void draw2Test() {

        //given
        this.model.setMapSize(3);

        int[][] bluePrint = new int[][] {{0, 1, 1, 0}, {1, 0, 1, 1}};
        int x = 1;
        int y = 1;

        //when
        this.model.draw(bluePrint, x, y);
        //then
        assertArrayEquals(new int[][] {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}}, this.model.getMap());
        verify(this.model).mapChanged();
    }

    @Test
    @DisplayName("[nextState] 3*3 test")
    void nextState1Test() {

        //Model m = mock(Model.class);
        //given
        int[][] arr = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        this.model.setMap(arr);
        int[][] ans = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};

        //when
        this.model.nextState();

        //then
        assertArrayEquals(ans, this.model.getMap());

    }

    @Test
    @DisplayName("[nextState] 4*4 test")
    void nextState2Test() {
        //given
        int[][] arr = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}};
        this.model.setMap(arr);
        int[][] ans = {{0, 0, 0, 0}, {0, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}};

        //when
        this.model.nextState();

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("nextState : 5*5 test")
    void nextState3Test() {
        //given
        int[][] arr = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};
        this.model.setMap(arr);
        int[][] ans = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};

        //when
        this.model.nextState();

        //then
        assertArrayEquals(ans, this.model.getMap());
    }

    @Test
    @DisplayName("[nextState] nextState 호출시 mapChanged 2번 호출되는지 test")
    void nextStateTest() {
        //given
        int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.model.setMap(arr);
        //when
        this.model.nextState();
        //then
        verify(this.model, times(2)).mapChanged();
    }

    @Test
    @DisplayName("[Model] 통합 테스트")
    void modelTest() {
        this.model.setMapSize(3);
        this.model.toggle(0, 0);
        this.model.toggle(0, 1);
        this.model.toggle(0, 2);
        this.model.toggle(1, 0);
        this.model.toggle(2, 2);
        int[][] arr = this.model.getMap();
        int[][] ans = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        assertArrayEquals(ans, arr);

        this.model.nextState();
        int[][] ans2 = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] arr2 = this.model.getMap();
        assertArrayEquals(ans2, arr2);

        this.model.toggle(2, 2);
        int[][] arr3 = this.model.getMap();
        this.model.nextState();
        int[][] ans3 = {{1, 1, 0}, {1, 0, 1}, {0, 1, 0}};
        assertArrayEquals(ans3, arr3);
    }

    @Test
    @DisplayName("[mapChanged] mapChanged 호출 시 LifePanel에 update가 잘 호출는지 테스트")
    void mapChangedTest() {

        //given
        LifePanel lifePanel = mock(LifePanel.class);

        this.model.addObserver(lifePanel);

        //when
        this.model.mapChanged();

        //then
        verify(lifePanel).update(any(), any());
    }

    @Test
    @DisplayName("[nextState] generation test")
    void nextstate_gen() {
        //given
        int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.model.setMap(arr);
        //when
        this.model.nextState();
        this.model.nextState();
        //then
        assertEquals(2, this.model.getGeneration());
    }

    @Test
    @DisplayName("[clearMap] clearmap시 generation이 0이 되는지 test")
    void clearmap_gen() {
        int[][] arr = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        this.model.setMap(arr);
        //when
        this.model.nextState();
        this.model.nextState();
        this.model.clearMap();
        //then
        assertEquals(0, this.model.getGeneration());
    }

    @Test
    @DisplayName("[toggle&clearmap] toggle시 livecell 수가 변하는지 test")
    void toggle_cell() {
        this.model.setMapSize(3);
        this.model.toggle(0, 0);
        this.model.toggle(0, 1);
        this.model.toggle(0, 2);
        this.model.toggle(1, 0);
        this.model.toggle(2, 2);
        this.model.toggle(1, 0);

        assertEquals(4, this.model.getLivecell());

        this.model.clearMap();

        assertEquals(0, this.model.getLivecell());
    }

    @Test
    @DisplayName("[nextState] nextState시 livecell수가 변하는지 test")
    void next_cell() {
        int[][] arr = {{1, 1, 1}, {1, 0, 0}, {0, 0, 1}};
        this.model.setMap(arr);
        this.model.nextState();

        assertEquals(4, this.model.getLivecell());
        //int[][] ans2 = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};

        this.model.toggle(2, 2);
        this.model.nextState();
        //int[][] ans3 = {{1, 1, 0}, {1, 0, 1}, {0, 1, 0}};
        assertEquals(5, this.model.getLivecell());
    }

}
