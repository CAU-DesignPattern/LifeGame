package com.LifeGame.controller.action;

import com.LifeGame.controller.MenuController;
import com.LifeGame.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TickActionTest {

    @Mock
    private Model model;
    @InjectMocks
    private TickAction tickAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        //when
        this.tickAction.action();

        //then
        verify(this.model).nextState();
    }
}