package com.LifeGame.model;

import com.LifeGame.controller.LifeController;
import com.LifeGame.view.PatternImagePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatternModelTest {

    @Spy
    private PatternModel patternModel;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[patternChanged] patternChanged 호출 시 LifeController와 PatternImagePanel에 update가 잘 호출는지 테스트")
    void patternChangedTest() {

        //given
        LifeController lifeController = mock(LifeController.class);
        PatternImagePanel patternImagePanel = mock(PatternImagePanel.class);

        this.patternModel.addObserver(lifeController);
        this.patternModel.addObserver(patternImagePanel);

        //when
        this.patternModel.patternChanged();

        //then
        verify(lifeController).update(any(), any());
        verify(patternImagePanel).update(any(), any());
    }

    @Test
    @DisplayName("[changePattern] changePattern 기능 테스트")
    void changePattern1Test() {

        //given
        int id = 1;
        int[][] bluePrint = new int[][] {{0}};
        this.patternModel.setId(id);

        //when
        this.patternModel.changePattern(id, bluePrint);

        //then
        assertEquals(0, this.patternModel.getId());
        assertNull(this.patternModel.getBluePrint());
        verify(this.patternModel).patternChanged();
    }

    @Test
    @DisplayName("[changePattern] changePattern 기능 테스트")
    void changePattern2Test() {

        //given
        int id = 1;
        int[][] bluePrint = new int[][] {{0}};

        //when
        this.patternModel.changePattern(id, bluePrint);

        //then
        assertEquals(id, this.patternModel.getId());
        assertEquals(bluePrint, this.patternModel.getBluePrint());
        verify(this.patternModel).patternChanged();
    }
}