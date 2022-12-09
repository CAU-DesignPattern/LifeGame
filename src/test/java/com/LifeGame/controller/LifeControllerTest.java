package com.LifeGame.controller;

import com.LifeGame.controller.drawBehavior.DefaultDrawBehavior;
import com.LifeGame.controller.drawBehavior.PatternDrawBehavior;
import com.LifeGame.model.Model;
import com.LifeGame.model.PatternModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LifeControllerTest {

    @Mock
    private Model model;
    @Mock
    private PatternModel patternModel;

    @Mock
    private DefaultDrawBehavior defaultDrawBehavior;
    @Mock
    private PatternDrawBehavior patternDrawBehavior;

    @InjectMocks
    private LifeController lifeController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[mouseAction] draw 메서드가 잘 호출되는지 테스트")
    void mouseActionTest() {

        //given
        int x = 0;
        int y = 0;

        //when
        this.lifeController.mouseAction(x, y);

        //then
        verify(this.lifeController.getDrawBehavior()).draw(x, y);
    }

    @Test
    @DisplayName("[update/default] 업데이트 상황에 따라 setDrawBehavior 메서드가 잘 호출되는지 테스트")
    void updateDefaultTest() {

        //given
        when(this.patternModel.getId()).thenReturn(0);

        //when
        this.lifeController.update(this.patternModel, null);

        //then
        assertEquals(this.defaultDrawBehavior, this.lifeController.getDrawBehavior());
    }

    @Test
    @DisplayName("[update/pattern] 업데이트 상황에 따라 setDrawBehavior 메서드가 잘 호출되는지 테스트")
    void updatePatternTest() {

        //given
        when(this.patternModel.getId()).thenReturn(1);

        //when
        this.lifeController.update(this.patternModel, null);

        //then
        assertEquals(this.patternDrawBehavior, this.lifeController.getDrawBehavior());
    }
}