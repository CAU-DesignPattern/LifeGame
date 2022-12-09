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
class SlowActionTest {

    @Mock
    private Worker worker;
    @InjectMocks
    private SlowAction slowAction;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("[action] action 기능 테스트")
    void actionTest() {

        //given
        //when
        this.slowAction.action();

        //then
        verify(this.worker).setSpeed(150);
        verify(this.worker).startThread();
    }
}