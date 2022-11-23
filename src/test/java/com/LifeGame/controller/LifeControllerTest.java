package com.LifeGame.controller;

import com.LifeGame.controller.action.Action;
import com.LifeGame.view.LifePanel;
import com.LifeGame.view.View;
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

@ExtendWith(MockitoExtension.class)
class LifeControllerTest {

    @Mock
    View view;
    @Mock
    LifePanel lifePanel;
    @InjectMocks
    LifeController lifeController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[addMouseAction] LifePanel의 적절한 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void addMouseActionTest() {

        //given
        Action action = Mockito.mock(Action.class);

        //when
        this.lifeController.addMouseAction(action);

        //then
        verify(this.lifePanel).setMouseListener(action);
    }

    @Test
    @DisplayName("[addComponentAction] LifePanel의 적절한 메서드를 호출하고 파라미터가 잘 넘어가는지 테스트")
    void addComponentActionTest() {

        //given
        Action action = Mockito.mock(Action.class);

        //when
        this.lifeController.addComponentAction(action);

        //then
        verify(this.lifePanel).setComponentListener(action);
    }
}