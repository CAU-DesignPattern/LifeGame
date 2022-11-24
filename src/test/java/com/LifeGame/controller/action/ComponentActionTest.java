package com.LifeGame.controller.action;

import com.LifeGame.controller.LifeController;
import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComponentActionTest {

    @Mock
    private LifePanel lifePanel;
    @Mock
    private LifeController lifeController;
    @InjectMocks
    private ComponentAction componentAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[constructor] lifeController의 addComponentAction 메서드를 잘 호출하는지 테스트")
    void constructorTest() {

        //given
        //when
        //then
        verify(this.lifeController).addComponentAction(this.componentAction);
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        Rectangle bounds = Mockito.mock(Rectangle.class);
        when(this.lifePanel.getBounds()).thenReturn(bounds);
        when(this.lifePanel.getWidthInCells()).thenReturn(1);

        //when
        this.componentAction.action();

        //then
        verify(this.lifePanel).setBounds(bounds);
    }
}