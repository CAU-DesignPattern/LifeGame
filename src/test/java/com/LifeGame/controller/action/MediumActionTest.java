package com.LifeGame.controller.action;

import com.LifeGame.worker.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MediumActionTest {

    @Mock
    private Worker worker;
    @InjectMocks
    private MediumAction mediumAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        //when
        this.mediumAction.action();

        //then
        verify(this.worker).setSpeed(70);
        verify(this.worker).startThread();
    }
}