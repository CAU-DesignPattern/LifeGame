package com.LifeGame.controller.action;

import com.LifeGame.controller.LifeController;
import com.LifeGame.view.LifePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
}